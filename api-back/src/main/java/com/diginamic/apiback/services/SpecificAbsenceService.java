package com.diginamic.apiback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.diginamic.apiback.models.Organization;
import com.diginamic.apiback.models.SpecificAbsence;
import com.diginamic.apiback.repository.SpecificAbsenceRepository;

import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;

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

    @SuppressWarnings("null")
    public SpecificAbsence updateSpecificAbsence(@Valid @NonNull SpecificAbsence specificAbsence, @NonNull Long id) {
        boolean idAbsence = specificAbsenceRepository.existsById(id);
        if (idAbsence != true) {
            throw new EntityNotFoundException("cette absence spécifique n'existe pas");
        }
        specificAbsence.setId(id);
        specificAbsence.setOrganization(organizationService.findById(specificAbsence.getOrganization_id()).get());
        return specificAbsenceRepository.save(specificAbsence);
    }

    @SuppressWarnings("null")
    public SpecificAbsence createSpecificAbsence(@Valid SpecificAbsence specificAbsence) {
        Optional<Organization> organizationOptional = organizationService
                .findById(specificAbsence.getOrganization_id());
        if (organizationOptional.isPresent()) {
            Organization organizationObject = organizationOptional.get();
            specificAbsence.setOrganization(organizationObject);
            return specificAbsenceRepository.save(specificAbsence);
        } 

        throw new EntityNotFoundException("Organization not found");
    }

    public SpecificAbsence deleteSpecificAbsence(@NonNull Long id) {
        SpecificAbsence absenceToDelete = specificAbsenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID : " + id + " introuvable"));
        if (absenceToDelete != null) {
            specificAbsenceRepository.deleteById(id);
        }
        return absenceToDelete;
    }

    public List<SpecificAbsence> findAbsencesAndMonthAndYear(Long organizationId, String month, String year){
        return specificAbsenceRepository.findAbsencesAndMonthAndYear(organizationId, month, year);
    }

}
