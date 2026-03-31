# Proyecto Semana 04 — API de Gestión de Empleados con Validación y Swagger

## 🎯 Descripción

Construye una API REST para gestionar empleados aplicando validación completa con Jakarta Bean Validation, manejo global de errores con `@ControllerAdvice` y documentación automática con SpringDoc OpenAPI.

## 📋 Escenario

Una empresa necesita una API para administrar su directorio de empleados. Todos los datos de entrada deben validarse. Los errores deben retornar respuestas estructuradas. La documentación debe generarse automáticamente con Swagger UI.

## 🏗️ DTOs

```java
// Entrada: crear o actualizar empleado
public record EmployeeRequest(
    @NotBlank @Size(min=2, max=100) String name,
    @NotBlank @Email String email,
    @Min(18) @Max(65) int age,
    @Positive double salary,
    @NotBlank String department,
    @NotBlank String position
) {}

// Salida: respuesta (no expone salary)
public record EmployeeResponse(
    Long id,
    String name,
    String email,
    int age,
    String department,
    String position
) {}
```

## 📌 Requerimientos

### Validación
- [ ] **R1:** `@Valid` en todos los endpoints que reciben `@RequestBody`
- [ ] **R2:** `400 Bad Request` con JSON detallando qué campo falló y por qué
- [ ] **R3:** `@ControllerAdvice` que maneja `MethodArgumentNotValidException` y retorna respuesta estructurada
- [ ] **R4:** `DuplicateEmailException` → `409 Conflict`; `EmployeeNotFoundException` → `404 Not Found`

### Lógica de negocio
- [ ] **R5:** `GET /api/employees?department=Engineering` — filtro opcional por departamento
- [ ] **R6:** `POST /api/employees` — valida unicidad de email antes de crear
- [ ] **R7:** `PUT /api/employees/{id}` — actualiza empleado existente
- [ ] **R8:** `DELETE /api/employees/{id}` — elimina o retorna 404

### Documentación
- [ ] **R9:** Swagger UI en `/swagger-ui.html` con todos los endpoints visibles
- [ ] **R10:** `@Operation(summary=...)` y `@ApiResponse` en cada endpoint con códigos de respuesta
- [ ] **R11:** `@Tag(name="Employees")` en el controller

## 📂 Estructura del Starter

```
src/main/java/com/bootcamp/
├── EmployeeApiApplication.java
├── controller/
│   └── EmployeeController.java     ← TODOs del controller
├── service/
│   └── EmployeeService.java        ← TODOs de lógica
├── dto/
│   ├── EmployeeRequest.java        ← constraints ya definidos
│   └── EmployeeResponse.java
└── exception/
    ├── EmployeeNotFoundException.java
    ├── DuplicateEmailException.java
    └── GlobalExceptionHandler.java  ← TODOs de handlers
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Validación con Jakarta Validation (`@Valid`, constraints) | 25 |
| `400 Bad Request` con detalle de errores por campo | 20 |
| `404` y `409` manejados con mensajes claros | 15 |
| CRUD completo funcionando (`findAll`, `findById`, `create`, `update`, `delete`) | 25 |
| Swagger UI con documentación real (`@Operation`, `@ApiResponse`) | 15 |
| **Total** | **100** |
