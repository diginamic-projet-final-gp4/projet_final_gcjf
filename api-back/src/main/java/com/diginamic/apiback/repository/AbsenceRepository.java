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
    

    @Query(value ="select absence.* from user  " +
    "inner join service on user.service_id = service.id " +
    "inner join absence on user.id = absence.user_id " +
    "where service.id= :id " +
    "AND EXTRACT(MONTH FROM absence.dt_debut) = :month " +
    "AND EXTRACT(YEAR FROM absence.dt_debut) = :year", nativeQuery = true )
    List<Absence> findAbsencesByServiceIdAndMonthAndYear(String id, String month, String year);
    
    
}
