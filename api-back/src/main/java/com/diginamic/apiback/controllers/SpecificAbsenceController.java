package com.diginamic.apiback.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.diginamic.apiback.dto.SpecificAbsenceDTO;
import com.diginamic.apiback.models.SpecificAbsence;
import com.diginamic.apiback.services.SpecificAbsenceService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/specific-absence")
public class SpecificAbsenceController {
    @Autowired
    SpecificAbsenceService specificAbsenceService;

    @GetMapping("/all")
    public List<SpecificAbsenceDTO> findAll() {
        List<SpecificAbsenceDTO> absenceDtoList = new ArrayList<>();
        List<SpecificAbsence> absenceList = specificAbsenceService.findAll();
        for (SpecificAbsence specificAbsence : absenceList) {
            absenceDtoList.add(specificAbsence.toDto());
        }
        return absenceDtoList;
    }

    @GetMapping("/{id}")
    public SpecificAbsenceDTO findById(@NonNull @PathVariable("id") Long id) {
        Optional<SpecificAbsence> specificAbsence = specificAbsenceService.findById(id);
        if (specificAbsence.isPresent()) {
            return specificAbsence.get().toDto();
        } else {
            throw new EntityNotFoundException("No specificAbsence entity with corresponding id found in db");
        }
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public SpecificAbsenceDTO createAbsence(@RequestBody @Valid SpecificAbsence specificAbsence) {
        SpecificAbsence abs = specificAbsenceService.createSpecificAbsence(specificAbsence);
        return abs.toDto();
    }

    @PutMapping("/update/{id}")
    public SpecificAbsenceDTO updateSpecificAbsence(@NonNull @RequestBody @Valid SpecificAbsence specificAbsence, @NonNull @PathVariable("id") Long id) {
        try {
            return specificAbsenceService.updateSpecificAbsence(specificAbsence, id).toDto();
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException("No specificAbsence entity with corresponding id found in db");
        }
    }

    @DeleteMapping("/delete/{id}")
    public SpecificAbsenceDTO deleteUser(@NonNull @PathVariable("id") Long id) {
        Optional<SpecificAbsence> abs = specificAbsenceService.findById(id);
        if (abs.isPresent()) {
            specificAbsenceService.deleteSpecificAbsence(id);
            return abs.get().toDto();
        } else {
            throw new EntityNotFoundException("No specificAbsence entity with corresponding id found in db");
        }
    }

}
