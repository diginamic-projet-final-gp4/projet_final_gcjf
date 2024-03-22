package com.diginamic.apiback.services;

/**
 * TokenService est une interface qui définit les méthodes de service pour la
 * gestion des tokens.
 * 
 */
public interface TokenService {
    String generateToken(String email);

    void deleteExpiredTokens();

}
