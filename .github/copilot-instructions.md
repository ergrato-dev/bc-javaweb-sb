# 🤖 Instrucciones para GitHub Copilot

## 📋 Contexto del Bootcamp

Este es un Bootcamp de **Java Web con Spring Boot — Zero to Hero** estructurado para llevar a estudiantes
con conocimientos previos de Java básico y OOP a desarrollador backend junior con Spring Boot.

### 📊 Datos del Bootcamp

- **Prerrequisito:** Java básico y POO en Java ✅
- **Duración:** 16 semanas (~4 meses)
- **Dedicación semanal:** 8 horas
- **Total de horas:** ~128 horas
- **Nivel de salida:** Desarrollador Backend Junior (Spring Boot)
- **Alcance:** ⚠️ **Exclusivamente backend — API RESTful** (sin frontend, sin HTML/CSS/JS)
- **Enfoque:** Spring Boot moderno con Java 21 LTS
- **Stack:** Spring Boot 3.4.13, Spring Data JPA 3.4+, Spring Security 6.4+, Hibernate 6.6+, PostgreSQL 17+, H2, Flyway 10+, MapStruct 1.6.3, SpringDoc OpenAPI 2.8.16, JUnit 5.11.4, Mockito 5+, Testcontainers 1.20.6, Docker 27+, Maven 3.9+

### 🗺️ Ecosistema de Bootcamps

Este bootcamp forma parte de una ruta de aprendizaje completa:

| Bootcamp                | Tecnología                       | Descripción                 |
| ----------------------- | -------------------------------- | --------------------------- |
| **Este bootcamp**       | Spring Boot + Java               | Backend API RESTful         |
| bc-react                | React                            | Frontend SPA                |
| **Proyecto Integrador** | Spring Boot + React + PostgreSQL | Full-stack con y sin Docker |

---

## 🎯 Objetivos de Aprendizaje

Al finalizar el bootcamp, los estudiantes serán capaces de:

- ✅ Dominar Java moderno (Streams, Lambdas, Records, Generics, Optional)
- ✅ Construir APIs RESTful completas con Spring Boot
- ✅ Implementar validación de datos con Jakarta Bean Validation
- ✅ Trabajar con bases de datos usando Spring Data JPA + Hibernate
- ✅ Implementar autenticación y autorización (JWT, OAuth2) con Spring Security
- ✅ Escribir tests automatizados con JUnit 5, Mockito y Testcontainers
- ✅ Documentar APIs automáticamente (OpenAPI/Swagger con SpringDoc)
- ✅ Desplegar aplicaciones con Docker y CI/CD con GitHub Actions
- ✅ Aplicar arquitectura en capas y arquitectura hexagonal
- ✅ Construir proyectos completos listos para producción

---

## 📚 Estructura del Bootcamp

### Distribución por Fases

#### Fase 1: Spring Core & Boot (Semanas 1–3) — 24 horas

- Java moderno: Lambdas, Streams API, Optional, Records, `var`, Generics
- IoC container, Dependency Injection, Spring beans, ciclo de vida
- Spring Boot starters, auto-configuration, `application.yml`, perfiles, Actuator
- `@RestController`, HTTP methods, path params, query params, `@RequestBody`/`@ResponseBody`

#### Fase 2: Validación, DTOs y Documentación (Semana 4) — 8 horas

- Jakarta Bean Validation (`@NotNull`, `@Valid`, custom validators)
- DTOs y mapeo con MapStruct
- `@ExceptionHandler`, `@ControllerAdvice`, manejo global de errores
- SpringDoc OpenAPI / Swagger UI

#### Fase 3: Persistencia con Spring Data JPA (Semanas 5–7) — 24 horas

- `@Entity`, Spring Data JPA, `JpaRepository`, H2 dev / PostgreSQL prod
- OneToMany, ManyToMany, JPQL, `@Transactional`, lazy vs eager loading
- Flyway (migraciones), Projections, Specifications, auditoría (`@CreatedDate`)

