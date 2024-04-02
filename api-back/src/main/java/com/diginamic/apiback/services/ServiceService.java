package com.diginamic.apiback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import com.diginamic.apiback.models.Service;

import com.diginamic.apiback.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    public Optional<com.diginamic.apiback.models.Service> findById(@NonNull Long id) {
        return serviceRepository.findById(id);
    }

    public List<Service> findAll() {
        List<Service> services = serviceRepository.findAll();
        return services;
    }

}
