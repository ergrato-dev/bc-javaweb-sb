# 🏥 Proyecto Semana 07 — Hospital Appointment API

## 🎯 Objetivo

Construir una API REST para gestión de citas hospitalarias aplicando **migraciones con Flyway**, **queries dinámicas con JPA Specifications** y **optimización de queries con `@EntityGraph`**.

## 🚀 Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

La H2 Console estará disponible en `http://localhost:8080/h2-console`  
(URL: `jdbc:h2:mem:hospitaldb`, usuario: `sa`, sin contraseña)

## 📋 Dominio

- **Doctor** — nombre, especialidad, email único, número de licencia, activo/inactivo
- **Patient** — nombre, apellido, email, fecha de nacimiento, tipo de sangre
- **Appointment** — doctor + patient + fecha + estado (SCHEDULED, COMPLETED, CANCELLED, NO_SHOW)

Las migraciones en `db/migration/` crean el schema y datos de prueba automáticamente.

## 📝 Tareas a implementar

### 1. `AppointmentRepository` — queries con `@Query`

Implementa los TODOs dejando métodos que:
- busquen citas de un paciente cargando doctor y patient (`JOIN FETCH`)
- carguen una cita por ID con `@EntityGraph`
- busquen citas en un rango de fechas usando _text block_ JPQL

### 2. `AppointmentService` — búsqueda dinámica con Specifications

Crea la clase `AppointmentSpec` con métodos estáticos que devuelvan `Specification<Appointment>`:
- `hasDoctor(Long doctorId)`
- `hasPatient(Long patientId)`
- `hasStatus(Status status)`
- `scheduledAfter(LocalDateTime date)`
- `scheduledBefore(LocalDateTime date)`

Combínalos en `AppointmentService.search()` usando `Specification.where().and()`.

### 3. `AppointmentController` — endpoints REST

Implementa los TODOs del controlador:
- `GET /api/appointments` — con filtros opcionales + paginación
- `POST /api/appointments` — crear cita (validar `@Valid`, retornar 201)
- `PUT /api/appointments/{id}/status` — actualizar estado
- `DELETE /api/appointments/{id}` — cancelar cita (retornar 204)

## ✅ Criterios de evaluación

| Criterio | Pts |
|---|---|
| Flyway corre sin errores: tablas creadas correctamente | 20 |
| `AppointmentSpec` con 5 specifications implementadas | 25 |
| `AppointmentService.search()` combina specs correctamente | 20 |
| Controller: todos los endpoints funcionan con filtros + paginación | 25 |
| Tests pasan: `AppointmentRepositoryTest` | 10 |

**Total: 100 puntos — Mínimo aprobatorio: 70**

## 🧪 Tests

```bash
./mvnw test
```

Los tests en `AppointmentRepositoryTest` validan que Flyway ejecuta las migraciones correctamente y que las Specifications funcionan.
