package com.example.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

    String storeFile(MultipartFile file);

    Resource loadFile(String fileName);
}
