package com.diginamic.apiback.services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.AbsenceRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@Service
public class AbsenceService {

    @Autowired
    AbsenceRepository absenceRepository;
    @Autowired
    UserService userService;

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
        System.out.println("test absence" + absence);
        Optional<User> userOptional = userService.findById(absence.getUser_id());
        if (userOptional.isPresent()) {
            User userObject = userOptional.get();
            absence.setUser(userObject);
            return absenceRepository.save(absence);
        } else {
            throw new EntityNotFoundException("User not found");
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
