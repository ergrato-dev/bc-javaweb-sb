# Glosario — Semana 05: Spring Data JPA Básico

---

## C

**`@Column`**
Anotación JPA para personalizar la columna de base de datos: nombre, nullable, longitud, precisión.

**`create-drop`**
Valor de `ddl-auto` que crea el schema al iniciar y lo elimina al parar. Ideal para desarrollo con H2.

---

## D

**`@DataJpaTest`**
Slice de test de Spring Boot que carga solo el contexto JPA: entidades, repositorios, H2 y TransactionManager. No carga servicios ni controllers.

**DDL-Auto**
Propiedad `spring.jpa.hibernate.ddl-auto` que controla cómo Hibernate gestiona el schema:
- `create-drop`: crea al inicio, elimina al parar
- `validate`: solo valida que el schema coincide (producción con Flyway)
- `update`: modifica tablas existentes (peligroso en producción)

**Derived Query Methods**
Métodos en repositorios JPA cuyo nombre sigue convenciones que Spring Data analiza para generar SQL automáticamente. Ej: `findByCategory(String c)` → `WHERE category = ?`.

---

## E

**`@Entity`**
Anotación JPA que marca una clase Java como entidad persistible — será mapeada a una tabla relacional.

---

## G

**`GenerationType.IDENTITY`**
Estrategia de generación de ID que delega al motor de BD (autoincrement en PostgreSQL/MySQL). El más común en Spring Boot.

---

## H

**H2 Console**
Interfaz web integrada en H2 para explorar datos en memoria. Disponible en `/h2-console`. Solo para desarrollo.

**Hibernate**
Implementación de JPA usada por Spring Boot por defecto. Traduce operaciones JPA a SQL específico del motor.

---

## I

**`@Id`**
Anotación JPA que marca el campo como clave primaria de la entidad.

---

## J

**`JpaRepository<T, ID>`**
Interfaz de Spring Data JPA que provee CRUD completo + paginación + ordenamiento. Extender esta interfaz crea un repositorio completo sin implementar nada.

**JPQL (Java Persistence Query Language)**
Lenguaje de consulta orientado a objetos. Opera sobre entidades (clases Java), no sobre tablas. Independiente del motor de BD.

---

## M

**`@Modifying`**
Anotación necesaria en `@Query` para operaciones UPDATE/DELETE. Sin ella, Spring lanza una excepción.

---

## P

**`Page<T>`**
Resultado paginado que incluye: contenido (lista), número de página, tamaño, total de elementos y total de páginas.

**`Pageable`**
Interfaz que encapsula información de paginación: número de página, tamaño, y ordenamiento. Spring MVC lo inyecta automáticamente desde parámetros de query.

**`@Param`**
Anotación que vincula un parámetro del método con un parámetro nombrado en la consulta JPQL (`:paramName`).

---

## Q

**`@Query`**
Anotación para definir consultas JPQL o SQL nativo. Usada cuando los Derived Query Methods son insuficientes o demasiado verbosos.

---

## T

**`@Table`**
Anotación JPA opcional para especificar el nombre de la tabla, schema o catálogo.

**`TestEntityManager`**
Utilidad de `@DataJpaTest` para operaciones JPA de bajo nivel en tests: `persist()`, `flush()`, `refresh()`. Evita depender del repositorio para preparar datos.

**`@Transactional`**
Marca un método o clase para ejecutarse dentro de una transacción ACID. Spring hace commit al terminar o rollback si hay excepción no verificada.

**`@Transactional(readOnly = true)`**
Optimización para operaciones de solo lectura: le dice al proveedor JPA (Hibernate) que no necesita hacer flush del contexto de persistencia. Mejor rendimiento.
