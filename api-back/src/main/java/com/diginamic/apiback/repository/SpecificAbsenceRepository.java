package com.diginamic.apiback.repository;

import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.SpecificAbsence;

@Repository
public interface SpecificAbsenceRepository extends JpaRepository<SpecificAbsence, Long> {

    @Query(value ="""
        select s from SpecificAbsence s
        INNER JOIN Organization o ON s.organization_id = o.id
        WHERE s.organization_id = :id
        AND MONTH(s.dt_debut) = :month
        AND YEAR(s.dt_debut) = :year
        ORDER BY s.dt_debut ASC
        """)
    List<SpecificAbsence> findAbsencesAndMonthAndYear(Long id , String month, String year);
    
    @Query("""
            SELECT sa
            FROM SpecificAbsence sa
            WHERE sa.type = AbsenceType.RTT_EMPLOYER
            AND sa.status = Status.INITIALE
            """)
    List<SpecificAbsence> findInitialEmployerWtr();
}
