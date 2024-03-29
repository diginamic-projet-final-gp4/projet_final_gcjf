package com.diginamic.apiback.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider.Service;
import java.util.List;

import com.diginamic.apiback.dto.AbsenceDTO;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.services.AbsenceService;
import com.diginamic.apiback.services.ServiceService;
import com.diginamic.apiback.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private ServiceService serviceService;

    // @GetMapping()
    // public List<UserDTO> findAll() {
    //     return userService.findAll();
    // }

    @GetMapping()
    public Optional<User> findById(Authentication authentication) {
        final User user = userService.loadUserByUsername(authentication.getName());
        Long id = user.getId();
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@NonNull @RequestBody @Valid User user, @NonNull @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    @GetMapping("/absence")
    public List<AbsenceDTO> getAbsencesForUser(Authentication authentication) {
        final User user = userService.loadUserByUsername(authentication.getName());
        Long id = user.getId();
        // if (id != user.getId()) throw new EntityNotFoundException("TU NA PAS LE DROIT");
        return absenceService.findAbscenceForUserId(id);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@NonNull @PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/service/{id}")
    public List<User> findByServiceId(@NonNull @PathVariable Long id) {
        Optional<com.diginamic.apiback.models.Service> service = serviceService.findById(id);
        if(service.isPresent()){
            return userService.findByService(service.get());
        }

        throw new EntityNotFoundException("MES COUIILLLLES");
    }

}
