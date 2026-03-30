# Proyecto Semana 08 — API de Blog con Arquitectura en Capas

## 🎯 Descripción

Construye una API de blog aplicando arquitectura en capas de forma rigurosa: `Controller → Service (con interfaz) → Repository`. DTOs en cada frontera, jerarquía de excepciones y MapStruct avanzado.

## 📋 Escenario

Una plataforma de blogging necesita una API backend para gestionar posts, comentarios y autores. El código debe estar organizado en capas con responsabilidades claramente separadas.

## 🏗️ Entidades

```
Author (1) ──→ (N) Post (1) ──→ (N) Comment
```

## 📌 Requerimientos

### Capas y DTOs
- [ ] **R1:** `AuthorController` sin lógica → llama `AuthorService` (interfaz) → usa `AuthorRepository`
- [ ] **R2:** `PostController` sin lógica → llama `PostService` (interfaz) → usa `PostRepository`
- [ ] **R3:** DTOs: `AuthorCreateRequest`, `PostCreateRequest`, `PostUpdateRequest`, `PostResponse` (con embedded `AuthorSummary`)
- [ ] **R4:** `PostResponse` incluye autor embebido: `{ id, title, content, author: { id, name } }`

### MapStruct
- [ ] **R5:** `PostMapper` mapea `Post` → `PostResponse` incluyendo el autor anidado
- [ ] **R6:** `PostMapper.toResponseList(List<Post>)` mapea lista completa

### Excepciones
- [ ] **R7:** `ResourceNotFoundException` para 404 (post no encontrado, autor no encontrado)
- [ ] **R8:** `BusinessRuleException` para 422 (autor con >10 posts published no puede publicar más)
- [ ] **R9:** `@RestControllerAdvice` con `@ExceptionHandler` para ambas excepciones + `MethodArgumentNotValidException`

### Reglas de Negocio (en Service, no en Controller)
- [ ] **R10:** Al crear post, verificar que el autor existe y no tiene demasiados posts

## 📂 Estructura Requerida

```
src/main/java/com/bootcamp/blog/
├── controller/        (PostController, AuthorController, CommentController)
├── service/           (PostService, PostServiceImpl, AuthorService, AuthorServiceImpl)
├── repository/        (PostRepository, AuthorRepository, CommentRepository)
├── domain/            (Post, Author, Comment — @Entity)
├── dto/               (todos los Records Request/Response)
├── mapper/            (PostMapper, AuthorMapper)
└── exception/         (ResourceNotFoundException, BusinessRuleException, GlobalExceptionHandler)
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Controller sin lógica de negocio | 20 |
| Services con interfaz | 15 |
| DTOs diferenciados y MapStruct funcionando | 20 |
| `PostResponse` con autor anidado mapeado | 15 |
| Jerarquía de excepciones con `@ControllerAdvice` | 20 |
| Reglas de negocio en service (no controller) | 10 |
| **Total** | **100** |
