# Proyecto Semana 07 — API de Empleados con Historial de Cambios

## 🎯 Descripción

Construye una API para gestión de empleados con esquema completamente controlado por Flyway, búsqueda dinámica con Specifications y campos de auditoría automáticos.

## 📋 Escenario

Una empresa de RRHH necesita registrar empleados con historial de cuándo fueron creados y modificados, y poder buscar por múltiples criterios opcionales sin escribir queries complejas.

## 🏗️ Migraciones Flyway

```
V1__create_departments.sql   — tabla departments
V2__create_employees.sql     — tabla employees con FK a departments
V3__seed_departments.sql     — datos iniciales de departamentos
V4__add_phone_to_employees.sql — ALTER TABLE para agregar columna
```

## 📌 Requerimientos

### Flyway
- [ ] **R1:** 4 scripts de migración en `src/main/resources/db/migration/`
- [ ] **R2:** `spring.flyway.enabled=true`; `ddl-auto: validate` en todos los entornos
- [ ] **R3:** `flyway_schema_history` con los 4 registros después de arrancar

### Entidad con Auditoría
- [ ] **R4:** `Employee` con `@CreatedDate` y `@LastModifiedDate` (se llenan automáticamente)
- [ ] **R5:** `@EnableJpaAuditing` en la clase principal de configuración

### Projections
- [ ] **R6:** `EmployeeSummary` — interface projection con solo `id`, `fullName`, `email`, `departmentName`
- [ ] **R7:** Endpoint `GET /employees/summary` retorna projection (no entidad completa)

### Specifications
- [ ] **R8:** `EmployeeSpecifications` con métodos estáticos: `byDepartment()`, `bySalaryBetween()`, `byHireDateAfter()`, `byActive()`
- [ ] **R9:** Endpoint `GET /employees?department=IT&minSalary=50000&active=true` usa Specification combinada
- [ ] **R10:** Todos los parámetros de búsqueda son opcionales

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Flyway con 4 migraciones y `ddl-auto: validate` | 25 |
| Auditoría automática (`createdAt`, `updatedAt`) | 15 |
| Projection en endpoint `GET /employees/summary` | 15 |
| Specifications con ≥3 filtros opcionales combinables | 30 |
| Paginación en búsqueda con Specification | 15 |
| **Total** | **100** |
