package com.diginamic.apiback.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

import com.diginamic.apiback.dto.AbsenceDTO;
import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.services.AbsenceService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/absence")
public class AbsenceController {
    @Autowired
    AbsenceService absenceService;

    @GetMapping()
    public List<AbsenceDTO> findAll() {
        List<AbsenceDTO> absenceDtoList = new ArrayList<>();
        List<Absence> absenceList = absenceService.findAll();
        for (Absence absence : absenceList) {
            absenceDtoList.add(absence.toDto());
        }
        return absenceDtoList;
    }

    @GetMapping("/{id}")
    public AbsenceDTO findById(@NonNull @PathVariable("id") Long id) {
        Optional<Absence> absence = absenceService.findById(id);
        if (absence.isPresent()) {
            return absence.get().toDto();
        } else {
            throw new EntityNotFoundException("No absence entity with corresponding id found in db");
        }
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public AbsenceDTO createAbsence(@RequestBody @Valid Absence absence) {
        Absence abs = absenceService.createAbsence(absence);
        return abs.toDto();
    }

    @PutMapping("/update/{id}")
    public AbsenceDTO updateUser(@NonNull @RequestBody @Valid Absence absence, @NonNull @PathVariable("id") Long id) {
        Optional<Absence> abs = absenceService.findById(id);
        if (abs.isPresent()) {
            return absenceService.updateAbsence(absence, id).toDto();
        } else {
            throw new EntityNotFoundException("No absence entity with corresponding id found in db");
        }
    }

    @DeleteMapping("/delete/{id}")
    public AbsenceDTO deleteUser(@NonNull @PathVariable("id") Long id) {
        Optional<Absence> abs = absenceService.findById(id);
        if (abs.isPresent()) {
            absenceService.deleteAbsence(id);
            return abs.get().toDto();
        } else {
            throw new EntityNotFoundException("No absence entity with corresponding id found in db");
        }
    }

}
