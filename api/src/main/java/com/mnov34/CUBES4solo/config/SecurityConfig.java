package com.mnov34.CUBES4solo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults()).authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/employees").permitAll()
                        .requestMatchers("/api/sites").permitAll()
                        .requestMatchers("/api/services").permitAll()
                        .requestMatchers("/api/auth/**").permitAll() // Endpoints publics
                        .requestMatchers("/api/guest/**").permitAll() // Endpoints publics
                        .requestMatchers("/api/admin/**").hasAuthority("SCOPE_admin") // Endpoints admin uniquement
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
