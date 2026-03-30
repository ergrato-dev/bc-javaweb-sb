package com.bootcamp.taskapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Servicio para generar y validar JSON Web Tokens (JWT).
 *
 * Estructura de un JWT:
 *   HEADER.PAYLOAD.SIGNATURE
 *   - Header: algoritmo de firma (HS256)
 *   - Payload: claims (sub, iat, exp, roles)
 *   - Signature: firma con clave secreta — garantiza integridad
 *
 * La clave secreta debe tener al menos 256 bits (32 bytes) para HS256.
 * Nunca hardcodear la clave — viene de application.yml.
 */
@Service
public class JwtService {

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.security.jwt.expiration-ms}")
    private long jwtExpirationMs;

    // ============================================
    // TODO: Implementar generateToken
    // ============================================
    // Genera un JWT firmado con HS256 para el usuario dado.
    // El token debe incluir:
    //   - subject: userDetails.getUsername()
    //   - claim "roles": la lista de authorities del usuario
    //   - issuedAt: fecha actual
    //   - expiration: fecha actual + jwtExpirationMs
    // Usa Jwts.builder()...signWith(getSigningKey())...compact()
    //
    public String generateToken(UserDetails userDetails) {
        // TODO: implementar usando Jwts.builder()
        // Pista: las authorities se obtienen con userDetails.getAuthorities()
        //        y se pueden convertir a String con stream().map(Object::toString).toList()
        return null;
    }

    // ============================================
    // TODO: Implementar isTokenValid
    // ============================================
    // Retorna true si el token es válido para el usuario:
    //   1. El username en el token coincide con userDetails.getUsername()
    //   2. El token no está expirado (isTokenExpired() == false)
    //
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // TODO: implementar
        return false;
    }

    // ============================================
    // TODO: Implementar extractUsername
    // ============================================
    // Extrae el "subject" (username) del token.
    // Usa extractClaim(token, Claims::getSubject)
    //
    public String extractUsername(String token) {
        // TODO: implementar
        return null;
    }

    // ---- Métodos helper (ya implementados) ----

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        // Jwts.parser() valida la firma automáticamente — lanza excepción si es inválido
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
