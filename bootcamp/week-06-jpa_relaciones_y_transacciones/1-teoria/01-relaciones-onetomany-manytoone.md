# JPA Relaciones — @OneToMany y @ManyToOne

## 🎯 Objetivos
- Mapear relaciones `@OneToMany` / `@ManyToOne`
- Entender Lazy vs Eager loading y evitar N+1
- Configurar cascadas y orphanRemoval

---

## 1. Relación @OneToMany / @ManyToOne

```java
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)          // 1 autor tiene muchos posts
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "post",               // 1 post tiene muchos comments
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // Método helper para manejar la bidireccionalidad
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }
}

@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
```

---

## 2. Lazy vs Eager: Elegir bien

| Tipo | Comportamiento | Cuándo usar |
|------|---------------|-------------|
| `LAZY` (defecto en `@OneToMany`) | Carga bajo demanda | Siempre en colecciones |
| `EAGER` (defecto en `@ManyToOne`) | Carga inmediata con JOIN | Solo si siempre necesitas el dato |

> ⚠️ **N+1 Problem**: Si cargas 100 Posts con LAZY y luego accedes a cada `post.getAuthor()`, JPA hace 101 queries. Solución: **JOIN FETCH**.

```java
// ❌ Genera N+1 queries
List<Post> posts = postRepository.findAll();
posts.forEach(p -> System.out.println(p.getAuthor().getName())); // N queries extra

// ✅ Un solo JOIN — todos los datos en una query
@Query("SELECT p FROM Post p JOIN FETCH p.author WHERE p.published = true")
List<Post> findPublishedWithAuthor();
```

---

## 3. CascadeType y orphanRemoval

```java
@OneToMany(mappedBy = "post",
           cascade = CascadeType.ALL,    // persiste, actualiza y elimina comments junto con el post
           orphanRemoval = true)         // elimina comments huérfanos (sin post)
private List<Comment> comments = new ArrayList<>();
```

| CascadeType | Efecto |
|-------------|--------|
| `PERSIST` | Al guardar Post, guarda también sus Comments |
| `REMOVE` | Al eliminar Post, elimina sus Comments |
| `ALL` | Todos los cascades |
| `orphanRemoval = true` | Elimina Comment si se remueve de la lista del Post |

---

## 4. @ManyToMany

```java
@Entity
public class Post {
    @ManyToMany
    @JoinTable(
        name = "post_tags",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}

@Entity
public class Tag {
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();
}
```

> Para `@ManyToMany` con atributos adicionales en la tabla intermedia, usar una entidad explícita con `@ManyToOne` en cada lado.

---

## ✅ Checklist de Verificación
- [ ] `FetchType.LAZY` en todas las relaciones por defecto
- [ ] `mappedBy` en el lado no-owning de la relación
- [ ] Métodos helper para manejar bidireccionalidad
- [ ] JOIN FETCH cuando necesitas datos relacionados en la misma query
- [ ] `CascadeType.ALL + orphanRemoval = true` para composición (Post → Comments)
