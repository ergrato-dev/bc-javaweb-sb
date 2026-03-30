# Rúbrica de Evaluación — Semana 15
## Docker, CI/CD y Deployment

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
| **Multi-stage Dockerfile** | Explica por qué 2 stages: builder (JDK + Maven) vs runtime (JRE slim); tamaño resultante | Crea `FROM ... AS builder` + stage final | Un solo stage con JDK completo |
| **Variables de entorno** | Usa `.env` + `${VAR:-default}`; `.env.example` en repo; `.env` en `.gitignore` | Variables en `docker-compose.yml` sin `.env` | Credenciales hardcodeadas en Dockerfile |
| **Perfiles Spring** | `SPRING_PROFILES_ACTIVE=prod` en contenedor; `application-prod.yml` con `DATABASE_URL` | Perfil prod activo en contenedor | Mismo perfil dev en producción |
| **GitHub Actions** | Estructura de workflow: `on:`, `jobs:`, `steps:`, `uses:`, `run:`; reusa `actions/setup-java` | Workflow que hace `mvn verify` | Sin CI configurado |
| **Deployment** | Enumera al menos 2 opciones (Railway, Fly.io, Render, Heroku); diferencia entre PaaS y IaaS | Desplegó en al menos una plataforma | Sin deployment |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **`docker build` exitoso** | Imagen construida sin errores; `docker images` muestra imagen <200MB | Imagen construida aunque sea grande | `docker build` falla |
| **`docker compose up` funcional** | API + BD levantados, API responde en `http://localhost:8080/actuator/health` | `docker compose up` inicia sin errores | Solo API sin BD |
| **Pipeline en verde** | GitHub Actions ejecuta `mvn verify` en cada push; badge verde en README | Pipeline ejecuta sin fallar | Pipeline nunca llega a ejecutarse |
| **`.env` correcto** | `.env.example` con todas las variables documentadas; `.env` en `.gitignore` | Variables en `.env` sin commitear | Credenciales en `docker-compose.yml` o en git |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **URL pública funcionando** | `GET /actuator/health` → `{"status":"UP"}` desde internet | Deployment accesible | Solo local |
| **CI/CD completo** | Push → tests → build imagen → deploy automático | Push → tests pasan | Sin CI |
| **README actualizado** | Instrucciones para levantar con `docker compose up`; URL pública; variables de entorno | Instrucciones básicas de Docker | Sin documentación de deployment |
| **Imagen en producción** | Imagen multi-stage en producción; JRE slim sin JDK ni código fuente | Imagen funcional aunque no optimizada | Imagen con JDK completo y código fuente expuesto |

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
- `docker compose up` levanta la aplicación completa
- Sin credenciales ni secretos en el repositorio de código
- URL pública con `GET /actuator/health` → `200 OK`
- Entrega puntual (penalización del 10% por día de retraso)
