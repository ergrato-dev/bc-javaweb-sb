# Configuración con `@Value`, Environment y Profiles

## 🎯 Objetivos  
- Externaliazar configuración con `@Value` y `@ConfigurationProperties`
- Gestionar múltiples perfiles (`dev`, `prod`)
- Leer propiedades de forma segura y tipada

---

![Jerarquía de configuración y @Value vs @ConfigurationProperties](../0-assets/03-properties-binding.svg)

## 1. `application.yml` — Estructura

Spring Boot lee configuración de `src/main/resources/application.yml`:

```yaml
# application.yml — configuración base
server:
  port: 8080

spring:
  application:
    name: library-api
  profiles:
    active: dev   # perfil activo por defecto

app:
  name: "Library API"
  version: "1.0.0"
  max-books-per-user: 5
  features:
    late-fees: true
    reservations: false
```

---

## 2. `@Value` — Inyección directa

```java
@Component
public class AppInfo {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String version;

    @Value("${app.max-books-per-user:3}")  // 3 = valor por defecto
    private int maxBooksPerUser;

    @Value("${app.features.late-fees}")
    private boolean lateFeesEnabled;
}
```

> ⚠️ `@Value` no funciona en objetos creados con `new`. Solo en beans gestionados por Spring.

---

## 3. `@ConfigurationProperties` — Configuración tipada ✅

Más robusto que `@Value` para agrupar propiedades relacionadas:

```java
@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String name,
        String version,
        int maxBooksPerUser,
        Features features
) {
    public record Features(boolean lateFees, boolean reservations) {}
}
```

Habilitar en la clase principal o en `@Configuration`:

```java
@SpringBootApplication
@ConfigurationPropertiesScan  // escanea @ConfigurationProperties
public class LibraryApplication {}
```

Uso:

```java
@Service
public class BookService {
    private final AppProperties props;

    public BookService(AppProperties props) {
        this.props = props;
    }

    public boolean canBorrow(User user) {
        return user.currentBorrows() < props.maxBooksPerUser();
    }
}
```

---

## 4. Perfiles — Configuración por Entorno

```yaml
# application.yml
spring:
  profiles:
    active: dev

---
# Perfil dev
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:h2:mem:devdb
    driver-class-name: org.h2.Driver

---
# Perfil prod
spring:
  config:
    activate:
      on-profile: prod
  datasource:
    url: ${DATABASE_URL}
```

O como archivos separados:
- `application-dev.yml`
- `application-prod.yml`

Activar por línea de comandos:
```bash
java -jar app.jar --spring.profiles.active=prod
# o con Docker:
SPRING_PROFILES_ACTIVE=prod java -jar app.jar
```

---

## 5. Variables de Entorno

Spring Boot convierte automáticamente variables de entorno a propiedades:

```
DATABASE_URL  →  spring.datasource.url
APP_MAX_BOOKS_PER_USER  →  app.max-books-per-user
```

```yaml
# application-prod.yml — nunca hardcodear credenciales
spring:
  datasource:
    url: ${DATABASE_URL}          # variable de entorno obligatoria
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

---

## ✅ Checklist
- [ ] Configuración externalizada en `application.yml`, nunca hardcodeada
- [ ] Credenciales en variables de entorno (`${VAR}`)
- [ ] `@ConfigurationProperties` para grupos de propiedades relacionadas
- [ ] Perfiles separados para dev/prod
- [ ] Valor por defecto con `${prop:default}` en `@Value`
