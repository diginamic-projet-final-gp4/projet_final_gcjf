package com.diginamic.apiback.models;

import java.util.ArrayList;
import java.util.List;

import com.diginamic.apiback.enums.Profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;

@Entity
@lombok.Getter
@lombok.Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy="manager")
    @JoinColumn(name="user_id", nullable=true)
    private List<User> users = new ArrayList<>();
    
    private User Manager;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private List<Service> service = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="absence_id", nullable=false)
    private Absence absence;

    private String firstName;


    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Profile profile;


    @OneToOne(mappedBy = "user")
    @Column(name = "tokens_id")
    private Jwt jwt;

    public User() {

    }

    @Override
    public String toString() {
        return "User [id=" + id + ", Manager=" + Manager + ", users=" + users + ", departments=" + departments
                + ", absence=" + absence + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", profile=" + profile + ", jwt=" + jwt + "]";
    }
  

}
