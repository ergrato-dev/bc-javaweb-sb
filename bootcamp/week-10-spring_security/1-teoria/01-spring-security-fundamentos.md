# Spring Security: Fundamentos

## 🎯 Objetivos

- Entender el `SecurityFilterChain` y cómo protege la aplicación
- Configurar autenticación con `UserDetailsService`
- Diferenciar autenticación de autorización
- Hashear contraseñas con `BCryptPasswordEncoder`

---

## 1. Arquitectura de Spring Security

Cada request HTTP pasa por una cadena de filtros antes de llegar al controller:

```
Request HTTP
     │
     ▼
┌─────────────────────────────────────────┐
│         Security Filter Chain            │
│  ┌──────────────────────────────────┐   │
│  │ CorsFilter                       │   │
│  │ CsrfFilter                       │   │
│  │ UsernamePasswordAuthenticationFilter│  │
│  │ BearerTokenAuthenticationFilter  │   │
│  │ ExceptionTranslationFilter       │   │
│  │ FilterSecurityInterceptor        │   │
│  └──────────────────────────────────┘   │
└─────────────────────────────────────────┘
     │
     ▼
  Controller
```

---

## 2. Dependencia

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

> ⚠️ Con solo agregar la dependencia, Spring Boot protege **todos** los endpoints.
> La contraseña temporal se imprime en la consola al iniciar.

---

## 3. SecurityFilterChain

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()   // sin auth
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()                   // todo lo demás: necesita auth
            )
            .httpBasic(Customizer.withDefaults())               // authn con usuario:contraseña
            .csrf(csrf -> csrf.disable());                      // deshabilitado para APIs REST

        return http.build();
    }
}
```

---

## 4. UserDetailsService

Spring Security usa `UserDetailsService` para cargar el usuario desde cualquier fuente:

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())  // ya hasheado en DB
                        .roles(user.getRole().name())  // "ADMIN" → ROLE_ADMIN
                        .build())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));
    }
}
```

---

## 5. Hashear contraseñas con BCrypt

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

```java
// Al registrar usuario — hashear antes de guardar
String rawPassword = request.password();
String hashed = passwordEncoder.encode(rawPassword);
user.setPassword(hashed);

// Spring Security verifica automáticamente con passwordEncoder.matches()
// NO necesitas llamar matches() manualmente en el login
```

> ⚠️ **Nunca** guardes contraseñas en texto plano. BCrypt incluye el salt automáticamente.

---

## 6. Contexto de seguridad

```java
// Obtener el usuario autenticado en cualquier capa
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
String username = auth.getName();

// En controller — forma más limpia:
@GetMapping("/me")
public ResponseEntity<UserResponse> getCurrentUser(
        @AuthenticationPrincipal UserDetails userDetails) {
    return ResponseEntity.ok(userService.findByUsername(userDetails.getUsername()));
}
```

---

## ✅ Checklist

- [ ] Dependencia `spring-boot-starter-security` en pom.xml
- [ ] `SecurityFilterChain` con `permitAll()` para rutas públicas
- [ ] `UserDetailsService` implementado con `UserRepository`
- [ ] `BCryptPasswordEncoder` como `@Bean`
- [ ] CSRF deshabilitado para APIs REST
- [ ] Tests con `@WithMockUser`
