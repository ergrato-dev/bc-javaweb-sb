# Glosario — Semana 13: Testcontainers e Integración

## A

**`@AutoConfigureTestDatabase(replace = NONE)`**
Anotación que le dice a Spring que NO reemplace el DataSource configurado con H2. Indispensable al usar `@DataJpaTest` con Testcontainers, para que el test use el contenedor de PostgreSQL real en lugar de H2.

```java
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest { ... }
```

---

## C

**`@Container`**
Anotación de Testcontainers que marca un campo como contenedor Docker a gestionar. Si el campo es `static`, el contenedor se inicia una sola vez para toda la clase de test (más eficiente). Si es de instancia, se inicia/detiene por cada test.

```java
@Container
static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");
```

---

## D

**`@DataJpaTest`**
Slice de test de Spring que carga solo la capa JPA (repositorios, entidades, configuración de BD). NO carga controllers ni services. Por defecto usa H2; con `@AutoConfigureTestDatabase(replace=NONE)` usa la BD que configures (Testcontainers).

---

## S

**`@ServiceConnection`**
Anotación de Spring Boot 3.1+ que lee la conexión de un contenedor Testcontainers y configura automáticamente el DataSource. Elimina la necesidad de `@DynamicPropertySource`.

```java
@Container
@ServiceConnection
static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");
```

**`@SpringBootTest`**
Anotación que carga el contexto completo de Spring para tests de integración. Con `webEnvironment = RANDOM_PORT` levanta un servidor HTTP real en un puerto aleatorio para usar con `TestRestTemplate`.

---

## T

**`TestEntityManager`**
Wrapper de `EntityManager` diseñado para tests `@DataJpaTest`. Ofrece métodos convenientes como `persistAndFlush()` y `find()` para preparar datos de test y verificar el estado de la BD.

**`TestRestTemplate`**
Cliente HTTP diseñado para tests `@SpringBootTest`. Hace llamadas reales al servidor levantado por Spring Boot en el test. Similar a `RestTemplate` pero adaptado para pruebas (no lanza excepciones en 4xx/5xx).

```java
ResponseEntity<ProductResponse> response =
    restTemplate.getForEntity("/api/products/1", ProductResponse.class);
assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
```

**Testcontainers**
Librería Java que permite levantar contenedores Docker desde código de test. Garantiza que los tests usen el mismo motor de BD (PostgreSQL) que producción, eliminando las diferencias de comportamiento con H2.

**`@Testcontainers`**
Anotación a nivel de clase que activa la extensión de JUnit 5 de Testcontainers. Hace que los contenedores marcados con `@Container` sean gestionados automáticamente (start/stop).

**`@Transactional` (en tests)**
En tests con `@DataJpaTest`, Spring aplica `@Transactional` por defecto: cada test corre en una transacción que se **revierte** al terminar (rollback automático), dejando la BD limpia para el siguiente test.

---

## W

**`WebEnvironment.RANDOM_PORT`**
Configuración de `@SpringBootTest` que levanta un servidor HTTP real en un puerto aleatorio. Permite tests de integración end-to-end con `TestRestTemplate` sin conflictos de puertos entre tests paralelos.
