# Proyecto Semana 05 — API de Tienda Online con JPA

> Construye una API REST CRUD completa con Spring Data JPA, H2 en desarrollo
> y los cimientos para conectar PostgreSQL en producción.

---

## 🎯 Objetivo

Implementar los métodos del repositorio y del servicio de una API de productos,
aplicando `JpaRepository`, JPQL custom, paginación con `Pageable` y tests con
`@DataJpaTest`.

---

## 📦 Contexto

Una tienda online necesita un backend para gestionar su catálogo de productos.
Tu tarea es completar la capa de acceso a datos y utilizarla desde el servicio
y el controller.

---

## 🗂️ Estructura del Starter

```
starter/
├── pom.xml
└── src/
    ├── main/java/com/bootcamp/
    │   ├── StoreApiApplication.java         ← Punto de entrada (no modificar)
    │   ├── controller/
    │   │   └── ProductController.java       ← TODO: implementar endpoints
    │   ├── domain/
    │   │   └── Product.java                 ← Entidad JPA (lista)
    │   ├── dto/
    │   │   ├── ProductRequest.java          ← DTO de entrada con validación (lista)
    │   │   └── ProductResponse.java         ← DTO de salida (lista)
    │   ├── exception/
    │   │   ├── GlobalExceptionHandler.java  ← Manejo de errores (listo)
    │   │   └── ProductNotFoundException.java ← Excepción personalizada (lista)
    │   ├── repository/
    │   │   └── ProductRepository.java       ← TODO: agregar queries
    │   └── service/
    │       └── ProductService.java          ← TODO: implementar lógica
    └── resources/
        └── application.yml                  ← H2 configurado (no modificar)
    test/java/com/bootcamp/repository/
        └── ProductRepositoryTest.java        ← Tests con @DataJpaTest (listos)
```

---

## ✅ Tareas

### 1. `ProductRepository.java` — Agrega los métodos custom

```java
// 1. Derived query method con paginación
Page<Product> findByCategory(String category, Pageable pageable);

// 2. JPQL con LIKE para búsqueda parcial (case-insensitive)
@Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
List<Product> searchByName(@Param("name") String name);

// 3. JPQL con BETWEEN para rango de precio
@Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
List<Product> findByPriceBetween(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

// 4. JPQL UPDATE con @Modifying — decrementa stock si es suficiente
@Modifying
@Transactional
@Query("UPDATE Product p SET p.stock = p.stock - :qty WHERE p.id = :id AND p.stock >= :qty")
int decrementStock(@Param("id") Long id, @Param("qty") int qty);
```

### 2. `ProductService.java` — Implementa los métodos

Cada método tiene un comentario `// TODO` con instrucciones detalladas. Implementa:
- `findAll(String category, Pageable pageable)` — retorna `Page<ProductResponse>`
- `findById(Long id)` — retorna `ProductResponse` o lanza `ProductNotFoundException`
- `create(ProductRequest request)` — crea y retorna `ProductResponse`
- `update(Long id, ProductRequest request)` — actualiza y retorna `ProductResponse`
- `delete(Long id)` — elimina o lanza `ProductNotFoundException`
- `decrementStock(Long id, int quantity)` — decrementa stock
- `searchByName(String name)` — retorna `List<ProductResponse>`

### 3. `ProductController.java` — Implementa los endpoints

Añade `@RestController` y `@RequestMapping("/api/products")`. Implementa:

| Método | Ruta | Descripción | Respuesta |
|--------|------|-------------|-----------|
| GET | `/api/products` | Lista paginada, filtrable por `?category=` | `Page<ProductResponse>` |
| GET | `/api/products/search` | Busca por nombre parcial `?name=` | `List<ProductResponse>` |
| GET | `/api/products/{id}` | Obtiene un producto | `200` o `404` |
| POST | `/api/products` | Crea producto, body `@Valid` | `201 Created` + `Location` |
| PUT | `/api/products/{id}` | Actualiza producto, body `@Valid` | `200` o `404` |
| DELETE | `/api/products/{id}` | Elimina producto | `204 No Content` |
| PATCH | `/api/products/{id}/stock` | Decrementa stock `?quantity=` | `200` o `404` |

---

## 🚀 Cómo Ejecutar

```bash
# Con Maven:
cd starter
./mvnw spring-boot:run

# La API estará disponible en: http://localhost:8080
# H2 Console: http://localhost:8080/h2-console
#    JDBC URL: jdbc:h2:mem:storedb
```

---

## 🧪 Tests

Los tests de repositorio ya están escritos en `ProductRepositoryTest.java`.
Deben pasar **todos** al completar `ProductRepository.java`:

```bash
./mvnw test
```

Los 7 tests verifican: `findByCategory`, `searchByName`, `findByPriceBetween`,
`decrementStock` (con stock suficiente e insuficiente), y `existsByName`.

---

## 🔍 Ejemplos de API

```bash
# Crear producto
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop Pro","description":"Potente laptop","price":999.99,"stock":10,"category":"Electronics"}'

# Listar paginado
curl "http://localhost:8080/api/products?page=0&size=5&sort=price,asc"

# Filtrar por categoría
curl "http://localhost:8080/api/products?category=Electronics&page=0&size=10"

# Buscar por nombre
curl "http://localhost:8080/api/products/search?name=lap"

# Decrementar stock
curl -X PATCH "http://localhost:8080/api/products/1/stock?quantity=2"
```

---

## 📊 Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Todos los tests de repositorio pasan | 30 pts |
| CRUD completo funcional vía API | 30 pts |
| Paginación y filtro por categoría | 20 pts |
| `@Query` JPQL custom implementadas | 20 pts |
| **Total** | **100 pts** |

---

## 📌 Entregables

- [ ] `ProductRepository.java` con los 4 métodos implementados
- [ ] `ProductService.java` con todos los métodos implementados
- [ ] `ProductController.java` con los 7 endpoints implementados
- [ ] `./mvnw test` con todos los tests en verde
