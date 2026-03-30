# SpringDoc OpenAPI — Swagger UI

## 🎯 Objetivos
- Auto-generar documentación OpenAPI desde el código
- Agregar descripciones con `@Operation` y `@ApiResponse`
- Acceder a Swagger UI en desarrollo

---

## 1. Dependencia

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.7.0</version>
</dependency>
```

Acceso automático:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

## 2. Configuración Básica

```yaml
# application.yml
springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method
  info:
    title: Employee Management API
    version: 1.0.0
    description: API for managing employees and departments
```

O via `@Bean`:

```java
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")
                        .version("1.0.0")
                        .description("REST API for employee management")
                        .contact(new Contact().name("Team").email("dev@company.com")));
    }
}
```

---

## 3. Documentar Endpoints

```java
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees", description = "Employee management operations")
public class EmployeeController {

    @Operation(
        summary = "Get employee by ID",
        description = "Returns a single employee. Returns 404 if not found."
    )
    @ApiResponse(responseCode = "200", description = "Employee found",
                 content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @Operation(summary = "Create a new employee")
    @ApiResponse(responseCode = "201", description = "Employee created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(
            @Valid @RequestBody EmployeeRequest request) {
        var created = employeeService.create(request);
        return ResponseEntity.created(URI.create("/api/employees/" + created.id()))
                             .body(created);
    }
}
```

---

## 4. Documentar Esquemas (DTOs)

```java
@Schema(description = "Request body for creating an employee")
public record EmployeeRequest(

    @Schema(description = "Full name", example = "John Doe")
    @NotBlank
    String name,

    @Schema(description = "Work email", example = "john.doe@company.com")
    @Email @NotBlank
    String email,

    @Schema(description = "Annual salary in USD", example = "75000.00", minimum = "0")
    @Positive
    double salary
) {}
```

---

## 5. Ocultar Endpoints

```java
@Hidden  // no aparece en Swagger
@GetMapping("/internal/health-check")
public String internalCheck() { return "ok"; }
```

---

## ✅ Checklist
- [ ] springdoc-openapi-starter-webmvc-ui en pom.xml
- [ ] `@Tag` en cada controller
- [ ] `@Operation(summary)` en endpoints importantes
- [ ] `@ApiResponse` para 200, 201, 400, 404 según corresponda
- [ ] `@Schema(example = "...")` en DTOs para mejores ejemplos en Swagger UI
