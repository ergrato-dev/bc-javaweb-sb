# JwtAuthenticationFilter y Spring Security

## 🎯 Objetivos

- Implementar `OncePerRequestFilter` para interceptar requests con JWT
- Integrar el filtro JWT en el `SecurityFilterChain`
- Configurar sesiones stateless (sin estado en servidor)
- Manejar errores de JWT (expirado, inválido, malformado)

---

## 1. JwtAuthenticationFilter

```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // ... constructor

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 1. Extraer el token del header Authorization
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // No hay token → pasar al siguiente filtro (permitAll o 401 si es ruta protegida)
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraer el token (quitar "Bearer ")
        var token = authHeader.substring(7);

        try {
            // 3. Extraer username del token
            var username = jwtService.extractUsername(token);

            // 4. Solo autenticar si hay username y no hay autenticación previa
            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                var userDetails = userDetailsService.loadUserByUsername(username);

                // 5. Validar el token (firma + expiración + username)
                if (jwtService.isValid(token, userDetails)) {
                    // 6. Crear objeto de autenticación y setearlo en el contexto
                    var authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (JwtException ex) {
            // Token inválido, expirado o malformado — limpiar contexto y continuar
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
```

---

## 2. SecurityFilterChain con JWT

```java
@Bean
public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        JwtAuthenticationFilter jwtFilter) throws Exception {

    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        // Stateless: NO crear sesiones HTTP en el servidor
        .sessionManagement(s ->
                s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // Sin CSRF — stateless + Bearer token ya protege
        .csrf(csrf -> csrf.disable())
        // Registrar el filtro JWT ANTES del filtro de usuario/contraseña
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
```

> 💡 `SessionCreationPolicy.STATELESS` le dice a Spring Security que **nunca cree ni use**
> una sesión HTTP. El servidor no recuerda nada entre requests — el JWT contiene todo.

---

## 3. Endpoint de Login

```java
@PostMapping("/api/auth/login")
public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    // Verifica credenciales contra la DB (llama a UserDetailsService internamente)
    var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.username(), request.password()));

    var userDetails = (UserDetails) authentication.getPrincipal();
    var token = jwtService.generateToken(userDetails);

    return ResponseEntity.ok(new AuthResponse(token, expirationMs));
}

record AuthResponse(String token, long expiresIn) {}
```

---

## 4. Refresh Tokens (Concepto)

Los access tokens tienen vida corta (15 min - 1 hora). Los refresh tokens son de vida larga
y se usan para obtener nuevos access tokens sin pedir credenciales:

```
POST /api/auth/refresh
{ "refreshToken": "..." }

→ { "accessToken": "nuevo JWT", "expiresIn": 900000 }
```

Implementación básica:

```java
@Entity
public class RefreshToken {
    @Id UUID token;
    String username;
    LocalDateTime expiresAt;
    boolean revoked;
}
```

> ⚠️ Los refresh tokens deben almacenarse en DB para poder revocarlos.
> Guardar el `token` como `HttpOnly cookie` para evitar XSS.

---

## 5. Variables de Entorno para Producción

```yaml
# application.yml — usa variables de entorno en producción
app:
  jwt:
    secret: ${JWT_SECRET:dev-secret-key-at-least-32-chars-long}
    expiration-ms: ${JWT_EXPIRATION_MS:86400000}  # 24 horas
```

```bash
# .env (nunca commitear al repositorio)
JWT_SECRET=mi-clave-super-secreta-de-produccion-minimo-32-caracteres
JWT_EXPIRATION_MS=3600000
```

---

## ✅ Checklist

- [ ] `JwtAuthenticationFilter` extiende `OncePerRequestFilter`
- [ ] `SessionCreationPolicy.STATELESS` en `SecurityFilterChain`
- [ ] Filtro JWT añadido `addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)`
- [ ] `JwtException` capturada para tokens inválidos
- [ ] Secreto en variable de entorno, no hardcodeado
- [ ] `POST /api/auth/login` retorna `{"token": "...", "expiresIn": ...}`
