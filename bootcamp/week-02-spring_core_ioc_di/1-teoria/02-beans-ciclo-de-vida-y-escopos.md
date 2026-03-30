# Spring Beans — Ciclo de Vida y Escopos

## 🎯 Objetivos
- Entender el ciclo de vida de un bean
- Usar `@PostConstruct` y `@PreDestroy`
- Conocer los escopos: singleton, prototype, request, session

---

## 1. Ciclo de Vida de un Bean

```
Container arranxa
    → Instancia el bean (new)
    → Inyecta dependencias
    → @PostConstruct (inicialización)
    → Bean listo para usar
    → ... uso ...
    → @PreDestroy (limpieza)
    → Container se cierra
```

```java
@Service
public class CacheService {

    private final Map<String, Object> cache = new HashMap<>();

    @PostConstruct
    void init() {
        // Se ejecuta una vez después de inyectar dependencias
        System.out.println("CacheService initialized — loading warm-up data");
        cache.put("config", loadConfig());
    }

    @PreDestroy
    void cleanup() {
        // Se ejecuta antes de que el container cierre
        System.out.println("CacheService shutting down — clearing cache");
        cache.clear();
    }

    private Object loadConfig() { return "default-config"; }
}
```

---

## 2. Escopos de Beans

### Singleton (por defecto)

**Una instancia** para toda la aplicación. Estado compartido — debe ser thread-safe.

```java
@Service  // singleton por defecto
public class BookService { /* ... */ }
```

### Prototype

**Nueva instancia** cada vez que se solicita el bean.

```java
@Component
@Scope("prototype")
public class ReportBuilder {
    private final List<String> lines = new ArrayList<>();

    public void addLine(String line) { lines.add(line); }
    public String build() { return String.join("\n", lines); }
}
```

### Request / Session (web)

```java
// Nueva instancia por request HTTP
@Component
@RequestScope
public class RequestContext {
    private String correlationId;
}

// Nueva instancia por sesión HTTP
@Component
@SessionScope
public class ShoppingCart {
    private final List<String> items = new ArrayList<>();
}
```

---

## 3. Inyectar Prototype en Singleton

El problema: un bean singleton que necesita un prototype — Spring solo inyecta
el prototype una vez (en la construcción del singleton).

**Solución: `ObjectProvider<T>`**

```java
@Service  // singleton
public class ReportService {

    private final ObjectProvider<ReportBuilder> builderProvider;

    public ReportService(ObjectProvider<ReportBuilder> builderProvider) {
        this.builderProvider = builderProvider;
    }

    public String generateReport(List<String> items) {
        // Creates a NEW ReportBuilder for each call
        var builder = builderProvider.getObject();
        items.forEach(builder::addLine);
        return builder.build();
    }
}
```

---

## 4. Perfiles con `@Profile`

Activar beans solo en ciertos entornos:

```java
@Configuration
@Profile("dev")
public class DevDataConfig {

    @Bean
    public DataLoader devDataLoader() {
        return new InMemoryDataLoader(); // datos ficticios en dev
    }
}

@Configuration
@Profile("prod")
public class ProdDataConfig {

    @Bean
    public DataLoader prodDataLoader() {
        return new DatabaseDataLoader(); // datos reales en prod
    }
}
```

Activar en `application.yml`:
```yaml
spring:
  profiles:
    active: dev
```

---

## 5. `@Conditional` — Beans bajo condición

```java
@Bean
@ConditionalOnProperty(name = "feature.cache.enabled", havingValue = "true")
public CacheManager cacheManager() {
    return new SimpleCacheManager();
}
```

---

## ✅ Checklist
- [ ] Entiendo que los beans son singleton por defecto
- [ ] `@PostConstruct` para inicialización, `@PreDestroy` para limpieza
- [ ] Prototype solo donde se necesita estado por instancia
- [ ] Perfiles para separar configuración por entorno
