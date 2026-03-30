# Proyecto Semana 05 — API de Biblioteca con Persistencia JPA

## 🎯 Descripción

Construye una API REST para gestionar el catálogo de una biblioteca, con persistencia real en PostgreSQL (prod) y H2 (dev) usando Spring Data JPA.

## 📋 Escenario

La Biblioteca Municipal necesita modernizar su sistema. El nuevo sistema debe persistir libros y autores en una base de datos, soportar búsquedas y paginar los resultados.

## 🏗️ Entidades

```java
@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @Column(length = 100)
    private String author;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    @DecimalMin("0.01")
    private BigDecimal price;

    private boolean available = true;
}
```

## 📌 Requerimientos

### Persistencia
- [ ] **R1:** Entidad `Book` mapeada a tabla `books` con todos los campos
- [ ] **R2:** `BookRepository extends JpaRepository<Book, Long>`
- [ ] **R3:** Perfil `dev` con H2 en memoria + H2 Console habilitada
- [ ] **R4:** Perfil `prod` con PostgreSQL vía Docker Compose

### Queries
- [ ] **R5:** `findByIsbn(String isbn)` → derived query
- [ ] **R6:** `findByGenreAndAvailable(String genre, boolean available)` → derived query
- [ ] **R7:** `@Query("SELECT b FROM Book b WHERE b.price BETWEEN :min AND :max")` — JPQL custom
- [ ] **R8:** `findAll(Pageable pageable)` en endpoint listado: `GET /books?page=0&size=10&sort=title`

### API
- [ ] Todos los endpoints con DTOs (no exponer `Book` directamente)
- [ ] Validación en `BookCreateRequest`
- [ ] `404 Not Found` cuando el libro no existe

## 📂 Estructura Sugerida

```
src/
├── main/
│   ├── java/com/bootcamp/library/
│   │   ├── controller/BookController.java
│   │   ├── service/BookService.java + BookServiceImpl.java
│   │   ├── repository/BookRepository.java
│   │   ├── domain/Book.java
│   │   └── dto/BookCreateRequest.java + BookResponse.java
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml    (H2)
│       └── application-prod.yml   (PostgreSQL)
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Datos persisten en BD (no en memoria) | 25 |
| H2 Console funcional en dev | 10 |
| CRUD completo via API | 25 |
| Al menos una query JPQL custom | 15 |
| Paginación funcionando | 15 |
| Docker Compose para PostgreSQL | 10 |
| **Total** | **100** |
