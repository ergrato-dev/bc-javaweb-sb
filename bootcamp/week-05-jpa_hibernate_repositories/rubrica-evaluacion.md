# Rúbrica de Evaluación — Semana 05
## JPA/Hibernate y Repositorios

---

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Descripción |
|------|-----------|-------------|
| 🧠 Conocimiento | 30% | Evaluación teórica de conceptos |
| 💪 Desempeño | 40% | Ejercicios prácticos en clase |
| 📦 Producto | 30% | Proyecto entregable funcional |

---

## 🧠 Conocimiento (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **`@Entity` y mapeo básico** | Explica `@Entity`, `@Table`, `@Id`, `@GeneratedValue(strategy=IDENTITY)`, `@Column` | Crea entidad con `@Entity` y `@Id` | No sabe qué hace `@Entity` |
| **JpaRepository** | Distingue `CrudRepository`, `JpaRepository`; usa `findById`, `findAll`, `save`, `deleteById` | `JpaRepository` con CRUD básico | No sabe qué extiende el repositorio |
| **Derived queries** | Crea `findByEmailAndActive(String email, boolean active)` sin `@Query` | Usa `findByField` de un solo campo | No sabe de derived query methods |
| **H2 vs PostgreSQL** | Configura ambos con perfiles; activa H2 Console; sabe por qué no usar H2 en prod | Configura H2 y ve datos en console | Solo usa H2, ignora PostgreSQL |
| **`Pageable` y `Page<T>`** | Usa `findAll(Pageable)`, retorna `Page<T>` con metadata de paginación | Paginación funcionando con parámetros básicos | Sin paginación en listados |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Entidades persistidas** | Datos se guardan y recuperan correctamente de la BD | CRUD básico funcionando | `INSERT` no persiste datos |
| **H2 Console** | Muestra tablas generadas, inserta datos manualmente, verifica con SELECT | H2 Console abierta, tablas visibles | No puede abrir H2 Console |
| **@Query JPQL** | Query con `WHERE` y al menos un `JOIN` (cuando aplique) escrita en JPQL | `@Query` simple con `WHERE` funcionando | Solo derived queries |
| **Paginación funcionando** | `GET /products?page=0&size=5&sort=name,asc` retorna datos paginados | Paginación básica (sin sort) | Sin paginación |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Persistencia real** | Datos sobreviven reinicios (PostgreSQL), H2 en dev | Datos persisten con H2 | Datos en memoria (sin BD) |
| **CRUD completo** | Crear, leer uno, listar todos (paginado), editar, eliminar | Al menos 4 operaciones | Solo 2 operaciones |
| **Flyway o ddl-auto: validate** | No usa `ddl-auto: create` o `update` en producción | `ddl-auto: create-drop` en dev (aceptable) | `ddl-auto: create` en prod |
| **Docker Compose para PostgreSQL** | `docker compose up` levanta PostgreSQL listo para conectar | PostgreSQL corriendo de alguna forma | Solo H2, sin PostgreSQL |

---

## 📏 Escala de Calificación

| Nota | Rango |
|------|-------|
| A | 90–100% |
| B | 80–89% |
| C | 70–79% |
| D | <70% |

---

## ✅ Criterios de Aprobación

- Mínimo **70%** en cada tipo de evidencia
- Datos persisten en BD (no en memoria)
- H2 Console funcional en perfil `dev`
- `mvn spring-boot:run` sin `HibernateException`
- Entrega puntual (penalización del 10% por día de retraso)
