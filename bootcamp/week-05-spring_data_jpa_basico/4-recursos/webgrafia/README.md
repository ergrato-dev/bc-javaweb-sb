# Webgrafía — Semana 05: Spring Data JPA Básico

---

## 1. Spring Data JPA — Documentación Oficial

| Recurso | Descripción |
|---------|-------------|
| [Spring Data JPA — Reference](https://docs.spring.io/spring-data/jpa/reference/jpa.html) | Documentación oficial: repositorios, queries, paginación |
| [JpaRepository Javadoc](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html) | Todos los métodos heredados con firmas completas |
| [Derived Query Methods](https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html) | Tabla completa de keywords: `findBy`, `countBy`, `existsBy`, comparadores |
| [Pageable y Page](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html) | API de paginación y ordenamiento |

---

## 2. Jakarta Persistence (JPA)

| Recurso | Descripción |
|---------|-------------|
| [Jakarta Persistence Spec](https://jakarta.ee/specifications/persistence/3.1/) | Especificación completa de JPA 3.1 (referencia oficial) |
| [Hibernate ORM User Guide](https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html) | Guía completa de Hibernate 6 (implementación JPA en Spring Boot) |
| [Hibernate Annotations](https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html#entity) | Todas las anotaciones de entidad: `@Entity`, `@Column`, `@Table` |

---

## 3. JPQL y Queries Avanzadas

| Recurso | Descripción |
|---------|-------------|
| [JPQL Reference](https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html#hql) | Sintaxis completa de JPQL / HQL |
| [Baeldung — @Query en Spring Data](https://www.baeldung.com/spring-data-jpa-query) | `@Query` con JPQL, named params, nativeQuery |
| [Baeldung — @Modifying](https://www.baeldung.com/spring-data-jpa-modifying-annotation) | UPDATE/DELETE con `@Modifying` y `@Transactional` |
| [Baeldung — Spring Data Sort y Order](https://www.baeldung.com/spring-data-sorting) | `Sort`, `PageRequest`, `Pageable` con ejemplos |

---

## 4. Testing con @DataJpaTest

| Recurso | Descripción |
|---------|-------------|
| [Spring Boot — @DataJpaTest](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications.autoconfigured-jpa) | Documentación oficial del slice de test JPA |
| [Baeldung — @DataJpaTest](https://www.baeldung.com/spring-boot-testing#datajpatest-integration-test) | `TestEntityManager`, rollback automático, ejemplos prácticos |
| [AssertJ Core Docs](https://assertj.github.io/doc/) | API completa de AssertJ para assertions fluidas en tests |

---

## 5. H2 y PostgreSQL

| Recurso | Descripción |
|---------|-------------|
| [H2 Console Guide](https://www.h2database.com/html/tutorial.html) | Tutorial de H2 en memoria, consola web |
| [Spring Boot — Embedded Database](https://docs.spring.io/spring-boot/reference/data/sql.html#data.sql.datasource.embedded) | Configuración de H2/HSQL/Derby embebidos |
| [Spring Guide — JPA con Spring Boot](https://spring.io/guides/gs/accessing-data-jpa) | Getting started oficial: entidad, repositorio, H2 |
