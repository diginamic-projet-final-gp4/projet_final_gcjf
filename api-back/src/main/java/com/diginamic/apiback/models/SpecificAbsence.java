package com.diginamic.apiback.models;

import java.util.Date;
import com.diginamic.apiback.enums.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@lombok.Getter
@lombok.Setter
public class SpecificAbsence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="organization_id", nullable=false)
    private Organization organization;

    private Date dt_debut;
    
    private Date dt_fin;

    private String type;

    private Status status;

    private String motif;

    public SpecificAbsence(){
        
    }

    @Override
    public String toString() {
        return "SpecificAbsence [id=" + id + ", organization=" + organization + ", dt_debut=" + dt_debut + ", dt_fin="
                + dt_fin + ", type=" + type + ", status=" + status + ", motif=" + motif + "]";
    }

}
