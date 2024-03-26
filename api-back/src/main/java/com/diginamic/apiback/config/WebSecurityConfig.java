package com.diginamic.apiback.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;
    @Autowired
    private JwtConfig jwtConfig;

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     String encodingId = "bcrypt";
    //     return new DelegatingPasswordEncoder(encodingId, Map.of(encodingId, new BCryptPasswordEncoder()));
    // }

    @Bean
    PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
}

    @Scope("prototype")
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(List.of("http://localhost:4200"));
      configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","OPTIONS"));
      configuration.setAllowedHeaders(List.of("Content-Type"));
      configuration.setAllowCredentials(true);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**",configuration);
      return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc, @Qualifier("corsConfiguration") CorsConfigurationSource source) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth

                        // .requestMatchers(mvc.pattern(HttpMethod.GET, "/api/user")).permitAll()
                        // .requestMatchers(mvc.pattern(HttpMethod.POST, "/api/user/**")).permitAll()

                        .requestMatchers(mvc.pattern(HttpMethod.POST, "/api/**")).permitAll()

                        // .requestMatchers(mvc.pattern("/admin/**")).hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())

                // Spring Security va valoriser un jeton stocké dans un cookie XSRF-TOKEN
                // Le client souhaitant effectuer une requête de modification (POST par exemple)
                // devra valoriser une entête HTTP "X-XSRF-TOKEN" avec le jeton.

                .csrf
                (csrf -> csrf.disable())
                        // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        // .csrfTokenRequestHandler(new XorCsrfTokenRequestAttributeHandler()::handle))
                
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(source))
                .logout(logout -> logout
                        // // en cas de succès un OK est envoyé
                        .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
                        // // suppression du cookie d'authentification
                        .deleteCookies(jwtConfig.getCookie()));
        return http.build();
    }


}