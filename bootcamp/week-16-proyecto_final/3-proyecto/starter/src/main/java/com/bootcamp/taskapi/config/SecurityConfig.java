package com.bootcamp.taskapi.config;

import com.bootcamp.taskapi.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de Spring Security.
 *
 * Conceptos clave:
 * - STATELESS: sin sesiones HTTP — cada request debe autenticarse con JWT
 * - CSRF deshabilitado: no necesario en APIs stateless (sin formularios HTML)
 * - authenticationProvider: usa DaoAuthenticationProvider + BCryptPasswordEncoder
 * - Los endpoints /auth/** son públicos; el resto requiere autenticación
 *
 * @EnableMethodSecurity habilita @PreAuthorize en controllers y services.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    // ============================================
    // TODO: Configurar el SecurityFilterChain
    // ============================================
    // La cadena de filtros define qué endpoints son públicos y cuáles requieren auth.
    //
    // Estructura base:
    // http
    //   .csrf(AbstractHttpConfigurer::disable)
    //   .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //   .authorizeHttpRequests(auth -> auth
    //       .requestMatchers("/auth/**").permitAll()
    //       .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/actuator/health").permitAll()
    //       .requestMatchers("/api/admin/**").hasRole("ADMIN")
    //       .anyRequest().authenticated()
    //   )
    //   .authenticationProvider(authenticationProvider())
    //   .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
    //   .build();
    //
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: implementar
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt con factor de coste 12 — balance entre seguridad y performance
        return new BCryptPasswordEncoder(12);
    }
}
