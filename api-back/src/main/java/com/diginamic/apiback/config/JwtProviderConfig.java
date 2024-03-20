package com.diginamic.apiback.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtTokenProvider est une classe qui permet de créer, valider et récupérer des
 * JWT (JSON Web Token).
 * 
 * @Value secretKey: la clé secrète pour signer le JWT
 * @Value validityInMilliseconds: la durée de validité du JWT
 * 
 */
@Component
public class JwtProviderConfig {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    /**
     * Crée un token JWT à partir de l'email.
     * 
     * @param email l'email de l'utilisateur
     * @return le token JWT
     * 
     */
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Réccupère l'email à partir du token.
     * 
     * @param token
     * @return
     */
    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Vérifie si le token est valide.
     * 
     * @param token
     * @return
     * 
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("JWT token is expired or invalid");
        }
    }
}
