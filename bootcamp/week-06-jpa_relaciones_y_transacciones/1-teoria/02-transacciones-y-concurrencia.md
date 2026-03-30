# JPA — Transacciones y Control de Concurrencia

## 🎯 Objetivos
- Dominar `@Transactional` y sus propiedades
- Entender propagación y aislamiento
- Implementar bloqueo optimista con `@Version`

---

## 1. Transacciones en Spring

```java
@Service
@Transactional(readOnly = true)   // Default: todas las operaciones son read-only
public class PostService {

    @Transactional                // Override: esta operación escribe
    public Post create(PostRequest request) {
        var author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(request.authorId()));
        var post = new Post(request.title(), request.content(), author);
        return postRepository.save(post);
    }

    @Transactional                // Si falla en medio, se hace rollback automático
    public Post addComment(Long postId, CommentRequest request) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        var comment = new Comment(request.content(), request.authorName());
        post.addComment(comment);  // cascade persiste el comment
        return post;               // Spring hace flush + commit al salir del método
    }
}
```

---

## 2. Rollback Automático

Spring hace **rollback automático** ante `RuntimeException` (unchecked exceptions):

```java
@Transactional
public void transferCredits(Long fromId, Long toId, int amount) {
    var from = userRepository.findById(fromId).orElseThrow(…);
    var to   = userRepository.findById(toId).orElseThrow(…);

    from.deductCredits(amount);   // si deductCredits() lanza RuntimeException
    userRepository.save(from);   // → rollback automático: no se guarda nada

    to.addCredits(amount);
    userRepository.save(to);
}
```

> Para que checked exceptions hagan rollback: `@Transactional(rollbackFor = Exception.class)`

---

## 3. Propagación

```java
// REQUIRED (default): usa la transacción existente o crea una nueva
@Transactional(propagation = Propagation.REQUIRED)

// REQUIRES_NEW: siempre crea una nueva transacción independiente (útil para logs)
@Transactional(propagation = Propagation.REQUIRES_NEW)

// NOT_SUPPORTED: suspende la transacción actual (para operaciones que no deben participar)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
```

---

## 4. Bloqueo Optimista con @Version

Previene pérdida de datos cuando dos usuarios editan el mismo registro simultáneamente:

```java
@Entity
public class Post {
    @Id @GeneratedValue private Long id;

    @Version              // Hibernate gestiona este campo automáticamente
    private Long version; // Se incrementa en cada UPDATE

    // ... otros campos
}
```

```java
// Si dos usuarios editan el post 1 a la vez:
// Usuario A lee: post{id=1, version=0, title="Original"}
// Usuario B lee: post{id=1, version=0, title="Original"}
// Usuario A guarda: UPDATE ... WHERE id=1 AND version=0 → OK, version=1
// Usuario B intenta guardar: UPDATE ... WHERE id=1 AND version=0 → 0 rows → OptimisticLockException
```

---

## 5. LazyInitializationException — Solución

```java
// ❌ Error: acceder a lazy collection fuera de transacción
public PostResponse findById(Long id) {
    var post = postRepository.findById(id).orElseThrow(…);
    return new PostResponse(post.getId(), post.getComments().size()); // LazyInitializationException!
}

// ✅ Solución 1: JOIN FETCH en la query
@Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = :id")
Optional<Post> findByIdWithComments(@Param("id") Long id);

// ✅ Solución 2: Permanecer dentro de @Transactional
@Transactional(readOnly = true)
public PostResponse findById(Long id) {
    var post = postRepository.findById(id).orElseThrow(…);
    return new PostResponse(post.getId(), post.getComments().size()); // OK: aún en transacción
}
```

---

## ✅ Checklist de Verificación
- [ ] `@Transactional` en el Service, no en el Controller
- [ ] `@Transactional(readOnly = true)` a nivel de clase, override en escrituras
- [ ] Rollback automático para RuntimeException
- [ ] `@Version` para entidades con edición concurrente
- [ ] Nunca acceder a lazy collections fuera de transacción
