# Glosario — Semana 04: Validación, DTOs, OpenAPI

---

## B

**Bean Validation**
Estándar Java (Jakarta EE) para validar objetos con anotaciones. Spring Boot integra Hibernate Validator como implementación por defecto.

---

## C

**Constraint**
Regla de validación expresada como anotación. Puede ser estándar (`@NotBlank`, `@Email`) o personalizada implementando `ConstraintValidator<A, T>`.

---

## D

**DTO (Data Transfer Object)**
Objeto cuyo único propósito es transferir datos entre capas o sistemas. En APIs REST: `XxxRequest` (entrada) y `XxxResponse` (salida). Evita exponer entidades JPA.

---

## E

**`@Email`**
Constraint que valida que el valor sea una dirección de correo electrónico válida según RFC. Requiere `spring-boot-starter-validation`.

---

## J

**Jakarta Validation**
API estándar para la validación de objetos (antes llamada `javax.validation`). Definida en el paquete `jakarta.validation`. Implementada por Hibernate Validator.

---

## M

**`MethodArgumentNotValidException`**
Excepción lanzada por Spring MVC cuando la validación `@Valid` falla en un `@RequestBody`. Contiene un `BindingResult` con los errores por campo.

**MapStruct**
Framework de generación de código para mapeo entre objetos (entidades ↔ DTOs). Genera implementaciones Java en tiempo de compilación — sin reflexión.

**`@Mapper`**
Anotación de MapStruct que marca una interfaz como mapper. `componentModel = "spring"` hace que el mapper generado sea un bean de Spring.

**`@Mapping`**
Anotación de MapStruct para configurar el mapeo de un campo específico: renombrar, ignorar, o usar una expresión Java.

---

## N

**`@NotBlank`**
Valida que el String no sea `null`, no esté vacío y no sea solo espacios en blanco. Recomendado para campos de texto obligatorios (más estricto que `@NotEmpty`).

**`@NotNull`**
Valida que el valor no sea `null`. No valida Strings vacíos — usar `@NotBlank` para Strings.

---

## O

**OpenAPI**
Especificación estándar (OAS 3.x) para describir APIs RESTful. Antes llamada Swagger Specification. Generada automáticamente por SpringDoc.

**`@Operation`**
Anotación de SpringDoc para documentar un endpoint: summary, description, parámetros y respuestas.

---

## P

**`@Pattern`**
Valida que el String coincida con una expresión regular Java.

**`@Positive`** / **`@PositiveOrZero`**
Valida que el número sea estrictamente positivo (> 0) o positivo o cero (>= 0).

---

## S

**SpringDoc OpenAPI**
Librería que integra OpenAPI 3 con Spring Boot. Auto-genera la especificación OpenAPI leyendo los controllers y DTOs. Expone Swagger UI en `/swagger-ui.html`.

**Swagger UI**
Interfaz web interactiva que visualiza y permite probar una API documentada con OpenAPI. Accesible en `/swagger-ui.html` cuando springdoc está en el classpath.

---

## V

**`@Valid`**
Anotación de Jakarta Validation que activa la validación en cascada de un parámetro o campo. Requerido en `@RequestBody` para que Spring valide el DTO.

**`@Validated`**
Alternativa a `@Valid` de Spring que además habilita validación de grupos y validación de `@PathVariable`/`@RequestParam` cuando se usa en la clase controller.
