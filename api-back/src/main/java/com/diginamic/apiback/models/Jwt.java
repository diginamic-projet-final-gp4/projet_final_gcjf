
package com.diginamic.apiback.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/* 
Si lombok ne marche pas dans l'entité, décommenter les annotations ci-dessous
*/
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.Setter;

/**
 * Représente un JWT (JSON Web Token).
 * 
 * @Long id: L'identifiant du JWT
 * @String token: le token JWT
 * @User user: l'utilisateur associé au JWT
 * @Date createdAt: la date de création du JWT
 * @Date expiresAt: la date d'expiration du JWT
 * 
 */
@Entity
@Table(name = "jwt")
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
public class Jwt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date createdAt;

    private Date expiresAt;

    public Jwt() {
    }

}
