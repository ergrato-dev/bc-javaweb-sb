# JWT: JSON Web Tokens

## 🎯 Objetivos

- Entender la estructura de un JWT y sus tres partes
- Implementar autenticación stateless con tokens Bearer
- Generar y validar JWTs con la librería JJWT
- Migrar de HTTP Basic a JWT en una API Spring Boot

---

## 1. ¿Por qué JWT?

| HTTP Basic | JWT |
|------------|-----|
| Credenciales en cada request | Token firmado en cada request |
| Stateful (sesión en servidor posible) | Stateless (servidor no guarda nada) |
| No escala horizontalmente | Escala horizontal nativo |
| Usuario y contraseña viajan en red | Solo un token temporal |

---

## 2. Estructura de un JWT

Un JWT tiene tres partes separadas por puntos: `header.payload.signature`

```
eyJhbGciOiJIUzI1NiJ9
.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MzAwMDAwMDAsImV4cCI6MTczMDA4NjQwMH0
.7tI5e3PlZx9Z2dR-aF5v4Xz8yW1kNomJ4jTY9ZkOvL4
```

```json
// Header (algoritmo y tipo)
{
  "alg": "HS256",
  "typ": "JWT"
}

// Payload (claims — NO guardar passwords aquí)
{
  "sub": "user1",
  "roles": ["ROLE_USER"],
  "iat": 1730000000,   // issued at
  "exp": 1730086400    // expires at (24h después)
}

// Signature = HMAC-SHA256(base64(header) + "." + base64(payload), secretKey)
```

> ⚠️ El payload está **codificado en Base64, NO cifrado**. No guardes contraseñas, PINs ni datos sensibles en el JWT.

---

## 3. Dependencia JJWT

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```

---

## 4. JwtService — Generar y Validar Tokens

```java
@Component
public class JwtService {

    // Clave secreta — en producción desde variables de entorno
    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                // Incluir roles en el token para no consultar DB en cada request
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(a -> a.getAuthority())
                        .toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isValid(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isExpired(token);
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
```

---

## 5. Flujo de Autenticación JWT

```
1. POST /api/auth/login {username, password}
         │
         ▼
2. AuthenticationManager.authenticate()
         │  verifica credenciales contra DB
         ▼
3. JwtService.generateToken(userDetails)
         │
         ▼
4. Response: {"token": "eyJ....", "expiresIn": 86400000}
```

```
5. GET /api/tasks
   Authorization: Bearer eyJ....
         │
         ▼
6. JwtAuthenticationFilter
         │  extrae token del header
         │  valida firma y expiración
         │  carga UserDetails desde DB
         │  setea SecurityContextHolder
         ▼
7. Controller (usuario ya autenticado)
```

---

## ✅ Checklist

- [ ] Dependencias JJWT en pom.xml
- [ ] `JwtService` con `generateToken()` y `isValid()`
- [ ] Secreto JWT en `application.yml` (al menos 32 caracteres)
- [ ] Secreto productivo en variables de entorno (nunca hardcodeado)
- [ ] `JwtAuthenticationFilter` registrado en `SecurityFilterChain`
- [ ] `POST /api/auth/login` retorna el token
