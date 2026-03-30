# Glosario — Semana 08: Arquitectura en Capas

---

## A

**Arquitectura en Capas (Layered Architecture)**
Patrón donde cada capa tiene una responsabilidad única y solo se comunica con la capa adyacente. En Spring Boot: Controller → Service → Repository → Domain.

---

## C

**Controller**
Capa de presentación. Responsable de recibir peticiones HTTP, validar input y delegar al Service. No contiene lógica de negocio.

**Cross-cutting Concern**
Funcionalidad transversal a todas las capas: logging, seguridad, manejo de errores, auditoría. Se implementa con `@Aspect`, `@ControllerAdvice`, filtros.

---

## D

**DTO (Data Transfer Object)**
Objeto de transferencia de datos entre capas o sistemas. En Spring Boot, `record` Java es la forma idiomática. Desacopla la API HTTP del modelo de dominio JPA.

---

## G

**`@GlobalExceptionHandler`**
`@RestControllerAdvice` con métodos `@ExceptionHandler` para centralizar el manejo de errores. Evita duplicar try-catch en cada controller.

---

## I

**Inyección por Constructor**
Forma preferida de DI en Spring Boot: el bean recibe sus dependencias a través del constructor. Facilita testing con Mockito (sin `@SpringBootTest`).

---

## P

**`ProblemDetail`**
Clase de Spring Framework 6 que implementa RFC 7807 (Problem Details for HTTP APIs). Retorna errores estructurados con `type`, `title`, `status`, `detail`.

---

## R

**Repository Pattern**
Abstracción de acceso a datos. Solo persiste y recupera entidades — sin lógica de negocio. En Spring Data: `extends JpaRepository<T, ID>`.

---

## S

**Service Layer**
Capa que contiene toda la lógica de negocio. Orquesta repositorios, aplica reglas, gestiona transacciones. Un Service por dominio (OrderService, CustomerService).

**Separation of Concerns (SoC)**
Principio de diseño: cada módulo debe tener una única responsabilidad. Base de la arquitectura en capas.

---

## T

**`@Transactional(readOnly = true)`**
Optimización para operaciones de solo lectura: Hibernate deshabilita el seguimiento de cambios (dirty checking), mejorando rendimiento. Aplicar a nivel de clase en Services.

---

## V

**Validation (Jakarta Bean Validation)**
`@Valid` en el parámetro `@RequestBody` activa la validación automática de constraints (`@NotNull`, `@Min`, `@Email`, etc.) antes de que el método del controller se ejecute.
