# Semana 03 — Spring Boot: Configuración y REST MVC

> De Spring Core a Spring Boot: auto-configuración, perfiles,
> y tu primera API RESTful con `@RestController`.

---

## 🎯 Objetivos

- Entender qué agrega Spring Boot sobre Spring Core
- Usar `application.yml` para configurar la aplicación
- Manejar perfiles con `@Profile` y `application-{profile}.yml`
- Exponer métricas con Spring Boot Actuator
- Crear endpoints REST con `@RestController`
- Manejar path params (`@PathVariable`) y query params (`@RequestParam`)
- Recibir y retornar JSON con `@RequestBody` y `ResponseEntity`

---

## 📚 Requisitos Previos

- Semana 02: Spring Core, IoC, DI ✅
- HTTP básico: GET, POST, PUT, DELETE, códigos de estado ✅

---

## 🗂️ Estructura

```
week-03-spring_boot_configuracion_y_mvc/
├── 1-teoria/
│   ├── 01-spring-boot-autoconfiguration.md
│   ├── 02-application-yml-y-perfiles.md
│   └── 03-rest-controller-y-http.md
├── 2-practicas/
│   ├── practica-01-primer-spring-boot-app/
│   ├── practica-02-configuracion-perfiles/
│   └── practica-03-rest-endpoints/
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
| [01-spring-boot-autoconfiguration.md](1-teoria/01-spring-boot-autoconfiguration.md) | Starters, auto-config, `@SpringBootApplication`, Actuator |
| [02-application-yml-y-perfiles.md](1-teoria/02-application-yml-y-perfiles.md) | `application.yml`, `@Value`, `@ConfigurationProperties`, perfiles |
| [03-rest-controller-y-http.md](1-teoria/03-rest-controller-y-http.md) | `@RestController`, HTTP verbs, `@PathVariable`, `@RequestParam`, `ResponseEntity` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-primer-spring-boot-app](2-practicas/practica-01-primer-spring-boot-app/) | Crear primer proyecto con Spring Initializr y levantarlo con Docker |
| [practica-02-configuracion-perfiles](2-practicas/practica-02-configuracion-perfiles/) | Perfiles dev/prod con `application-dev.yml` |
| [practica-03-rest-endpoints](2-practicas/practica-03-rest-endpoints/) | CRUD básico en memoria sin base de datos |

### Proyecto (2.5h)

[📦 API de Inventario (en memoria)](3-proyecto/README.md) — API REST completa sin BD: gestión de productos con todos los verbos HTTP y validación de códigos de respuesta.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Auto-configuración | 45min |
| Teoría: application.yml y perfiles | 45min |
| Teoría: REST Controller y HTTP | 30min |
| Práctica 01: Primer app | 1h |
| Práctica 02: Perfiles | 45min |
| Práctica 03: REST endpoints | 1.75h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] App Spring Boot corriendo en Docker en puerto 8080
- [ ] Al menos 5 endpoints REST (GET, POST, PUT, DELETE + uno extra)
- [ ] Perfiles `dev` y `prod` configurados en `application.yml`
- [ ] Actuator habilitado: `/actuator/health` respondiendo
- [ ] Probado con curl o Postman/HTTPie

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 02 — Spring Core: IoC y DI](../week-02-spring_core_ioc_di/README.md) |
| ➡️ Siguiente | [Semana 04 — Validación, DTOs y OpenAPI](../week-04-validacion_dtos_openapi/README.md) |
