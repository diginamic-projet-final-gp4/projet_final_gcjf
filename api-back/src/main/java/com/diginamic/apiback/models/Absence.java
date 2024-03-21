package com.diginamic.apiback.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.diginamic.apiback.enums.*;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@lombok.Getter
@lombok.Setter
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy="absence")
    private List<User> users = new ArrayList<>();

    private Date dt_debut;
    
    private Date dt_fin;

    private String type;

    @Enumerated(EnumType.STRING)
    private Status status ;

    private String motif;


    public Absence(){
        
    }


    @Override
    public String toString() {
        return "Absence [id=" + id + ", users=" + users + ", dt_debut=" + dt_debut + ", dt_fin=" + dt_fin + ", type="
                + type + ", status=" + status + ", motif=" + motif + "]";
    }
    
    
}
