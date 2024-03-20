package com.diginamic.apiback.services;

/**
 * AuthService est une interface qui définit les méthodes de service pour
 * l'authentification.
 */
public interface AuthService {
    /**
     * Méthode de service pour l'authentification.
     * 
     * @param email
     * @param password
     * @return
     */
    String login(String email, String password);
}
