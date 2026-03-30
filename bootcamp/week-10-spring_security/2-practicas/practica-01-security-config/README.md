# Práctica 01 — Configurar Spring Security desde Cero

## 🎯 Objetivo

Agregar Spring Security a una API REST de tareas, configurar autenticación HTTP Basic
y proteger endpoints por rol.

## ⏱️ Duración estimada: 45 minutos

## 📋 Pasos

### Paso 1: Agregar la dependencia

Agrega `spring-boot-starter-security` y `spring-security-test` en el `pom.xml`.

Observa que al reiniciar la app, **todos los endpoints quedarán protegidos** y verás
en la consola:

```
Using generated security password: a1b2c3d4-...
```

### Paso 2: Crear SecurityConfig básica

**Abre `starter/SecurityApp.java`** y descomenta la sección `PASO 2`.

```java
// La SecurityFilterChain define qué rutas requieren autenticación
// permitAll() → acceso libre sin credenciales
// authenticated() → requiere cualquier usuario autenticado
// hasRole("ADMIN") → requiere rol específico
```

### Paso 3: Agregar UserDetailsService en memoria

Descomenta la sección `PASO 3`.

```java
// InMemoryUserDetailsManager es útil para pruebas rápidas.
// En producción siempre se usa un UserDetailsService que lean de la DB.
```

### Paso 4: Proteger con @PreAuthorize

Descomenta la sección `PASO 4`.

```java
// @PreAuthorize evalúa la expresión ANTES de ejecutar el método
// Si la expresión retorna false → 403 Forbidden automáticamente
```

### Paso 5: Probar con curl

```bash
# Sin credenciales → 401
curl -i http://localhost:8080/api/tasks

# Con credenciales de usuario → 200
curl -u user:password123 http://localhost:8080/api/tasks

# Con usuario intentando endpoint de admin → 403
curl -u user:password123 http://localhost:8080/api/admin/users
```

## ✅ Verificación

- [ ] `/api/tasks` retorna 401 sin credenciales
- [ ] `/api/tasks` retorna 200 con credenciales de `user`
- [ ] `/api/admin/users` retorna 403 con usuario `user`
- [ ] `/api/admin/users` retorna 200 con usuario `admin`
- [ ] `/api/public/health` retorna 200 sin credenciales
