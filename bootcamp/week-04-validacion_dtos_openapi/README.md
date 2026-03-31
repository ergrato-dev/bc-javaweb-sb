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
    ├── 01-jakarta-bean-validation.md
    ├── 02-dtos-y-mapstruct.md
    └── 03-springdoc-openapi-swagger.md
├── 2-practicas/
│   ├── practica-01-validation/
│   └── practica-02-openapi/
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
| [01-jakarta-bean-validation.md](1-teoria/01-jakarta-bean-validation.md) | `@Valid`, `@NotNull`, `@NotBlank`, `@Size`, `@Email`, validadores custom |
| [02-dtos-y-mapstruct.md](1-teoria/02-dtos-y-mapstruct.md) | Por qué DTOs, Records como DTOs, MapStruct `@Mapper` |
| [03-springdoc-openapi-swagger.md](1-teoria/03-springdoc-openapi-swagger.md) | SpringDoc OpenAPI, Swagger UI, `@Operation`, `@ApiResponse` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-validation](2-practicas/practica-01-validation/) | Validar requests y retornar errores estructurados |
| [practica-02-openapi](2-practicas/practica-02-openapi/) | Documentar API con SpringDoc Swagger UI |

### Proyecto (2.5h)

[📦 API de Gestión de Empleados](3-proyecto/README.md) — API con validación de entradas, manejo de errores consistente (`@ControllerAdvice`) y documentación Swagger.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Bean Validation | 45min |
| Teoría: DTOs + MapStruct | 45min |
| Teoría: Exception Handling + OpenAPI | 30min |
| Práctica 01: Validación | 1h |
| Práctica 02: OpenAPI + Swagger | 1.25h |
| Buffer / revisión | 1.25h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Validaciones activas: requests inválidos retornan `400` con mensaje descriptivo
- [ ] DTOs separados del dominio (`EmployeeRequest`, `EmployeeResponse`)
- [ ] `@ControllerAdvice` centralizando todos los errores (400, 404, 409, 500)
- [ ] Swagger UI accesible en `/swagger-ui.html`
- [ ] Endpoints documentados con `@Operation` y `@ApiResponse`

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 03 — Spring Boot: Configuración y MVC](../week-03-spring_boot_configuracion_y_mvc/README.md) |
| ➡️ Siguiente | [Semana 05 — Spring Data JPA: Entidades y Repositorios](../week-05-spring_data_jpa_basico/README.md) |
