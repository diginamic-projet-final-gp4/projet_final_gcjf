package com.diginamic.apiback.controllers;

import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

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

    @GetMapping("/all")
    public List<AbsenceDTO> findAll() {
        List<AbsenceDTO> absenceDtoList = new ArrayList<>();
        List<Absence> absenceList = absenceService.findAll();
        for (Absence absence : absenceList) {
            absenceDtoList.add(absence.toDto());
        }
        return absenceDtoList;
    }

    // Create route to retrieve absence by user
    // that would take a get param like:
    // http .... /absence?end_date=2024-04-24
    /*
     *[
        {
            "fullname": "Juan miguel"
            "absences": [
                {
                    "id": 4,
                    "dt_debut": "2024-04-24T23:00:00.000+00:00",
                    "dt_fin": "2024-04-25T23:00:00.000+00:00",
                    "type": "PAID_LEAVE",
                    "status": "INITIALE",
                    "motif": "test"
                },
                {
                    "id": 5,
                    "dt_debut": "2024-04-24T23:00:00.000+00:00",
                    "dt_fin": "2024-04-25T23:00:00.000+00:00",
                    "type": "PAID_LEAVE",
                    "status": "INITIALE",
                    "motif": "test"
                }
            ]
        }
     ] 
     */






    @GetMapping("/{id}")
    public AbsenceDTO findById(@NonNull @PathVariable("id") Long id) {
        Optional<Absence> absence = absenceService.findById(id);
        if (absence.isPresent()) {
            return absence.get().toDto();
        } 

        throw new EntityNotFoundException("No absence entity with corresponding id found in db");
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public AbsenceDTO createAbsence(@RequestBody @Valid Absence absence) {
        Absence abs = absenceService.createAbsence(absence);
        return abs.toDto();
    }

    @PutMapping("/update/{id}")
    public Absence updateUser(@NonNull @RequestBody @Valid Absence absence, @NonNull @PathVariable("id")Long id){
        return absenceService.updateAbsence(absence, id);
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

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteAbsenceRequest(final Authentication authentication, @PathVariable final Long id) {
    //     final AbsenceRequest absenceRequest = absenceRequestService.find(id);

    //     // Verify that this absence request exists
    //     if (absenceRequest == null) {
    //         return ResponseEntity
    //             .status(HttpStatus.NOT_FOUND)
    //             .body(Map.of("message", "Cette d'absence n'existe pas ou plus."));
    //     }

    //     final User user = userService.loadUserByUsername(authentication.getName());

    //     // Verify that the absence request is owned by this user
    //     if (!absenceRequest.getUser().equals(user)) {
    //         return ResponseEntity
    //             .status(HttpStatus.UNAUTHORIZED)
    //             .body(Map.of("message", "Cette d'absence ne vous appartient pas."));
    //     }

    //     absenceRequestService.delete(absenceRequest);

    //     return ResponseEntity.ok(Map.of("message", "La d'absence a été supprimée."));
    // }

}
