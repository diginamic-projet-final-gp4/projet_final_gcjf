package com.diginamic.apiback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.config.JwtProviderConfig;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.UserRepository;

/**
 * AuthServiceImpl est une classe de service qui implémente l'interface
 * AuthService.
 * 
 * Elle permet de gérer l'authentification des utilisateurs.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProviderConfig jwtProviderConfig;

    @Autowired
    private TokenService tokenService;

    @Override
    public String login(String email, String password) {
        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the password matches
        if (passwordEncoder.matches(password, user.getPassword())) {
            // Supprimer les tokens expirés
            tokenService.deleteExpiredTokens();

            // Generate a token
            return tokenService.generateToken(email);

        } else {
            throw new RuntimeException("Invalid password");
        }
    }

    /**
     * Créer un nouvel utilisateur.
     * 
     * @param email
     * @param password
     * @return
     */
    @Override
    public String register(String email, String password) {
        // Check if the user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // Create a new user
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        // Save the user
        userRepository.save(user);

        return jwtProviderConfig.createToken(email);
    }

}
