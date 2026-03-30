# Proyecto Semana 03 — API REST de Gestión de Tareas

## 🎯 Descripción

Construye una API REST funcional para gestión de tareas (to-do list). Esta semana el almacenamiento es en memoria (sin BD todavía); el foco está en los endpoints REST, códigos HTTP correctos y la configuración de Spring Boot.

## 📋 Escenario

Un equipo necesita una API para gestionar sus tareas diarias. La API debe ser consumible desde cualquier cliente HTTP (Postman, curl, frontend React).

## 🏗️ Modelo

```java
public record Task(
    Long id,
    String title,
    String description,
    TaskPriority priority,
    TaskStatus status,
    LocalDate dueDate
) {}

public enum TaskPriority { LOW, MEDIUM, HIGH, CRITICAL }
public enum TaskStatus { TODO, IN_PROGRESS, DONE, CANCELLED }
```

## 📌 Endpoints Requeridos

| Método | Path | Descripción | HTTP Response |
|--------|------|-------------|---------------|
| `GET` | `/api/v1/tasks` | Listar todas las tareas | `200 OK` |
| `GET` | `/api/v1/tasks/{id}` | Obtener tarea por ID | `200 OK` / `404 Not Found` |
| `GET` | `/api/v1/tasks?status=TODO` | Filtrar por estado | `200 OK` |
| `POST` | `/api/v1/tasks` | Crear tarea | `201 Created` con Location header |
| `PUT` | `/api/v1/tasks/{id}` | Actualizar tarea completa | `200 OK` / `404 Not Found` |
| `PATCH` | `/api/v1/tasks/{id}/status` | Cambiar solo el estado | `200 OK` / `404 Not Found` |
| `DELETE` | `/api/v1/tasks/{id}` | Eliminar tarea | `204 No Content` / `404 Not Found` |

## 📌 Requerimientos Técnicos

- [ ] Prefijo `/api/v1` configurado en `application.yml` (no hardcodeado en cada controller)
- [ ] Perfil `dev` con `server.port=8080` y perfil `test` con `server.port=8081`
- [ ] `ResponseEntity<?>` usado en todos los endpoints (no retornar objetos directamente)
- [ ] `@RestControllerAdvice` con manejo de `TaskNotFoundException`
- [ ] Actuator habilitado: `GET /actuator/health` → `{"status":"UP"}`
- [ ] Almacenamiento en `ConcurrentHashMap<Long, Task>` (thread-safe, en memoria)
- [ ] IDs generados con `AtomicLong` (thread-safe)

## 📂 Estructura Sugerida

```
src/main/java/com/bootcamp/tasks/
├── TasksApplication.java
├── controller/
│   └── TaskController.java
├── service/
│   ├── TaskService.java
│   └── TaskServiceImpl.java
├── model/
│   ├── Task.java
│   ├── TaskPriority.java
│   └── TaskStatus.java
└── exception/
    ├── TaskNotFoundException.java
    └── GlobalExceptionHandler.java
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Todos los endpoints funcionando | 40 |
| Códigos HTTP semánticamente correctos | 20 |
| `@RestControllerAdvice` con 404 | 15 |
| Perfiles y `application.yml` | 10 |
| Actuator accesible | 5 |
| Location header en POST 201 | 10 |
| **Total** | **100** |
