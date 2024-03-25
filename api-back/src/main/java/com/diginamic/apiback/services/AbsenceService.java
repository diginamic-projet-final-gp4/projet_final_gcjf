package com.diginamic.apiback.services;

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
        boolean idAbsence = absenceRepository.existsById(id);
        if (idAbsence != true) {
            throw new EntityNotFoundException("cette absence n'existe pas");
        }
        return absenceRepository.save(absence);
    }

    public Absence createAbsence(@Valid Absence absence) {
        System.err.println("absence post" + absence);
        if (absence.getId() != null) {
            throw new EntityNotFoundException("présence d'un ID ");
        }else{
            return absenceRepository.save(absence);

        }

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
