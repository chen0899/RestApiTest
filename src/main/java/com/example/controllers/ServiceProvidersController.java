package com.example.controllers;

import com.example.entity.ServiceProviders;
import com.example.service.FilesStorageService;
import com.example.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by Illia Chenchak
 */
@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class ServiceProvidersController {

    @Autowired
    ServiceProviderService serviceProviderService;

    @Autowired
    FilesStorageService fileStorageService;

    @PostMapping("save")
    public ResponseEntity<ServiceProviders> saveServiceProvider(@RequestBody ServiceProviders serviceProviders) {
        return new ResponseEntity<>(serviceProviderService.save(serviceProviders), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceProviders> updateServiceProviders(@PathVariable("id") Long id, @RequestBody ServiceProviders serviceProviders) {
        return new ResponseEntity<>(serviceProviderService.update(id, serviceProviders), HttpStatus.OK);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<ServiceProviders>> findAll() {
        return new ResponseEntity<>(serviceProviderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find-all/page")
    public Page<ServiceProviders> getServiceProvidersByPage(@RequestParam(defaultValue = "0") int page)  {
        return serviceProviderService.getServiceProvidersByPage(page);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceProviders> deleteServiceProvidersResponse(@PathVariable("id") Long id) {
        serviceProviderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find-one/{id}")
    public ResponseEntity<ServiceProviders> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(serviceProviderService.findById(id), HttpStatus.OK);
    }

    @PostMapping("{userId}")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId") Long id,
            @RequestParam("imageFile") MultipartFile file
    ) {
        System.out.println(file.getOriginalFilename());

        fileStorageService.storeFile(file);
        serviceProviderService.addImageToCustomer(id, file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<?> getImage(
            @PathVariable("imageName") String name,
            HttpServletRequest servletRequest
    ) {

        Resource resource = fileStorageService.loadFile(name);

        String contentType = null;

        try {
            contentType = servletRequest
                    .getServletContext()
                    .getMimeType(
                            resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
