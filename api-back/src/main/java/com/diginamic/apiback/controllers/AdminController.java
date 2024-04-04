package com.diginamic.apiback.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    /**
     * Route pour récupérer tous les utilisateurs (version Admin)
     * 
     * @return une liste d'utilisateurs
     */
    @GetMapping()
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    /**
     * Route pour récupérer un utilisateur par son id
     * 
     * @param id l'ID de l'utilisateur
     * @return l'utilisateur
     */
    @GetMapping("/{id}")
    public Optional<User> findById(@NonNull @PathVariable("id") Long id) {
        return userService.findById(id);
    }

    /**
     * Route pour créer un utilisateur (non implémentée)
     * 
     * @param user l'utilisateur
     * @param id   l'ID de l'utilisateur
     * @return l'utilisateur créé
     */
    // @PostMapping("/register")
    // public ResponseEntity<String> register(@RequestBody RegisterRequest
    // registerRequest) {
    // authService.register(registerRequest.getEmail(),
    // registerRequest.getPassword());
    // return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    // }

    /**
     * Route pour mettre à jour un utilisateur
     * 
     * @param user l'utilisateur
     * @param id   l'ID de l'utilisateur
     * @return l'utilisateur mis à jour
     */
    @PutMapping("/{id}")
    public User updateUser(@NonNull @RequestBody @Valid User user, @NonNull @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    /**
     * Route pour supprimer un utilisateur
     * 
     * @param id l'ID de l'utilisateur
     * @return l'utilisateur supprimé
     */
    // TODO : à voir si on désactive la fonctionnalité
    @DeleteMapping("/delete/{id}")
    public User deleteUser(@NonNull @PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

}
