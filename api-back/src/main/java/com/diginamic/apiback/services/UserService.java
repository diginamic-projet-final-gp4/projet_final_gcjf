package com.diginamic.apiback.services;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

     public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(@Valid @NonNull User user,@NonNull Long id) {
        boolean idUser = userRepository.existsById(id);
        if (idUser != true) {
            throw new EntityNotFoundException("cette user n'existe pas");
        }
        return userRepository.save(user);
    }

    public User deleteUser(@NonNull Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID : " + id + " introuvable"));
        if (userToDelete != null) {
            userRepository.deleteById(id);
        }
        return userToDelete;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
