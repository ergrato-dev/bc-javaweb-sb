package com.bootcamp.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.Optional;

/**
 * PRÁCTICA 02 — UserDetailsService con Base de Datos
 *
 * Implementa autenticación usando un UserRepository JPA.
 * Descomenta cada sección en orden y verifica el comportamiento.
 */
public class UserManagementApp {

    // ============================================
    // PASO 1: Entidad User
    //
    // Esta entidad representa a un usuario del sistema.
    // El campo password almacena el hash BCrypt, NUNCA el texto plano.
    // Descomenta las siguientes líneas:
    // ============================================

    // @Entity
    // @Table(name = "users")
    // static class AppUser {
    //
    //     @Id
    //     @GeneratedValue(strategy = GenerationType.IDENTITY)
    //     private Long id;
    //
    //     @Column(unique = true, nullable = false)
    //     private String username;
    //
    //     // Almacena el hash BCrypt — nunca el password en texto plano
    //     @Column(nullable = false)
    //     private String password;
    //
    //     // "ROLE_USER" o "ROLE_ADMIN" — el prefijo ROLE_ es requerido por Spring Security
    //     @Column(nullable = false)
    //     private String role;
    //
    //     // Getters y setters omitidos por brevedad
    //     public Long getId() { return id; }
    //     public String getUsername() { return username; }
    //     public String getPassword() { return password; }
    //     public String getRole() { return role; }
    //     public void setUsername(String username) { this.username = username; }
    //     public void setPassword(String password) { this.password = password; }
    //     public void setRole(String role) { this.role = role; }
    // }

    // ============================================
    // PASO 2: Repositorio y DTOs
    //
    // findByUsername es una query derivada: Spring Data JPA genera automáticamente
    // SELECT * FROM users WHERE username = :username
    // Descomenta las siguientes líneas:
    // ============================================

    // interface UserRepository extends JpaRepository<AppUser, Long> {
    //     Optional<AppUser> findByUsername(String username);
    //     boolean existsByUsername(String username);
    // }

    // record RegisterRequest(
    //         @NotBlank String username,
    //         @NotBlank @Size(min = 6) String password,
    //         String role  // opcional, default ROLE_USER
    // ) {}

    // record UserResponse(Long id, String username, String role) {
    //     static UserResponse from(AppUser user) {
    //         return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    //     }
    // }

    // ============================================
    // PASO 3: CustomUserDetailsService
    //
    // Spring Security llama a loadUserByUsername() automáticamente
    // en cada request autenticada para verificar credenciales.
    // Descomenta las siguientes líneas:
    // ============================================

    // @Service
    // static class CustomUserDetailsService implements UserDetailsService {
    //
    //     private final UserRepository userRepository;
    //
    //     CustomUserDetailsService(UserRepository userRepository) {
    //         this.userRepository = userRepository;
    //     }
    //
    //     @Override
    //     public UserDetails loadUserByUsername(String username)
    //             throws UsernameNotFoundException {
    //         // Buscar el usuario en la base de datos
    //         return userRepository.findByUsername(username)
    //                 .map(user -> User.builder()
    //                         .username(user.getUsername())
    //                         // El password ya está hasheado — Spring lo verifica con passwordEncoder
    //                         .password(user.getPassword())
    //                         // authorities() acepta nombres de authority completos (con ROLE_ si aplica)
    //                         .authorities(user.getRole())
    //                         .build())
    //                 .orElseThrow(() ->
    //                         new UsernameNotFoundException("User not found: " + username));
    //     }
    // }

    // ============================================
    // PASO 4: Servicio de registro y Controller
    //
    // Al registrar: hashear el password ANTES de guardar
    // passwordEncoder.encode() aplica BCrypt con salt aleatorio
    // Descomenta las siguientes líneas:
    // ============================================

    // @Service
    // static class UserService {
    //
    //     private final UserRepository userRepository;
    //     private final PasswordEncoder passwordEncoder;
    //
    //     UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    //         this.userRepository = userRepository;
    //         this.passwordEncoder = passwordEncoder;
    //     }
    //
    //     public UserResponse register(RegisterRequest request) {
    //         if (userRepository.existsByUsername(request.username())) {
    //             throw new IllegalArgumentException("Username already taken");
    //         }
    //
    //         var user = new AppUser();
    //         user.setUsername(request.username());
    //         // ⚠️ SIEMPRE hashear el password antes de guardar
    //         user.setPassword(passwordEncoder.encode(request.password()));
    //         // Si no se especifica rol, asignar ROLE_USER por defecto
    //         user.setRole(request.role() != null ? request.role() : "ROLE_USER");
    //
    //         return UserResponse.from(userRepository.save(user));
    //     }
    // }

    // @RestController
    // @RequestMapping("/api/auth")
    // static class AuthController {
    //
    //     private final UserService userService;
    //
    //     AuthController(UserService userService) {
    //         this.userService = userService;
    //     }
    //
    //     @PostMapping("/register")
    //     @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    //     public UserResponse register(@Valid @RequestBody RegisterRequest request) {
    //         return userService.register(request);
    //         // Retorna 201 Created con los datos del usuario (sin el password)
    //     }
    // }

    public static void main(String[] args) {
        System.out.println("Descomenta los pasos en orden.");
        System.out.println("Recuerda agregar la migración Flyway para crear la tabla users.");
        System.out.println();
        System.out.println("SQL de migración (V1__create_users_table.sql):");
        System.out.println("CREATE TABLE users (");
        System.out.println("  id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,");
        System.out.println("  username VARCHAR(100) UNIQUE NOT NULL,");
        System.out.println("  password VARCHAR(255) NOT NULL,");
        System.out.println("  role VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER'");
        System.out.println(");");
    }
}
