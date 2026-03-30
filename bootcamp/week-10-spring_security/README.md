# Semana 10 — Spring Security: Autenticación y Autorización

> Protege tu API: configura el `SecurityFilterChain`, implementa
> autenticación con `UserDetailsService` y controla el acceso por roles.

---

## 🎯 Objetivos

- Entender el `SecurityFilterChain` y cómo intercepta requests
- Configurar autenticación con `UserDetailsService` y BCrypt
- Distinguir autenticación (¿quién eres?) de autorización (¿qué puedes hacer?)
- Implementar roles con `@PreAuthorize` y `hasRole()`
- Usar `GrantedAuthority` para permisos granulares
- Configurar CORS y protección CSRF correctamente

---

## 📚 Requisitos Previos

- Semana 08–09: Arquitectura en capas, Service Layer ✅
- HTTP: cabeceras de autenticación básicas ✅
- Spring Data JPA: entidades y repositorios ✅

---

## 🗂️ Estructura

```
week-10-spring_security/
├── 1-teoria/
│   ├── 01-spring-security-fundamentos.md
│   ├── 02-autenticacion-y-autorizacion.md
│   └── 03-roles-permisos-y-cors.md
├── 2-practicas/
│   ├── practica-01-security-config/
│   └── practica-02-userdetails-service/
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
| [01-spring-security-fundamentos.md](1-teoria/01-spring-security-fundamentos.md) | `SecurityFilterChain`, filter chain, `UserDetailsService`, BCrypt |
| [02-autenticacion-y-autorizacion.md](1-teoria/02-autenticacion-y-autorizacion.md) | `AuthenticationManager`, `UserDetails`, `SecurityContext`, `@PreAuthorize` |
| [03-roles-permisos-y-cors.md](1-teoria/03-roles-permisos-y-cors.md) | Roles jerárquicos, `GrantedAuthority`, CORS, CSRF en APIs stateless |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-security-config](2-practicas/practica-01-security-config/) | Configurar `SecurityFilterChain`: rutas públicas y protegidas |
| [practica-02-userdetails-service](2-practicas/practica-02-userdetails-service/) | `UserDetailsService` con usuarios en BD + autorización por roles |

### Proyecto (2.5h)

[📦 Task Management API](3-proyecto/README.md) — API de gestión de tareas con autenticación HTTP Basic, roles `ADMIN` / `USER`, protección de endpoints por rol y tests de seguridad con `@WithMockUser`.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Fundamentos Spring Security | 45min |
| Teoría: Autenticación y autorización | 45min |
| Teoría: Roles, permisos y CORS | 30min |
| Práctica 01: Security config | 1.5h |
| Práctica 02: UserDetailsService | 2h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] `SecurityFilterChain` configurado: rutas públicas (`/api/public/**`) y protegidas (`/api/**`)
- [ ] `UserDetailsService` cargando usuarios desde PostgreSQL
- [ ] Contraseñas hasheadas con BCrypt
- [ ] Roles `ADMIN` y `USER` con acceso diferenciado a endpoints
- [ ] CORS configurado para permitir requests del frontend React

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 09 — Arquitectura Hexagonal](../week-09-arquitectura_hexagonal/README.md) |
| ➡️ Siguiente | [Semana 11 — JWT y OAuth2](../week-11-jwt_y_oauth2/README.md) |
