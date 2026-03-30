# Glosario — Semana 07: Flyway y Queries Avanzadas

---

## C

**Checksum (Flyway)**
Hash MD5 calculado sobre el contenido de cada script de migración. Flyway lo verifica al arrancar — si el checksum cambia, la app no inicia. Por eso las migraciones son inmutables.

---

## E

**`@EntityGraph`**
Alternativa declarativa a `JOIN FETCH` en `@Query`. Define qué relaciones cargar eager para una consulta específica, sin afectar la estrategia global de la entidad.

---

## F

**Flyway**
Herramienta de migración de base de datos para Java. Aplica scripts SQL versionados de forma incremental y registra el historial en `flyway_schema_history`.

**`flyway_schema_history`**
Tabla interna creada por Flyway para rastrear qué migraciones se han ejecutado: versión, descripción, checksum, fecha de ejecución, resultado.

---

## J

**`JpaSpecificationExecutor<T>`**
Interfaz de Spring Data que agrega el método `findAll(Specification, Pageable)` a un repositorio, habilitando queries dinámicas con Criteria API.

---

## M

**Migración SQL (Flyway)**
Script SQL con nomenclatura `V{version}__{descripcion}.sql`. Se ejecuta UNA sola vez en orden ascendente de versión. Una vez aplicado, es inmutable.

---

## N

**Nomenclatura Flyway**
- `V{n}__{desc}.sql` — versioned migration (se ejecuta una vez)
- `R__{desc}.sql` — repeatable migration (se re-ejecuta cuando cambia el checksum)
- `U{n}__{desc}.sql` — undo migration (requiere Flyway Teams)

---

## R

**Repeatable Migration (`R__`)**
Migración de Flyway sin versión. Se re-ejecuta cada vez que su contenido cambia. Útil para views, stored procedures o seed data de desarrollo.

---

## S

**`Specification<T>`**
Interfaz de Spring Data que encapsula un predicado JPA Criteria (`Predicate`). Se combinan con `.and()` / `.or()` / `.not()` para construir queries dinámicas.

---

## V

**Versioned Migration (`V__`)**
Migración de Flyway con número de versión. Se aplica una sola vez en orden numérico. El número puede ser entero, decimal o timestamp: `V1__`, `V1.1__`, `V20250115__`.

**`validate`**
Valor de `ddl-auto` que hace que Hibernate verifique que el schema de BD coincide con las entidades JPA. No modifica el schema — lo hace Flyway.
