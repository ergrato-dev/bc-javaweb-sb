# Rúbrica de Evaluación — Semana 03
## Spring Boot: Configuración y REST MVC

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
| **Auto-configuration** | Explica cómo funciona `spring.factories`, qué hace un starter | Sabe que Spring Boot auto-configura | No entiende la diferencia con Spring puro |
| **`application.yml`** | Lee, escribe y referencia propiedades con `@Value`; configura perfiles | Edita propiedades existentes correctamente | No sabe dónde configurar el puerto |
| **Anotaciones REST** | Distingue `@RestController`, `@GetMapping`, `@PostMapping`, `@PathVariable`, `@RequestParam`, `@RequestBody` | Usa las 3 principales correctamente | Confunde parámetros de ruta con query params |
| **`ResponseEntity`** | Usa `ResponseEntity.ok()`, `created()`, `notFound()`, `noContent()` apropiadamente | Retorna `ResponseEntity.ok()` correctamente | Retorna objetos sin envolver en ResponseEntity |
| **Actuator** | Activa y consulta `/actuator/health`, `/actuator/info`, entiende para qué sirve | Levanta Actuator y consulta health | No sabe qué es Actuator |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **CRUD REST completo** | `GET /items`, `GET /items/{id}`, `POST /items`, `PUT /items/{id}`, `DELETE /items/{id}` | Al menos 3 operaciones CRUD correctas | Solo GET implementado |
| **Códigos HTTP correctos** | 200, 201, 204, 404 usados en el contexto correcto | 200 y 404 correctos; errors en 200-201 | Siempre retorna 200 |
| **Perfiles de Spring** | `dev` + `prod` configurados, `-Dspring.profiles.active=dev` funciona | Un perfil configurado | Sin perfiles |
| **Prueba con HTTP client** | Demuestra todos los endpoints con curl, Postman o HTTPie | Prueba con Postman básico | No puede probar la API |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **API funcional** | Todos los endpoints responden correctamente al dominio del proyecto | ≥80% de endpoints funcionando | Menos de 3 endpoints funcionando |
| **Configuración** | `application.yml` con configuración real (puerto, contexto), perfiles activos | `application.yml` con configuración básica | Solo `application.properties` por defecto |
| **Códigos HTTP semánticamente correctos** | Sin `200 OK` en creación, sin `200 OK` en eliminación | Mayoría correctos | Siempre `200 OK` |
| **`@RestControllerAdvice`** | Manejo global de errores básico implementado | Maneja `404` básicamente | Sin manejo de errores |

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
- API inicia y responde en al menos `GET /` o `/actuator/health`
- Sin lógica de negocio en el controller (datos en memoria están bien esta semana)
- Entrega puntual (penalización del 10% por día de retraso)
