# Proyecto Semana 02 — Library Catalog con IoC/DI

## 🎯 Descripción

Completa un catálogo de biblioteca implementando la lógica de negocio del `BookService` y `BookRepository`. El container IoC de Spring gestiona todos los beans y sus dependencias.

## 📋 Escenario

Una biblioteca digital necesita un sistema para consultar y filtrar su catálogo de libros. El `BookService` aplica lógica de negocio (ordenamiento, búsqueda, resúmenes) sobre los datos que provee el `BookRepository`.

## 🏗️ Estructura del Proyecto

```
src/main/java/com/bootcamp/
├── config/
│   └── LibraryProperties.java       (@ConfigurationProperties)
├── model/
│   └── Book.java                    (record — título, ISBN, autor, categoría)
├── repository/
│   └── BookRepository.java          (@Repository — in-memory)
├── service/
│   └── BookService.java             (@Service — lógica de negocio + TODO)
└── LibraryCatalogApplication.java   (@SpringBootApplication)
```

## 📌 Requerimientos

### BookRepository — implementar los TODOs

- [ ] **R1:** `findAll()` — retorna la lista de todos los libros
- [ ] **R2:** `findByIsbn(String isbn)` — busca por ISBN usando `stream().filter().findFirst()`
- [ ] **R3:** `findAvailable()` — filtra libros disponibles con `stream().filter().toList()`
- [ ] **R4:** `findByCategory(String category)` — filtra por categoría ignorando mayúsculas/minúsculas

### BookService — implementar los TODOs

- [ ] **R5:** Constructor que recibe `BookRepository` (inyección por constructor, campo `final`)
- [ ] **R6:** `@PostConstruct` que imprime: `"BookService initialized — catalog ready with X books"`
- [ ] **R7:** `getAllBooksSortedByTitle()` — ordena con `Comparator.comparing(Book::title)`
- [ ] **R8:** `findByIsbn(String isbn)` — delega al repository
- [ ] **R9:** `getCatalogSummary()` — usa Streams para calcular: totalBooks, availableCount, categories (distintas, ordenadas)

### Técnicos
- [ ] Constructor injection en `BookService` (sin `@Autowired` en campo)
- [ ] Campo `bookRepository` declarado como `final`
- [ ] `LibraryProperties` bindeado correctamente desde `application.yml`
- [ ] Todos los tests de `BookServiceTest` pasando

## ▶️ Ejecutar el Proyecto

```bash
cd 3-proyecto/starter
mvn spring-boot:run
```

Al arrancar verás en consola:
```
BookService initialized — catalog ready with 6 books
```

## 🧪 Ejecutar Tests

```bash
mvn test
```

Los tests en `BookServiceTest` verifican cada método del servicio.

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| R1-R4: BookRepository completo | 20 |
| R5-R6: Constructor injection + @PostConstruct | 20 |
| R7-R9: BookService completo | 30 |
| Todos los tests pasando | 20 |
| Sin `@Autowired` en campos (solo constructor injection) | 10 |
| **Total** | **100** |
