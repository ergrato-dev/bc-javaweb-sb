# Semana 07 — Migraciones y Queries Avanzadas

> Gestiona la evolución del esquema de DB con Flyway, y lleva
> tus consultas al siguiente nivel con Projections y Specifications.

---

## 🎯 Objetivos

- Gestionar migraciones de base de datos con Flyway
- Nunca usar `ddl-auto: create` o `update` en producción
- Consultar solo los campos necesarios con Projections
- Construir queries dinámicas con Specifications
- Auditar entidades con `@CreatedDate`, `@LastModifiedDate`
- Usar `@CreatedBy` y `@LastModifiedBy` con Spring Security

---

## 📚 Requisitos Previos

- Semana 06: Relaciones JPA, `@Transactional` ✅
- SQL: CREATE TABLE, ALTER TABLE ✅

---

## 🗂️ Estructura

```
week-07-migraciones_y_queries_avanzadas/
├── 1-teoria/
│   ├── 01-flyway-migraciones.md
│   ├── 02-projections-y-specifications.md
│   └── 03-auditoria-jpa.md
├── 2-practicas/
│   ├── practica-01-flyway/
│   ├── practica-02-projections/
│   └── practica-03-specifications/
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
| [01-flyway-migraciones.md](1-teoria/01-flyway-migraciones.md) | Convención `V1__desc.sql`, `flyway_schema_history`, rollback, baseline |
| [02-projections-y-specifications.md](1-teoria/02-projections-y-specifications.md) | Interface projections, class projections, `JpaSpecificationExecutor`, `Specification<T>` |
| [03-auditoria-jpa.md](1-teoria/03-auditoria-jpa.md) | `@EnableJpaAuditing`, `@CreatedDate`, `@LastModifiedDate`, `@EntityListeners` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-flyway](2-practicas/practica-01-flyway/) | Migrar esquema existente a Flyway; añadir columna con `V2__` |
| [practica-02-projections](2-practicas/practica-02-projections/) | Consultar solo nombre + email de usuarios (sin cargar toda la entidad) |
| [practica-03-specifications](2-practicas/practica-03-specifications/) | Filtro dinámico: búsqueda por múltiples criterios opcionales |

### Proyecto (2.5h)

[📦 API de Empleados con Historial de Cambios](3-proyecto/README.md) — Sistema con esquema versionado por Flyway, filtros dinámicos vía Specifications y auditoría automática de cambios.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Flyway | 45min |
| Teoría: Projections + Specifications | 45min |
| Teoría: Auditoría JPA | 30min |
| Práctica 01: Flyway | 1h |
| Práctica 02: Projections | 1.25h |
| Práctica 03: Specifications | 1.25h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Esquema creado 100% por Flyway (sin `ddl-auto: create`)
- [ ] Al menos 2 scripts de migración (`V1__` y `V2__`)
- [ ] Endpoint que use Projection (no retorne la entidad completa)
- [ ] Endpoint de búsqueda con al menos 3 filtros opcionales via Specification
- [ ] Campos `createdAt` y `updatedAt` auditados automáticamente

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 06 — Relaciones JPA y Transacciones](../week-06-relaciones_jpa_y_transacciones/README.md) |
| ➡️ Siguiente | [Semana 08 — Arquitectura en Capas Completa](../week-08-arquitectura_capas_service_repository/README.md) |
