package com.bootcamp.taskapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Entidad de usuario de la aplicación.
 *
 * Nota: usamos 'AppUser' en lugar de 'User' para evitar conflicto
 * con la clase java.sql.User y con Spring Security's UserDetails.
 *
 * La contraseña siempre se almacena hasheada con BCrypt (nunca en texto plano).
 */
@Entity
@Table(name = "app_users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  // Almacenado como hash BCrypt — NUNCA en texto plano
  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role = Role.USER;

  @CreatedDate
  @Column(updatable = false)
  private Instant createdAt;

  public AppUser(String username, String email, String hashedPassword, Role role) {
    this.username = username;
    this.email = email;
    this.password = hashedPassword;
    this.role = role;
  }
}
