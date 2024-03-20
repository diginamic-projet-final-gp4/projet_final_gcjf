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
        jwt.setExpiresAt(new Date(now.getTime() + expiration));

        // Enregistrer le Jwt en base de données
        jwtRepository.save(jwt);

        return token;
    }

    /**
     * Supprimer les tokens expirés.
     */
    @Override
    public void deleteExpiredTokens() {
        // Récupérer la date actuelle
        Date now = new Date();

        // Récupérer les tokens expirés
        jwtRepository.deleteByExpiresAtBefore(now);

        // Afficher un message dans la console
        System.out.println("Deleted expired tokens");
    }
}
