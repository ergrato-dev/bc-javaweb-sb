# Glosario — Semana 11: JWT + OAuth2

## A

**Access Token**
Token de vida corta (15 min - 1 hora) que se envía en cada request para autenticar.
Stateless: el servidor no lo almacena; la firma y expiración están en el propio token.

**AuthenticationManager**
Componente de Spring Security que verifica credenciales (username + password).
Delega en `UserDetailsService` + `PasswordEncoder`. Necesario para el endpoint de login manual.

## B

**Bearer Token**
Esquema de autenticación HTTP donde el token se envía en el header:
`Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...`

## C

**Claims**
Pares clave-valor en el payload de un JWT. Estándar (sub, iat, exp) y personalizados (roles, userId).

## H

**HMAC-SHA256 (HS256)**
Algoritmo de firma para JWT. Usa una clave secreta simétrica (la misma para firmar y verificar).
Alternativa asimétrica: RS256 (clave privada para firmar, pública para verificar).

## J

**JJWT**
Librería Java para trabajar con JWTs. API fluida:
`Jwts.builder()...compact()` para generar, `Jwts.parser()...parseSignedClaims()` para validar.

**JSON Web Token (JWT)**
Formato de token compacto y autocontenido: `header.payload.signature` en Base64.
El payload contiene claims (datos del usuario). La firma garantiza la integridad.

**JwtAuthenticationFilter**
Filtro de Spring Security que intercepta cada request para extraer y validar el JWT del header.
Extiende `OncePerRequestFilter` para garantizar ejecución única.

## O

**OAuth2**
Marco de autorización que permite delegar autenticación a un proveedor externo (Google, GitHub).
`spring-boot-starter-oauth2-resource-server` simplifica la integración.

**OncePerRequestFilter**
Clase base de Spring Web para filtros que deben ejecutarse una sola vez por request HTTP.
Garantiza que el filtro no se ejecute múltiples veces en forwards/redirects internos.

## R

**Refresh Token**
Token de vida larga (7-30 días) guardado en DB. Se usa para obtener nuevos access tokens
sin pedir credenciales al usuario. Puede revocarse en logout.

## S

**`SessionCreationPolicy.STATELESS`**
Configuración de Spring Security para no crear ni usar sesiones HTTP.
Obligatorio en APIs con JWT: el estado del usuario está en el token, no en el servidor.

**Signing Key**
Clave secreta usada para firmar (y verificar) el JWT. Con HMAC-SHA256 debe tener
al menos 256 bits (32 bytes). Nunca hardcodear en el código — usar variables de entorno.

## U

**`UsernamePasswordAuthenticationToken`**
Implementación de `Authentication` en Spring Security. Se usa para:
1. Solicitar autenticación: `new UsernamePasswordAuthenticationToken(username, password)`
2. Representar usuario autenticado: constructor con `username, null, authorities`
