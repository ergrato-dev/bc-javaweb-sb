# Semana 15 — Docker, CI/CD y Deployment

> Tu API lista para producción: contenedores Docker,
> pipelines automatizados y deploy en la nube con un `git push`.

---

## 🎯 Objetivos

- Construir una imagen Docker optimizada con multi-stage build
- Orquestar API + PostgreSQL con Docker Compose
- Gestionar secretos con archivos `.env` (nunca en código)
- Configurar perfiles de Spring (`dev`, `test`, `prod`) correctamente
- Crear un pipeline CI/CD con GitHub Actions: build → test → deploy
- Desplegar en un proveedor cloud gratuito (Railway, Fly.io o Render)

---

## 📚 Requisitos Previos

- Semanas 01–14: Stack completo incluyendo tests y Docker básico ✅
- Cuenta en GitHub y en al menos un proveedor cloud ✅

---

## 🗂️ Estructura

```
week-15-docker_cicd_deployment/
├── 1-teoria/
│   ├── 01-dockerfile-multistage.md
│   ├── 02-docker-compose-y-perfiles.md
│   └── 03-github-actions-cicd.md
├── 2-practicas/
│   ├── practica-01-dockerfile-optimizado/
│   ├── practica-02-docker-compose-full-stack/
│   └── practica-03-github-actions-pipeline/
├── 3-proyecto/
│   ├── README.md
│   └── starter/
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

### Teoría (2h)

| Archivo | Tema |
|---------|------|
| [01-dockerfile-multistage.md](1-teoria/01-dockerfile-multistage.md) | `FROM ... AS builder`, layers, `.dockerignore`, imagen final <200MB con Eclipse Temurin |
| [02-docker-compose-y-perfiles.md](1-teoria/02-docker-compose-y-perfiles.md) | `docker-compose.yml`, `SPRING_PROFILES_ACTIVE`, `.env`, health checks, depends_on |
| [03-github-actions-cicd.md](1-teoria/03-github-actions-cicd.md) | `workflow.yml`, triggers, jobs: `build` → `test` → `deploy`, secrets en GitHub |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-dockerfile-optimizado](2-practicas/practica-01-dockerfile-optimizado/) | Multi-stage build: `mvn package` en builder, JRE slim en runtime |
| [practica-02-docker-compose-full-stack](2-practicas/practica-02-docker-compose-full-stack/) | API + PostgreSQL + pgAdmin levantados con `docker compose up` |
| [practica-03-github-actions-pipeline](2-practicas/practica-03-github-actions-pipeline/) | Pipeline que corre `mvn verify` + Testcontainers en GitHub Actions |

### Proyecto (2.5h)

[📦 Deploy Completo de una API en la Nube](3-proyecto/README.md) — Dockerfile, Docker Compose, GitHub Actions con tests automáticos, deploy en Railway (o equivalente), URL pública con Swagger UI accesible.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Dockerfile multi-stage | 45min |
| Teoría: Docker Compose + perfiles | 45min |
| Teoría: GitHub Actions | 30min |
| Práctica 01: Dockerfile optimizado | 1h |
| Práctica 02: Docker Compose full stack | 1.25h |
| Práctica 03: GitHub Actions pipeline | 1.25h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Imagen Docker construida con multi-stage (builder + runtime)
- [ ] `docker compose up` levanta API + DB listos para usar
- [ ] `.env.example` en el repo; `.env` en `.gitignore`
- [ ] Pipeline GitHub Actions: verde en cada `git push` a `main`
- [ ] URL pública funcionando: `GET /actuator/health` → `{"status":"UP"}`

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 14 — Cache, Async y WebSocket](../week-14-cache_async_websocket/README.md) |
| ➡️ Siguiente | [Semana 16 — Proyecto Final](../week-16-proyecto_final/README.md) |
