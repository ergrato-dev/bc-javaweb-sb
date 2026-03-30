# Semana 04 — Validación, DTOs y OpenAPI

> APIs robustas: valida entradas, separa la representación
> de la lógica con DTOs y documenta automáticamente con Swagger.

---

## 🎯 Objetivos

- Validar request bodies con Jakarta Bean Validation (`@Valid`, `@NotNull`, `@Size`...)
- Crear validadores personalizados con `@Constraint`
- Diseñar DTOs para separar la API del dominio interno
- Mapear entidades ↔ DTOs con MapStruct
- Manejar errores globalmente con `@ControllerAdvice`
- Documentar la API con SpringDoc OpenAPI / Swagger UI

---

## 📚 Requisitos Previos

- Semana 03: Spring Boot, REST Controller ✅
- Anotaciones Java básicas ✅

---

## 🗂️ Estructura

```
week-04-validacion_dtos_openapi/
├── 1-teoria/
│   ├── 01-bean-validation.md
│   ├── 02-dtos-y-mapstruct.md
│   └── 03-exception-handling-y-openapi.md
├── 2-practicas/
│   ├── practica-01-bean-validation/
│   ├── practica-02-dtos-mapstruct/
│   └── practica-03-exception-handler-swagger/
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
| [01-bean-validation.md](1-teoria/01-bean-validation.md) | `@Valid`, `@NotNull`, `@NotBlank`, `@Size`, `@Email`, `@Min`, validadores custom |
| [02-dtos-y-mapstruct.md](1-teoria/02-dtos-y-mapstruct.md) | Por qué DTOs, Records como DTOs, MapStruct `@Mapper` |
| [03-exception-handling-y-openapi.md](1-teoria/03-exception-handling-y-openapi.md) | `@ExceptionHandler`, `@ControllerAdvice`, `ProblemDetail`, SpringDoc, `@Operation` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-bean-validation](2-practicas/practica-01-bean-validation/) | Validar requests y retornar errores estructurados |
| [practica-02-dtos-mapstruct](2-practicas/practica-02-dtos-mapstruct/) | Crear Request/Response DTOs y mapear con MapStruct |
| [practica-03-exception-handler-swagger](2-practicas/practica-03-exception-handler-swagger/) | Handler global de errores + Swagger UI configurado |

### Proyecto (2.5h)

[📦 API de Usuarios con Validación Completa](3-proyecto/README.md) — API con validación de entradas, DTOs separados, manejo de errores consistente y documentación Swagger.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Bean Validation | 45min |
| Teoría: DTOs + MapStruct | 45min |
| Teoría: Exception Handling + OpenAPI | 30min |
| Práctica 01: Validación | 1h |
| Práctica 02: DTOs + MapStruct | 1.25h |
| Práctica 03: Errores + Swagger | 1.25h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Validaciones activas: requests inválidos retornan `400` con mensaje descriptivo
- [ ] DTOs separados del dominio (`UserCreateRequest`, `UserResponse`)
- [ ] MapStruct mapeando automáticamente entre clases
- [ ] `@ControllerAdvice` centralizando todos los errores
- [ ] Swagger UI accesible en `/swagger-ui.html`

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 03 — Spring Boot: Configuración y MVC](../week-03-spring_boot_configuracion_y_mvc/README.md) |
| ➡️ Siguiente | [Semana 05 — Spring Data JPA: Entidades y Repositorios](../week-05-spring_data_jpa_basico/README.md) |
