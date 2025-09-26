package com.daniel.shopychip.service;

import com.daniel.shopychip.dto.ProductRequestDTO;
import com.daniel.shopychip.dto.ProductResponseDTO;
import com.daniel.shopychip.dto.ProductPatchDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO, Long userId);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO, Long userId);
    ProductResponseDTO patchProduct(Long id, ProductPatchDTO patchDTO, Long userId);
    void deleteProduct(Long id, Long userId);
    List<ProductResponseDTO> getAllProducts();
    List<ProductResponseDTO> getProductsByUser(Long userId);
}