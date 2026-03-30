package com.bootcamp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;

/**
 * PRÁCTICA 01 — Configurar Spring Security desde Cero
 *
 * Sigue los pasos en orden. Descomenta cada sección cuando llegues al paso correspondiente.
 * Después de cada paso, prueba la aplicación con curl para verificar el comportamiento.
 */
public class SecurityApp {

    // ============================================
    // PASO 2: SecurityFilterChain básico
    //
    // Esta clase define qué rutas requieren autenticación.
    // La anotación @Configuration la registra como bean de Spring.
    // Descomenta las siguientes líneas:
    // ============================================

    // @Configuration
    // @EnableWebSecurity
    // @EnableMethodSecurity
    // static class SecurityConfig {
    //
    //     @Bean
    //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //         http
    //             .authorizeHttpRequests(auth -> auth
    //                 // Rutas públicas — no requieren autenticación
    //                 .requestMatchers("/api/public/**").permitAll()
    //                 .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
    //                 // Solo ADMIN puede acceder a rutas de administración
    //                 .requestMatchers("/api/admin/**").hasRole("ADMIN")
    //                 // Todas las demás rutas requieren autenticación
    //                 .anyRequest().authenticated()
    //             )
    //             // HTTP Basic: envía credenciales en el header Authorization: Basic base64(user:pass)
    //             .httpBasic(Customizer.withDefaults())
    //             // CSRF deshabilitado — las APIs REST stateless no necesitan CSRF protection
    //             .csrf(csrf -> csrf.disable())
    //             // Respuestas JSON para errores de seguridad (en lugar de HTML por defecto)
    //             .exceptionHandling(ex -> ex
    //                 .authenticationEntryPoint((req, res, e) -> {
    //                     res.setContentType("application/json");
    //                     res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    //                     res.getWriter().write("{\"error\": \"Unauthorized\"}");
    //                 })
    //                 .accessDeniedHandler((req, res, e) -> {
    //                     res.setContentType("application/json");
    //                     res.setStatus(HttpServletResponse.SC_FORBIDDEN);
    //                     res.getWriter().write("{\"error\": \"Forbidden\"}");
    //                 })
    //             );
    //         return http.build();
    //     }
    //
    //     // BCrypt es el algoritmo recomendado para hashear contraseñas
    //     // Incluye el salt automáticamente y es resistente a ataques de fuerza bruta
    //     @Bean
    //     public PasswordEncoder passwordEncoder() {
    //         return new BCryptPasswordEncoder();
    //     }
    // }

    // ============================================
    // PASO 3: UserDetailsService en memoria
    //
    // InMemoryUserDetailsManager es útil para desarrollo y pruebas.
    // En producción, implementa UserDetailsService con un repositorio JPA.
    // Descomenta las siguientes líneas:
    // ============================================

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    //     // Crear usuarios con sus roles
    //     var user = User.builder()
    //             .username("user")
    //             // .password(rawPassword) NO — siempre hashear antes de guardar
    //             .password(encoder.encode("password123"))
    //             .roles("USER")          // Spring agrega prefijo ROLE_ → ROLE_USER
    //             .build();
    //
    //     var admin = User.builder()
    //             .username("admin")
    //             .password(encoder.encode("admin123"))
    //             .roles("ADMIN", "USER") // Admin también tiene rol USER
    //             .build();
    //
    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    // ============================================
    // PASO 4: Controladores protegidos con @PreAuthorize
    //
    // @PreAuthorize evalúa la expresión ANTES de ejecutar el método.
    // Si retorna false → 403 Forbidden automáticamente.
    // Descomenta las siguientes clases:
    // ============================================

    // @RestController
    // @RequestMapping("/api")
    // static class TaskController {
    //
    //     // Cualquier usuario autenticado puede ver tareas
    //     @GetMapping("/tasks")
    //     public String getTasks() {
    //         return "{\"tasks\": [\"task1\", \"task2\"]}";
    //     }
    //
    //     // Solo el usuario puede crear sus propias tareas
    //     @PostMapping("/tasks")
    //     public String createTask() {
    //         return "{\"message\": \"Task created\"}";
    //     }
    //
    //     // Endpoint público — sin autenticación
    //     @GetMapping("/public/health")
    //     public String health() {
    //         return "{\"status\": \"UP\"}";
    //     }
    // }

    // @RestController
    // @RequestMapping("/api/admin")
    // static class AdminController {
    //
    //     // Solo ADMIN puede acceder a este endpoint
    //     // hasRole('ADMIN') busca la authority ROLE_ADMIN
    //     @GetMapping("/users")
    //     @PreAuthorize("hasRole('ADMIN')")
    //     public String getUsers() {
    //         return "{\"users\": [\"user1\", \"admin\"]}";
    //     }
    // }

    public static void main(String[] args) {
        System.out.println("Descomenta los pasos en orden y reinicia la aplicación.");
        System.out.println("Luego prueba con:");
        System.out.println("  curl -i http://localhost:8080/api/tasks              → 401");
        System.out.println("  curl -u user:password123 http://localhost:8080/api/tasks → 200");
        System.out.println("  curl -u user:password123 http://localhost:8080/api/admin/users → 403");
        System.out.println("  curl -u admin:admin123 http://localhost:8080/api/admin/users   → 200");
    }
}
