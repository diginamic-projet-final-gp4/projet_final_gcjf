package com.diginamic.apiback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.models.User;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByUser(User user);
    

    @Query(value ="""
        select a from Absence a 
        INNER JOIN User u ON a.user_id = u.id
        WHERE a.user_id = :id
        AND MONTH(a.dt_debut) = :month
        AND YEAR(a.dt_debut) = :year
        ORDER BY a.dt_debut ASC
        """)
    List<Absence> findAbsencesAndMonthAndYear(Long id , String month, String year);
    
    
}
