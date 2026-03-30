# Práctica 01 — Implementar JwtService

## 🎯 Objetivo

Generar y validar JWTs usando la librería JJWT sin frameworks adicionales.

## ⏱️ Duración estimada: 40 minutos

## 📋 Pasos

### Paso 1: Agregar dependencias JJWT

Agrega las tres dependencias de JJWT en el pom.xml:
- `jjwt-api` (compile)
- `jjwt-impl` (runtime)
- `jjwt-jackson` (runtime)

### Paso 2: Crear JwtService

**Abre `starter/JwtApp.java`** y descomenta la sección `PASO 2`.

```java
// generateToken → crea el JWT firmado con HMAC-SHA256
// extractUsername → extrae el subject del payload
// isValid → verifica firma, expiración y username
```

### Paso 3: Crear JwtAuthenticationFilter

Descomenta la sección `PASO 3`.

```java
// OncePerRequestFilter garantiza que el filtro se ejecuta UNA vez por request
// Extrae el token del header Authorization: Bearer <token>
// Si el token es válido: setea el usuario en SecurityContextHolder
```

### Paso 4: Configurar SecurityFilterChain con JWT

Descomenta la sección `PASO 4`.

```java
// SessionCreationPolicy.STATELESS → sin sesiones HTTP
// addFilterBefore → ejecutar JwtFilter antes que el filtro de credenciales
```

### Paso 5: Probar el flujo completo

```bash
# Login → obtener token
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user1","password":"pass123"}' | grep -o '"token":"[^"]*"' | cut -d'"' -f4)

echo "Token: $TOKEN"

# Usar el token en requests subsiguientes
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/tasks
```

## ✅ Verificación

- [ ] `POST /api/auth/login` retorna `{"token": "eyJ...", "expiresIn": ...}`
- [ ] `GET /api/tasks` con Bearer token retorna 200
- [ ] `GET /api/tasks` sin token retorna 401
- [ ] Token expirado retorna 401
- [ ] Token con firma inválida retorna 401
