# Rúbrica de Evaluación — Semana 11
## JWT y OAuth2

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
| **Estructura JWT** | Explica header.payload.signature; decodifica un token en jwt.io; nombra claims estándar | Sabe que JWT tiene 3 partes | No puede decodificar un JWT |
| **Firma y verificación** | Explica HMAC-SHA256 con clave secreta; por qué nadie puede falsificar el token | Sabe que el token está firmado | Cree que JWT es encriptado |
| **Filter chain con JWT** | Explica cuándo se ejecuta `JwtAuthenticationFilter`, qué hace con el token extraído | El filtro extrae y valida el token | No entiende dónde va el filtro |
| **Refresh tokens** | Explica acceso (corta duración) vs refresh (larga); flujo de renovación | Diferencia access de refresh token | Cree que el access token dura indefinidamente |
| **Seguridad de la clave** | Clave en variable de entorno; mínimo 256 bits para HMAC-SHA256 | Clave en `application.yml` (no hardcodeada en código) | Clave hardcodeada en el código fuente |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **`POST /auth/login`** | Retorna `{ accessToken, refreshToken, expiresIn, tokenType: "Bearer" }` | Retorna `accessToken` válido | Retorna algo pero el token no es válido |
| **Endpoint protegido** | `Authorization: Bearer <token>` requerido; 401 sin header; respuesta correcta con token | Endpoint protegido funciona con token válido | Token no es validado |
| **Token expirado** | `401 Unauthorized` con mensaje claro cuando el token expiró | 401 cuando token expirado | 500 o 200 con token expirado |
| **`POST /auth/refresh`** | Refresh token válido → nuevo access token; refresh inválido → 401 | Refresh básico funcionando | Sin endpoint de refresh |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Auth completa** | Register → Login → Access token → Refresh → Logout (invalidar refresh) | Register + Login + Access token | Solo Login |
| **Tabla refresh_tokens** | `refresh_tokens` en BD con FK a `users`, `expires_at`, `revoked` | Refresh tokens en memoria (aceptable) | Sin manejo de refresh |
| **Clave en .env** | `JWT_SECRET` en `.env`; `.env.example` en repo; `.env` en `.gitignore` | Clave en `application.yml` con `${JWT_SECRET}` | Clave hardcodeada en código o YAML |
| **Roles en claims** | Claims JWT incluyen roles; `@PreAuthorize` lee del token | Roles en SecurityContext cargados del token | Roles cargados de BD en cada request |

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
- Clave JWT nunca hardcodeada en código fuente (usar variable de entorno)
- Token expirado retorna `401` (no `500` ni `200`)
- `mvn spring-boot:run` arranca sin errores de configuración de security
- Entrega puntual (penalización del 10% por día de retraso)
