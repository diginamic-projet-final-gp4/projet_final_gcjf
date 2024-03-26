package com.diginamic.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.SpecificAbsence;

@Repository
public interface SpecificAbsenceRepository extends JpaRepository<SpecificAbsence, Long> {

    
}
