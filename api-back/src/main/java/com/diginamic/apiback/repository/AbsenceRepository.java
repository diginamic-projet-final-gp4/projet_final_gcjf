package com.diginamic.apiback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.models.User;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByUser(User user);
}
