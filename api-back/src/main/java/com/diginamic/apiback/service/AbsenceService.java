package com.diginamic.apiback.service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.repository.AbsenceRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@Service
public class AbsenceService {

    @Autowired
    AbsenceRepository absenceRepository;

     public List<Absence> findAll() {
        return absenceRepository.findAll();
    }

    public Optional<Absence> findById(@NonNull Long id) {
        return absenceRepository.findById(id);
    }

        public Absence updateAbsence(@Valid @NonNull Absence absence,@NonNull Long id) {
        boolean idUser = absenceRepository.existsById(id);
        if (idUser != true) {
            throw new EntityNotFoundException("cette user n'existe pas");
        }
        return absenceRepository.save(absence);
    }

    public Absence deleteAbsence(@NonNull Long id) {
        Absence absenceToDelete = absenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID : " + id + " introuvable"));
        if (absenceToDelete != null) {
            absenceRepository.deleteById(id);
        }
        return absenceToDelete;
    }

}
