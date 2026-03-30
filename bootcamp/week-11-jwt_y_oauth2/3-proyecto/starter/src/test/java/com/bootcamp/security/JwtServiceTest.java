package com.bootcamp.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests unitarios del JwtService — sin Spring context.
 *
 * Prueban directamente la generación y validación de tokens.
 */
class JwtServiceTest {

  private JwtService jwtService;

  // Clave de prueba: "dev-secret-key-at-least-32-characters-long" en Base64
  private static final String TEST_SECRET = Base64.getEncoder().encodeToString(
      "dev-secret-key-at-least-32-characters-long".getBytes());

  @BeforeEach
  void setUp() {
    jwtService = new JwtService();
    // Inyectar valores sin Spring context — ReflectionTestUtils
    ReflectionTestUtils.setField(jwtService, "secretKey", TEST_SECRET);
    ReflectionTestUtils.setField(jwtService, "expirationMs", 86_400_000L);
  }

  @Test
  void generateToken_shouldReturnValidJwt() {
    var userDetails = buildUser("alice", "ROLE_USER");

    var token = jwtService.generateToken(userDetails);

    assertThat(token).isNotBlank();
    // Un JWT tiene exactamente 3 partes separadas por puntos
    assertThat(token.split("\\.")).hasSize(3);
  }

  @Test
  void extractUsername_shouldReturnCorrectUsername() {
    var userDetails = buildUser("alice", "ROLE_USER");
    var token = jwtService.generateToken(userDetails);

    var extracted = jwtService.extractUsername(token);

    assertThat(extracted).isEqualTo("alice");
  }

  @Test
  void isValid_shouldReturnTrueForFreshToken() {
    var userDetails = buildUser("alice", "ROLE_USER");
    var token = jwtService.generateToken(userDetails);

    assertThat(jwtService.isValid(token, userDetails)).isTrue();
  }

  @Test
  void isValid_shouldReturnFalseForDifferentUser() {
    var alice = buildUser("alice", "ROLE_USER");
    var bob = buildUser("bob", "ROLE_USER");
    var tokenForAlice = jwtService.generateToken(alice);

    // Token de Alice presentado como si fuera de Bob → inválido
    assertThat(jwtService.isValid(tokenForAlice, bob)).isFalse();
  }

  @Test
  void isValid_shouldReturnFalseForExpiredToken() {
    var userDetails = buildUser("alice", "ROLE_USER");
    // Generar token con expiración en el pasado
    ReflectionTestUtils.setField(jwtService, "expirationMs", -1000L);
    var expiredToken = jwtService.generateToken(userDetails);

    // Restaurar expiración normal para poder parsear
    ReflectionTestUtils.setField(jwtService, "expirationMs", 86_400_000L);

    assertThat(jwtService.isValid(expiredToken, userDetails)).isFalse();
  }

  @Test
  void extractUsername_shouldThrowForTamperedToken() {
    var userDetails = buildUser("alice", "ROLE_USER");
    var token = jwtService.generateToken(userDetails);

    // Modificar el payload del token → firma inválida
    var parts = token.split("\\.");
    var tamperedToken = parts[0] + ".tampered_payload." + parts[2];

    assertThatThrownBy(() -> jwtService.extractUsername(tamperedToken))
        .isInstanceOf(io.jsonwebtoken.JwtException.class);
  }

  private org.springframework.security.core.userdetails.UserDetails buildUser(
      String username, String authority) {
    return User.builder()
        .username(username)
        .password("hashed")
        .authorities(authority)
        .build();
  }
}
