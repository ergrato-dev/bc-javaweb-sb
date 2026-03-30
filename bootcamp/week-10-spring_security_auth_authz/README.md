# Semana 10 — Spring Security: Autenticación y Autorización

> Protege tu API. Aprende cómo Spring Security filtra cada request,
> autentica usuarios e impone roles y permisos con precisión.

---

## 🎯 Objetivos

- Comprender el `SecurityFilterChain` y el flujo de cada request HTTP
- Configurar `HttpSecurity` con `@EnableWebSecurity`
- Implementar `UserDetailsService` personalizado con base de datos
- Usar `BCryptPasswordEncoder` para almacenar contraseñas de forma segura
- Configurar CORS para consumo desde frontend React
- Aplicar autorización granular con `@PreAuthorize` y `@Secured`
- Entender por qué se deshabilita CSRF en APIs stateless

---

## 📚 Requisitos Previos

- Semana 08/09: Arquitectura en capas o hexagonal ✅
- HTTP básico: stateless, headers, cookies ✅

---

## 🗂️ Estructura

```
week-10-spring_security_auth_authz/
├── 1-teoria/
│   ├── 01-security-filter-chain.md
│   ├── 02-userdetailsservice-y-bcrypt.md
│   └── 03-roles-permisos-cors.md
├── 2-practicas/
│   ├── practica-01-proteger-endpoints/
│   ├── practica-02-userdetailsservice-db/
│   └── practica-03-preauthorize-roles/
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
| [01-security-filter-chain.md](1-teoria/01-security-filter-chain.md) | `SecurityFilterChain`, flujo de filtros, `AuthenticationManager`, `SecurityContext` |
| [02-userdetailsservice-y-bcrypt.md](1-teoria/02-userdetailsservice-y-bcrypt.md) | `UserDetailsService`, `UserDetails`, `BCryptPasswordEncoder`, por qué no MD5/SHA1 |
| [03-roles-permisos-cors.md](1-teoria/03-roles-permisos-cors.md) | `ROLE_*`, `@PreAuthorize`, `@Secured`, `hasRole`, `hasAuthority`, CORS config, CSRF |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-proteger-endpoints](2-practicas/practica-01-proteger-endpoints/) | Configurar `SecurityFilterChain`: rutas públicas vs protegidas |
| [practica-02-userdetailsservice-db](2-practicas/practica-02-userdetailsservice-db/) | `UserDetailsService` que carga usuarios desde PostgreSQL |
| [practica-03-preauthorize-roles](2-practicas/practica-03-preauthorize-roles/) | `ADMIN` puede DELETE, `USER` solo GET/POST con `@PreAuthorize` |

### Proyecto (2.5h)

[📦 API de Inventario con Control de Acceso](3-proyecto/README.md) — Solo `ADMIN` crea/elimina productos; `USER` consulta. Usuarios en BD con contraseñas BCrypt, Flyway seed de roles, CORS configurado.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Security Filter Chain | 45min |
| Teoría: UserDetailsService + BCrypt | 45min |
| Teoría: Roles, permisos, CORS | 30min |
| Práctica 01: Proteger endpoints | 1.25h |
| Práctica 02: UserDetailsService + DB | 1.25h |
| Práctica 03: @PreAuthorize | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Endpoints públicos (`/auth/**`, `/health`) y protegidos configurados
- [ ] Contraseñas almacenadas con BCrypt (nunca en texto plano)
- [ ] Usuarios y roles en BD con seed via Flyway
- [ ] CORS configurado para `http://localhost:3000` (React dev server)
- [ ] `@PreAuthorize("hasRole('ADMIN')")` en endpoints destructivos

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 09 — Arquitectura Hexagonal](../week-09-arquitectura_hexagonal/README.md) |
| ➡️ Siguiente | [Semana 11 — JWT y OAuth2](../week-11-jwt_y_oauth2/README.md) |
