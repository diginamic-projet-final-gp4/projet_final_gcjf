package com.diginamic.apiback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diginamic.apiback.models.Jwt;

/**
 * JwtRepository est une interface qui étend JpaRepository pour gérer les JWT en
 * base de données.
 * 
 * @Optional findByToken: permet de récupérer un JWT à partir de son token
 * 
 * @param Jwt
 */
public interface JwtRepository extends JpaRepository<Jwt, Long> {
    Optional<Jwt> findByToken(String token);

    @SuppressWarnings("null")
    void delete(Jwt jwt);

}