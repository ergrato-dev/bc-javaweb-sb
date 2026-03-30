# Práctica 02 — UserDetailsService con Base de Datos

## 🎯 Objetivo

Implementar un `UserDetailsService` que carga usuarios desde la base de datos
con un `UserRepository` JPA y registra nuevos usuarios con BCrypt.

## ⏱️ Duración estimada: 50 minutos

## 📋 Pasos

### Paso 1: Entidad User con roles

**Abre `starter/UserManagementApp.java`** y descomenta la sección `PASO 1`.

```java
// La entidad User almacena el password ya hasheado
// NUNCA almacenes el password en texto plano
```

### Paso 2: Repositorio y DTO

Descomenta la sección `PASO 2`.

```java
// Spring Data JPA deriva la query automáticamente:
// findByUsername → SELECT * FROM users WHERE username = ?
```

### Paso 3: CustomUserDetailsService

Descomenta la sección `PASO 3`.

```java
// UserDetailsService es la interfaz que Spring Security usa para autenticar
// loadUserByUsername se llama automáticamente en cada request
```

### Paso 4: Endpoint de registro

Descomenta la sección `PASO 4`.

```java
// Al registrar: hashear el password ANTES de guardar en DB
// passwordEncoder.encode(rawPassword) → hash diferente cada vez (salt incluido)
```

### Paso 5: Probar el flujo completo

```bash
# Registrar un nuevo usuario
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "juan", "password": "pass123", "role": "ROLE_USER"}'

# Autenticarse con el usuario registrado
curl -u juan:pass123 http://localhost:8080/api/tasks
```

## ✅ Verificación

- [ ] Registro retorna 201 con datos del usuario (sin el password)
- [ ] Autenticación con las credenciales recién registradas funciona
- [ ] El password en la DB empieza con `$2a$` (BCrypt)
- [ ] Un usuario con `ROLE_USER` no puede acceder a `/api/admin/**`
