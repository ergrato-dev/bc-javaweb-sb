# Rúbrica de Evaluación — Semana 10
## Spring Security: Autenticación y Autorización

---

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Descripción |
|------|-----------|-------------|
| 🧠 Conocimiento | 30% | Evaluación teórica de conceptos |
| 💪 Desempeño | 40% | Ejercicios prácticos en clase |
| 📦 Producto | 30% | Proyecto entregable funcional |

---

## 🧠 Conocimiento (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Security Filter Chain** | Describe el flujo completo: request → filtros → AuthenticationManager → SecurityContext | Sabe que Spring Security intercepta requests | No entiende por qué algunos endpoints requieren auth |
| **UserDetailsService** | Implementa `loadUserByUsername()`; retorna `UserDetails` con roles | `UserDetailsService` implementado correctamente | Usa `InMemoryUserDetailsManager` en producción |
| **BCryptPasswordEncoder** | Explica por qué BCrypt (con salt) y no MD5/SHA1; usa `passwordEncoder.encode()` | BCrypt configurado y usado | Contraseñas en texto plano o SHA1 |
| **CORS y CSRF** | Explica CORS para frontend React; CSRF desactivado en APIs stateless y por qué | CORS configurado para `localhost:3000` | Sin CORS → frontend no puede conectar |
| **`@PreAuthorize`** | Usa `hasRole('ADMIN')`, `hasAuthority('user:write')`, `#userId == authentication.name` | `@PreAuthorize("hasRole('ADMIN')")` funcionando | Sin autorización a nivel de método |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Rutas protegidas** | `/api/**` requiere auth; `/actuator/health`, `/swagger-ui/**` son públicos | Separación básica auth/no-auth | Todo requiere auth o nada requiere auth |
| **BCrypt en BD** | Usuarios con hash BCrypt en la tabla; nunca texto plano | BCrypt en registro nuevo | Contraseñas en texto plano en BD |
| **Login funcionando** | `POST /auth/login` retorna algo útil (sesión o token básico) | Login con HTTP Basic funcionando | Sin endpoint de login |
| **Roles diferenciados** | ADMIN y USER con endpoints diferenciados y probados | Un rol forzado en todos los endpoints protegidos | Sin roles, solo autenticado/no-autenticado |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Usuarios en BD** | Tabla `users` con `password_hash` (BCrypt), tabla `roles`, seed via Flyway | Usuarios en BD con BCrypt | Usuarios hardcodeados en código |
| **Autorización granular** | Al menos 3 endpoints con permisos distintos según rol | 2 roles con acceso diferenciado | Un solo nivel de acceso |
| **CORS correcto** | `http://localhost:3000` en allowed origins; headers correctos | CORS activo sin errores preflight | Sin CORS → 403 en preflight |
| **Swagger UI accesible** | `/swagger-ui.html` sin autenticación; endpoints documentados con auth requerida | Swagger UI accesible | Swagger UI bloqueado por Security |

---

## 📏 Escala de Calificación

| Nota | Rango |
|------|-------|
| A | 90–100% |
| B | 80–89% |
| C | 70–79% |
| D | <70% |

---

## ✅ Criterios de Aprobación

- Mínimo **70%** en cada tipo de evidencia
- Contraseñas almacenadas con BCrypt (nunca en texto plano)
- `401 Unauthorized` al acceder sin credenciales a endpoint protegido
- `403 Forbidden` al acceder con rol insuficiente
- Entrega puntual (penalización del 10% por día de retraso)
