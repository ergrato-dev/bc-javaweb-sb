## Práctica 02 — JPA Specifications

## 🎯 Objetivo
Construir un endpoint de búsqueda de citas con filtros dinámicos usando Specifications.

## ⏱️ Duración estimada: 50 minutos

---

## Paso 1: Explorar la API base

**Abre `starter/SpecsApp.java`** — tiene un endpoint GET con filtros implementados con `if-else`.

El código funciona pero es verboso. Al agregar un nuevo filtro se vuelve exponencialmente complejo.

---

## Paso 2: Agregar JpaSpecificationExecutor al repository

**Descomenta la sección `// STEP 2`** — extiende `JpaSpecificationExecutor<Appointment>`.

---

## Paso 3: Implementar Specification individual

**Descomenta la sección `// STEP 3`** — implementa `AppointmentSpec.hasStatus()`.

---

## Paso 4: Combinar Specifications

**Descomenta la sección `// STEP 4`** — combina todas las specs con `where().and()`.

Prueba:
```
GET /api/appointments?status=SCHEDULED
GET /api/appointments?doctorName=García
GET /api/appointments?status=COMPLETED&doctorName=García
```

---

## ✅ Verificación Final
- [ ] Filtro solo por status
- [ ] Filtro solo por doctorName
- [ ] Combinación de múltiples filtros
- [ ] Sin filtros → retorna todos
