package com.diginamic.apiback.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.apiback.config.JwtProviderConfig;
import com.diginamic.apiback.dto.LoginRequestDTO;
import com.diginamic.apiback.dto.RegisterRequest;
import com.diginamic.apiback.services.AuthService;

/**
 * AuthController est un contrôleur REST qui gère les requêtes liées à
 * l'authentification.
 * 
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProviderConfig jwtProviderConfig;

    @GetMapping("/hello")
    public String hello() {
        // Met un gros block dans la console pour démarquer les logs
        System.out.println("\n" + "========================================================" + "\n");

        return "Hello World!";
    }

    /**
     * Méthode POST pour l'authentification.
     * 
     * Permet de se connecter à l'application en fournissant un email et un mot de
     * passe. Retourne un token JWT.
     * 
     * @param loginRequest
     * @return
     */
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

    /**
     * Méthode POST pour l'enregistrement.
     * 
     * Permet de créer un nouvel utilisateur en fournissant un email et un mot de
     * passe.
     * 
     * @param loginRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest.getEmail(), registerRequest.getPassword());
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    }

    /**
     * Méthode POST pour valider un token.
     * 
     * Permet de valider un token JWT.
     * 
     * @param token
     * @return
     */
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
}