#### 🏗️ Progresión Arquitectónica (Semanas 8–9)

| Semana | Arquitectura   | Descripción                                              |
| ------ | -------------- | -------------------------------------------------------- |
| 08     | Capas completo | Controllers → Services → Repositories + DTOs/MapStruct   |
| 09     | Hexagonal      | Domain, Application (use cases), Infrastructure adapters |

#### Fase 4: Arquitectura y Patrones (Semanas 8–9) — 16 horas

- Arquitectura en capas, Service Layer, Repository Pattern, DTOs completos
- Ports & Adapters, domain model, use cases, infrastructure adapters en Spring Boot

#### Fase 5: Seguridad (Semanas 10–11) — 16 horas

- Security filter chain, autenticación, autorización, roles/permisos, CORS, CSRF
- JWT con Spring Security, `Bearer` tokens, OAuth2 Resource Server, refresh tokens

#### Fase 6: Testing (Semanas 12–13) — 16 horas

- JUnit 5, Mockito, `@WebMvcTest`, `MockMvc`, `@DataJpaTest`, `@SpringBootTest`
- Testcontainers, tests de integración full-stack, JaCoCo, TDD básico

#### Fase 7: Características Avanzadas (Semana 14) — 8 horas

- Spring Cache + Redis, `@Async` / `CompletableFuture`, `@Scheduled`
- WebSocket con STOMP

#### Fase 8: Producción y CI/CD (Semana 15) — 8 horas

- Docker multi-stage, Docker Compose, perfiles Spring por entorno
- GitHub Actions CI/CD, deployment (Railway, Fly.io, Render)

#### Fase 9: Proyecto Final (Semana 16) — 8 horas

- API REST completa production-ready: auth JWT, JPA + PostgreSQL, hexagonal, tests, Docker, Swagger

---

## 🗂️ Estructura de Carpetas

Cada semana sigue esta estructura estándar:

```
bootcamp/week-XX-tema_principal/
├── README.md                 # Descripción y objetivos de la semana
├── rubrica-evaluacion.md     # Criterios de evaluación detallados
├── 0-assets/                 # Imágenes, diagramas y recursos visuales (SVG)
├── 1-teoria/                 # Material teórico (archivos .md)
├── 2-practicas/              # Ejercicios guiados paso a paso
├── 3-proyecto/               # Proyecto semanal integrador
│   ├── README.md             # Instrucciones del proyecto
│   ├── starter/              # Código inicial para el estudiante
│   └── solution/             # ⚠️ OCULTA — Solo para instructores
├── 4-recursos/               # Recursos adicionales
│   ├── ebooks-free/
│   ├── videografia/
│   └── webgrafia/
└── 5-glosario/               # Términos clave (README.md)
```

### 📁 Carpetas Raíz

- `_assets/`: Recursos visuales globales (logos, headers)
- `_docs/`: Documentación general del bootcamp
- `_scripts/`: Scripts de automatización y utilidades
- `bootcamp/`: Contenido semanal del bootcamp

---

## 🎓 Componentes de Cada Semana

### 1. Teoría (1-teoria/)

- Archivos markdown con explicaciones conceptuales
- Ejemplos de código con comentarios claros
- Diagramas SVG cuando sea necesario
- Referencias a documentación oficial de Spring Boot
- **Extensión máxima: ~200 líneas por archivo** — los aprendices son adversos a textos largos; preferir concisión, ejemplos de código y diagramas sobre prosa extensa
- Si el tema requiere más contenido, dividir en múltiples archivos temáticos (ej. `01-introduccion.md`, `02-ejemplos.md`)

### 2. Prácticas (2-practicas/)

- Ejercicios guiados paso a paso
- Incremento progresivo de dificultad
- Soluciones comentadas
- Casos de uso del mundo real

#### 📋 Formato de Ejercicios

