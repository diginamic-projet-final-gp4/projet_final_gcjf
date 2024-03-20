
package com.diginamic.apiback.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/* 
Si lombok ne marche pas dans l'entité, décommenter les annotations ci-dessous
*/
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.Setter;

/**
 * Represents a JWT (JSON Web Token) entity.
 * 
 * @Long id: the JWT id
 * @String token: name of the JWT
 * @User user: the user associated with the JWT
 * @Date createdAt: the date of creation of the JWT
 * @Date expiresAt: the date of expiration of the JWT
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date createdAt;

    private Date expiresAt;

    public Jwt() {
    }

}
