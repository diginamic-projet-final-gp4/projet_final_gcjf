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
import java.util.List;

import com.diginamic.apiback.models.SpecificAbsence;
import com.diginamic.apiback.services.SpecificAbsenceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/specific-absence")
public class SpecificAbsenceController {
    @Autowired
    SpecificAbsenceService specificAbsenceService;
    
        @GetMapping()
    public List<SpecificAbsence> findAll() {
        return specificAbsenceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<SpecificAbsence> findById(@NonNull @PathVariable("id") Long id) {
        return specificAbsenceService.findById(id);
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public SpecificAbsence createAbsence(@RequestBody @Valid SpecificAbsence specificAbsence) {
        return specificAbsenceService.createAbsence(specificAbsence);
    }

    @PutMapping("/{id}")
    public SpecificAbsence updateUser(@NonNull @RequestBody @Valid SpecificAbsence specificAbsence, @NonNull @PathVariable("id")Long id){
        return specificAbsenceService.updateAbsence(specificAbsence, id);
    }

    @DeleteMapping("/delete/{id}")
    public SpecificAbsence deleteUser(@NonNull @PathVariable("id")Long id){
       return specificAbsenceService.deleteAbsence(id);
    }

}
