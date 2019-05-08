package com.example.service.impl;

import com.example.entity.ServiceProviders;
import com.example.repository.ServiceProviderRepository;
import com.example.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Illia Chenchak
 */

@Service
@Transactional
public class ServiceProviderServiceImpl implements ServiceProviderService {

    @Autowired
    ServiceProviderRepository serviceProviderRepository;


    @Override
    public ServiceProviders findById(Long id) {
        return serviceProviderRepository.findById(id).get();
    }

    @Override
    public List<ServiceProviders> findAll() {
        return serviceProviderRepository.findAll();
    }

    @Override
    public ServiceProviders save(ServiceProviders serviceProviders) {
        return serviceProviderRepository.save(serviceProviders);
    }

    @Override
    public ServiceProviders update(Long id, ServiceProviders serviceProviders) {
        ServiceProviders newServiceProviders = serviceProviderRepository.findById(id).get();
        newServiceProviders.setName(serviceProviders.getName());
        return newServiceProviders;
    }

    @Override
    public void delete(Long id) {
        serviceProviderRepository.delete(serviceProviderRepository.findById(id).get());
    }

    @Override
    public void addImageToCustomer(Long id, String fileName) {
        ServiceProviders serviceProviders=
                serviceProviderRepository.findById(id).get();

        serviceProviders.setImage(fileName);
        serviceProviderRepository.save(serviceProviders);
    }

    @Override
    public Page<ServiceProviders> getServiceProvidersByPage(int page) {
        Page<ServiceProviders> serviceProviders =
                serviceProviderRepository.findAll(new PageRequest(page,4));
        return serviceProviders;
    }
}
