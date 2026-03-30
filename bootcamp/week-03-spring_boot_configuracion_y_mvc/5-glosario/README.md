# Glosario — Semana 03: Spring Boot REST MVC

---

## A

**Actuator**
Módulo de Spring Boot que expone endpoints de monitoreo (`/actuator/health`, `/actuator/metrics`, etc.) listos para producción sin configuración adicional.

**Auto-configuration**
Mecanismo de Spring Boot que configura componentes automáticamente basándose en las dependencias del classpath. Activado por `@EnableAutoConfiguration` (incluido en `@SpringBootApplication`).

---

## C

**`@ControllerAdvice`**
Componente que centraliza el manejo de excepciones para todos los controladores. Usado con `@ExceptionHandler` para mapear excepciones a respuestas HTTP específicas.

---

## D

**DispatcherServlet**
Servlet central de Spring MVC. Recibe todas las requests HTTP y las despacha al controller apropiado según la URL y el método HTTP.

---

## E

**`@ExceptionHandler`**
Método dentro de `@ControllerAdvice` que maneja un tipo específico de excepción y retorna la respuesta HTTP apropiada.

---

## H

**HTTP Status Codes**
Códigos numéricos que indican el resultado de una petición HTTP. Los más usados en REST: 200 OK, 201 Created, 204 No Content, 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found, 409 Conflict, 500 Internal Server Error.

---

## J

**Jackson**
Biblioteca de serialización/deserialización JSON usada por Spring Boot por defecto. Convierte automáticamente objetos Java a JSON y viceversa en los endpoints REST.

---

## M

**`@MockBean`**
Anotación de Spring Boot Test que crea un mock de Mockito y lo registra en el ApplicationContext. Usado en `@WebMvcTest` para simular servicios.

**MockMvc**
Framework de testing de Spring MVC que permite ejecutar peticiones HTTP simuladas sin levantar un servidor real. Ideal para `@WebMvcTest`.

---

## P

**`@PathVariable`**
Anotación que extrae un segmento de la URL y lo vincula a un parámetro del método. Ejemplo: `/products/{id}` → `@PathVariable Long id`.

**`@PostMapping`** / **`@GetMapping`** / etc.
Anotaciones de mapeo HTTP que vinculan un método del controller a una URL y método HTTP específico. Atajo para `@RequestMapping(method = RequestMethod.XXX)`.

---

## Q

**Query Parameter**
Parámetro opcional en la URL después de `?`. Ejemplo: `/products?category=Electronics`. Vinculado con `@RequestParam`.

---

## R

**`@RequestBody`**
Anotación que deserializa el cuerpo HTTP (generalmente JSON) al tipo del parámetro anotado.

**`@RequestMapping`**
Define la ruta base para todos los endpoints de un controller. Se puede combinar con `@GetMapping`, `@PostMapping`, etc.

**`@RequestParam`**
Extrae un parámetro de query string. Soporta `required = false` y `defaultValue`.

**`ResponseEntity<T>`**
Wrapper que permite controlar el código HTTP, headers y body de la respuesta. Más flexible que retornar `T` directamente.

**`@RestController`**
Combinación de `@Controller` + `@ResponseBody`. Todos sus métodos serializan el retorno a JSON automáticamente.

**`@RestControllerAdvice`**
Combinación de `@ControllerAdvice` + `@ResponseBody`. El handler de excepciones retorna JSON automáticamente.

---

## S

**Starter**
Dependencia de Spring Boot que agrupa un conjunto coherente de librerías con versiones compatibles. Ejemplo: `spring-boot-starter-web` incluye Spring MVC, Jackson, Tomcat embebido y más.

---

## W

**`@WebMvcTest`**
Slice test de Spring Boot que carga solo el contexto de la capa web (controllers, filters, advice) sin JPA ni servicios reales. Ideal para tests de controllers con `MockMvc`.
