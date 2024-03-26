package com.diginamic.apiback.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diginamic.apiback.config.JwtProviderConfig;
import com.diginamic.apiback.dto.LoginRequestDTO;
import com.diginamic.apiback.dto.RegisterRequest;
import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.services.AuthService;
import com.diginamic.apiback.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProviderConfig jwtProviderConfig;

    @GetMapping()
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@NonNull @PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequestDTO loginRequest) {
        // Génération des données de réponse
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        // Création de la réponse
        Map<String, String> response = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatDateTime = now.format(formatter);

        // Ajout des données à la réponse
        response.put("token", token);
        response.put("time", formatDateTime);

        System.out.println("\n" + "========================================================" + "\n");
        System.out.println(response);

        // Retourner la réponse
        return response;
    }

    @PostMapping("/validate")
    public Map<String, String> validate(@RequestBody Map<String, String> token) {
        Map<String, String> response = new HashMap<>();
        if (jwtProviderConfig.validateToken(token.get("token"))) {
            response.put("valid", "true");
        } else {
            response.put("valid", "false");
        }
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest.getEmail(), registerRequest.getPassword());
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public User updateUser(@NonNull @RequestBody @Valid User user, @NonNull @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@NonNull @PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

}
