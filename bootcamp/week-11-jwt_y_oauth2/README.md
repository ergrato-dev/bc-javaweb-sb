# Semana 11 — JWT y OAuth2

> Implementa autenticación stateless con tokens JWT: el estándar
> para APIs REST modernas consumidas por React y móviles.

---

## 🎯 Objetivos

- Entender la estructura de un JWT (header, payload, signature)
- Generar y validar tokens con `jjwt` (io.jsonwebtoken)
- Implementar `JwtAuthenticationFilter` en el filter chain de Spring Security
- Configurar login con `POST /auth/login` → `Bearer token`
- Implementar refresh tokens para sesiones de larga duración
- Configurar Spring Boot como OAuth2 Resource Server

---

## 📚 Requisitos Previos

- Semana 10: `SecurityFilterChain`, `UserDetailsService`, roles ✅
- HTTP headers: `Authorization: Bearer <token>` ✅

---

## 🗂️ Estructura

```
week-11-jwt_y_oauth2/
├── 1-teoria/
│   ├── 01-jwt-estructura-y-firma.md
│   ├── 02-jwt-spring-security-integration.md
│   └── 03-refresh-tokens-y-oauth2.md
├── 2-practicas/
│   ├── practica-01-generar-validar-jwt/
│   ├── practica-02-jwt-filter-chain/
│   └── practica-03-refresh-tokens/
├── 3-proyecto/
│   ├── README.md
│   └── starter/
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

### Teoría (2h)

| Archivo | Tema |
|---------|------|
| [01-jwt-estructura-y-firma.md](1-teoria/01-jwt-estructura-y-firma.md) | Header.Payload.Signature, claims, `exp`, `sub`, `iat`, firmado con HMAC-SHA256 |
| [02-jwt-spring-security-integration.md](1-teoria/02-jwt-spring-security-integration.md) | `JwtService`, `JwtAuthenticationFilter`, `OncePerRequestFilter`, `SecurityContextHolder` |
| [03-refresh-tokens-y-oauth2.md](1-teoria/03-refresh-tokens-y-oauth2.md) | Access token (15min) vs refresh token (7d), rotación, OAuth2 Resource Server |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-generar-validar-jwt](2-practicas/practica-01-generar-validar-jwt/) | `JwtService.generateToken(user)` + `validateToken(token)` con jjwt |
| [practica-02-jwt-filter-chain](2-practicas/practica-02-jwt-filter-chain/) | `JwtAuthenticationFilter` extrae token del header y autentica la request |
| [practica-03-refresh-tokens](2-practicas/practica-03-refresh-tokens/) | Tabla `refresh_tokens` en BD; endpoint `POST /auth/refresh` |

### Proyecto (2.5h)

[📦 API Segura con Autenticación JWT Completa](3-proyecto/README.md) — `POST /auth/register`, `POST /auth/login`, `POST /auth/refresh`, `POST /auth/logout`; acceso por roles; tokens firmados con clave secreta en `.env`.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: JWT estructura + firma | 45min |
| Teoría: Integración con Spring Security | 45min |
| Teoría: Refresh tokens + OAuth2 | 30min |
| Práctica 01: Generar/validar JWT | 1.25h |
| Práctica 02: JWT filter chain | 1.25h |
| Práctica 03: Refresh tokens | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] `POST /auth/login` retorna `{ accessToken, refreshToken, expiresIn }`
- [ ] Endpoints protegidos requieren `Authorization: Bearer <token>`
- [ ] Token expirado retorna `401 Unauthorized` con mensaje claro
- [ ] `POST /auth/refresh` genera nuevo access token sin re-login
- [ ] Clave JWT cargada desde variable de entorno (nunca hardcodeada)

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 10 — Spring Security: Auth & Authz](../week-10-spring_security_auth_authz/README.md) |
| ➡️ Siguiente | [Semana 12 — Testing con JUnit 5 y Mockito](../week-12-testing_junit5_mockito/README.md) |
