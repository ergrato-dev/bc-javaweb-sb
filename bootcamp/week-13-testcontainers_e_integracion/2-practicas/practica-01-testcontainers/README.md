# Práctica 1: Testcontainers Setup

## 🎯 Objetivo

Configurar Testcontainers con PostgreSQL y ejecutar el primer test de integración real.

---

## Requisito

Docker debe estar corriendo:
```bash
docker ps  # debe listar contenedores (aunque sea vacío)
```

---

## Paso 1: Agregar dependencias

Para usar Testcontainers en un proyecto Spring Boot 3.1+:

```xml
<!-- En pom.xml, en <dependencies> -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-testcontainers</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
<!-- Driver PostgreSQL (no H2) -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

**Abre `starter/TestcontainersApp.java`** y descomenta `PASO 1`.

---

## Paso 2: Configurar el contenedor

```java
@Container
@ServiceConnection
static PostgreSQLContainer<?> postgres =
    new PostgreSQLContainer<>("postgres:17-alpine");
```

`@ServiceConnection` hace que Spring Boot configure automáticamente el DataSource con la URL del contenedor.

**Descomenta `PASO 2`**.

---

## Paso 3: Primer test con DB real

After @ServiceConnection, los tests pueden usar el repositorio normalmente:

```java
@Test
void save_persistsToRealPostgres() {
    var product = productRepository.save(new Product("Widget", "SKU-001", 9.99));
    assertThat(product.getId()).isNotNull();
    assertThat(productRepository.findById(product.getId())).isPresent();
}
```

**Descomenta `PASO 3`** y ejecuta con `mvn test`. Observa en los logs cómo Docker descarga y levanta el contenedor de PostgreSQL.

---

## Paso 4: @DataJpaTest con Testcontainers

`@DataJpaTest` es más rápido que `@SpringBootTest` para testear solo repositorios:

```java
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Container @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");
    // ...
}
```

**Descomenta `PASO 4`** y compara el tiempo de ejecución con el PASO 3.

