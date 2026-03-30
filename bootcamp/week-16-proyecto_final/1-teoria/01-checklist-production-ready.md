# Checklist Production-Ready

> Una API "production-ready" no es solo código que funciona — es código que
> se puede mantener, proteger, escalar y desplegar de forma confiable.

---

## 🔐 1. Seguridad

### Autenticación y Autorización
```java
// ✅ BCrypt para contraseñas — factor de coste recomendado: 12
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
}

// ✅ JWT con expiración corta + refresh token
// Access token: 15 min — 1 hora
// Refresh token: 7 — 30 días (almacenado en BD o cookie HttpOnly)

// ✅ Endpoints protegidos por rol con @PreAuthorize
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/admin/users")
public ResponseEntity<UserResponse> createAdmin(@RequestBody @Valid UserCreateRequest req) { ... }
```

### CORS configurado correctamente
```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    var config = new CorsConfiguration();
    // ❌ MAL: config.setAllowedOrigins(List.of("*")) con credenciales
    config.setAllowedOrigins(List.of("https://mi-frontend.com"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
    config.setAllowCredentials(true);
    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/**", config);
    return source;
}
```

### Nunca exponer entidades JPA directamente
```java
// ❌ MAL — expone la entidad (incluye password, relaciones lazy, etc.)
@GetMapping("/{id}")
public User getUser(@PathVariable Long id) { return userRepository.findById(id).orElseThrow(); }

// ✅ BIEN — DTO sin campos sensibles
@GetMapping("/{id}")
public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
}
```

---

## 🗄️ 2. Persistencia

### Flyway en producción (nunca `ddl-auto: create`)
```yaml
# application-prod.yml
spring:
  jpa:
    hibernate:
      ddl-auto: validate   # ✅ solo valida el esquema
  flyway:
    enabled: true
    locations: classpath:db/migration
```

### Evitar N+1 con `@EntityGraph` o `JOIN FETCH`
```java
// ❌ N+1: por cada Task, Hibernate hace una query extra para cargar Project
taskRepository.findAll().forEach(t -> System.out.println(t.getProject().getName()));

// ✅ Una sola query con JOIN FETCH
@Query("SELECT t FROM Task t JOIN FETCH t.project WHERE t.status = :status")
List<Task> findByStatusWithProject(@Param("status") TaskStatus status);
```

### Paginación en listados
```java
// ✅ Siempre paginar — nunca retornar listas sin límite
@GetMapping
public ResponseEntity<Page<TaskResponse>> getTasks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
    return ResponseEntity.ok(taskService.findAll(PageRequest.of(page, size)));
}
```

---

## ✅ 3. Validación y Manejo de Errores

### Validación en capa de entrada
```java
public record CreateTaskRequest(
    @NotBlank @Size(max = 200) String title,
    @NotNull Long projectId,
    @NotNull TaskPriority priority,
    @Email String assigneeEmail    // opcional
) {}

@PostMapping
public ResponseEntity<TaskResponse> create(@RequestBody @Valid CreateTaskRequest req) { ... }
```

### GlobalExceptionHandler consistente
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", errors.toString()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorResponse("NOT_FOUND", ex.getMessage()));
    }
}
```

---

## 📖 4. Documentación (Swagger UI)

```java
@Operation(
    summary = "Create task",
    description = "Creates a new task in a project. Only project members can create tasks."
)
@ApiResponse(responseCode = "201", description = "Task created")
@ApiResponse(responseCode = "400", description = "Invalid input")
@ApiResponse(responseCode = "403", description = "Not a project member")
@ApiResponse(responseCode = "404", description = "Project not found")
@PostMapping
public ResponseEntity<TaskResponse> createTask(@RequestBody @Valid CreateTaskRequest req) { ... }
```

**Swagger UI accesible en:** `http://localhost:8080/swagger-ui.html`

---

## 🧪 5. Tests

### Pirámide de tests mínima

| Tipo | Anotación | Qué testa |
|------|-----------|-----------|
| Unitario | `@ExtendWith(MockitoExtension)` | Lógica de servicio |
| Slice | `@WebMvcTest` | Controllers (serialización, status codes, seguridad) |
| Slice | `@DataJpaTest` | Queries JPA personalizadas |
| Integración | `@SpringBootTest` + Testcontainers | Flujo end-to-end real con PostgreSQL |

### Cobertura con JaCoCo
```xml
<!-- pom.xml — falla el build si coverage < 70% -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <executions>
        <execution>
            <id>check</id>
            <goals><goal>check</goal></goals>
            <configuration>
                <rules>
                    <rule>
                        <element>BUNDLE</element>
                        <limits>
                            <limit>
                                <counter>LINE</counter>
                                <value>COVEREDRATIO</value>
                                <minimum>0.70</minimum>
                            </limit>
                        </limits>
                    </rule>
                </rules>
            </configuration>
        </execution>
    </executions>
</plugin>
```

---

## 🐳 6. Docker

```dockerfile
# ✅ Multi-stage: imagen final ~250MB vs ~700MB
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -f pom.xml package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
COPY --from=build /app/target/*.jar app.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]
```

---

## 🚀 7. CI/CD

```yaml
# .github/workflows/ci.yml — Pipeline mínimo
name: CI
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    services:
      postgres:
        image: postgres:17
        env: { POSTGRES_DB: testdb, POSTGRES_USER: test, POSTGRES_PASSWORD: test }
        options: --health-cmd pg_isready
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with: { java-version: '21', distribution: 'temurin' }
      - run: mvn verify   # compila + tests + JaCoCo + chequeo de cobertura
```

---

## ✅ Checklist Final

Antes de presentar el proyecto:

- [ ] `mvn verify` pasa sin errores (tests + JaCoCo ≥ 70%)
- [ ] `docker compose up` levanta la app sin errores
- [ ] `POST /auth/register` y `POST /auth/login` funcionan
- [ ] Endpoints protegidos retornan 401 sin token
- [ ] Endpoints con rol retornan 403 si el rol no corresponde
- [ ] Swagger UI muestra todos los endpoints con `@Operation`
- [ ] Flyway: no hay `ddl-auto: create` en producción
- [ ] No hay `System.out.println` — usar SLF4J (`private static final Logger log = ...`)
- [ ] GitHub Actions: pipeline verde en `main`
- [ ] URL pública: app deployada y accesible
