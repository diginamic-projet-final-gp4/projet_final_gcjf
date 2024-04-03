package com.diginamic.apiback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diginamic.apiback.models.Service;
import com.diginamic.apiback.models.User;

/**
 * UserRepository est une interface qui étend JpaRepository pour gérer les
 * opérations CRUD sur les utilisateurs.
 * 
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByService(Service service);

}
