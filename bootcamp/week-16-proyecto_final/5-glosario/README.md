# Glosario — Semana 16: Proyecto Final

Términos clave del proyecto final y conceptos de producción.

---

## A

**Access Token**
JWT de corta duración (15min — 1h) que el cliente incluye en cada request con el header `Authorization: Bearer <token>`. Usado para autenticar operaciones.

**AuthenticationManager**
Componente de Spring Security que delega la autenticación al `AuthenticationProvider` configurado. Lanza `BadCredentialsException` si las credenciales son inválidas.

**AuthenticationProvider**
Implementación concreta de autenticación. `DaoAuthenticationProvider` es el más común: carga el usuario con `UserDetailsService` y verifica la contraseña con `PasswordEncoder`.

---

## B

**BCrypt**
Algoritmo de hashing de contraseñas con un factor de coste configurable (recomendado: 12). Incluye salt automático — dos hash del mismo password son siempre distintos.

**Bearer Token**
Esquema de autenticación HTTP: `Authorization: Bearer <jwt>`. El portador ("bearer") del token obtiene acceso.

---

## C

**Claims**
Datos codificados en el payload de un JWT. Claims estándar: `sub` (subject/username), `iat` (issued at), `exp` (expiration). Claims personalizados: roles, permisos, etc.

**CORS (Cross-Origin Resource Sharing)**
Mecanismo de seguridad del navegador que controla qué orígenes pueden acceder a la API. Debe configurarse en `SecurityConfig` para permitir requests desde el frontend.

**CSRF (Cross-Site Request Forgery)**
Ataque en el que un sitio malicioso hace requests en nombre del usuario. **Irrelevante en APIs stateless con JWT** — se deshabilita en Spring Security para APIs.

---

## D

**DaoAuthenticationProvider**
Implementación de `AuthenticationProvider` que usa un `UserDetailsService` (para cargar usuario de BD) y un `PasswordEncoder` (para verificar contraseña).

---

## F

**Filter Chain (cadena de filtros)**
Secuencia de filtros de Spring Security que procesan cada request. `JwtAuthFilter` se agrega antes de `UsernamePasswordAuthenticationFilter` para validar el token primero.

---

## G

**GrantedAuthority**
Permiso o rol asignado a un usuario en Spring Security. `SimpleGrantedAuthority("ROLE_ADMIN")` es la forma más común. Los roles deben tener prefijo `ROLE_` para `hasRole()`.

---

## H

**HS256 (HMAC-SHA256)**
Algoritmo de firma simétrico para JWT. Usa una misma clave secreta para firmar y verificar. Alternativa asimétrica: RS256 (par pública/privada).

---

## J

**JaCoCo (Java Code Coverage)**
Herramienta de cobertura de código integrada en Maven. Genera reportes HTML y puede fallar el build si la cobertura cae por debajo de un umbral configurado (`mvn verify`).

**JWT (JSON Web Token)**
Token auto-contenido con tres partes Base64: `HEADER.PAYLOAD.SIGNATURE`. Stateless: toda la info del usuario está en el token, no en sesión del servidor.

---

## O

**OncePerRequestFilter**
Clase base de Spring para filtros que deben ejecutarse exactamente una vez por request HTTP, sin importar forwards o includes internos.

---

## P

**`@PreAuthorize`**
Anotación de Spring Security que evalúa expresiones SpEL antes de ejecutar un método. Ejemplos: `@PreAuthorize("hasRole('ADMIN')")`, `@PreAuthorize("#userId == authentication.name")`.

---

## R

**Refresh Token**
Token de larga duración (días/semanas) usado para obtener nuevos access tokens sin requerir login. Se almacena en BD o cookie HttpOnly (más seguro que localStorage).

---

## S

**SecurityContext**
Almacén del usuario autenticado para el request actual (en `ThreadLocal`). Spring Security lo limpia automáticamente al final de cada request.

**`@ServiceConnection`**
Anotación de Testcontainers + Spring Boot que configura automáticamente las propiedades de datasource para conectar al contenedor. Elimina la necesidad de `@DynamicPropertySource`.

**Stateless (sin estado)**
Arquitectura donde el servidor no guarda estado de sesión entre requests. Cada request es independiente y se autentica con el JWT. Escala horizontalmente sin sesiones compartidas.

---

## U

**`UserDetails`**
Interfaz de Spring Security que representa un usuario autenticado. Proporciona username, password (hasheado), authorities y flags de estado (enabled, accountExpired, etc.).

**`UserDetailsService`**
Interfaz con un método: `loadUserByUsername(String)`. Spring Security la usa durante la autenticación para cargar el usuario desde la fuente de datos (BD, LDAP, etc.).

---

## V

**`mvn verify`**
Fase de Maven que ejecuta: compilación + tests unitarios + tests de integración + JaCoCo check. El pipeline de CI/CD debe ejecutar esta fase (no solo `mvn test`).
