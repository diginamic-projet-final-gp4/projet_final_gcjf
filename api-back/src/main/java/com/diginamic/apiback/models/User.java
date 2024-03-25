package com.diginamic.apiback.models;

import java.util.ArrayList;
import java.util.List;

import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Long id;
    
    @OneToMany(mappedBy = "manager")
    private List<User> users = new ArrayList<>();
    
    @ManyToOne
    private User manager;
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Absence> absences = new ArrayList<>();

    private String firstName;

    private String lastName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Float rttEmployee;

    private Float unpaidLeave;
    
    private Float paidLeave;


    @OneToOne(mappedBy = "user")
    private Jwt jwt;

    public User() {

    }

    @Override
    public String toString() {
        return "User [id=" + id + ", users=" + users + ", Manager=" + manager + ", service=" + service + ", absences="
                + absences + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
                + password + ", role=" + role + ", rttEmployee=" + rttEmployee + ", unpaidLeave=" + unpaidLeave
                + ", paidLeave=" + paidLeave + ", jwt=" + jwt + "]";
    }


    public UserDTO toDto(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setEmail(email);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setManager(manager);
        userDTO.setPaidLeave(paidLeave);
        userDTO.setRttEmployee(rttEmployee);
        userDTO.setUnpaidLeave(unpaidLeave);
        userDTO.setService(service);
        userDTO.setAbsences(absences);

        return userDTO;
    }
}
