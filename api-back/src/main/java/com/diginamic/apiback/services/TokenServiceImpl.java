package com.diginamic.apiback.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.config.*;
import com.diginamic.apiback.models.Jwt;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.*;

/**
 * TokenServiceImpl est une classe de service qui implémente l'interface
 * TokenService.
 * 
 * Elle permet de gérer les tokens.
 * 
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JwtRepository jwtRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProviderConfig jwtProviderConfig;

    @Value("${jwt.expiration}")
    private int expiration;

    @SuppressWarnings("unused")
    private Date expiredAt;

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    /**
     * Générer un token JWT.
     */
    @Override
    public String generateToken(String email) {
        // Générer un token JWT
        String token = jwtProviderConfig.createToken(email);

        // Récupérer l'utilisateur associé à l'email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Date now = new Date();

        // Créer un nouvel objet Jwt
        Jwt jwt = new Jwt();
        jwt.setToken(token);
        jwt.setUser(user);
        jwt.setCreatedAt(now);

        // Enregistrer le Jwt en base de données
        jwtRepository.save(jwt);

        return token;
    }

    /**
     * Supprimer les tokens expirés.
     */
    @Override
    public void deleteExpiredTokens() {
        // Affiche tous les tokens
        jwtRepository.findAll().forEach(jwt -> {
            // Créer un objet JSON
            String token = jwt.getToken();

            @SuppressWarnings("unused")
            Date createdAt = jwt.getCreatedAt();

            @SuppressWarnings("unused")
            Date expiredAt = jwt.getExpiresAt();

            // Afficher les tokens expirés
            if (jwt.getExpiresAt().before(new Date())) {
                System.out.println("Token expiré: " + token);
                jwtRepository.delete(jwt);
            }
            // Afficher les tokens non expirés
            else {
                System.out.println("Token non expiré: " + token);
            }
        });
    }
}
