package com.bootcamp.controller;

import com.bootcamp.dto.ProductRequest;
import com.bootcamp.dto.ProductResponse;
import com.bootcamp.exception.GlobalExceptionHandler;
import com.bootcamp.exception.ProductNotFoundException;
import com.bootcamp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @WebMvcTest tests for ProductController.
 *             Uses MockMvc — no real HTTP server needed.
 */
@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProductService productService;

  @Test
  @DisplayName("GET /api/products returns 200 with list")
  void getAll_returns200WithList() throws Exception {
    var products = List.of(
        new ProductResponse(1L, "Laptop", "Electronics", 999.0, 10, true));
    when(productService.findAll(null)).thenReturn(products);

    mockMvc.perform(get("/api/products"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].name").value("Laptop"));
  }

  @Test
  @DisplayName("GET /api/products/{id} returns 200 when exists")
  void getById_returns200WhenExists() throws Exception {
    var product = new ProductResponse(1L, "Laptop", "Electronics", 999.0, 10, true);
    when(productService.findById(1L)).thenReturn(product);

    mockMvc.perform(get("/api/products/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Laptop"));
  }

  @Test
  @DisplayName("GET /api/products/{id} returns 404 when not found")
  void getById_returns404WhenNotFound() throws Exception {
    when(productService.findById(99L)).thenThrow(new ProductNotFoundException(99L));

    mockMvc.perform(get("/api/products/99"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.status").value(404))
        .andExpect(jsonPath("$.message").value("Product not found with id: 99"));
  }

  @Test
  @DisplayName("POST /api/products returns 201 with location header")
  void create_returns201WithLocation() throws Exception {
    var request = new ProductRequest("Monitor", "Electronics", 350.0, 5);
    var created = new ProductResponse(6L, "Monitor", "Electronics", 350.0, 5, true);
    when(productService.create(any())).thenReturn(created);

    mockMvc.perform(post("/api/products")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/api/products/6"))
        .andExpect(jsonPath("$.id").value(6));
  }

  @Test
  @DisplayName("DELETE /api/products/{id} returns 204")
  void delete_returns204() throws Exception {
    doNothing().when(productService).delete(1L);

    mockMvc.perform(delete("/api/products/1"))
        .andExpect(status().isNoContent());
  }
}
