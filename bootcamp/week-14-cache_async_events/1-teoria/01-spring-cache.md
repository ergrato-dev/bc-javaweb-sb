# Spring Cache — @Cacheable, @CacheEvict y Redis

## ¿Qué es el Caché de Aplicación?

El caché almacena resultados costosos (consultas a BD, cálculos, llamadas externas) en memoria para servir peticiones futuras sin recalcular. Spring Cache proporciona una abstracción que funciona con múltiples backends (Map en memoria, Redis, Caffeine, etc.).

```
Sin caché:     Request → Service → DB (50ms) → Response
Con caché:     Request → Service → Cache HIT → Response (0.5ms)  ← 100x más rápido
```

## Configuración en Spring Boot

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<!-- Para Redis (producción) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

```java
// Habilitar el caché en la aplicación
@SpringBootApplication
@EnableCaching  // ← indispensable
public class Application { ... }
```

```yaml
# application.yml — caché simple en memoria (dev)
spring:
  cache:
    type: simple  # ConcurrentHashMap

# application-prod.yml — Redis en producción
spring:
  cache:
    type: redis
  data:
    redis:
      host: ${REDIS_HOST:localhost}
      port: 6379
```

## Anotaciones Principales

### `@Cacheable` — Devuelve resultado cacheado

```java
@Service
public class ProductService {

    // El resultado se cachea con la key = id
    // En llamadas siguientes con el mismo id, NO se ejecuta el método
    @Cacheable(value = "products", key = "#id")
    public ProductResponse findById(Long id) {
        // Este log NO aparece si hay hit en caché
        log.info("Consultando BD para producto {}", id);
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    // Cache con condition: solo cachear si el stock > 0
    @Cacheable(value = "products", key = "#sku", condition = "#result != null")
    public ProductResponse findBySku(String sku) {
        return productRepository.findBySku(sku)
                .map(this::toResponse)
                .orElse(null);
    }
}
```

### `@CacheEvict` — Invalida el caché

```java
// Al actualizar o eliminar, el caché debe invalidarse
@CacheEvict(value = "products", key = "#id")
public ProductResponse update(Long id, ProductUpdateRequest request) {
    // La entrada con key=#id es eliminada del caché después de ejecutar
    var product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    product.updateDetails(request.name(), request.price(), request.category());
    return toResponse(productRepository.save(product));
}

// Vaciar TODO el caché de una región
@CacheEvict(value = "products", allEntries = true)
public void delete(Long id) {
    productRepository.deleteById(id);
}
```

### `@CachePut` — Actualiza sin saltarse el método

```java
// A diferencia de @Cacheable, SIEMPRE ejecuta el método pero actualiza el caché
@CachePut(value = "products", key = "#result.id()")
public ProductResponse create(ProductCreateRequest request) {
    var product = toEntity(request);
    return toResponse(productRepository.save(product));
}
```

## Estrategia de Caché por Caso de Uso

| Operación | Anotación | Comportamiento |
|-----------|-----------|----------------|
| GET por ID | `@Cacheable` | Si hit → devuelve caché. Si miss → consulta BD y cachea |
| POST | `@CachePut` | Siempre ejecuta, guarda resultado en caché |
| PUT / PATCH | `@CacheEvict` + `@CachePut` | Primero invalida, luego actualiza |
| DELETE | `@CacheEvict` | Invalida la entrada |

## Evitar Problemas Comunes

```java
// ❌ MAL — self-invocation: el caché NO funciona si el método se llama desde la misma clase
@Service
public class ProductService {
    public void doSomething() {
        this.findById(1L); // ← NO pasa por el proxy de Spring, el caché se ignora
    }

    @Cacheable("products")
    public ProductResponse findById(Long id) { ... }
}

// ✅ BIEN — inyectar el propio bean o extraer a otro servicio
@Service
public class ProductService {
    @Autowired
    private ProductService self; // ← Spring inyecta el proxy

    public void doSomething() {
        self.findById(1L); // ← pasa por el proxy, el caché funciona
    }

    @Cacheable("products")
    public ProductResponse findById(Long id) { ... }
}
```

## ✅ Checklist de Verificación

- [ ] `@EnableCaching` en la clase principal o en una clase `@Configuration`
- [ ] `spring-boot-starter-cache` en el pom.xml
- [ ] `@CacheEvict` en todos los métodos de escritura para mantener consistencia
- [ ] `application.yml` con `spring.cache.type: simple` para desarrollo
- [ ] Tests verifican que el repositorio se llama UNA sola vez (caché activo)
