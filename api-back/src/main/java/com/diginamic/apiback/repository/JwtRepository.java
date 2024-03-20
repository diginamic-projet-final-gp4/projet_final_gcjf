package com.diginamic.apiback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diginamic.apiback.models.Jwt;

public interface JwtRepository extends JpaRepository<Jwt, Long> {
    Optional<Jwt> findByToken(String token);

    @SuppressWarnings("null")
    void delete(Jwt jwt);

}