package com.diginamic.apiback.services;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByEmail(username);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Invalid credentials.");
    }

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

    public List<User> findByService(com.diginamic.apiback.models.Service service) {
        return userRepository.findByService(service);
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
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            String encodedPassword = user.getPassword();
            if (passwordEncoder.matches(motDePasse, encodedPassword)) {
                return user;
            }
        }
        return null;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