Los ejercicios son tutoriales guiados, NO tareas con TODOs. El estudiante aprende descomentando código:

**README.md del ejercicio:**

```markdown
### Paso 1: Crear endpoint GET básico

Explicación del concepto con ejemplo:

// Ejemplo explicativo
@GetMapping("/items/{id}")
public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
return ResponseEntity.ok(itemService.findById(id));
}

**Abre `starter/ItemController.java`** y descomenta la sección correspondiente.
```

**starter/ItemController.java:**

```java
// ============================================
// PASO 1: Crear endpoint GET básico
// ============================================

// Este endpoint recibe un parámetro de ruta
// Descomenta las siguientes líneas:
// @GetMapping("/items/{id}")
// public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
//     return ResponseEntity.ok(itemService.findById(id));
// }
```

> ⚠️ **IMPORTANTE:** Los ejercicios NO tienen carpeta `solution/`. El estudiante aprende descomentando el código y verificando que funcione correctamente.

#### ❌ NO usar este formato en ejercicios:

```java
// ❌ INCORRECTO — Este formato es para PROYECTOS, no ejercicios
public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
    // TODO: Implementar
    return null;
}
```

#### ✅ Usar este formato en ejercicios:

```java
// ✅ CORRECTO — Código comentado para descomentar
// Descomenta las siguientes líneas:
// @GetMapping("/items/{id}")
// public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
//     return ResponseEntity.ok(itemService.findById(id));
// }
```

### 3. Proyecto (3-proyecto/)

- Proyecto integrador que consolida lo aprendido
- README.md con instrucciones claras
- Código inicial en `starter/`
- Carpeta `solution/` oculta (en `.gitignore`) solo para instructores
- Criterios de evaluación específicos

#### 📋 Formato de Proyecto (con TODOs)

A diferencia de los ejercicios, el proyecto SÍ usa TODOs para que el estudiante implemente desde cero:

```java
// ============================================
// MÉTODO: createUser
// Crea un nuevo usuario en la base de datos
// ============================================

public UserResponse createUser(UserCreateRequest request) {
    // TODO: Implementar lógica de creación
    // 1. Validar que el email no exista
    // 2. Hashear la contraseña con BCrypt
    // 3. Mapear request a entidad User
    // 4. Guardar en base de datos con userRepository.save()
    // 5. Mapear entidad a UserResponse y retornar
    return null;
}
```

> 📁 Estructura del proyecto:
>
> ```
> 3-proyecto/
> ├── README.md          # Instrucciones del proyecto
> ├── starter/           # Código inicial para el estudiante
> └── solution/          # ⚠️ OCULTA — Solo para instructores
> ```
>
> La carpeta `solution/` está en `.gitignore` y NO se sube al repositorio público.

### 4. Recursos (4-recursos/)

- `ebooks-free/`: Libros gratuitos relevantes
- `videografia/`: Videos tutoriales complementarios
- `webgrafia/`: Enlaces a documentación y artículos

### 5. Glosario (5-glosario/)

- Términos técnicos ordenados alfabéticamente
- Definiciones claras y concisas
- Ejemplos de código cuando aplique

---

## 📝 Convenciones de Código

### Estilo Java Moderno (Java 21)

```java
// ✅ BIEN — usar tipos específicos con generics
public Optional<User> findUserById(Long id) {
    return userRepository.findById(id);
}

// ✅ BIEN — Records para DTOs inmutables
public record UserResponse(Long id, String email, String name) {}

// ✅ BIEN — var para variables locales obvias
var users = userRepository.findAll();

// ✅ BIEN — Pattern matching (Java 16+)
if (shape instanceof Circle c) {
    return Math.PI * c.radius() * c.radius();
}

// ✅ BIEN — Switch expressions (Java 14+)
String result = switch (status) {
    case ACTIVE -> "Active user";
    case INACTIVE -> "Inactive user";
    default -> "Unknown status";
};

// ❌ MAL — código verboso innecesario con Java moderno
Optional<User> optUser = userRepository.findById(id);
if (optUser.isPresent()) {
    User user = optUser.get();
    // ...
}
```

