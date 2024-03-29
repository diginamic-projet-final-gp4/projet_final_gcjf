package com.diginamic.apiback.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.repository.ServiceRepository;



@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;


    public Optional<com.diginamic.apiback.models.Service> findById(@NonNull Long id) {
        return serviceRepository.findById(id);
    }

}
