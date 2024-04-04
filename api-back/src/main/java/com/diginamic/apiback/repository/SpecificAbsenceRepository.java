package com.diginamic.apiback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.SpecificAbsence;

@Repository
public interface SpecificAbsenceRepository extends JpaRepository<SpecificAbsence, Long> {

    @Query("""
            SELECT sa
            FROM SpecificAbsence sa
            WHERE sa.type = AbsenceType.RTT_EMPLOYER
            AND sa.status = Status.INITIALE
            """)
    List<SpecificAbsence> findInitialEmployerWtr();
}
