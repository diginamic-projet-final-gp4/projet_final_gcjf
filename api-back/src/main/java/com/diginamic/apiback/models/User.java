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
    
    @OneToMany
    @JoinColumn(name="user_id", nullable=true)
    private List<User> users = new ArrayList<>();
    
    @ManyToOne
    private User Manager;
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany
    private List<Absence> absences = new ArrayList<>();

    private String firstName;

    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Profile profile;

    private Float rttEmployee;

    private Float unpaidLeave;
    
    private Float paidLeave;


    @OneToOne(mappedBy = "user")
    private Jwt jwt;

    public User() {

    }

    @Override
    public String toString() {
        return "User [id=" + id + ", users=" + users + ", Manager=" + Manager + ", service=" + service + ", absences="
                + absences + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
                + password + ", profile=" + profile + ", rttEmployee=" + rttEmployee + ", unpaidLeave=" + unpaidLeave
                + ", paidLeave=" + paidLeave + ", jwt=" + jwt + "]";
    }


}
