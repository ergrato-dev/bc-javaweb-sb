# Spring Data JPA — Entidades y Repositorios

## 🎯 Objetivos
- Mapear una clase Java como entidad JPA con `@Entity`
- Usar `JpaRepository<T, ID>` para CRUD sin SQL
- Configurar H2 para desarrollo y PostgreSQL para producción

---

## 1. Java Persistence API (JPA)

JPA es el estándar Java para persistencia relacional. Spring Data JPA elimina el boilerplate: no escribes SQL CRUD — solo defines interfaces.

```
Entidad Java  ←→  Tabla SQL
@Entity          CREATE TABLE
@Id              PRIMARY KEY
@Column          COLUMN definition
```

---

## 2. Mapear una Entidad

```java
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "category")
    private String category;

    // JPA requiere constructor sin argumentos
    protected Product() {}

    public Product(String name, BigDecimal price, Integer stock, String category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // getters...
}
```

> ⚠️ JPA necesita el constructor `protected` vacío — no lo elimines aunque usen constructores propios.

---

## 3. JpaRepository

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
    // CRUD gratuito: findAll(), findById(), save(), deleteById()...

    // Spring Data genera el SQL automáticamente por el nombre del método:
    List<Product> findByCategory(String category);
    List<Product> findByPriceLessThan(BigDecimal maxPrice);
    boolean existsByName(String name);
    long countByCategory(String category);
}
```

**Convenciones de nomenclatura:**

| Prefijo | Método | SQL equivalente |
|---------|--------|-----------------|
| `findBy` | `findByName(String n)` | `WHERE name = ?` |
| `findBy...And...` | `findByCategoryAndStockGreaterThan` | `WHERE category = ? AND stock > ?` |
| `countBy` | `countByCategory(String c)` | `SELECT COUNT(*) WHERE category = ?` |
| `deleteBy` | `deleteByCategory(String c)` | `DELETE WHERE category = ?` |
| `existsBy` | `existsByEmail(String e)` | `SELECT EXISTS(...)` |

---

## 4. Configuración H2 (Desarrollo)

```yaml
# application-dev.yml
spring:
  datasource:
    url: jdbc:h2:mem:devdb
    driver-class-name: org.h2.Driver
  h2:
    console:
      enabled: true   # acceso en /h2-console
  jpa:
    hibernate:
      ddl-auto: create-drop   # crea tablas al arrancar, las elimina al parar
    show-sql: true
    properties:
      hibernate.format_sql: true
```

---

## 5. Configuración PostgreSQL (Producción)

```yaml
# application-prod.yml
spring:
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USER}
    password: ${DATABASE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate   # Flyway gestiona el schema, JPA solo valida
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

---

## 6. Uso en el Service

```java
@Service
@Transactional(readOnly = true)          // todas las operaciones son read-only por defecto
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional                       // override: esta operación escribe en BD
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
```

---

## ✅ Checklist de Verificación
- [ ] `@Entity` + `@Table` + `@Id` + `@GeneratedValue(IDENTITY)`
- [ ] Constructor `protected` vacío en la entidad
- [ ] Repository extiende `JpaRepository<T, ID>`
- [ ] Queries por nombre de método generadas automáticamente
- [ ] `@Transactional(readOnly = true)` en service de lectura
- [ ] `@Transactional` en métodos que escriben
