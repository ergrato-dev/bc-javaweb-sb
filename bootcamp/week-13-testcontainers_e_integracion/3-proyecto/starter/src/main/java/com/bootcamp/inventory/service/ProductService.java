package com.bootcamp.inventory.service;

import com.bootcamp.inventory.domain.Product;
import com.bootcamp.inventory.dto.Dtos.*;
import com.bootcamp.inventory.exception.ProductNotFoundException;
import com.bootcamp.inventory.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::toResponse);
    }

    public Page<ProductResponse> searchByName(String name, Pageable pageable) {
        return productRepository.searchByName(name, pageable).map(this::toResponse);
    }

    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
            .map(this::toResponse)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ProductResponse findBySku(String sku) {
        return productRepository.findBySku(sku)
            .map(this::toResponse)
            .orElseThrow(() -> new ProductNotFoundException(sku));
    }

    @Transactional
    public ProductResponse create(ProductCreateRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new IllegalArgumentException("Product with SKU " + request.sku() + " already exists");
        }
        var product = new Product(null, request.name(), request.sku(),
                                 request.price(), request.stock(), request.category());
        return toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        product.updateDetails(request.name(), request.price());
        return toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse addStock(Long id, StockAdjustRequest request) {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        product.addStock(request.quantity());
        return toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse removeStock(Long id, StockAdjustRequest request) {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
        product.removeStock(request.quantity()); // throws IllegalStateException if insufficient
        return toResponse(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) throw new ProductNotFoundException(id);
        productRepository.deleteById(id);
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getSku(),
                                  p.getPrice(), p.getStock(), p.getCategory(), p.getCreatedAt());
    }
}
