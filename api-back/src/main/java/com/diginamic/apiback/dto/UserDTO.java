package com.diginamic.apiback.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.diginamic.apiback.enums.Role;
import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.models.Service;
import com.diginamic.apiback.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;

@Component
public class UserDTO {
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

    @Enumerated(EnumType.STRING)
    private Role role;

    private Float rttEmployee;

    private Float unpaidLeave;

    private Float paidLeave;

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", users=" + users + ", manager=" + manager + ", service=" + service
                + ", absences=" + absences + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", role=" + role + ", rttEmployee=" + rttEmployee + ", unpaidLeave=" + unpaidLeave
                + ", paidLeave=" + paidLeave + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Float getRttEmployee() {
        return rttEmployee;
    }

    public void setRttEmployee(Float rttEmployee) {
        this.rttEmployee = rttEmployee;
    }

    public Float getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(Float unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public Float getPaidLeave() {
        return paidLeave;
    }

    public void setPaidLeave(Float paidLeave) {
        this.paidLeave = paidLeave;
    }

}