### Convenciones de Nomenclatura

- Variables y métodos: `camelCase`
- Constantes: `UPPER_SNAKE_CASE`
- Clases e interfaces: `PascalCase`
- Paquetes: `lowercase.separated.by.dots`
- Endpoints: `kebab-case` en URLs (`/user-profile`)
- **Idioma:** Inglés para código, español para documentación

### Estructura de Proyecto Spring Boot

```
src/
├── main/
│   ├── java/com/bootcamp/app/
│   │   ├── Application.java         # Punto de entrada
│   │   ├── config/                  # Configuración (Security, Beans, etc.)
│   │   ├── controller/              # REST Controllers
│   │   ├── service/                 # Lógica de negocio
│   │   ├── repository/              # Repositorios JPA
│   │   ├── domain/                  # Entidades JPA
│   │   ├── dto/                     # Data Transfer Objects
│   │   ├── mapper/                  # Mappers MapStruct
│   │   ├── exception/               # Excepciones personalizadas
│   │   └── security/                # JWT, filtros, etc.
│   └── resources/
│       ├── application.yml          # Configuración principal
│       ├── application-dev.yml      # Perfil desarrollo
│       ├── application-prod.yml     # Perfil producción
│       └── db/migration/            # Scripts Flyway
└── test/
    └── java/com/bootcamp/app/
        ├── controller/              # Tests @WebMvcTest
        ├── service/                 # Tests unitarios
        └── integration/             # Tests @SpringBootTest / Testcontainers
```

---

## 🧪 Testing

El bootcamp enseña testing con JUnit 5, Mockito y Testcontainers.

### Estructura de Tests

```java
// tests/controller/UserControllerTest.java
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldReturnUserWhenExists() throws Exception {
        var response = new UserResponse(1L, "test@example.com", "Test User");
        when(userService.findById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        when(userService.findById(99L)).thenThrow(new UserNotFoundException(99L));

        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound());
    }
}
```

---

## 📖 Documentación

### README.md de Semana

Debe incluir:

1. Título y descripción
2. 🎯 Objetivos de aprendizaje
3. 📚 Requisitos previos
4. 🗂️ Estructura de la semana
5. 📝 Contenidos (con enlaces a teoría/prácticas)
6. ⏱️ Distribución del tiempo (8 horas)
7. 📌 Entregables
8. 🔗 Navegación (semana anterior / siguiente)

### Archivos de Teoría

> ⚠️ **Límite:** ~200 líneas por archivo. Priorizar código sobre texto. Un buen diagrama reemplaza tres párrafos.

```markdown
# Título del Tema

## 🎯 Objetivos

- Objetivo 1
- Objetivo 2

## 📋 Contenido

### 1. Introducción

### 2. Conceptos Clave

### 3. Ejemplos Prácticos

### 4. Ejercicios

## 📚 Recursos Adicionales

## ✅ Checklist de Verificación
```

---

## 🎨 Recursos Visuales y Estándares de Diseño

### Formato de Assets

- ✅ Preferir SVG para todos los diagramas, iconos y gráficos
- ❌ NO usar ASCII art para diagramas o visualizaciones
- ✅ Usar PNG/JPG solo para screenshots o fotografías
- ✅ Optimizar imágenes antes de incluirlas

### Criterio para Assets SVG por Semana

Los assets SVG en `0-assets/` de cada semana tienen un propósito educativo específico:

- ✅ Apoyo visual para comprensión de conceptos teóricos
- ✅ Diagramas de arquitectura (capas, hexagonal, flujo de datos)
- ✅ Visualización de procesos (request/response, auth flow, Spring lifecycle)
- ✅ Headers de semana para identificación visual

Reglas de vinculación:

