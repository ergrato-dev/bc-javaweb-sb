# Semana 07 — Flyway y Queries Avanzadas

> Gestiona el schema de base de datos con migraciones versionadas,
> construye filtros dinámicos con Specifications y optimiza queries complejas.

---

## 🎯 Objetivos

- Gestionar el schema de BD con migraciones versionadas de Flyway
- Crear seeds de datos con Flyway
- Construir queries dinámicas con JPA Specifications para filtros complejos
- Escribir queries JPQL avanzadas con agregaciones y subconsultas
- Usar `@EntityGraph` como alternativa limpia a `JOIN FETCH`
- Identificar y resolver el problema N+1 con `@EntityGraph`

---

## 📚 Requisitos Previos

- Semana 06: Relaciones JPA, `@Transactional` ✅
- SQL intermedio: JOIN, GROUP BY, subconsultas ✅

---

## 🗂️ Estructura

```
week-07-flyway_y_queries_avanzadas/
├── 1-teoria/
│   ├── 01-flyway-migraciones.md
│   ├── 02-specifications-y-filtros-dinamicos.md
│   └── 03-queries-avanzadas-y-optimizacion.md
├── 2-practicas/
│   ├── practica-01-flyway/
│   └── practica-02-specifications/
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
| [01-flyway-migraciones.md](1-teoria/01-flyway-migraciones.md) | Flyway, migraciones `V1__`, `V2__`, ciclo de vida, seeds de datos |
| [02-specifications-y-filtros-dinamicos.md](1-teoria/02-specifications-y-filtros-dinamicos.md) | `Specification<T>`, `JpaSpecificationExecutor`, filtros dinámicos sin `if-else` |
| [03-queries-avanzadas-y-optimizacion.md](1-teoria/03-queries-avanzadas-y-optimizacion.md) | JPQL con agregaciones, `@EntityGraph`, resolver N+1, `@NamedEntityGraph` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-flyway](2-practicas/practica-01-flyway/) | Crear migraciones `V1__` → `V3__` y seed de datos inicial |
| [practica-02-specifications](2-practicas/practica-02-specifications/) | Filtros dinámicos por nombre, precio y categoría con `Specification` |

### Proyecto (2.5h)

[📦 Hospital Appointment API](3-proyecto/README.md) — API de citas hospitalarias con Flyway para el schema, Specifications para búsqueda de médicos por especialidad/disponibilidad y `@EntityGraph` para evitar N+1 en citas con paciente+médico.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Flyway migraciones | 40min |
| Teoría: Specifications | 40min |
| Teoría: Queries avanzadas + N+1 | 40min |
| Práctica 01: Flyway | 1.5h |
| Práctica 02: Specifications | 2h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Migraciones Flyway versionadas (`V1__`, `V2__`) aplicadas automáticamente al arrancar
- [ ] Al menos un script de seed de datos con Flyway
- [ ] Endpoint de búsqueda con al menos 3 filtros dinámicos opcionales usando `Specification`
- [ ] `@EntityGraph` eliminando N+1 en al menos una relación
- [ ] Tests `@DataJpaTest` verificando las queries

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 06 — JPA Relaciones y Transacciones](../week-06-jpa_relaciones_y_transacciones/README.md) |
| ➡️ Siguiente | [Semana 08 — Arquitectura en Capas](../week-08-arquitectura_en_capas/README.md) |
