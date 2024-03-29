package com.diginamic.apiback.services;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.dto.AbsenceDTO;
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
    
    @Autowired
    ServiceService serviceService;

    public List<Absence> findAll() {
        return absenceRepository.findAll();
    }

    public List<AbsenceDTO> findAbscenceForUserId(@NonNull Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            List<AbsenceDTO> absenceDTOs = new ArrayList<>();
            for (Absence abs : absenceRepository.findByUser(user.get())) {
                absenceDTOs.add(abs.toDto());
            }
            return absenceDTOs;
        }

        throw new EntityNotFoundException("L'utilisateur recherché n'a pas été trouvé");
    }
    
    public List<User> findUserForServiceId(@NonNull Long id) {
        Optional<com.diginamic.apiback.models.Service> service = serviceService.findById(id);
        if (service.isPresent()) {
            List<User> users = new ArrayList<>();
            for (User user : userService.findByService(service.get())) {
                users.add(user);
            }
            return users;
        }

        throw new EntityNotFoundException("L'utilisateur recherché n'a pas été trouvé");
    }

    public Optional<Absence> findById(@NonNull Long id) {
        return absenceRepository.findById(id);
    }

    public Absence updateAbsence(@Valid @NonNull Absence absence, @NonNull Long id) {
        boolean idAbsence = absenceRepository.existsById(id);
        if (idAbsence != true) {
            throw new EntityNotFoundException("cette absence n'existe pas");
        }
        absence.setId(id);
        absence.setUser(userService.findById(absence.getUser_id()).get());
        return absenceRepository.save(absence);
    }

    public Absence createAbsence(@Valid Absence absence) {
        Optional<User> userOptional = userService.findById(absence.getUser_id());
        if (userOptional.isPresent()) {
            User userObject = userOptional.get();
            absence.setUser(userObject);
            return absenceRepository.save(absence);
        } 

        throw new EntityNotFoundException("User not found");
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
