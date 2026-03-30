# Proyecto Semana 04 — API de Gestión de Productos con Validación y Swagger

## 🎯 Descripción

Extiende la API de tareas (o inicia con productos) aplicando validación completa con Jakarta Bean Validation, DTOs con MapStruct y documentación automática con SpringDoc OpenAPI.

## 📋 Escenario

Una tienda online necesita una API para gestionar su catálogo de productos. Todos los datos de entrada deben validarse. La documentación debe generarse automáticamente.

## 🏗️ DTOs

```java
// Entrada: crear producto
public record ProductCreateRequest(
    @NotBlank @Size(min=2, max=100) String name,
    @NotBlank @Size(max=500) String description,
    @NotNull @DecimalMin("0.01") BigDecimal price,
    @NotNull @Min(0) Integer stock,
    @NotBlank String category
) {}

// Entrada: actualizar producto
public record ProductUpdateRequest(
    @Size(min=2, max=100) String name,
    @Size(max=500) String description,
    @DecimalMin("0.01") BigDecimal price,
    @Min(0) Integer stock
) {}

// Salida: respuesta
public record ProductResponse(
    Long id, String name, String description,
    BigDecimal price, Integer stock, String category,
    boolean available
) {}
```

## 📌 Requerimientos

### Validación
- [ ] **R1:** `@Valid` en todos los endpoints que reciben `@RequestBody`
- [ ] **R2:** `400 Bad Request` con JSON detallando qué campo falló y por qué
- [ ] **R3:** Validator custom: `@ValidCategory` verifica que la categoría exista en una lista predefinida
- [ ] **R4:** `@ControllerAdvice` que maneja `MethodArgumentNotValidException` y retorna respuesta estructurada

### MapStruct
- [ ] **R5:** `ProductMapper` con `@Mapper(componentModel = "spring")` para mapear Request → Entity y Entity → Response
- [ ] **R6:** Mapear lista `List<Product>` → `List<ProductResponse>` automáticamente

### Documentación
- [ ] **R7:** Swagger UI en `/swagger-ui.html` con todos los endpoints visibles
- [ ] **R8:** `@Operation(summary=...)` y `@ApiResponse` en cada endpoint con códigos de respuesta
- [ ] **R9:** Info del API en `application.yml`: título, versión, descripción

## 📂 Estructura Sugerida

```
src/main/java/com/bootcamp/catalog/
├── controller/
│   └── ProductController.java
├── service/
│   ├── ProductService.java
│   └── ProductServiceImpl.java
├── dto/
│   ├── ProductCreateRequest.java
│   ├── ProductUpdateRequest.java
│   └── ProductResponse.java
├── mapper/
│   └── ProductMapper.java
├── validation/
│   ├── ValidCategory.java         (anotación custom)
│   └── CategoryValidator.java     (ConstraintValidator)
└── exception/
    └── GlobalExceptionHandler.java
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Validación con Jakarta Validation (`@Valid`, constraints) | 25 |
| `400 Bad Request` con detalle de errores | 15 |
| Validator custom funcional | 15 |
| MapStruct Request → Response | 20 |
| Swagger UI con documentación real | 15 |
| Entidades JPA nunca expuestas (DTOs en toda la cadena) | 10 |
| **Total** | **100** |
