package com.diginamic.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {

}
