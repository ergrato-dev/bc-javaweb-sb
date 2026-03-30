# Rúbrica de Evaluación — Semana 16
## Proyecto Final

---

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Descripción |
|------|-----------|-------------|
| 🧠 Conocimiento | 20% | Defensa técnica y toma de decisiones |
| 💪 Desempeño | 30% | Code review y calidad del proceso |
| 📦 Producto | 50% | Proyecto final production-ready |

> ⚠️ Esta semana el **Producto** tiene mayor peso (50%) por ser el entregable integrador del bootcamp.

---

## 🧠 Conocimiento (20%) — Defensa Técnica

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Justificación de arquitectura** | Explica por qué eligió capas o hexagonal para su dominio; pros/cons | Explica la arquitectura elegida sin profundizar | No puede explicar por qué tomó las decisiones |
| **Decisiones de seguridad** | Justifica BCrypt, JWT, refresh tokens, CORS; menciona amenazas mitigadas | Explica implementación de seguridad | Solo repite "porque lo vimos en clase" |
| **Estrategia de testing** | Explica qué cubre cada tipo de test (unitario/slice/integración) y por qué esa cobertura | Describe los tests implementados | No puede explicar qué testa cada tipo |
| **Retos y aprendizajes** | Describe 2+ problemas concretos encontrados y cómo los resolvió | Menciona al menos un reto real | Sin reflexión sobre el proceso |

---

## 💪 Desempeño (30%) — Code Review

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Sin antipatrones** | Sin N+1, sin lógica en controllers, sin entidades expuestas, sin contraseñas en texto plano | 1-2 antipatrones menores | ≥3 antipatrones críticos |
| **Código limpio** | Nombres descriptivos, métodos pequeños, sin código duplicado, sin código comentado inútil | Código legible con alguna inconsistencia | Código difícil de leer o muy duplicado |
| **Commits semánticos** | Historia de commits con Conventional Commits (`feat:`, `fix:`, `test:`) | Commits descriptivos (no "wip" ni "update") | Un solo commit o historial confuso |
| **README completo** | Setup en Docker, variables de entorno, endpoints principales, URL pública, arquitectura | README con instrucciones funcionales | README vacío o solo el template |

---

## 📦 Producto (50%) — API Production-Ready

### Funcionalidad (15%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **CRUD + relaciones** | ≥3 entidades relacionadas; CRUD completo en ≥2 entidades | 2 entidades con CRUD básico | 1 entidad o CRUD incompleto |
| **Auth JWT completa** | Register + Login + Refresh + Logout; roles diferenciados | Register + Login funcionando | Sin autenticación o HTTP Basic |
| **Búsqueda y paginación** | Filtros opcionales via Specification + paginación en listados | Filtro simple + paginación | Solo `findAll()` sin paginar |

### Calidad Técnica (20%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Tests en verde** | `mvn verify` pasa; cobertura ≥70%; suite con las 3 capas | `mvn test` pasa; cobertura <70% | Tests con fallos |
| **Schema con Flyway** | Toda DDL en Flyway; `ddl-auto: validate`; ≥3 migraciones | Flyway con `V1__` funcionando | `ddl-auto: create` o `update` |
| **Seguridad** | BCrypt, JWT firmado con variable de entorno, `@PreAuthorize` por rol | BCrypt + JWT sin roles diferenciados | Contraseñas en texto plano |
| **Documentación API** | Swagger UI completo con `@Operation`, `@ApiResponse`, schemas reales | Swagger UI generado automáticamente | Sin Swagger |

### DevOps (15%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Docker** | Multi-stage Dockerfile + Docker Compose funcional; imagen <200MB | `docker compose up` funciona | Sin Docker |
| **CI/CD** | GitHub Actions en verde con cada push; incluye tests + cobertura | GitHub Actions corre tests | Sin CI |
| **URL pública** | Swagger UI accesible en internet; `GET /actuator/health` → 200 | API accesible aunque sea `/hello` | Solo local |

---

## 📏 Escala de Calificación

| Nota | Rango |
|------|-------|
| A | 90–100% |
| B | 80–89% |
| C | 70–79% |
| D | <70% |

---

## ✅ Criterios de Aprobación del Bootcamp

Para obtener el certificado de completación:

- [ ] Mínimo **70%** en cada tipo de evidencia (Conocimiento, Desempeño, Producto)
- [ ] `mvn verify` pasa sin fallos
- [ ] Cobertura JaCoCo ≥ 70%
- [ ] URL pública funcionando con Swagger UI
- [ ] Sin credenciales hardcodeadas en el repositorio
- [ ] `docker compose up` levanta la aplicación completa
- [ ] GitHub Actions pipeline en verde
- [ ] Presentación técnica completada (10 min)
