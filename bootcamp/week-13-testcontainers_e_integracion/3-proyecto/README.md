# Proyecto Semana 13 — Inventory API con Testcontainers

## 🎯 Objetivo

Escribir tests de integración reales usando **Testcontainers + PostgreSQL** para una API de inventario ya implementada, aplicando los tres tipos de test: repositorio (`@DataJpaTest`), servicio (Mockito) y full HTTP (`@SpringBootTest`).

---

## 📦 Dominio — Inventory API

| Entidad | Campos                                          |
|---------|-------------------------------------------------|
| Product | id, name, sku, price, stock, category, createdAt |

**Endpoints disponibles:**

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/api/products` | Listar (con `?name=` search) |
| GET | `/api/products/{id}` | Obtener por ID |
| POST | `/api/products` | Crear producto |
| PUT | `/api/products/{id}` | Actualizar |
| PATCH | `/api/products/{id}/stock/add` | Añadir stock |
| PATCH | `/api/products/{id}/stock/remove` | Quitar stock |
| DELETE | `/api/products/{id}` | Eliminar |

**Reglas de negocio del dominio:**
- El SKU debe ser único (validación en `ProductService.create()`)
- `addStock(int)` — no tiene límite superior
- `removeStock(int)` — lanza `IllegalStateException` si stock insuficiente
- El controller devuelve **409 Conflict** ante `IllegalStateException`

---

## 🗂️ Tu Tarea

El código de producción está **completo**. Tu tarea es implementar los tres archivos de test:

### 1. `ProductRepositoryTest.java` — @DataJpaTest + Testcontainers
> Prueba las queries custom del repositorio contra PostgreSQL real

Métodos a testear:
- `findBySku(String sku)` — encuentra el producto correcto
- `existsBySku(String sku)` — retorna true/false
- `searchByName(String pattern)` — búsqueda case-insensitive
- `findByCategory(String category)` — filtra por categoría

### 2. `ProductServiceTest.java` — @ExtendWith(MockitoExtension.class)
> Prueba la lógica de negocio del service en aislamiento total (sin BD)

Clases anidadas a completar:
- `FindAll` — lista sin filtro vs. con filtro por nombre
- `FindById` — happy path + not found
- `Create` — ArgumentCaptor para verificar el objeto guardado + SKU duplicado
- `AddStock` — verifica el stock final con ArgumentCaptor
- `RemoveStock` — happy path + stock insuficiente
- `Delete` — verifica deleteById + not found

### 3. `ProductApiIntegrationTest.java` — @SpringBootTest (extends AbstractIntegrationTest)
> Prueba flujos HTTP completos contra PostgreSQL real via Testcontainers

Tests a completar:
- `POST /api/products` — 201 Created, 400 inválido, 400 SKU duplicado
- `GET /api/products/{id}` — 200 OK, 404 not found
- Stock management — add (200+stock correcto), remove (200+stock correcto), 409 insufficient
- Flujo completo CRUD lifecycle

---

## 🚀 Cómo Ejecutar

**Requisito previo: Docker corriendo**

```bash
# Ejecutar todos los tests (requiere Docker para los de integración)
./mvnw test

# Solo tests unitarios (sin Docker)
./mvnw test -Dgroups="unit" # o excluyendo Testcontainers

# Ver reporte de cobertura
./mvnw test jacoco:report
open target/site/jacoco/index.html
```

> Los tests con `@Testcontainers` descargan automáticamente la imagen `postgres:17-alpine` la primera vez. Las siguientes ejecuciones reutilizan la imagen en caché.

---

## 📁 Estructura del Proyecto

```
starter/
├── src/
│   ├── main/java/com/bootcamp/inventory/
│   │   ├── InventoryApiApplication.java
│   │   ├── controller/ProductController.java      ← ya implementado
│   │   ├── service/ProductService.java             ← ya implementado
│   │   ├── repository/ProductRepository.java       ← ya implementado
│   │   ├── domain/Product.java                     ← ya implementado
│   │   ├── dto/Dtos.java                           ← ya implementado
│   │   └── exception/                              ← ya implementado
│   └── test/java/com/bootcamp/inventory/
│       ├── AbstractIntegrationTest.java            ← ya implementado ← BASE
│       ├── repository/
│       │   └── ProductRepositoryTest.java          ← 🎯 TU TAREA
│       ├── service/ (en raíz del paquete)
│       │   └── ProductServiceTest.java             ← 🎯 TU TAREA
│       └── ProductApiIntegrationTest.java          ← 🎯 TU TAREA
└── pom.xml
```

---

## 📊 Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| `ProductRepositoryTest` — los 4 métodos implementados y pasando | 25 pts |
| `ProductServiceTest` — todas las clases anidadas implementadas | 35 pts |
| `ProductApiIntegrationTest` — todos los tests implementados | 30 pts |
| Cobertura ≥ 70% (reporte JaCoCo) | 10 pts |
| **Total** | **100 pts** |

---

## 💡 Hints

```java
// Hint 1: restTemplate.exchange() para PATCH (patchForObject no soporta body en todas las versiones)
HttpEntity<StockAdjustRequest> req = new HttpEntity<>(new StockAdjustRequest(20));
ResponseEntity<ProductResponse> response = restTemplate.exchange(
    "/api/products/" + id + "/stock/add",
    HttpMethod.PATCH, req, ProductResponse.class);

// Hint 2: TestEntityManager en @DataJpaTest
@Autowired EntityManager em;
Product saved = em.persist(product);
em.flush();
em.clear(); // importante: fuerza un nuevo SELECT en el siguiente find

// Hint 3: ArgumentCaptor con BDDMockito
given(productRepository.save(productCaptor.capture())).willReturn(product);
productService.create(request);
assertThat(productCaptor.getValue().getSku()).isEqualTo("SKU-X");
```
