# Rúbrica de Evaluación — Semana 04
## Validación, DTOs y OpenAPI

---

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Descripción |
|------|-----------|-------------|
| 🧠 Conocimiento | 30% | Evaluación teórica de conceptos |
| 💪 Desempeño | 40% | Ejercicios prácticos en clase |
| 📦 Producto | 30% | Proyecto entregable funcional |

---

## 🧠 Conocimiento (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Jakarta Bean Validation** | Explica diferencia `@NotNull` vs `@NotBlank`, combina constraints, crea validator custom | Usa `@NotNull`, `@NotBlank`, `@Size`, `@Email` correctamente | No sabe cómo activar la validación con `@Valid` |
| **DTOs** | Explica por qué no exponer entidades JPA directamente; diferencia Request vs Response | Crea DTOs y los usa en controllers | Expone entidad directamente en la API |
| **MapStruct** | Configura `@Mapper(componentModel="spring")`, mapea listas, campos con nombres distintos | Mapeo básico 1:1 con MapStruct | No sabe cómo funciona MapStruct (copia manual) |
| **Manejo global de errores** | `@ControllerAdvice` con múltiples `@ExceptionHandler`, `ProblemDetail` o RFC 7807 | Un `@ExceptionHandler` básico funcionando | Sin manejo global de errores |
| **SpringDoc OpenAPI** | Documenta endpoints con `@Operation`, `@ApiResponse`, schemas generados | Swagger UI visible en `/swagger-ui.html` | No puede abrir Swagger UI |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Validación activada** | `@Valid` en parámetros; errores retornan 400 con detalle de qué falló | `@Valid` activo, 400 básico | Sin `@Valid`, datos inválidos pasan |
| **DTO pipeline completo** | `CreateRequest` → servicio → entidad → `Response`; nada de entidad en el controller | DTOs en la mayoría del flujo | Entidad expuesta directamente |
| **MapStruct funcionando** | `mvn compile` genera implementación; mapper inyectado vía Spring | Mapper generado, uso manual opcional | Mapeo manual con setters |
| **Swagger UI útil** | Todos los endpoints documentados con examples, descriptions y response codes | Al menos 3 endpoints documentados | Solo URL base visible en Swagger |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Validación completa** | Todos los campos validados; `400 Bad Request` con mensaje descriptivo | ≥70% de campos validados | Sin validación o siempre 200/500 |
| **DTOs en todas las fronteras** | Entidades JPA nunca llegan al controller ni salen en la respuesta | Mayoría usa DTOs | Entidad expuesta directamente |
| **Error handling global** | `@ControllerAdvice` manejando 400, 404, 409, 500 con formato consistente | 400 y 404 manejados | Sin `@ControllerAdvice` |
| **Documentación API** | Swagger UI refleja DTOs reales con ejemplos; response codes documentados | Swagger UI generado automáticamente | Sin SpringDoc configurado |

---

## 📏 Escala de Calificación

| Nota | Rango |
|------|-------|
| A | 90–100% |
| B | 80–89% |
| C | 70–79% |
| D | <70% |

---

## ✅ Criterios de Aprobación

- Mínimo **70%** en cada tipo de evidencia
- Datos inválidos retornan `400 Bad Request` (nunca `500`)
- Entidades JPA no expuestas directamente en la API
- Swagger UI disponible en `/swagger-ui.html`
- Entrega puntual (penalización del 10% por día de retraso)
