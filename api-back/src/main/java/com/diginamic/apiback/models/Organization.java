package com.diginamic.apiback.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@lombok.Getter
@lombok.Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private List<SpecificAbsence> specificAbsence = new ArrayList<>();

    private String Name;

    private float rtt_employer;

    private float public_holiday;

    public Organization(){
        
    }

    @Override
    public String toString() {
        return "Organization [id=" + id + ", specificAbsence=" + specificAbsence + ", Name=" + Name + ", rtt_employer="
                + rtt_employer + ", public_holiday=" + public_holiday + "]";
    }

    

}
