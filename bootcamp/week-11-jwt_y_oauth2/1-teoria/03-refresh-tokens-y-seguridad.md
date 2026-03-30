# Refresh Tokens y Seguridad Avanzada

## 🎯 Objetivos

- Implementar refresh tokens para renovar access tokens expirados
- Aplicar mejores prácticas de seguridad con JWT
- Entender OAuth2 Resource Server como alternativa a JWT manual
- Conocer ataques comunes y cómo mitigarlos

---

## 1. Estrategia de Tokens Dual

```
┌─────────────────────────────────────────────────────┐
│  Access Token (JWT)    │  Refresh Token              │
│  — Vida corta 15-60min │  — Vida larga 7-30 días     │
│  — Stateless           │  — Guardado en DB           │
│  — Para cada request   │  — Solo para renovar access │
│  — En header Bearer    │  — En HttpOnly cookie       │
└─────────────────────────────────────────────────────┘
```

---

## 2. Entidad RefreshToken

```java
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean revoked = false;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isValid() {
        return !revoked && !isExpired();
    }
}
```

---

## 3. Endpoints de Auth Completos

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        // 1. Autenticar
        authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var userDetails = (UserDetails) authentication.getPrincipal();

        // 2. Generar access token (JWT, vida corta)
        var accessToken = jwtService.generateToken(userDetails);

        // 3. Generar refresh token (guardado en DB)
        var refreshToken = refreshTokenService.create(userDetails.getUsername());

        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshRequest request) {
        // 1. Buscar refresh token en DB
        var refreshToken = refreshTokenService.findByToken(request.refreshToken())
                .filter(RefreshToken::isValid)
                .orElseThrow(() -> new RefreshTokenExpiredException());

        // 2. Generar nuevo access token
        var userDetails = userDetailsService.loadUserByUsername(refreshToken.getUsername());
        var newAccessToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new RefreshResponse(newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshRequest request) {
        refreshTokenService.revoke(request.refreshToken());
        return ResponseEntity.noContent().build();
    }
}
```

---

## 4. OAuth2 Resource Server (Alternativa)

Spring Security tiene soporte nativo para OAuth2 que simplifica la configuración JWT:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://accounts.google.com  # o tu proveedor
          # O con clave simétrica local:
          # secret: ${JWT_SECRET}
```

```java
// Con OAuth2 Resource Server, Spring maneja el filtro JWT automáticamente
http.oauth2ResourceServer(oauth2 ->
    oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
);
```

> 💡 Para producción con Google/GitHub/Okta: usar OAuth2 Resource Server.
> Para JWT propio sin proveedor externo: usar `JwtAuthenticationFilter` manual (como en la práctica).

---

## 5. Ataques Comunes y Mitigaciones

| Ataque | Qué hace | Mitigación |
|--------|----------|------------|
| **Token theft** | Robo del JWT | HTTPS obligatorio, tokens de vida corta |
| **XSS** | Inyecta JS que roba el token del localStorage | Guardar en `HttpOnly` cookie |
| **JWT alg:none** | Modificar el header para saltarse la firma | JJWT rechaza `alg:none` por defecto |
| **Weak secret** | Fuerza bruta de la clave secreta | Mínimo 256 bits (32 chars), entropía alta |
| **Replay attack** | Reutilizar un token válido robado | Refresh tokens revocables, vida corta del access |

---

## 6. Claims Útiles

```java
Jwts.builder()
    .subject(username)
    .claim("roles", roles)          // Roles para evitar consulta a DB
    .claim("userId", user.getId())  // ID para joins directos
    .claim("email", user.getEmail())
    // NO incluir: password, credit card, SSN, datos sensibles
    .issuedAt(new Date())
    .expiration(new Date(System.currentTimeMillis() + expirationMs))
    .signWith(key)
    .compact();
```

---

## ✅ Checklist

- [ ] Refresh tokens almacenados en DB con campo `revoked`
- [ ] `POST /api/auth/logout` revoca el refresh token
- [ ] HTTPS en producción (el JWT viaja en texto)
- [ ] Secreto JWT con al menos 32 caracteres aleatorios
- [ ] Claims sin datos sensibles (solo username, roles, ID)
- [ ] Manejo de `JwtException` en el filtro para respuesta 401 limpia
