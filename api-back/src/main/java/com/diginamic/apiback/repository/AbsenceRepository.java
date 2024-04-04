package com.diginamic.apiback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.enums.AbsenceType;
import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.models.User;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    /**
     * Méthode permettant de rechercher les absences d'un utilisateur
     * 
     * @param user l'utilisateur
     * @return la liste des absences
     */
    List<Absence> findByUser(User user);

    @Query(value = "SELECT * FROM absence WHERE status = :status", nativeQuery = true)
    List<Absence> findByStatus(String status);

    @Query(value = "select absence.* from user  " +
            "inner join service on user.service_id = service.id " +
            "inner join absence on user.id = absence.user_id " +
            "where service.id= :id " +
            "AND EXTRACT(MONTH FROM absence.dt_debut) = :month " +
            "AND EXTRACT(YEAR FROM absence.dt_debut) = :year", nativeQuery = true)
    List<Absence> findAbsencesByServiceIdAndMonthAndYear(Long id, String month, String year);

    @Query("""
                SELECT COALESCE(SUM(DATEDIFF(a.dt_fin,a.dt_debut)+1),0)
                FROM Absence a
                WHERE a.status = Status.VALIDEE
                AND a.user = :user
                AND a.type = :type
            """)
    long sumApprovedAbsences(User user, AbsenceType type);

}
