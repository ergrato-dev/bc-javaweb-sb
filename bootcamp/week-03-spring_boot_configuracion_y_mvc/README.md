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
│   ├── 01-spring-boot-autoconfig-y-actuator.md
│   ├── 02-rest-controllers-y-http.md
│   └── 03-manejo-de-excepciones.md
├── 2-practicas/
│   ├── practica-01-rest-controller/
│   └── practica-02-exception-handler/
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
| [01-spring-boot-autoconfig-y-actuator.md](1-teoria/01-spring-boot-autoconfig-y-actuator.md) | Starters, auto-config, `@SpringBootApplication`, Actuator |
| [02-rest-controllers-y-http.md](1-teoria/02-rest-controllers-y-http.md) | `@RestController`, HTTP verbs, `@PathVariable`, `@RequestParam`, `ResponseEntity` |
| [03-manejo-de-excepciones.md](1-teoria/03-manejo-de-excepciones.md) | `@ExceptionHandler`, `@ControllerAdvice`, `ProblemDetail`, códigos HTTP |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-rest-controller](2-practicas/practica-01-rest-controller/) | Primer `@RestController` con todos los verbos HTTP |
| [practica-02-exception-handler](2-practicas/practica-02-exception-handler/) | `@ControllerAdvice` para manejo global de errores |

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
