package com.bootcamp.inventory;

import com.bootcamp.inventory.domain.Product;
import com.bootcamp.inventory.dto.Dtos.*;
import com.bootcamp.inventory.exception.ProductNotFoundException;
import com.bootcamp.inventory.repository.ProductRepository;
import com.bootcamp.inventory.service.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

/**
 * ProductServiceTest — Tests unitarios del servicio con Mockito.
 *
 * Sin Spring context, sin base de datos real: puro Java + Mockito.
 * ProductRepository está mockeado para aislar la lógica del Service.
 *
 * INSTRUCCIONES:
 * 1. Implementa los TODOs de cada método de test
 * 2. Sigue el patrón given / when / then
 * 3. Usa given().willReturn() de BDDMockito (ya importado)
 * 4. Usa assertThat() de AssertJ (ya importado)
 * 5. Ejecutar con: mvn test (no requiere Docker)
 *
 * PATRONES CLAVE:
 * - ArgumentCaptor: captura el argumento que pasó al save()
 * - verify(): verifica cuántas veces llamamos a un método del mock
 * - assertThatThrownBy(): verifica que se lanza una excepción
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService Unit Tests")
class ProductServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @Captor
  private ArgumentCaptor<Product> productCaptor;

  // Helper — crea un Product de prueba
  private Product makeProduct(Long id, String name, String sku) {
    return Product.builder()
        .id(id)
        .name(name)
        .sku(sku)
        .price(BigDecimal.valueOf(19.99))
        .stock(100)
        .category("TECH")
        .build();
  }

  // ============================================
  // findAll()
  // ============================================

  @Nested
  @DisplayName("findAll()")
  class FindAll {

    @Test
    @DisplayName("returns all products as ProductResponse list")
    void returnsAllProducts() {
      // TODO: Implementar
      // given: productRepository.findAll() retorna una lista con 2 productos
      // when: productService.findAll(null) — sin filtro de búsqueda
      // then: la lista resultado tiene 2 elementos

      // given
      // var p1 = makeProduct(1L, "Laptop", "LAP-001");
      // var p2 = makeProduct(2L, "Mouse", "MOU-002");
      // given(productRepository.findAll()).willReturn(List.of(p1, p2));

      // when
      // var result = productService.findAll(null);

      // then
      // assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("uses searchByName when name parameter provided")
    void useSearchWhenNameProvided() {
      // TODO: Implementar
      // given: productRepository.searchByName("%laptop%") retorna lista con 1
      // producto
      // when: productService.findAll("laptop")
      // then: resultado tiene 1 elemento
      // verify: productRepository.searchByName() fue llamado, NOT findAll()
    }
  }

  // ============================================
  // findById()
  // ============================================

  @Nested
  @DisplayName("findById()")
  class FindById {

    @Test
    @DisplayName("returns ProductResponse when product exists")
    void returnsProduct() {
      // TODO: Implementar
      // given: productRepository.findById(1L) retorna Optional.of(makeProduct(1L,
      // ...))
      // when: productService.findById(1L)
      // then: resultado.id() == 1L y resultado.name() == nombre esperado
    }

    @Test
    @DisplayName("throws ProductNotFoundException when product not found")
    void throwsWhenNotFound() {
      // TODO: Implementar
      // given: productRepository.findById(99L) retorna Optional.empty()
      // when/then: assertThatThrownBy(() -> productService.findById(99L))
      // .isInstanceOf(ProductNotFoundException.class)
    }
  }

  // ============================================
  // create()
  // ============================================

  @Nested
  @DisplayName("create()")
  class Create {

    @Test
    @DisplayName("saves product and maps to response")
    void savesProduct() {
      // TODO: Implementar con ArgumentCaptor
      // given:
      // - productRepository.existsBySku("SKU-NEW") retorna false
      // - productRepository.save(any()) retorna el producto guardado (capturado)
      // when: productService.create(new ProductCreateRequest("Teclado", "SKU-NEW",
      // 49.99, 30, "PERIPH"))
      // then (about the captor):
      // - productCaptor.getValue().getName() == "Teclado"
      // - productCaptor.getValue().getSku() == "SKU-NEW"
      // - productCaptor.getValue().getStock() == 30
    }

    @Test
    @DisplayName("throws IllegalArgumentException when SKU already exists")
    void throwsWhenDuplicateSku() {
      // TODO: Implementar
      // given: productRepository.existsBySku("SKU-DUP") retorna true
      // then: assertThatThrownBy con IllegalArgumentException es lanzada
    }
  }

  // ============================================
  // addStock()
  // ============================================

  @Nested
  @DisplayName("addStock()")
  class AddStock {

    @Test
    @DisplayName("increases stock by quantity")
    void increasesStock() {
      // TODO: Implementar
      // given: producto con stock=50
      // when: productService.addStock(1L, new StockAdjustRequest(20))
      // then: verify save fue llamado, capturar con productCaptor
      // y verificar que el stock capturado es 70
    }
  }

  // ============================================
  // removeStock()
  // ============================================

  @Nested
  @DisplayName("removeStock()")
  class RemoveStock {

    @Test
    @DisplayName("decreases stock by quantity")
    void decreasesStock() {
      // TODO: Implementar similar a addStock pero restando
    }

    @Test
    @DisplayName("throws IllegalStateException when stock insufficient")
    void throwsOnInsufficientStock() {
      // TODO: Implementar
      // given: producto con stock=10
      // when: productService.removeStock(1L, new StockAdjustRequest(50))
      // then: assertThatThrownBy con IllegalStateException
      // (la lanza product.removeStock() → propagada por el service)
    }
  }

  // ============================================
  // delete()
  // ============================================

  @Nested
  @DisplayName("delete()")
  class Delete {

    @Test
    @DisplayName("calls deleteById when product exists")
    void deletesProduct() {
      // TODO: Implementar
      // given: productRepository.findById(1L) retorna Optional.of(...)
      // when: productService.delete(1L)
      // then: verify(productRepository).deleteById(1L) fue llamado UNA vez
    }

    @Test
    @DisplayName("throws ProductNotFoundException when product does not exist")
    void throwsWhenNotFound() {
      // TODO: Implementar
      // given: productRepository.findById(99L) retorna Optional.empty()
      // then: assertThatThrownBy con ProductNotFoundException
    }
  }
}
