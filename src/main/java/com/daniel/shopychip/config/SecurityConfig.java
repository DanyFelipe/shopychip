package com.daniel.shopychip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; // FALTA ESTA!
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // ¡Esta anotación es obligatoria!
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}