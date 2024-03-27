package com.diginamic.apiback.controllers;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.apiback.config.JwtConfig;
import com.diginamic.apiback.dto.LoginRequestDTO;
import com.diginamic.apiback.dto.UserDTO;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api/user")
public class SessionController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtConfig jwtConfig;

    public SessionController(JwtConfig jwtConfig, UserService userService,
            PasswordEncoder passwordEncoder) {
        this.jwtConfig = jwtConfig;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody UserDTO userDto) {
        Optional<User> userOptional = this.userService.findByEmail(userDto.getEmail());
        System.out.println("test" + userOptional);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, buildJWTCookie(user)).build();
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO userLoginDTO) {
        // Authentification
        System.out.println("test" + userLoginDTO);
        User user = userService.authenticateUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        if (user != null) {
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, buildJWTCookie(user)).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de l'authentification");
        }
    }

    private String buildJWTCookie(User user) {
        System.out.println("test cookie2 " + user);
        byte[] secretKeyBytes = jwtConfig.getSecretKey().getEncoded();
        String jetonJWT = Jwts.builder()
                .setSubject(user.getEmail())
                .addClaims(Map.of("role", user.getRole(),"user_id",user.getId(), "firstname", user.getFirstName(), "lastname", user.getLastName()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireIn() * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKeyBytes) 
                .compact();
        ResponseCookie tokenCookie = ResponseCookie.from(jwtConfig.getCookie(),
                jetonJWT)
                .httpOnly(true)
                .maxAge(jwtConfig.getExpireIn()) // La durée de validité est en secondes
                .path("/")
                .build();
        return tokenCookie.toString();
    }

}
