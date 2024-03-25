package com.diginamic.apiback.models;

import com.diginamic.apiback.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;

    private Date dt_debut;
    
    private Date dt_fin;

    @Enumerated(EnumType.STRING)
    private AbsenceType type;

    @Enumerated(EnumType.STRING)
    private Status status ;

    private String motif;


    public Absence(){
        
    }

    @Override
    public String toString() {
        return "Absence [id=" + id + ", user=" + user + ", dt_debut=" + dt_debut + ", dt_fin=" + dt_fin + ", type="
                + type + ", status=" + status + ", motif=" + motif + "]";
    }


    
}
