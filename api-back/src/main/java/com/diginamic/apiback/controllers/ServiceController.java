package com.diginamic.apiback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.apiback.models.Service;
import com.diginamic.apiback.services.ServiceService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    ServiceService serviceService ;
    
    @GetMapping("/all")
    public List<Service> findAll() {
        return serviceService.findAll();
    }

    @GetMapping("/absence")
    public List<Service> findAbsenceWithServiceMonthAndYear() {
        return serviceService.findAll();
    }
    
}
