package com.diginamic.apiback.models;

import com.diginamic.apiback.enums.*;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@lombok.Getter
@lombok.Setter
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="absence_id", nullable=false)
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
