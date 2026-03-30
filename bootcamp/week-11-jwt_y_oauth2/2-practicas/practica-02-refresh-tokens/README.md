# Práctica 02 — Login y Refresh Tokens

## 🎯 Objetivo

Implementar el flujo completo: login → access token + refresh token → renovación → logout.

## ⏱️ Duración estimada: 55 minutos

## 📋 Pasos

### Paso 1: Endpoint de Login

**Abre `starter/AuthFlowApp.java`** y descomenta la sección `PASO 1`.

```java
// AuthenticationManager.authenticate() verifica credenciales
// Si son correctas: retorna el Authentication con UserDetails
// Si son incorrectas: lanza BadCredentialsException
```

### Paso 2: Entidad RefreshToken

Descomenta la sección `PASO 2`.

```java
// Refresh tokens se guardan en DB para poder revocarlos
// El campo revoked permite logout sin esperar la expiración
```

### Paso 3: RefreshTokenService

Descomenta la sección `PASO 3`.

```java
// create() → genera token UUID, guarda en DB con fecha de expiración
// findByToken() → busca en DB para validar
// revoke() → marca como revocado (logout)
```

### Paso 4: Endpoint de Refresh y Logout

Descomenta la sección `PASO 4`.

```java
// POST /api/auth/refresh: intercambia refresh token por nuevo access token
// POST /api/auth/logout: revoca el refresh token
```

### Paso 5: Probar el flujo

```bash
# 1. Login
RESP=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"alice123"}')

ACCESS=$(echo $RESP | grep -o '"accessToken":"[^"]*"' | cut -d'"' -f4)
REFRESH=$(echo $RESP | grep -o '"refreshToken":"[^"]*"' | cut -d'"' -f4)

# 2. Usar access token
curl -H "Authorization: Bearer $ACCESS" http://localhost:8080/api/tasks

# 3. Renovar access token con refresh token
NEW_ACCESS=$(curl -s -X POST http://localhost:8080/api/auth/refresh \
  -H "Content-Type: application/json" \
  -d "{\"refreshToken\":\"$REFRESH\"}" | grep -o '"accessToken":"[^"]*"' | cut -d'"' -f4)

# 4. Logout
curl -X POST http://localhost:8080/api/auth/logout \
  -H "Content-Type: application/json" \
  -d "{\"refreshToken\":\"$REFRESH\"}"

# 5. Intentar refresh con token revocado → 401
curl -X POST http://localhost:8080/api/auth/refresh \
  -H "Content-Type: application/json" \
  -d "{\"refreshToken\":\"$REFRESH\"}"
```

## ✅ Verificación

- [ ] Login retorna `accessToken` + `refreshToken`
- [ ] Refresh genera nuevo access token
- [ ] Logout revoca el refresh token
- [ ] Refresh con token revocado retorna 401
