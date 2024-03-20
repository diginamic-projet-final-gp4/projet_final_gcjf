package com.diginamic.apiback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Méthodes HTTP autorisées
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * SecurityConfig est une classe de configuration de Spring Security.
 * Elle permet de configurer les autorisations des requêtes HTTP.
 * 
 * @Bean passwordEncoder: permet de définir un bean pour encoder les mots de
 *       passe
 * @Bean securityFilterChain: permet de définir un bean pour configurer les
 *       autorisations des requêtes HTTP
 * 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Permet de définir un bean pour encoder les mots de passe.
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Permet de définir un bean pour configurer les autorisations des requêtes
     * HTTP.
     * 
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Désactive la protection CSRF (Cross-Site Request Forgery)
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // Autorise les requêtes CORS
        httpSecurity
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()));

        // Autorise les requêtes HTTP
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                // TODO : à modifier
                .requestMatchers(GET, "/test").permitAll()
                .requestMatchers(POST, "/test").permitAll()
                .requestMatchers(POST, "/auth/**").permitAll()
                .requestMatchers(GET, "/auth/**").permitAll()

                // --------------- TEMPORAIRE ---------------
                .anyRequest().permitAll());

        // Retourne la configuration de sécurité
        return httpSecurity.build();
    }

}
