package com.bootcamp.inventory.controller;

import com.bootcamp.inventory.dto.Dtos.*;
import com.bootcamp.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public Page<ProductResponse> findAll(
      @RequestParam(required = false) String name,
      Pageable pageable) {
    if (name != null && !name.isBlank()) {
      return productService.searchByName(name, pageable);
    }
    return productService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ProductResponse findById(@PathVariable Long id) {
    return productService.findById(id);
  }

  @PostMapping
  public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest request) {
    var created = productService.create(request);
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(created.id()).toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
    return productService.update(id, request);
  }

  @PatchMapping("/{id}/stock/add")
  public ProductResponse addStock(@PathVariable Long id, @Valid @RequestBody StockAdjustRequest request) {
    return productService.addStock(id, request);
  }

  @PatchMapping("/{id}/stock/remove")
  public ProductResponse removeStock(@PathVariable Long id, @Valid @RequestBody StockAdjustRequest request) {
    return productService.removeStock(id, request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
