package com.diginamic.apiback.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.models.User;
// import com.diginamic.apiback.services.AuthService;
import com.diginamic.apiback.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    // private AuthService authService;


    @GetMapping()
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@NonNull @PathVariable("id") Long id) {
        return userService.findById(id);
    }

    // @PostMapping("/register")
    // public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
    //     authService.register(registerRequest.getEmail(), registerRequest.getPassword());
    //     return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    // }

    @PutMapping("/{id}")
    public User updateUser(@NonNull @RequestBody @Valid User user, @NonNull @PathVariable("id") Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@NonNull @PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

}
