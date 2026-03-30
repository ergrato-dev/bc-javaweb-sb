# Spring Data JPA — @DataJpaTest y Testing de Repositorios

## 🎯 Objetivos
- Testear repositorios con `@DataJpaTest` (H2 en memoria)
- Verificar queries custom y derived query methods

---

## 1. Slice @DataJpaTest

```java
@DataJpaTest     // carga solo JPA: entidades, repositorios, H2, TransactionManager
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    // @DataJpaTest activa rollback automático por test — datos no persisten entre tests
}
```

`@DataJpaTest` NO carga:
- `@Service`, `@Controller`, `@Component`
- Configuración de seguridad
- Actuator

---

## 2. Crear Datos de Prueba

```java
@DataJpaTest
class ProductRepositoryTest {

    @Autowired TestEntityManager em;          // utilidad para persist/flush
    @Autowired ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        em.persist(new Product("Laptop",     new BigDecimal("999.99"),  10, "Electronics"));
        em.persist(new Product("Mouse",      new BigDecimal("29.99"),   50, "Electronics"));
        em.persist(new Product("Desk Chair", new BigDecimal("199.99"),   5, "Furniture"));
        em.flush();
    }

    @Test
    void findByCategory_shouldReturnMatchingProducts() {
        var electronics = productRepository.findByCategory("Electronics");

        assertThat(electronics).hasSize(2);
        assertThat(electronics).extracting(Product::getCategory)
                               .containsOnly("Electronics");
    }
}
```

---

## 3. Testear Paginación

```java
@Test
void findAll_withPageable_shouldReturnPagedResults() {
    var page = productRepository.findAll(PageRequest.of(0, 2, Sort.by("price").ascending()));

    assertThat(page.getContent()).hasSize(2);
    assertThat(page.getTotalElements()).isEqualTo(3);
    assertThat(page.getContent().get(0).getPrice()).isLessThan(
            page.getContent().get(1).getPrice());
}
```

---

## 4. Testear Queries @Query

```java
@Test
void findCheaperThan_shouldReturnProductsBelowPrice() {
    var cheap = productRepository.findByPriceLessThan(new BigDecimal("100.00"));

    assertThat(cheap).hasSize(1);
    assertThat(cheap.get(0).getName()).isEqualTo("Mouse");
}

@Test
void existsByName_shouldReturnTrueForExistingProduct() {
    assertThat(productRepository.existsByName("Laptop")).isTrue();
    assertThat(productRepository.existsByName("Nonexistent")).isFalse();
}
```

---

## 5. Testear @Modifying

```java
@Test
void incrementStock_shouldUpdateStockValue() {
    var laptop = em.persist(new Product("Laptop", new BigDecimal("999"), 10, "Electronics"));
    em.flush();

    int updated = productRepository.incrementStock(laptop.getId(), 5);

    assertThat(updated).isEqualTo(1);
    em.refresh(laptop);   // refresca la entidad desde BD
    assertThat(laptop.getStock()).isEqualTo(15);
}
```

---

## ✅ Checklist de Verificación
- [ ] `@DataJpaTest` sin `@SpringBootTest` para tests de repositorio
- [ ] `TestEntityManager` para insertar datos de prueba
- [ ] `em.flush()` antes de ejecutar queries
- [ ] Cada test es independiente (rollback automático)
- [ ] Assertions con AssertJ (`assertThat`) — más legible que JUnit asserts
