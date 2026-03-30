# Semana 11 — Fintech Payment API (JWT)

## 🎯 Objetivo del Proyecto

Autenticar y autorizar una API de pagos usando **JSON Web Tokens (JWT)**.
Los usuarios obtienen un token al hacer login y lo envían en cada request
en el header `Authorization: Bearer <token>`.

## 📋 Endpoints

| Método | URL | Auth | Descripción |
|--------|-----|------|-------------|
| POST | `/api/auth/register` | ❌ público | Registrar usuario |
| POST | `/api/auth/login` | ❌ público | Login → retorna JWT |
| GET | `/api/payments` | ✅ JWT | Mis pagos (admin: todos) |
| GET | `/api/payments/{id}` | ✅ JWT | Pago por ID |
| POST | `/api/payments` | ✅ JWT | Crear pago |
| PATCH | `/api/payments/{id}/cancel` | ✅ JWT | Cancelar pago PENDING |

## 🏗️ Estructura del Proyecto

```
src/main/java/com/bootcamp/
├── domain/
│   ├── AppUser.java, Role.java
│   ├── Payment.java, PaymentStatus.java
├── repository/
│   ├── UserRepository.java
│   └── PaymentRepository.java
├── dto/Dtos.java
├── security/
│   ├── JwtService.java        # Generar/validar JWTs con JJWT
│   ├── JwtAuthenticationFilter.java  # Filtro OncePerRequestFilter
│   └── SecurityConfig.java    # STATELESS + addFilterBefore
├── service/
│   ├── AuthService.java       # Registro
│   └── PaymentService.java    # CRUD de pagos con ownership
├── controller/
│   ├── AuthController.java    # /api/auth/register + /login
│   └── PaymentController.java # /api/payments
└── exception/
    ├── PaymentNotFoundException.java
    ├── UserNotFoundException.java
    └── GlobalExceptionHandler.java
```

## 📝 TODOs a Implementar

### AuthService.java
- `register()` — validar username único, hashear password, guardar

### AuthController.java
- `register()` — 201 Created con UserResponse
- `login()` — autenticar con AuthenticationManager, generar JWT, retornar AuthResponse

### PaymentService.java
- `create()` — crear Payment con ownerUsername del usuario autenticado
- `findPayments()` — admin ve todo, usuario ve solo los suyos
- `findById()` — respeta ownership
- `cancel()` — solo pagos PENDING pueden cancelarse

### PaymentController.java
- Implementar los 4 endpoints extrayendo username e isAdmin del `@AuthenticationPrincipal`

## 🧪 Verificación

```bash
# Compilar y testear
./mvnw test

# Ejecutar la aplicación
./mvnw spring-boot:run

# Registrar usuario
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"alice123"}'

# Login → copiar el accessToken
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"alice123"}'

# Usar el token
TOKEN="eyJ..."
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/payments

# Crear pago
curl -u "$TOKEN" -X POST http://localhost:8080/api/payments \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"amount":100.50,"currency":"USD","recipientAccount":"ACC-456"}'
```

## 📊 Rúbrica de Evaluación

| Criterio | Puntos |
|----------|--------|
| `POST /api/auth/login` retorna JWT válido | 25 |
| Requests con JWT → 200; sin JWT → 401 | 20 |
| Usuario solo ve sus propios pagos | 20 |
| Cancela solo pagos PENDING | 15 |
| `JwtServiceTest` — 5 tests pasan | 15 |
| Secreto JWT en variable de entorno | 5 |
| **Total** | **100** |
