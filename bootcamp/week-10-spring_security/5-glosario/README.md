# Glosario — Semana 10: Spring Security

## A

**Authentication (Autenticación)**
Proceso de verificar la identidad de un usuario. Responde a "¿quién eres?".
En Spring Security: `AuthenticationManager` + `UserDetailsService`.

**AuthenticationEntryPoint**
Componente que maneja errores 401 (no autenticado). Por defecto devuelve HTML;
se sobreescribe para retornar JSON en APIs REST.

**Authorization (Autorización)**
Proceso de verificar qué puede hacer un usuario. Responde a "¿qué puedes hacer?".
En Spring Security: `@PreAuthorize`, `hasRole()`, `hasAuthority()`.

## B

**BCryptPasswordEncoder**
Implementación de `PasswordEncoder` que usa el algoritmo BCrypt. Incluye salt aleatorio
automáticamente, lo que hace que el mismo password genere hashes diferentes.
Estándar de la industria para almacenar contraseñas.

## C

**CORS (Cross-Origin Resource Sharing)**
Mecanismo que permite a un frontend en un dominio diferente consumir la API.
Configurado con `CorsConfigurationSource` y `allowedOrigins`.

**CSRF (Cross-Site Request Forgery)**
Ataque que ejecuta acciones no autorizadas en nombre del usuario. Las APIs REST
stateless generalmente deshabilitan CSRF porque no usan cookies de sesión.

## G

**GrantedAuthority**
Interfaz que representa un permiso o rol en Spring Security. Los roles tienen
el prefijo `ROLE_`, las authorities son nombres exactos.

## H

**HTTP Basic Authentication**
Mecanismo de auth donde las credenciales se envían en el header:
`Authorization: Basic base64(username:password)`. Simple pero requiere HTTPS.

## P

**PasswordEncoder**
Interfaz de Spring Security para hashear y verificar contraseñas. Implementación
recomendada: `BCryptPasswordEncoder`.

**`@PreAuthorize`**
Anotación que evalúa una expresión SpEL antes de ejecutar un método. Si la
expresión es false → 403 Forbidden. Requiere `@EnableMethodSecurity`.

**Principal**
El usuario autenticado actualmente. Se obtiene via
`SecurityContextHolder.getContext().getAuthentication().getName()` o
`@AuthenticationPrincipal UserDetails`.

## R

**Role**
Agrupación de permisos representada como `GrantedAuthority` con prefijo `ROLE_`.
`hasRole("ADMIN")` busca la authority `ROLE_ADMIN`.

## S

**SecurityFilterChain**
Bean que define la configuración de seguridad: rutas protegidas, mecanismo de
autenticación, CORS, CSRF. Reemplaza el extendido `WebSecurityConfigurerAdapter`.

**SecurityContextHolder**
Almacén del contexto de seguridad con el usuario autenticado. Default: `ThreadLocal`
(un contexto por hilo/request).

## U

**UserDetails**
Interfaz principal de Spring Security que representa un usuario: username, password,
authorities, estado de cuenta (activo, expirado, bloqueado).

**UserDetailsService**
Interfaz con un único método `loadUserByUsername(String)`. El punto de extensión
principal para cargar usuarios desde cualquier fuente (DB, LDAP, API externa).

## W

**`@WithMockUser`**
Anotación de testing de Spring Security que simula un usuario autenticado sin
credenciales reales. Esencial para tests de `@WebMvcTest`.
