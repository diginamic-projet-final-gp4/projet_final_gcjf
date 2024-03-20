package com.diginamic.apiback.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstname;

    private String lastname;

    @Column(nullable = false)
    private String password;

    @Column(name = "actif", nullable = false)
    private boolean enabled;

    // @OneToMany(mappedBy = "user")
    // @Column(name = "tokens_id")
    // private Jwt jwt;

    public User() {
    }

}