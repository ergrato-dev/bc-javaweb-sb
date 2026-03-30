# Semana 16 — Proyecto Final

> Zero to Hero, completado. Construye una API REST production-ready
> que integra todo lo aprendido en 16 semanas.

---

## 🎯 Objetivos

- Integrar todo el stack: Spring Boot + JPA + Security + JWT + Tests + Docker
- Aplicar arquitectura hexagonal (o en capas) de forma autónoma
- Documentar la API con SpringDoc OpenAPI / Swagger UI
- Asegurar calidad con tests: unitarios + integración + cobertura ≥ 70%
- Desplegar en producción con CI/CD automatizado
- Presentar y defender las decisiones técnicas tomadas

---

## 📚 Requisitos Previos

- Semanas 01–15: Todo el bootcamp completado ✅

---

## 🗂️ Estructura

```
week-16-proyecto_final/
├── 1-teoria/
│   └── 01-checklist-production-ready.md
├── 2-practicas/
│   └── practica-01-code-review/
├── 3-proyecto/
│   ├── README.md
│   └── starter/
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

### Teoría (30min)

| Archivo | Tema |
|---------|------|
| [01-checklist-production-ready.md](1-teoria/01-checklist-production-ready.md) | API production-ready checklist: seguridad, tests, docs, observabilidad, CI/CD |

### Práctica (1.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-code-review](2-practicas/practica-01-code-review/) | Revisar y corregir una API con errores comunes: N+1, lógica en controller, passwords en texto plano |

### Proyecto Final (6h)

[📦 API REST Production-Ready](3-proyecto/README.md) — Proyecto libre de dominio elegido por el estudiante. Requisitos mínimos detallados a continuación.

---

## 🏗️ Requisitos del Proyecto Final

### Funcionales
- Al menos 3 entidades relacionadas (con `@OneToMany` o `@ManyToMany`)
- CRUD completo en al menos 2 entidades
- Búsqueda con filtros opcionales + paginación
- Autenticación JWT (registro + login + refresh)
- Al menos 2 roles con permisos diferenciados

### Técnicos
- **Arquitectura:** hexagonal o capas (bien aplicada)
- **Base de datos:** PostgreSQL con Flyway (sin `ddl-auto: create`)
- **DTOs:** `CreateRequest`, `UpdateRequest`, `Response` diferenciados
- **Validación:** Jakarta Bean Validation en todos los endpoints
- **Documentación:** Swagger UI completo con `@Operation` y `@ApiResponse`
- **Tests:** unitarios + integración + cobertura ≥ 70% (JaCoCo)
- **Docker:** Dockerfile multi-stage + Docker Compose funcional
- **CI/CD:** GitHub Actions ejecuta `mvn verify` en cada push

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Revisión checklist production-ready | 30min |
| Code review práctico | 1.5h |
| Proyecto final | 6h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Repositorio GitHub con código fuente
- [ ] `README.md` con instrucciones para levantar con Docker Compose
- [ ] URL pública del deployment (Railway / Fly.io / Render)
- [ ] Swagger UI accesible en la URL pública (`/swagger-ui.html`)
- [ ] Pipeline GitHub Actions verde en `main`
- [ ] Reporte JaCoCo con cobertura ≥ 70%
- [ ] Presentación técnica (10 min): decisiones de arquitectura, retos, aprendizajes

---

## 🎓 Criterios de Aprobación del Bootcamp

| Criterio | Mínimo |
|----------|--------|
| Proyecto funcional | API responde correctamente |
| Tests en verde | `mvn test` sin fallos |
| Cobertura | ≥ 70% con JaCoCo |
| Documentación | Swagger UI con todos los endpoints |
| Deployment | URL pública accesible |
| Código limpio | Sin lógica en controllers, sin passwords hardcodeadas |

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 15 — Docker y CI/CD](../week-15-docker_cicd/README.md) |
| 🏠 Inicio | [README del Bootcamp](../../README.md) |
