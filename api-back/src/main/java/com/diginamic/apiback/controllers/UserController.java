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

import java.util.List;

import com.diginamic.apiback.dto.AbsenceDTO;
import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.services.AbsenceService;
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

    @GetMapping()
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@NonNull @PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@NonNull @RequestBody @Valid User user, @NonNull @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    @GetMapping("/{id}/absence")
    public List<AbsenceDTO> getAbsencesForUser(Authentication authentication, @NonNull @PathVariable Long id) {
        final User user = userService.loadUserByUsername(authentication.getName());
        if (id != user.getId())
            throw new EntityNotFoundException("TU NA PAS LE DROIT");
        return absenceService.findAbscenceForUserId(id);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@NonNull @PathVariable Long id) {
        return userService.deleteUser(id);
    }

    // @PostMapping("")
    // public ResponseEntity<?> login(@RequestBody UserDTO userLoginDTO) {
    // // Authentification
    // User user = userService.authenticateUser(userLoginDTO.getEmail(),
    // userLoginDTO.getPassword());

    // if (user != null) {
    // // L'authentification a réussi, vous pouvez retourner des informations
    // // utilisateur ou un token JWT ici
    // return ResponseEntity.ok(user);
    // } else {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de
    // l'authentification");
    // }
    // }

}
