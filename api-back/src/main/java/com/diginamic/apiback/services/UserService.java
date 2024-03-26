package com.diginamic.apiback.services;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(user.toDto());
        }

        return userDTOs;
    }

    public Optional<User> findById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(@Valid @NonNull User user, @NonNull Long id) {
        boolean idUser = userRepository.existsById(id);
        if (idUser != true) {
            throw new EntityNotFoundException("cet user n'existe pas");
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

    public User authenticateUser(String email, String motDePasse) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        System.out.println("mot de passe et email " + email + motDePasse);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            String encodedPassword = user.getPassword();
            if (passwordEncoder.matches(motDePasse, "$2a$10$nn2CjaBXKEDgT4NZEZg52ONmu.brfOuNlIb8.yCnIumhAprKUQ11q")) {
                return user;
            }
        }
        return null;
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
