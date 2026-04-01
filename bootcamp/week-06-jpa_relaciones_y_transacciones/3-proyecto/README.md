# Proyecto Semana 06 — API de Blog con Relaciones JPA

## 🎯 Objetivo

Construir una API REST para un blog que modela relaciones entre `Author`, `Post`, `Comment` y `Tag`, aplicando transacciones correctas y auditoría automática.

## 📋 Contexto

Una plataforma de blog donde múltiples autores publican posts. Cada post puede tener comentarios y etiquetas. Los timestamps de creación/modificación se gestionan automáticamente.

---

## 🗂️ Estructura del Starter

```
starter/src/main/java/com/bootcamp/
├── BlogApiApplication.java          # @EnableJpaAuditing habilitado
├── controller/
│   └── PostController.java          # TODO: implementar
├── domain/
│   ├── Author.java                  # @OneToMany posts, @CreatedDate
│   ├── Post.java                    # @ManyToOne author, @OneToMany comments, @ManyToMany tags
│   ├── Comment.java                 # @ManyToOne post, @CreatedDate
│   └── Tag.java                     # @ManyToMany posts
├── dto/
│   ├── PostCreateRequest.java       # (title, content, authorId) con validación
│   ├── PostSummaryResponse.java     # id, title, published, authorName, createdAt
│   ├── PostDetailResponse.java      # + content, updatedAt, List<CommentResponse>
│   ├── CommentCreateRequest.java    # (content, authorName) con validación
│   └── CommentResponse.java        # id, content, authorName, createdAt
├── exception/
│   ├── PostNotFoundException.java
│   ├── AuthorNotFoundException.java
│   └── GlobalExceptionHandler.java
├── repository/
│   ├── PostRepository.java          # TODO: implementar 3 queries
│   └── AuthorRepository.java       # findByEmail, existsByEmail listos
└── service/
    └── PostService.java             # TODO: implementar 6 métodos
```

---

## 📝 Tareas

### Tarea 1: Completar PostRepository

Agrega los tres métodos faltantes:

```java
// Paginación de posts publicados
Page<Post> findByPublishedTrue(Pageable pageable);

// POST + AUTHOR en una sola query (evita N+1)
@Query("SELECT p FROM Post p JOIN FETCH p.author WHERE p.id = :id")
Optional<Post> findByIdWithAuthor(@Param("id") Long id);

// POST + AUTHOR + COMMENTS (usa DISTINCT para evitar duplicados)
@Query("SELECT DISTINCT p FROM Post p JOIN FETCH p.author LEFT JOIN FETCH p.comments WHERE p.id = :id")
Optional<Post> findByIdWithAuthorAndComments(@Param("id") Long id);
```

### Tarea 2: Implementar PostService

Implementa los métodos marcados con `// TODO`. El service ya tiene `@Transactional(readOnly = true)` a nivel de clase. Cada método de escritura tiene `@Transactional` individual que lo sobreescribe.

| Método | Descripción |
|--------|-------------|
| `findPublished(pageable)` | Retorna posts publicados paginados como `PostSummaryResponse` |
| `findById(id)` | Post con author+comments. Lanza `PostNotFoundException` si no existe |
| `create(request)` | Verifica author, crea Post, retorna `PostSummaryResponse` |
| `addComment(postId, request)` | Usa `post.addComment()` para bidireccionalidad, retorna `CommentResponse` |
| `publish(postId)` | Setea `published=true`, retorna `PostSummaryResponse` |
| `delete(postId)` | Elimina post (orphanRemoval borrará sus comments) |

### Tarea 3: Implementar PostController

Implementa los 6 endpoints en `PostController.java`:

| Método | Endpoint | Request | Response |
|--------|----------|---------|----------|
| GET | `/api/posts` | `Pageable` | `Page<PostSummaryResponse>` 200 |
| GET | `/api/posts/{id}` | — | `PostDetailResponse` 200 |
| POST | `/api/posts` | `@Valid PostCreateRequest` | `PostSummaryResponse` 201 |
| POST | `/api/posts/{id}/comments` | `@Valid CommentCreateRequest` | `CommentResponse` 201 |
| PUT | `/api/posts/{id}/publish` | — | `PostSummaryResponse` 200 |
| DELETE | `/api/posts/{id}` | — | 204 No Content |

---

## 🔧 Ejecutar el Proyecto

```bash
cd starter
./mvnw spring-boot:run
```

- API disponible en: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:blogdb`)

## 🧪 Ejecutar Tests

```bash
./mvnw test
```

Los tests del `PostRepositoryTest` verifican las 3 queries del repositorio y las relaciones JPA.

---

## 🌐 Ejemplos de Uso

```bash
# Crear autor (via SQL en H2 Console o data.sql)
# El proyecto usa create-drop, poblar con H2 Console:
# INSERT INTO authors (name, email, bio, created_at) VALUES ('Alice', 'alice@blog.com', 'Writer', NOW());

# Crear post
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot Tips","content":"Content here","authorId":1}'

# Listar posts publicados
curl http://localhost:8080/api/posts?page=0&size=10

# Ver post con comments
curl http://localhost:8080/api/posts/1

# Agregar comment
curl -X POST http://localhost:8080/api/posts/1/comments \
  -H "Content-Type: application/json" \
  -d '{"content":"Great post!","authorName":"reader1"}'

# Publicar post
curl -X PUT http://localhost:8080/api/posts/1/publish

# Eliminar post (cascade elimina comments)
curl -X DELETE http://localhost:8080/api/posts/1
```

---

## ✅ Criterios de Evaluación

- [ ] Los 5 tests de `PostRepositoryTest` pasan
- [ ] `GET /api/posts` retorna solo posts con `published: true`
- [ ] `GET /api/posts/{id}` incluye lista de `comments` en la respuesta
- [ ] `POST /api/posts` retorna 201 con JSON del post creado
- [ ] `DELETE /api/posts/{id}` retorna 204 y elimina los comments en cascade
- [ ] Campos `createdAt` presentes en JSON de respuesta
- [ ] `PostService` usa `@Transactional(readOnly=true)` a nivel de clase
