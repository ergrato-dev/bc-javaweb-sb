# Semana 15 — Docker y CI/CD

## 🎯 Objetivos de Aprendizaje

- ✅ Escribir un `Dockerfile` multi-stage para Spring Boot (build + runtime)
- ✅ Crear una imagen Docker con usuario no-root por seguridad
- ✅ Orquestar API + PostgreSQL con `docker-compose.yml`
- ✅ Gestionar variables de entorno con `.env` y `.env.example`
- ✅ Crear un workflow de GitHub Actions para CI (compilar + ejecutar tests)
- ✅ Entender los perfiles Spring (`dev`, `test`, `prod`) por entorno

---

## 📚 Requisitos Previos

- Docker Desktop o Docker Engine instalado y corriendo
- Cuenta en GitHub
- Semana 13: Testcontainers (para entender cómo los tests usan PostgreSQL real)

---

## 🗂️ Estructura

```
week-15-docker_cicd/
├── README.md
├── rubrica-evaluacion.md
├── 1-teoria/
│   ├── 01-dockerfile-multistage.md  ← Multi-stage build, .dockerignore, comandos
│   ├── 02-docker-compose.md         ← docker-compose.yml, .env, healthcheck
│   └── 03-github-actions-cicd.md   ← CI/CD workflow, secrets, GitHub Actions
├── 2-practicas/
│   ├── practica-01-docker/          ← Crear Dockerfile paso a paso
│   └── practica-02-compose-cicd/   ← Docker Compose + GitHub Actions
├── 3-proyecto/                      ← Blog API — dockerizar
│   ├── README.md
│   └── starter/                     ← Maven project listo para dockerizar
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

| Archivo | Tema |
|---------|------|
| [01-dockerfile-multistage.md](1-teoria/01-dockerfile-multistage.md) | Dockerfile, multi-stage build, perfiles Spring |
| [02-docker-compose.md](1-teoria/02-docker-compose.md) | docker-compose.yml, variables de entorno, healthcheck |
| [03-github-actions-cicd.md](1-teoria/03-github-actions-cicd.md) | GitHub Actions CI/CD, secrets, deploy |

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Dockerfile multi-stage | 40 min |
| Teoría: Docker Compose | 40 min |
| Teoría: GitHub Actions | 40 min |
| Práctica 1: Dockerfile | 1h 45min |
| Práctica 2: Compose + CI/CD | 1h 45min |
| Proyecto: Dockerizar Blog API | 2h 30min |

---

## 📌 Entregables

- [ ] `Dockerfile` multi-stage en el starter del proyecto
- [ ] `docker compose up --build` levanta API + PostgreSQL
- [ ] `GET /actuator/health` responde `{"status":"UP"}`
- [ ] `.github/workflows/ci.yml` pasa en GitHub Actions
- [ ] `.env.example` con todas las variables necesarias

---

## 🔗 Navegación

← [Semana 14 — Cache, Async y Events](../week-14-cache_async_events/README.md)
→ [Semana 16 — Proyecto Final](../week-16-proyecto_final/README.md)
