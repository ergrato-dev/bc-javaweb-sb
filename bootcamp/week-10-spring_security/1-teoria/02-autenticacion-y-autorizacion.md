# Autenticación y Autorización

## 🎯 Objetivos

- Distinguir entre autenticación (¿quién eres?) y autorización (¿qué puedes hacer?)
- Implementar autenticación con `UserDetailsService`
- Usar `@PreAuthorize` para control de acceso granular
- Manejar excepciones de seguridad con respuestas HTTP correctas

---

## 1. Conceptos Clave

| Concepto | Pregunta | Spring Security |
|----------|----------|-----------------|
| **Autenticación** | ¿Quién eres? | `AuthenticationManager` + `UserDetailsService` |
| **Autorización** | ¿Qué puedes hacer? | `@PreAuthorize`, `hasRole()`, `hasAuthority()` |
| **Principal** | El usuario autenticado | `SecurityContextHolder.getContext().getAuthentication()` |

---

## 2. Flujo de Autenticación HTTP Basic

```
Client: GET /api/tasks
        Authorization: Basic dXNlcjpwYXNz  (base64 user:pass)
         │
         ▼
BasicAuthenticationFilter
         │  llama a:
         ▼
UserDetailsService.loadUserByUsername("user")
         │  retorna UserDetails con la contraseña hasheada
         ▼
PasswordEncoder.matches(rawPassword, storedHash)
         │  si ok:
         ▼
SecurityContextHolder <- Authentication(user, authorities)
         │
         ▼
Controller
```

---

## 3. Autorización por URL (método en SecurityFilterChain)

```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers(HttpMethod.GET, "/api/tasks").hasAnyRole("USER", "ADMIN")
    .requestMatchers(HttpMethod.POST, "/api/tasks").hasRole("USER")
    .requestMatchers("/api/admin/**").hasRole("ADMIN")
    .requestMatchers("/api/auth/**").permitAll()
    .anyRequest().authenticated()
)
```

---

## 4. @PreAuthorize (autorización a nivel método)

```java
@EnableMethodSecurity  // en la clase @Configuration
public class SecurityConfig { ... }
```

```java
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    // Solo el dueño de la tarea o un admin puede actualizarla
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.name")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(
            @PathVariable Long id,
            @RequestParam String username,
            @Valid @RequestBody TaskUpdateRequest request) {
        return ResponseEntity.ok(taskService.update(id, request));
    }

    // Solo ADMIN puede eliminar
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## 5. Manejar 401 y 403

Por defecto Spring Security retorna HTML. Para APIs REST necesitamos JSON:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // ...
        .exceptionHandling(ex -> ex
            // 401 - No autenticado
            .authenticationEntryPoint((request, response, authException) -> {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("""
                    {"error": "Unauthorized", "message": "Authentication required"}
                    """);
            })
            // 403 - Sin permisos suficientes
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("""
                    {"error": "Forbidden", "message": "Insufficient permissions"}
                    """);
            })
        );
    return http.build();
}
```

---

## 6. Tests con @WithMockUser

```java
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean TaskService taskService;

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void shouldReturnTasksForAuthenticatedUser() throws Exception {
        when(taskService.findAll(any())).thenReturn(Page.empty());

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn401WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturn403WhenUserTriesAdminEndpoint() throws Exception {
        mockMvc.perform(delete("/api/admin/users/1"))
                .andExpect(status().isForbidden());
    }
}
```

---

## ✅ Checklist

- [ ] `@EnableMethodSecurity` en `SecurityConfig`
- [ ] `@PreAuthorize` en métodos sensibles
- [ ] Entry point personalizado para 401 (JSON en lugar de HTML)
- [ ] Access denied handler para 403
- [ ] Tests unitarios con `@WithMockUser` y sin autenticación
