package com.diginamic.apiback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.Service;

/**
 * UserRepository est une interface qui étend JpaRepository pour gérer les
 * opérations CRUD sur les utilisateurs.
 * 
 */

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
