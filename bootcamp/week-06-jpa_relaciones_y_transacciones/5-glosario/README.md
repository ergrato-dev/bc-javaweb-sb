# Glosario — Semana 06: JPA Relaciones y Transacciones

---

## A

**`@AuditingEntityListener`**
Listener JPA de Spring Data que intercepta los eventos de ciclo de vida para auto-asignar `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy`.

---

## B

**Bidireccional**
Relación mapeada en ambos lados. El lado "owning" (sin `mappedBy`) controla la FK en BD. El lado "inverse" (`mappedBy`) mantiene la referencia en memoria.

---

## C

**`CascadeType`**
Propagación de operaciones JPA de entidad padre a hijos:
- `PERSIST` / `REMOVE` / `MERGE` / `REFRESH` / `DETACH` / `ALL`

---

## E

**`@EnableJpaAuditing`**
Activa el soporte de auditoría en Spring Data JPA. Se coloca en la clase de configuración o en `@SpringBootApplication`.

---

## F

**FetchType**
Estrategia de carga de relaciones:
- `LAZY`: carga bajo demanda (recomendado)
- `EAGER`: carga inmediata con JOIN

---

## J

**JOIN FETCH**
Cláusula JPQL que fuerza la carga de relaciones en la misma query. Evita el problema N+1.

---

## L

**`LazyInitializationException`**
Error que ocurre al acceder a una colección LAZY fuera de una sesión JPA activa (fuera de `@Transactional`). Solución: `@Transactional` o `JOIN FETCH`.

---

## M

**`mappedBy`**
Atributo en `@OneToMany` que indica el campo de la entidad hijo que contiene la FK. Marca el lado inverso (no-owning) de la relación.

---

## N

**N+1 Problem**
Anti-patrón donde cargar N entidades genera N consultas adicionales para sus relaciones. Se resuelve con `JOIN FETCH` o `@EntityGraph`.

---

## O

**`orphanRemoval = true`**
Si una entidad hijo es eliminada de la colección del padre, JPA la elimina automáticamente de la BD. Útil para composición (las partes no existen sin el todo).

---

## P

**Propagation (Transaccional)**
Comportamiento al invocar un método `@Transactional` desde dentro de otro método `@Transactional`:
- `REQUIRED` (default): participa en la existente o crea una nueva
- `REQUIRES_NEW`: siempre crea una nueva

---

## R

**Rollback**
Deshacer todos los cambios de una transacción. Ocurre automáticamente ante `RuntimeException` con `@Transactional`.

---

## V

**`@Version`**
Campo de control de bloqueo optimista. Hibernate verifica que la versión no ha cambiado al hacer UPDATE. Si otro proceso ya actualizó, lanza `OptimisticLockException`.
