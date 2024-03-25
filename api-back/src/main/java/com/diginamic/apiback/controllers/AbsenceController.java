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

import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.services.AbsenceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/absence")
public class AbsenceController {
    @Autowired
    AbsenceService absenceService;
    
        @GetMapping()
    public List<Absence> findAll() {
        return absenceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Absence> findById(@NonNull @PathVariable("id") Long id) {
        return absenceService.findById(id);
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public Absence createAbsence(@RequestBody @Valid Absence absence) {
        return absenceService.createAbsence(absence);
    }

    @PutMapping("/{id}")
    public Absence updateUser(@NonNull @RequestBody @Valid Absence absence, @NonNull @PathVariable("id")Long id){
        return absenceService.updateAbsence(absence, id);
    }

    @DeleteMapping("/delete/{id}")
    public Absence deleteUser(@NonNull @PathVariable("id")Long id){
       return absenceService.deleteAbsence(id);
    }

}
