package com.daniel.shopychip.controller;

import com.daniel.shopychip.dto.ProductRequestDTO;
import com.daniel.shopychip.dto.ProductResponseDTO;
import com.daniel.shopychip.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestBody ProductRequestDTO productRequestDTO,
            @RequestParam Long userId) {
        ProductResponseDTO created = productService.createProduct(productRequestDTO, userId);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO productRequestDTO,
            @RequestParam Long userId) {
        ProductResponseDTO updated = productService.updateProduct(id, productRequestDTO, userId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id,
            @RequestParam Long userId) {
        productService.deleteProduct(id, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByUser(@PathVariable Long userId) {
        List<ProductResponseDTO> products = productService.getProductsByUser(userId);
        return ResponseEntity.ok(products);
    }
}