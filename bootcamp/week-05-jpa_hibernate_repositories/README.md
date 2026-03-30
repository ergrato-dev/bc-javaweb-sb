# Semana 05 — JPA/Hibernate y Repositorios

> Persiste datos de verdad. Conecta tu API a una base de datos
> usando Spring Data JPA, entidades y repositorios.

---

## 🎯 Objetivos

- Mapear clases Java a tablas con `@Entity`, `@Table`, `@Column`
- Configurar Spring Data JPA con H2 (dev) y PostgreSQL (prod)
- Usar `JpaRepository` para operaciones CRUD sin escribir SQL
- Definir queries personalizadas con `@Query` y JPQL
- Paginar resultados con `Pageable`
- Integrar la capa de persistencia con el stack REST completo

---

## 📚 Requisitos Previos

- Semana 04: Validación, DTOs, MapStruct ✅
- SQL básico: SELECT, INSERT, UPDATE, DELETE ✅

---

## 🗂️ Estructura

```
week-05-jpa_hibernate_repositories/
├── 1-teoria/
│   ├── 01-jpa-entidades-y-mapeo.md
│   ├── 02-spring-data-jpa-repositorios.md
│   └── 03-queries-personalizadas-paginacion.md
├── 2-practicas/
│   ├── practica-01-primera-entidad/
│   ├── practica-02-jparepository-crud/
│   └── practica-03-custom-queries/
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
| [01-jpa-entidades-y-mapeo.md](1-teoria/01-jpa-entidades-y-mapeo.md) | `@Entity`, `@Id`, `@GeneratedValue`, `@Column`, tipos de columna |
| [02-spring-data-jpa-repositorios.md](1-teoria/02-spring-data-jpa-repositorios.md) | `JpaRepository`, `CrudRepository`, `findBy*`, H2 console, PostgreSQL |
| [03-queries-personalizadas-paginacion.md](1-teoria/03-queries-personalizadas-paginacion.md) | `@Query`, JPQL, `Pageable`, `Page<T>`, `Sort` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-primera-entidad](2-practicas/practica-01-primera-entidad/) | Crear entidad `Product` y verla en la consola H2 |
| [practica-02-jparepository-crud](2-practicas/practica-02-jparepository-crud/) | CRUD completo con `JpaRepository` y endpoints REST |
| [practica-03-custom-queries](2-practicas/practica-03-custom-queries/) | Búsquedas por nombre, precio y paginación |

### Proyecto (2.5h)

[📦 API de Biblioteca con Persistencia](3-proyecto/README.md) — CRUD completo de libros con H2 en dev, PostgreSQL en prod (Docker Compose), paginación y búsqueda personalizada.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: JPA y entidades | 45min |
| Teoría: Spring Data JPA | 45min |
| Teoría: Queries y paginación | 30min |
| Práctica 01: Primera entidad + H2 | 1h |
| Práctica 02: CRUD con JpaRepository | 1.25h |
| Práctica 03: Custom queries | 1.25h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Entidad persistida en H2 (dev) y PostgreSQL (prod)
- [ ] CRUD completo via endpoints REST + base de datos
- [ ] Al menos una query JPQL con `@Query`
- [ ] Paginación funcionando (`/books?page=0&size=10&sort=title`)
- [ ] H2 Console accesible en dev: `/h2-console`

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 04 — Validación, DTOs y OpenAPI](../week-04-validacion_dtos_openapi/README.md) |
| ➡️ Siguiente | [Semana 06 — Relaciones JPA y Transacciones](../week-06-relaciones_jpa_y_transacciones/README.md) |
