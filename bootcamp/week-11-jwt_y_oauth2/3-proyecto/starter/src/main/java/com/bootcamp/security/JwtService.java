package com.bootcamp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Servicio para generar y validar JSON Web Tokens.
 *
 * Usa la librería JJWT 0.12.x con HMAC-SHA256.
 */
@Component
public class JwtService {

  @Value("${app.jwt.secret}")
  private String secretKey;

  @Value("${app.jwt.expiration-ms}")
  private long expirationMs;

  /**
   * Genera un JWT firmado para el usuario dado.
   * Incluye username (sub), roles y timestamps de emisión/expiración.
   */
  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
        .subject(userDetails.getUsername())
        .claim("roles", userDetails.getAuthorities().stream()
            .map(a -> a.getAuthority())
            .toList())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationMs))
        .signWith(getSigningKey())
        .compact();
  }

  /** Extrae el username (subject) del JWT. */
  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  /**
   * Retorna true si el token es válido (firma correcta, no expirado, username
   * coincide).
   */
  public boolean isValid(String token, UserDetails userDetails) {
    var username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isExpired(token);
  }

  public long getExpirationMs() {
    return expirationMs;
  }

  private boolean isExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }
}
