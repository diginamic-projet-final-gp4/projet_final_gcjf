package com.diginamic.apiback.services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.models.SpecificAbsence;
import com.diginamic.apiback.models.Organization;
import com.diginamic.apiback.models.SpecificAbsence;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.SpecificAbsenceRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class SpecificAbsenceService {

    @Autowired
    SpecificAbsenceRepository specificAbsenceRepository;

    @Autowired
    OrganizationService organizationService;

    public List<SpecificAbsence> findAll() {
        return specificAbsenceRepository.findAll();
    }

    public Optional<SpecificAbsence> findById(@NonNull Long id) {
        return specificAbsenceRepository.findById(id);
    }

    public SpecificAbsence updateAbsence(@Valid @NonNull SpecificAbsence absence, @NonNull Long id) {
        boolean idAbsence = specificAbsenceRepository.existsById(id);
        if (idAbsence != true) {
            throw new EntityNotFoundException("cette absence spécifique n'existe pas");
        }
        return specificAbsenceRepository.save(absence);
    }

    public SpecificAbsence createAbsence(@Valid SpecificAbsence specificAbsence) {
        System.out.println("test absence" + specificAbsence);
        Optional<Organization> organizationOptional = organizationService
                .findById(specificAbsence.getOrganization_id());
        if (organizationOptional.isPresent()) {
            System.out.println("=========================== \n");
            Organization organizationObject = organizationOptional.get();
            specificAbsence.setOrganization(organizationObject);
            return specificAbsenceRepository.save(specificAbsence);
        } else {
            throw new EntityNotFoundException("Organization not found");
        }
    }

    public SpecificAbsence deleteAbsence(@NonNull Long id) {
        SpecificAbsence absenceToDelete = specificAbsenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID : " + id + " introuvable"));
        if (absenceToDelete != null) {
            specificAbsenceRepository.deleteById(id);
        }
        return absenceToDelete;
    }

}