1. Todo SVG debe estar vinculado en al menos un archivo de teoría o práctica
2. Usar sintaxis markdown: `![Descripción](../0-assets/nombre.svg)`
3. Incluir texto alternativo descriptivo para accesibilidad
4. Nombrar archivos descriptivamente: `spring-lifecycle.svg`, `jpa-entity-states.svg`

### Tema Visual

- 🌙 Tema dark para todos los assets visuales
- ❌ Sin degradados (gradients) en diseños
- ✅ Colores sólidos y contrastes claros
- ✅ Paleta consistente basada en verde Spring (#6DB33F)

### Tipografía

- ✅ Fuentes sans-serif exclusivamente
- ✅ Recomendadas: Inter, Roboto, Open Sans, System UI
- ❌ NO usar fuentes serif (Times, Georgia, etc.)

---

## 🌐 Idioma y Nomenclatura

### Código y Comentarios Técnicos

- ✅ Nomenclatura en inglés (variables, métodos, clases, paquetes)
- ✅ Comentarios de código en inglés
- ✅ Usar términos técnicos estándar de la industria

```java
// ✅ CORRECTO — inglés
public Optional<User> findUserByEmail(String email) {
    // Fetch user from database by email address
    return userRepository.findByEmail(email);
}

// ❌ INCORRECTO — español en código
public Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
    // Buscar usuario en la base de datos por correo
    return usuarioRepositorio.buscarPorCorreo(correo);
}
```

### Documentación

- ✅ Documentación en español (READMEs, teoría, guías)
- ✅ Explicaciones y tutoriales en español
- ✅ Comentarios educativos en español cuando expliquen conceptos pedagógicos

```java
// ✅ CORRECTO — código en inglés, comentario educativo en español
// En Spring Data JPA, findById() retorna Optional<T> para evitar NullPointerException
// Si el usuario no existe, el Optional estará vacío y podemos manejarlo con orElseThrow()
public User findUserById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
}
```

---

## 🔐 Mejores Prácticas

### Código Limpio

- Nombres descriptivos y significativos
- Métodos pequeños con una sola responsabilidad
- Comentarios solo cuando sea necesario explicar el "por qué"
- Evitar anidamiento profundo
- Usar early returns

### Seguridad

- Validar TODOS los inputs con Jakarta Bean Validation (`@Valid`)
- Usar hashing seguro para contraseñas (BCrypt via Spring Security)
- No exponer información sensible en mensajes de error
- Usar HTTPS en producción
- Implementar rate limiting
- Nunca exponer entidades JPA directamente en APIs (usar DTOs)
- Usar `@PreAuthorize` o Spring Security para autorización granular

### Rendimiento

- Implementar paginación en listados (`Pageable`)
- Cachear respuestas cuando sea apropiado (`@Cacheable`)
- Usar lazy loading de relaciones por defecto (evitar N+1)
- Connection pooling con HikariCP (incluido en Spring Boot)
- Usar `@Transactional(readOnly = true)` para operaciones de solo lectura

---

## 🤖 Instrucciones para Copilot

Cuando trabajes en este proyecto:

### Límites de Respuesta

1. **Divide respuestas largas**
   - ❌ NUNCA generar respuestas que superen los límites de tokens
   - ✅ SIEMPRE dividir contenido extenso en múltiples entregas
   - ✅ Crear contenido por secciones, esperar confirmación del usuario
   - ✅ Priorizar calidad sobre cantidad en cada entrega

2. **Estrategia de División**
   - Para semanas completas: dividir por carpetas (`teoria` → `practicas` → `proyecto`)
   - Para archivos grandes: dividir por secciones lógicas
   - Siempre indicar claramente qué parte se entrega y qué falta
   - Esperar confirmación del usuario antes de continuar

### Generación de Código

1. **Usa siempre Java 21 moderno**
   - Records para DTOs inmutables
   - `var` para variables locales cuando el tipo es obvio
   - Pattern matching cuando aplique
   - Switch expressions en lugar de switch statements
   - Streams API para operaciones sobre colecciones
   - Optional para valores que pueden ser nulos

2. **Entorno de Desarrollo con Docker**
   - ✅ USAR Docker para evitar problemas con múltiples versiones de Java
   - ✅ `docker compose` para orquestar servicios (API, DB, etc.)
   - ✅ Crear archivos `.env` para configuración de entorno
   - ❌ NO instalar Java o Maven directamente en el sistema anfitrión
   - Estructura recomendada:
     ```
     proyecto/
     ├── docker-compose.yml    # Orquestación de servicios
     ├── Dockerfile            # Imagen de la aplicación
     ├── .env.example          # Variables de entorno (template)
     ├── .env                  # Variables de entorno (ignorado en git)
     └── src/                  # Código fuente Maven/Gradle
     ```
   - Comandos esenciales:

     ```bash
     # Construir y levantar servicios
     docker compose up --build

     # Levantar en background
     docker compose up -d

     # Ver logs
     docker compose logs -f api

     # Ejecutar comandos dentro del contenedor
     docker compose exec api bash

     # Detener servicios
     docker compose down

     # Limpiar todo (incluye volúmenes)
     docker compose down -v
     ```

3. **Base de Datos**
   - ✅ H2 para desarrollo y testing (en memoria, sin configuración)
   - ✅ PostgreSQL 17+ para producción
   - ORM: Spring Data JPA + Hibernate 6.x
   - Migraciones: Flyway
   - Configuración típica por entorno:

     ```yaml
     # application-dev.yml
     spring:
       datasource:
         url: jdbc:h2:mem:devdb
         driver-class-name: org.h2.Driver
       jpa:
         hibernate:
           ddl-auto: validate
       flyway:
         enabled: true

     # application-prod.yml
     spring:
       datasource:
         url: ${DATABASE_URL}
       jpa:
         hibernate:
           ddl-auto: validate
       flyway:
         enabled: true
     ```

4. **Documentación de API**
   - ✅ OpenAPI/Swagger via SpringDoc (integrado, no necesita configuración extra)
   - Acceso automático en `/swagger-ui.html` y `/v3/api-docs`
   - Documentar endpoints con anotaciones `@Operation`, `@ApiResponse`

   ```java
   @Operation(summary = "Get user by ID")
   @ApiResponse(responseCode = "200", description = "User found")
   @ApiResponse(responseCode = "404", description = "User not found")
   @GetMapping("/{id}")
   public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
       return ResponseEntity.ok(userService.findById(id));
   }
   ```

5. **Comenta el código de manera educativa**
   - Explica conceptos para principiantes
   - Incluye referencias a documentación cuando sea útil
   - Usa comentarios que enseñen, no solo que describan

6. **Proporciona ejemplos completos y funcionales**
   - Código que se pueda copiar y ejecutar
   - Incluye casos de uso realistas
   - Muestra tanto lo que se debe hacer como lo que se debe evitar

### Creación de Contenido

1. **Estructura clara y progresiva**
   - De lo simple a lo complejo
   - Conceptos construidos sobre conocimientos previos
   - Repetición espaciada de conceptos clave

2. **Ejemplos del mundo real**
   - Casos de uso prácticos y relevantes
   - Proyectos que los estudiantes puedan mostrar en portfolios
   - Problemas que encontrarán en desarrollo empresarial real

3. **Enfoque moderno**
   - No mencionar características obsoletas de Spring (XML config, etc.)
   - Enfocarse en anotaciones y Java config
   - Usar Spring Boot 3.x con Jakarta EE (no javax)

### Respuestas y Ayuda

1. **Explicaciones claras**
   - Lenguaje simple y directo
   - Evitar jerga innecesaria
   - Proporcionar analogías cuando sea útil

2. **Código comentado**
   - Explicar cada paso importante
   - Destacar conceptos clave de Spring
   - Señalar posibles errores comunes (N+1, LazyInitializationException, etc.)

3. **Recursos adicionales**
   - Referencias a documentación oficial de Spring Boot
   - Enlaces a Spring Guides
   - Artículos relevantes de quality

---

## 📊 Evaluación

Cada semana incluye tres tipos de evidencias:

1. **Conocimiento 🧠 (30%):** Evaluaciones teóricas, cuestionarios
2. **Desempeño 💪 (40%):** Ejercicios prácticos en clase
3. **Producto 📦 (30%):** Proyecto entregable funcional

### Criterios de Aprobación

- Mínimo 70% en cada tipo de evidencia
- Entrega puntual de proyectos
- Código funcional y bien documentado
- Tests pasando (cuando aplique)

---

## 🚀 Metodología de Aprendizaje

### Estrategias Didácticas

- **Aprendizaje Basado en Proyectos (ABP):** Proyectos semanales integradores
- **Práctica Deliberada:** Ejercicios incrementales
- **API Challenges:** Problemas del mundo real
- **Code Review:** Revisión de código entre estudiantes
- **Live Coding:** Sesiones en vivo de programación

### Distribución del Tiempo (8h/semana)

- Teoría: 2 horas
- Prácticas: 3.5 horas
- Proyecto: 2.5 horas

---

## 📚 Referencias Oficiales

- Spring Boot Documentation: https://docs.spring.io/spring-boot/
- Spring Framework Documentation: https://docs.spring.io/spring-framework/
- Spring Data JPA: https://docs.spring.io/spring-data/jpa/
- Spring Security: https://docs.spring.io/spring-security/
- Hibernate ORM: https://hibernate.org/orm/documentation/
- Flyway Documentation: https://flywaydb.org/documentation/
- MapStruct Documentation: https://mapstruct.org/documentation/
- SpringDoc OpenAPI: https://springdoc.org/
- JUnit 5 Documentation: https://junit.org/junit5/docs/current/user-guide/
- Testcontainers: https://testcontainers.com/guides/
- Java 21 Documentation: https://docs.oracle.com/en/java/javase/21/

---

## 🔗 Enlaces Importantes

- Repositorio: https://github.com/ergrato-dev/bc-javaweb-sb
- Documentación general: [\_docs/README.md](../_docs/README.md)
- Primera semana: [bootcamp/week-01-java_moderno_streams_y_records/README.md](../bootcamp/week-01-java_moderno_streams_y_records/README.md)

---

## ✅ Checklist para Nuevas Semanas

Cuando crees contenido para una nueva semana:

- [ ] Crear estructura de carpetas completa
- [ ] `README.md` con objetivos y estructura
- [ ] `rubrica-evaluacion.md` con criterios claros
- [ ] Material teórico en `1-teoria/`
- [ ] Ejercicios guiados en `2-practicas/`
- [ ] Proyecto integrador en `3-proyecto/`
- [ ] Recursos adicionales en `4-recursos/`
- [ ] Glosario de términos en `5-glosario/`
- [ ] Verificar coherencia con semanas anteriores
- [ ] Revisar progresión de dificultad
- [ ] Probar código de ejemplos (compilar y ejecutar)
- [ ] Verificar que `solution/` esté en `.gitignore`

---

## 💡 Notas Finales

- **Prioridad:** Claridad sobre brevedad
- **Enfoque:** Aprendizaje práctico sobre teoría abstracta
- **Objetivo:** Preparar desarrolladores backend listos para trabajar en entornos empresariales
- **Filosofía:** Spring Boot moderno desde el día 1 — anotaciones, Java config, sin XML
- **Scope:** Solo backend. NO generar código de frontend (HTML, CSS, JavaScript, JSP, Thymeleaf views). Este bootcamp produce APIs que serán consumidas por el bootcamp de React en el Proyecto Integrador.

_Última actualización: Marzo 2026 — Versión: 1.0_
