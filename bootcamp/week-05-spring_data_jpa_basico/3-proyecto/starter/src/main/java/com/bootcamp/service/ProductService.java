package com.bootcamp.service;

import com.bootcamp.domain.Product;
import com.bootcamp.dto.ProductRequest;
import com.bootcamp.dto.ProductResponse;
import com.bootcamp.exception.ProductNotFoundException;
import com.bootcamp.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(),
                p.getPrice(), p.getStock(), p.getCategory(), p.getCreatedAt());
    }

    /**
     * TODO:
     *  1. If category is not null: return productRepository.findByCategory(category, pageable)
     *     mapped to Page<ProductResponse>
     *  2. Otherwise: return productRepository.findAll(pageable) mapped to Page<ProductResponse>
     *  Hint: use page.map(this::toResponse)
     */
    public Page<ProductResponse> findAll(String category, Pageable pageable) {
        // TODO: Implement with optional category filter and pagination
        return Page.empty(pageable);
    }

    /**
     * TODO:
     *  1. Use productRepository.findById(id).orElseThrow(ProductNotFoundException::new)
     *  2. Map Product to ProductResponse with toResponse()
     */
    public ProductResponse findById(Long id) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  1. Create new Product from request fields
     *  2. Save with productRepository.save()
     *  3. Return toResponse(saved)
     */
    @Transactional
    public ProductResponse create(ProductRequest request) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  1. Find existing product by id (throw ProductNotFoundException if not found)
     *  2. Update all fields from request
     *  3. Save and return toResponse(updated)
     */
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  1. Check product exists (throw ProductNotFoundException if not)
     *  2. Delete with productRepository.deleteById(id)
     */
    @Transactional
    public void delete(Long id) {
        // TODO: Implement
    }

    /**
     * TODO:
     *  1. Call productRepository.decrementStock(id, quantity)
     *  2. If result == 0 → throw ProductNotFoundException or IllegalStateException("Insufficient stock")
     *  3. Return updated product via findById(id)
     */
    @Transactional
    public ProductResponse decrementStock(Long id, int quantity) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  Call productRepository.searchByName(name) and map to List<ProductResponse>
     */
    public List<ProductResponse> searchByName(String name) {
        // TODO: Implement
        return List.of();
    }
}
