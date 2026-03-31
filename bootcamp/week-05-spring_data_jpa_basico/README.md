# Semana 05 — Spring Data JPA: Entidades y Repositorios

> Conecta tu API a una base de datos real: mapea entidades JPA,
> persiste datos con `JpaRepository` y consulta con JPQL y paginación.

---

## 🎯 Objetivos

- Mapear una clase Java como entidad JPA con `@Entity`, `@Id`, `@GeneratedValue`
- Usar `JpaRepository<T, ID>` para CRUD completo sin SQL
- Configurar H2 para desarrollo y PostgreSQL para producción
- Escribir consultas custom con `@Query` y JPQL
- Implementar paginación y ordenamiento con `Pageable`
- Testear repositorios con `@DataJpaTest`

---

## 📚 Requisitos Previos

- Semana 04: Validación, DTOs, OpenAPI ✅
- Conceptos básicos de bases de datos relacionales ✅
- SQL básico: SELECT, INSERT, UPDATE, DELETE ✅

---

## 🗂️ Estructura

```
week-05-spring_data_jpa_basico/
├── 0-assets/
│   ├── 01-jpa-entity-mapping.svg
│   ├── 02-repository-methods.svg
│   └── 03-pagination-flow.svg
├── 1-teoria/
│   ├── 01-entidades-y-repositorios-jpa.md
│   ├── 02-queries-jpql-y-paginacion.md
│   └── 03-data-jpa-test-y-testing.md
├── 2-practicas/
│   ├── practica-01-primera-entidad/
│   └── practica-02-paginacion-y-queries/
├── 3-proyecto/
│   ├── README.md
│   └── starter/
├── 4-recursos/
│   ├── ebooks-free/
│   ├── videografia/
│   └── webgrafia/
├── 5-glosario/
│   └── README.md
└── rubrica-evaluacion.md
```

---

## 📝 Contenidos

### Teoría (2h)

| Archivo | Tema |
|---------|------|
| [01-entidades-y-repositorios-jpa.md](1-teoria/01-entidades-y-repositorios-jpa.md) | `@Entity`, `@Id`, `@GeneratedValue`, `JpaRepository`, H2 vs PostgreSQL |
| [02-queries-jpql-y-paginacion.md](1-teoria/02-queries-jpql-y-paginacion.md) | `@Query` JPQL, derived query methods, `Pageable`, `Page<T>` |
| [03-data-jpa-test-y-testing.md](1-teoria/03-data-jpa-test-y-testing.md) | `@DataJpaTest`, H2 en memoria, verificar queries custom |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-primera-entidad](2-practicas/practica-01-primera-entidad/) | Crear entidad `Product` persistida en H2 con CRUD completo |
| [practica-02-paginacion-y-queries](2-practicas/practica-02-paginacion-y-queries/) | Paginación con `Pageable` y queries JPQL custom |

### Proyecto (2.5h)

[📦 API de Biblioteca](3-proyecto/README.md) — CRUD de libros y autores persistido en PostgreSQL con Spring Data JPA, Flyway para migraciones iniciales y tests con `@DataJpaTest`.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Entidades y repositorios | 45min |
| Teoría: JPQL y paginación | 45min |
| Teoría: @DataJpaTest | 30min |
| Práctica 01: Primera entidad | 1.5h |
| Práctica 02: Paginación y queries | 2h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Entidad JPA persistida con H2 en desarrollo y PostgreSQL en producción
- [ ] CRUD completo usando `JpaRepository`
- [ ] Al menos una query JPQL custom con `@Query`
- [ ] Paginación implementada en al menos un endpoint de listado
- [ ] Tests de repositorio con `@DataJpaTest` pasando

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 04 — Validación, DTOs y OpenAPI](../week-04-validacion_dtos_openapi/README.md) |
| ➡️ Siguiente | [Semana 06 — JPA Relaciones y Transacciones](../week-06-jpa_relaciones_y_transacciones/README.md) |
