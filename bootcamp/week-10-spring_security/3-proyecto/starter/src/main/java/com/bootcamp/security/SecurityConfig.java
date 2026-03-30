package com.bootcamp.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Habilita @PreAuthorize, @PostAuthorize, @Secured
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            // Rutas públicas — sin autenticación
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
            // Solo admins pueden gestionar usuarios
            .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
            // Cualquier usuario autenticado puede acceder a las demás rutas
            .anyRequest().authenticated())
        // HTTP Basic: Authorization: Basic base64(username:password)
        .httpBasic(Customizer.withDefaults())
        // Las APIs REST stateless no necesitan CSRF
        .csrf(csrf -> csrf.disable())
        // CORS para el frontend (bc-react)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        // Respuestas JSON para errores de autenticación/autorización
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint((request, response, authException) -> {
              response.setContentType("application/json");
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              response.getWriter().write(
                  "{\"error\": \"Unauthorized\", \"message\": \"Authentication required\"}");
            })
            .accessDeniedHandler((request, response, accessDeniedException) -> {
              response.setContentType("application/json");
              response.setStatus(HttpServletResponse.SC_FORBIDDEN);
              response.getWriter().write(
                  "{\"error\": \"Forbidden\", \"message\": \"Insufficient permissions\"}");
            }));

    return http.build();
  }

  /**
   * BCrypt es el estándar para hashear contraseñas — incluye salt automáticamente
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * AuthenticationManager necesario para autenticación manual en endpoints de
   * login
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  /** CORS: permite que el frontend en http://localhost:3000 consuma la API */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    var configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    configuration.setAllowedMethods(
        List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/**", configuration);
    return source;
  }
}
