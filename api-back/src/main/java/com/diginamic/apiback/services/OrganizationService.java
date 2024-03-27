package com.diginamic.apiback.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.models.Organization;
import com.diginamic.apiback.repository.OrganizationRepository;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    UserService userService;

    public Optional<Organization> findById(@NonNull Long id) {
        return organizationRepository.findById(id);
    }

}
