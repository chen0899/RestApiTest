package com.example.service;

import com.example.entity.ServiceProviders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceProviderService {

    ServiceProviders findById (Long id);

    List<ServiceProviders> findAll();

    ServiceProviders save(ServiceProviders serviceProviders);

    void delete (Long id);

    ServiceProviders update (Long id, ServiceProviders serviceProviders);

    void addImageToCustomer(Long id, String fileName);

    Page<ServiceProviders> getServiceProvidersByPage(int page);

}
