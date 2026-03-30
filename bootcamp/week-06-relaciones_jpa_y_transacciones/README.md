# Semana 06 — Relaciones JPA y Transacciones

> Modela el mundo real: entidades relacionadas, integridad de datos
> con transacciones y evita las trampas del lazy loading.

---

## 🎯 Objetivos

- Modelar relaciones `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `@OneToOne`
- Configurar `cascade` y `orphanRemoval`
- Entender y controlar `FetchType.LAZY` vs `FetchType.EAGER`
- Evitar el problema N+1 con `@EntityGraph` y `JOIN FETCH`
- Usar `@Transactional` correctamente
- Escribir JPQL con joins entre entidades relacionadas

---

## 📚 Requisitos Previos

- Semana 05: JPA, entidades, `JpaRepository` ✅
- SQL: JOINs (INNER, LEFT) ✅

---

## 🗂️ Estructura

```
week-06-relaciones_jpa_y_transacciones/
├── 1-teoria/
│   ├── 01-relaciones-onetomany-manytoone.md
│   ├── 02-manytomany-y-tablas-intermedias.md
│   └── 03-transacciones-lazy-eager.md
├── 2-practicas/
│   ├── practica-01-onetomany/
│   ├── practica-02-manytomany/
│   └── practica-03-transaccional-n1/
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
| [01-relaciones-onetomany-manytoone.md](1-teoria/01-relaciones-onetomany-manytoone.md) | `@OneToMany`, `@ManyToOne`, `mappedBy`, `cascade`, lado dueño |
| [02-manytomany-y-tablas-intermedias.md](1-teoria/02-manytomany-y-tablas-intermedias.md) | `@ManyToMany`, `@JoinTable`, tabla intermedia con atributos extra |
| [03-transacciones-lazy-eager.md](1-teoria/03-transacciones-lazy-eager.md) | `@Transactional`, `LAZY` vs `EAGER`, N+1, `@EntityGraph`, `JOIN FETCH` |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-onetomany](2-practicas/practica-01-onetomany/) | `Author` → `Book`: relación bidireccional con cascade |
| [practica-02-manytomany](2-practicas/practica-02-manytomany/) | `Student` ↔ `Course`: tabla intermedia con fecha de inscripción |
| [practica-03-transaccional-n1](2-practicas/practica-03-transaccional-n1/) | Detectar y resolver el problema N+1 con `@EntityGraph` |

### Proyecto (2.5h)

[📦 Sistema de E-commerce: Órdenes y Productos](3-proyecto/README.md) — API con `Order`, `OrderItem` y `Product`; relaciones bidireccionales, transacciones y queries optimizadas.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: OneToMany + ManyToOne | 45min |
| Teoría: ManyToMany | 45min |
| Teoría: Transacciones + N+1 | 30min |
| Práctica 01: OneToMany | 1.25h |
| Práctica 02: ManyToMany | 1.25h |
| Práctica 03: N+1 | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Al menos una relación bidireccional funcionando correctamente
- [ ] Tabla intermedia con atributo adicional (no solo IDs)
- [ ] Logs de SQL habilitados (`spring.jpa.show-sql=true`) — demostrar ausencia de N+1
- [ ] `@Transactional` en métodos de servicio que modifican datos
- [ ] `LazyInitializationException` nunca ocurre en producción

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 05 — JPA/Hibernate y Repositorios](../week-05-jpa_hibernate_repositories/README.md) |
| ➡️ Siguiente | [Semana 07 — Migraciones y Queries Avanzadas](../week-07-migraciones_y_queries_avanzadas/README.md) |
</content>
