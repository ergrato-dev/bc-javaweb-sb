# Proyecto Final — API REST Production-Ready

> Este es el proyecto integrador del bootcamp. No hay un dominio impuesto — tú
> eliges el problema que quieres resolver. La API debe demostrar dominio completo
> del stack aprendido en las 16 semanas.

---

## 🎯 Lo que construirás

Una API REST completa con:

- **Spring Security + JWT** — autenticación, roles y autorización
- **Spring Data JPA + Flyway** — persistencia con PostgreSQL
- **Validación + DTOs + MapStruct** — datos limpios y tipados
- **SpringDoc OpenAPI** — documentación automática
- **Tests + JaCoCo ≥ 70%** — calidad verificable
- **Docker + GitHub Actions** — deployable desde el día 1

---

## 🗂️ Estructura Recomendada

```
src/main/java/com/tuapp/
├── Application.java
├── config/
│   ├── SecurityConfig.java         # Filter chain, CORS, rutas públicas/privadas
│   └── OpenApiConfig.java          # Configuración Swagger con JWT bearer
├── security/
│   ├── JwtService.java             # Generación y validación de tokens
│   ├── JwtAuthFilter.java          # Filtro que extrae y valida el token en cada request
│   └── UserDetailsServiceImpl.java # Carga el usuario desde la BD
├── controller/
│   ├── AuthController.java         # POST /auth/register, POST /auth/login
│   └── [TuDominio]Controller.java
├── service/
│   ├── AuthService.java
│   └── [TuDominio]Service.java
├── repository/
├── domain/       # Entidades JPA
├── dto/          # Records: CreateRequest, UpdateRequest, Response
├── mapper/       # MapStruct mappers
└── exception/
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java
```

---

## ✅ Requisitos Mínimos

### Funcionales (elige tu dominio)

Algunas ideas de dominio:

| Dominio | Entidades sugeridas |
|---------|---------------------|
| E-commerce | Product, Order, OrderItem, Category |
| Blog | Post, Comment, Tag, Author |
| Gestor de tareas | Task, Project, User, Label |
| Reservas | Venue, Reservation, User, TimeSlot |
| Inventario | Product, Category, Supplier, StockMovement |

**Requisitos de dominio:**
- [ ] ≥ 3 entidades con relaciones (`@OneToMany`, `@ManyToMany`)
- [ ] CRUD completo en ≥ 2 entidades
- [ ] Búsqueda con filtros opcionales + paginación (`Page<T>`)
- [ ] Al menos 2 roles con permisos diferenciados

### Técnicos (obligatorios)

- [ ] **Auth JWT:** `POST /auth/register`, `POST /auth/login` (retorna JWT)
- [ ] **Autorización por rol:** endpoints que requieren `ROLE_ADMIN` vs `ROLE_USER`
- [ ] **Flyway:** todas las tablas creadas por migraciones (sin `ddl-auto: create`)
- [ ] **DTOs separados:** `CreateRequest`, `UpdateRequest`, `Response` (nunca la entidad)
- [ ] **Validación:** `@Valid` con `@NotBlank`, `@Email`, `@Size` en todos los endpoints
- [ ] **Swagger UI** con `@Operation` y `@ApiResponse` en todos los endpoints
- [ ] **Tests:** unitarios (services) + slice (@WebMvcTest) + integración (Testcontainers)
- [ ] **JaCoCo:** cobertura ≥ 70% (`mvn verify` debe pasar)
- [ ] **Dockerfile multi-stage** + **Docker Compose** (app + PostgreSQL)
- [ ] **GitHub Actions:** `mvn verify` en cada push a `main`

---

## 🏗️ Ejemplo de Implementación JWT

El `starter/` incluye el esqueleto de autenticación. Los TODOs están en los archivos clave:

### JwtService.java
```java
// Genera un token firmado con HS256
public String generateToken(UserDetails userDetails) {
    // TODO: usar Jwts.builder() con subject, expiration y firma HS256
    return null;
}

// Valida que el token sea válido y no esté expirado
public boolean isTokenValid(String token, UserDetails userDetails) {
    // TODO: extraer username del token y comparar con userDetails.getUsername()
    return false;
}
```

### SecurityConfig.java
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}
```

---

## 📦 El código starter incluye

```
starter/
├── pom.xml                              # Spring Boot 3.4 + Security + JPA + JWT + Docker
├── src/main/java/com/bootcamp/taskapi/
│   ├── TaskApiApplication.java
│   ├── config/
│   │   └── SecurityConfig.java          # TODO: configurar filter chain
│   ├── security/
│   │   ├── JwtService.java              # TODO: implementar generate + validate
│   │   ├── JwtAuthFilter.java           # TODO: extraer token del header
│   │   └── UserDetailsServiceImpl.java  # TODO: cargar usuario desde BD
│   ├── controller/
│   │   └── AuthController.java          # TODO: register + login
│   ├── service/
│   │   └── AuthService.java             # TODO: lógica de registro y login
│   ├── domain/
│   │   ├── AppUser.java                 # JPA entity (username, password, role)
│   │   └── Role.java                    # enum ADMIN, USER
│   ├── repository/
│   │   └── UserRepository.java
│   ├── dto/
│   │   └── AuthDtos.java                # RegisterRequest, LoginRequest, AuthResponse records
│   └── exception/
│       ├── ResourceNotFoundException.java
│       └── GlobalExceptionHandler.java
└── src/main/resources/
    ├── application.yml
    └── db/migration/
        └── V1__create_users_schema.sql
```

---

## ⏱️ Distribución del Tiempo (6 horas)

| Bloque | Tiempo | Actividad |
|--------|--------|-----------|
| Setup | 30min | Crear proyecto, estrutura de paquetes, pom.xml |
| Dominio + BD | 45min | Entidades, Flyway migrations |
| Seguridad | 90min | JWT: JwtService, JwtAuthFilter, SecurityConfig, AuthController |
| Business logic | 60min | Services + Controllers del dominio |
| Tests | 60min | Unitarios + @WebMvcTest + 1 test integración |
| Docker + CI/CD | 30min | Dockerfile, docker-compose.yml, ci.yml |
| Deploy + docs | 15min | Swagger UI, README, push |

---

## 🎓 Entregables

1. **Repositorio GitHub** — con commits semánticos (`feat:`, `fix:`, `test:`)
2. **`README.md`** — instrucciones para `docker compose up`, variables de entorno, endpoints
3. **URL pública** — app deployada en Railway / Fly.io / Render
4. **Swagger UI** — accesible en la URL pública (`/swagger-ui.html`)
5. **CI verde** — GitHub Actions en `main` pasando
6. **Presentación** — 10 min: dominio elegido, decisiones de arquitectura, retos

---

## 💡 Hints

**¿Cómo agregar JWT a una librería?**
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```

**¿Por qué JwtAuthFilter extiende `OncePerRequestFilter`?**
Spring Security puede invocar filtros múltiples veces en el mismo request (forwards, includes). `OncePerRequestFilter` garantiza ejecución única por request.

**¿Cómo testear endpoints con tokens?**
```java
// En @WebMvcTest: usar @WithMockUser para simular usuario autenticado
@Test
@WithMockUser(roles = "ADMIN")
void adminEndpointShouldReturn200() throws Exception {
    mockMvc.perform(get("/admin/users")).andExpect(status().isOk());
}
```

**¿Testcontainers en el proyecto final?**
```java
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
class FullApiIntegrationTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired TestRestTemplate restTemplate;

    @Test
    void registerAndLoginFlow() {
        // POST /auth/register → 201
        // POST /auth/login → 200 con token
        // GET /api/tasks con token → 200
    }
}
```
