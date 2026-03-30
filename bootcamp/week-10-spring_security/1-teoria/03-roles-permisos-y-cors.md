# Roles, Permisos y CORS

## 🎯 Objetivos

- Implementar roles jerárquicos con Spring Security
- Usar `GrantedAuthority` para permisos granulares
- Configurar CORS correctamente para APIs consumidas por frontends
- Entender la diferencia entre `hasRole()` y `hasAuthority()`

---

## 1. Roles vs Authorities

| Concepto | Internamente | Uso |
|----------|-------------|-----|
| `Role` | `ROLE_` + nombre | `hasRole("ADMIN")` → busca `ROLE_ADMIN` |
| `Authority` | nombre exacto | `hasAuthority("TASK_CREATE")` |

```java
// roles() agrega el prefijo ROLE_ automáticamente
User.builder()
        .roles("ADMIN")          // → GrantedAuthority: "ROLE_ADMIN"
        .build()

// authorities() NO agrega prefijo
User.builder()
        .authorities("ROLE_ADMIN", "TASK_CREATE", "TASK_DELETE")
        .build()
```

---

## 2. Enum de Roles

```java
public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    // Convertir a GrantedAuthority para Spring Security
    public GrantedAuthority toGrantedAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }
}
```

```java
// En UserDetailsService
.authorities(user.getRole().toGrantedAuthority())
```

---

## 3. Permisos Granulares con Enum

```java
public enum Permission {
    TASK_CREATE,
    TASK_READ,
    TASK_UPDATE,
    TASK_DELETE,
    USER_MANAGE;
}
```

```java
// En un proyecto más avanzado — roles con permisos asociados
public enum Role {
    ROLE_USER(Set.of(TASK_CREATE, TASK_READ, TASK_UPDATE)),
    ROLE_ADMIN(Set.of(TASK_CREATE, TASK_READ, TASK_UPDATE, TASK_DELETE, USER_MANAGE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        var authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }
}
```

---

## 4. CORS Configuration

CORS (Cross-Origin Resource Sharing) permite que un frontend en otro dominio consuma la API:

```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    var configuration = new CorsConfiguration();

    // Orígenes permitidos (en prod: solo el dominio del frontend)
    configuration.setAllowedOrigins(List.of(
            "http://localhost:3000",    // React dev server
            "https://myapp.com"         // Producción
    ));

    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/**", configuration);
    return source;
}
```

```java
// En SecurityFilterChain
.cors(cors -> cors.configurationSource(corsConfigurationSource()))
```

> ⚠️ `allowedOrigins("*")` con `allowCredentials(true)` es inválido. Usa orígenes específicos cuando necesites credenciales.

---

## 5. Endpoint de Registro

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        var user = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
```

```java
// En SecurityConfig — rutas públicas
.requestMatchers("/api/auth/**").permitAll()
.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
```

---

## 6. Estructura de Paquetes para Security

```
security/
├── SecurityConfig.java          # FilterChain, CORS, BCrypt
├── CustomUserDetailsService.java # Implementa UserDetailsService
├── SecurityUtils.java           # Helpers estáticos (getCurrentUser, etc.)
```

```java
public class SecurityUtils {

    private SecurityUtils() {}

    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    public static boolean hasRole(String role) {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }
}
```

---

## ✅ Checklist

- [ ] Enum `Role` con `ROLE_` prefijo
- [ ] `GrantedAuthority` correctamente configurado en `UserDetailsService`
- [ ] CORS configurado con orígenes específicos
- [ ] Rutas de Swagger accesibles sin autenticación
- [ ] `SecurityUtils` o `@AuthenticationPrincipal` para obtener usuario actual
