# Jakarta Bean Validation — @Valid y Constraints

## 🎯 Objetivos
- Validar inputs de API con anotaciones estándar
- Crear validadores personalizados
- Capturar errores de validación globalmente

---

## 1. Dependencia

```xml
<!-- Incluido en spring-boot-starter-web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## 2. Constraints Estándar

```java
public record EmployeeRequest(
    @NotBlank(message = "Name is required")
    String name,

    @Email(message = "Must be a valid email address")
    @NotBlank(message = "Email is required")
    String email,

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 65, message = "Age must be at most 65")
    int age,

    @Positive(message = "Salary must be positive")
    double salary,

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number")
    String phone,

    @NotNull(message = "Department is required")
    String department,

    @Size(min = 1, max = 3, message = "Between 1 and 3 skills required")
    List<String> skills
) {}
```

| Anotación | Valida |
|-----------|--------|
| `@NotNull` | No sea `null` |
| `@NotBlank` | No sea `null` ni vacío (ignora espacios) |
| `@NotEmpty` | No sea `null` ni vacío (no ignora espacios) |
| `@Size(min, max)` | Longitud de String, Collection, array |
| `@Min` / `@Max` | Valor numérico mínimo / máximo |
| `@Email` | Formato email válido |
| `@Pattern(regexp)` | Coincide con expresión regular |
| `@Positive` | Número > 0 |
| `@PositiveOrZero` | Número >= 0 |
| `@Future` / `@Past` | Fecha en el futuro / pasado |

---

## 3. Activar Validación con `@Valid`

```java
@PostMapping
public ResponseEntity<EmployeeResponse> create(
        @Valid @RequestBody EmployeeRequest request) {
    // Si la validación falla → MethodArgumentNotValidException → 400
    var created = employeeService.create(request);
    return ResponseEntity.created(URI.create("/api/employees/" + created.id())).body(created);
}
```

---

## 4. Capturar Errores de Validación

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, Object> handleValidation(MethodArgumentNotValidException ex,
                                          HttpServletRequest request) {
        // Extraer mensajes de error por campo
        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "invalid",
                        (a, b) -> a + ", " + b  // merge si hay múltiples errores por campo
                ));

        return Map.of(
            "status",  400,
            "error",   "Validation Failed",
            "errors",  errors,
            "path",    request.getRequestURI()
        );
    }
}
```

Respuesta:
```json
{
  "status": 400,
  "error": "Validation Failed",
  "errors": {
    "email": "Must be a valid email address",
    "age": "Age must be at least 18"
  },
  "path": "/api/employees"
}
```

---

## 5. Validación en Path Variables y Query Params

```java
@Validated  // requerido en la clase para validar @PathVariable/@RequestParam
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public EmployeeResponse getById(
            @PathVariable @Positive(message = "ID must be positive") Long id) {
        return employeeService.findById(id);
    }
}
```

---

## ✅ Checklist
- [ ] `spring-boot-starter-validation` en pom.xml
- [ ] `@Valid` en `@RequestBody` en todos los endpoints POST/PUT
- [ ] `@NotBlank` en Strings (no `@NotNull` — permite strings vacíos)
- [ ] Handler para `MethodArgumentNotValidException` en `GlobalExceptionHandler`
- [ ] Mensajes de error descriptivos en las anotaciones
