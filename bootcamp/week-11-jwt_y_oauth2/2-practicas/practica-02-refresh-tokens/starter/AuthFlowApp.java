package com.bootcamp.auth;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * PRÁCTICA 02 — Login, Refresh Tokens y Logout
 *
 * Implementa el ciclo de vida completo de los tokens.
 */
public class AuthFlowApp {

    // ============================================
    // PASO 1: Endpoint de Login con AuthenticationManager
    //
    // AuthenticationManager delega en UserDetailsService + PasswordEncoder
    // BadCredentialsException → 401 Unauthorized automáticamente
    // Descomenta las siguientes líneas:
    // ============================================

    // record LoginRequest(@NotBlank String username, @NotBlank String password) {}
    // record LoginResponse(String accessToken, String refreshToken, long expiresIn) {}

    // @RestController
    // @RequestMapping("/api/auth")
    // static class AuthController {
    //
    //     // Los campos y constructor se completarán en los siguientes pasos
    //
    //     @PostMapping("/login")
    //     public ResponseEntity<LoginResponse> login(
    //             @Valid @RequestBody LoginRequest request) {
    //         try {
    //             // authenticate() verifica credenciales contra UserDetailsService + PasswordEncoder
    //             var authentication = authenticationManager.authenticate(
    //                     new UsernamePasswordAuthenticationToken(
    //                             request.username(), request.password()));
    //
    //             var userDetails = (UserDetails) authentication.getPrincipal();
    //             var accessToken = jwtService.generateToken(userDetails);
    //             var refreshToken = refreshTokenService.create(userDetails.getUsername());
    //
    //             return ResponseEntity.ok(new LoginResponse(
    //                     accessToken, refreshToken.getToken(), expirationMs));
    //
    //         } catch (BadCredentialsException ex) {
    //             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    //         }
    //     }
    // }

    // ============================================
    // PASO 2: Entidad RefreshToken
    //
    // Los refresh tokens se persisten en DB para poder revocarlos.
    // Un UUID garantiza unicidad sin secuencias predecibles.
    // Descomenta las siguientes líneas:
    // ============================================

    // @Entity
    // @Table(name = "refresh_tokens")
    // static class RefreshToken {
    //
    //     @Id
    //     @GeneratedValue(strategy = GenerationType.UUID)
    //     private UUID id;
    //
    //     @Column(unique = true, nullable = false)
    //     private String token = UUID.randomUUID().toString();
    //
    //     @Column(nullable = false)
    //     private String username;
    //
    //     @Column(nullable = false)
    //     private LocalDateTime expiresAt;
    //
    //     @Column(nullable = false)
    //     private boolean revoked = false;
    //
    //     public boolean isExpired() { return LocalDateTime.now().isAfter(expiresAt); }
    //     public boolean isValid() { return !revoked && !isExpired(); }
    //
    //     // Getters
    //     public String getToken() { return token; }
    //     public String getUsername() { return username; }
    //     public void setUsername(String u) { this.username = u; }
    //     public void setExpiresAt(LocalDateTime e) { this.expiresAt = e; }
    //     public void setRevoked(boolean r) { this.revoked = r; }
    // }

    // ============================================
    // PASO 3: RefreshTokenService
    //
    // create(): genera un nuevo token y lo guarda en DB
    // findByToken(): busca en DB (para validar en el endpoint /refresh)
    // revoke(): marca como revocado (logout)
    // Descomenta las siguientes líneas:
    // ============================================

    // @Service
    // static class RefreshTokenService {
    //
    //     private final RefreshTokenRepository repo;
    //     private static final long REFRESH_EXPIRATION_DAYS = 7;
    //
    //     RefreshTokenService(RefreshTokenRepository repo) {
    //         this.repo = repo;
    //     }
    //
    //     public RefreshToken create(String username) {
    //         var token = new RefreshToken();
    //         token.setUsername(username);
    //         token.setExpiresAt(LocalDateTime.now().plusDays(REFRESH_EXPIRATION_DAYS));
    //         return repo.save(token);
    //     }
    //
    //     public Optional<RefreshToken> findByToken(String token) {
    //         return repo.findByToken(token);
    //     }
    //
    //     public void revoke(String tokenValue) {
    //         repo.findByToken(tokenValue).ifPresent(t -> {
    //             t.setRevoked(true);
    //             repo.save(t);
    //         });
    //     }
    // }

    // interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    //     Optional<RefreshToken> findByToken(String token);
    // }

    // ============================================
    // PASO 4: Endpoints Refresh y Logout
    //
    // Agrega estos métodos al AuthController del PASO 1.
    // Descomenta las siguientes líneas:
    // ============================================

    // record RefreshRequest(@NotBlank String refreshToken) {}
    // record RefreshResponse(String accessToken, long expiresIn) {}

    // @PostMapping("/refresh")
    // public ResponseEntity<RefreshResponse> refresh(
    //         @Valid @RequestBody RefreshRequest request) {
    //     // Buscar el refresh token en DB y verificar que sea válido
    //     var refreshToken = refreshTokenService.findByToken(request.refreshToken())
    //             .filter(RefreshToken::isValid)
    //             .orElseThrow(() -> new ResponseStatusException(
    //                     HttpStatus.UNAUTHORIZED, "Invalid or expired refresh token"));
    //
    //     // Generar nuevo access token para el mismo usuario
    //     var userDetails = userDetailsService.loadUserByUsername(refreshToken.getUsername());
    //     var newAccessToken = jwtService.generateToken(userDetails);
    //
    //     return ResponseEntity.ok(new RefreshResponse(newAccessToken, expirationMs));
    // }

    // @PostMapping("/logout")
    // public ResponseEntity<Void> logout(@Valid @RequestBody RefreshRequest request) {
    //     // Revocar el refresh token — el access token expirará solo
    //     refreshTokenService.revoke(request.refreshToken());
    //     return ResponseEntity.noContent().build();
    // }

    public static void main(String[] args) {
        System.out.println("Descomenta los pasos en orden.");
        System.out.println("Recuerda crear la tabla refresh_tokens en Flyway:");
        System.out.println();
        System.out.println("CREATE TABLE refresh_tokens (");
        System.out.println("  id        UUID PRIMARY KEY,");
        System.out.println("  token     VARCHAR(255) UNIQUE NOT NULL,");
        System.out.println("  username  VARCHAR(100) NOT NULL,");
        System.out.println("  expires_at TIMESTAMP NOT NULL,");
        System.out.println("  revoked   BOOLEAN NOT NULL DEFAULT FALSE");
        System.out.println(");");
    }
}
