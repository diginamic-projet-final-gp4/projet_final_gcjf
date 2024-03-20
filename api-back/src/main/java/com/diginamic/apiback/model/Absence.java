package com.diginamic.apiback.model;


import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_absence;

    private Date dt_debut;
    
    private Date dt_fin;

    private String type;

    private String status;

    private String motif;

    
}
