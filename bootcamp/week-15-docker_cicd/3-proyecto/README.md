# Proyecto Semana 15 — Blog API Production-Ready

## 🎯 Objetivo

Dockerizar y configurar CI/CD para una **Blog API** completa, lista para despliegue en Railway/Fly.io/Render o cualquier plataforma cloud que soporte Docker.

---

## 📦 La Aplicación — Blog API

La Blog API gestiona posts y comentarios:

| Endpoint | Descripción |
|----------|-------------|
| `GET /api/posts` | Listar posts publicados |
| `GET /api/posts/{id}` | Obtener post por ID |
| `POST /api/posts` | Crear post (DRAFT) |
| `POST /api/posts/{id}/publish` | Publicar un DRAFT |
| `POST /api/posts/{id}/comments` | Agregar comentario |
| `GET /actuator/health` | Health check (para Docker healthcheck) |

---

## 🗂️ Tu Tarea

### 1. `Dockerfile` — Multi-stage build

Crear el `Dockerfile` en la raíz del proyecto starter con:
- Etapa `builder`: Eclipse Temurin 21 JDK Alpine + Maven build
- Etapa `runtime`: Eclipse Temurin 21 JRE Alpine + usuario no-root `spring`
- Variables JVM: `-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0`

### 2. `.dockerignore`

Excluir: `target/`, `.git/`, `.github/`, archivos `.md`, `.env`, logs.

### 3. `docker-compose.yml`

Orquestar tres servicios:
- `api` (build desde el Dockerfile)
- `db` (postgres:17-alpine con healthcheck y volumen)
- (Opcional) `redis` (redis:7-alpine)

### 4. `.env.example`

Plantilla con las variables de entorno requeridas.

### 5. `.github/workflows/ci.yml`

Workflow de GitHub Actions que:
- Se ejecuta en push a `main` y en pull requests
- Configura Java 21 con cache Maven
- Ejecuta `./mvnw verify`
- Construye la imagen Docker (sin push) para verificar el Dockerfile

---

## 🚀 Verificación Local

```bash
# 1. Copiar variables de entorno
cp .env.example .env
# (editar .env con valores locales)

# 2. Construir y levantar
docker compose up --build

# 3. Verificar que responde
curl http://localhost:8080/actuator/health
# {"status":"UP"}

# 4. Explorar la API
open http://localhost:8080/swagger-ui.html

# 5. Detener
docker compose down
```

---

## 📁 Archivos a Crear

```
starter/
├── Dockerfile              ← 🎯 TU TAREA
├── .dockerignore           ← 🎯 TU TAREA
├── docker-compose.yml      ← 🎯 TU TAREA
├── .env.example            ← 🎯 TU TAREA
├── .github/
│   └── workflows/
│       └── ci.yml          ← 🎯 TU TAREA
└── src/                    ← ya implementado (no modificar)
    └── ...
```

---

## 📊 Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| `Dockerfile` multi-stage correcto (builder + runtime) | 25 pts |
| Usuario no-root en imagen final | 10 pts |
| `docker compose up --build` levanta correctamente | 25 pts |
| `GET /actuator/health` retorna 200 en el contenedor | 15 pts |
| `.github/workflows/ci.yml` ejecuta y pasa en GitHub Actions | 20 pts |
| `.env.example` con todas las variables requeridas | 5 pts |
| **Total** | **100 pts** |

---

## 💡 Comandos Útiles de Debug

```bash
# Ver logs de la API
docker compose logs -f api

# Entrar al contenedor
docker compose exec api sh

# Ver usuario que corre el proceso
docker compose exec api whoami

# Conectar a PostgreSQL
docker compose exec db psql -U ${POSTGRES_USER} -d ${POSTGRES_DB}

# Tamaño de la imagen
docker images blog-api
```
