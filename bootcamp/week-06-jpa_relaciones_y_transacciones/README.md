# Semana 06 — JPA: Relaciones y Transacciones

> Modela datos complejos con relaciones entre entidades, controla
> el alcance de las transacciones y audita cambios automáticamente.

---

## 🎯 Objetivos

- Mapear relaciones `@OneToMany` / `@ManyToOne` bidireccionales
- Entender Lazy vs Eager loading y evitar el problema N+1
- Dominar `@Transactional`: propagación, aislamiento y rollback
- Implementar bloqueo optimista con `@Version`
- Auditar entidades automáticamente con `@CreatedDate`, `@LastModifiedDate`
- Extraer proyecciones con Spring Data Projections

---

## 📚 Requisitos Previos

- Semana 05: Spring Data JPA básico, `JpaRepository` ✅
- SQL: JOIN, relaciones entre tablas ✅

---

## 🗂️ Estructura

```
week-06-jpa_relaciones_y_transacciones/
├── 1-teoria/
│   ├── 01-relaciones-onetomany-manytoone.md
│   ├── 02-transacciones-y-concurrencia.md
│   └── 03-auditoria-y-projections.md
├── 2-practicas/
│   ├── practica-01-relaciones/
│   └── practica-02-transaccional-auditoria/
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
| [01-relaciones-onetomany-manytoone.md](1-teoria/01-relaciones-onetomany-manytoone.md) | `@OneToMany`, `@ManyToOne`, `cascade`, `orphanRemoval`, Lazy vs Eager, N+1 |
| [02-transacciones-y-concurrencia.md](1-teoria/02-transacciones-y-concurrencia.md) | `@Transactional`, propagación, aislamiento, `@Version` bloqueo optimista |
| [03-auditoria-y-projections.md](1-teoria/03-auditoria-y-projections.md) | `@CreatedDate`, `@LastModifiedDate`, Spring Data Projections, `@EntityListeners` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-relaciones](2-practicas/practica-01-relaciones/) | Relación `Order` → `OrderItem` con `@OneToMany` + resolver N+1 |
| [practica-02-transaccional-auditoria](2-practicas/practica-02-transaccional-auditoria/) | Servicio con `@Transactional` + auditoría automática de entidades |

### Proyecto (2.5h)

[📦 API de Blog con Relaciones](3-proyecto/README.md) — Posts con múltiples comentarios y etiquetas (`@ManyToMany`), operaciones transaccionales, auditoría de fechas y projections para respuestas resumidas.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Relaciones JPA | 45min |
| Teoría: Transacciones | 45min |
| Teoría: Auditoría y Projections | 30min |
| Práctica 01: Relaciones | 1.5h |
| Práctica 02: Transaccional + Auditoría | 2h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Relación `@OneToMany` / `@ManyToOne` funcionando con cascade correcto
- [ ] Carga lazy sin `LazyInitializationException`
- [ ] Servicio con `@Transactional` que hace rollback ante error
- [ ] Entidades con `createdAt` / `updatedAt` poblados automáticamente
- [ ] Al menos una interface Projection reduciendo campos expuestos

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 05 — Spring Data JPA Básico](../week-05-spring_data_jpa_basico/README.md) |
| ➡️ Siguiente | [Semana 07 — Flyway y Queries Avanzadas](../week-07-flyway_y_queries_avanzadas/README.md) |
