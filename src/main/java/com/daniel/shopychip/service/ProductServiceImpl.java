package com.daniel.shopychip.service;

import com.daniel.shopychip.dto.ProductRequestDTO;
import com.daniel.shopychip.dto.ProductResponseDTO;
import com.daniel.shopychip.model.Product;
import com.daniel.shopychip.model.User;
import com.daniel.shopychip.repository.ProductRepository;
import com.daniel.shopychip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO, Long userId) {
        log.info("Creating product for user id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = Product.builder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .category(productRequestDTO.getCategory())
                .price(productRequestDTO.getPrice())
                .condition(productRequestDTO.getCondition())
                .picturesUrls(productRequestDTO.getPicturesUrls())
                .location(productRequestDTO.getLocation())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();

        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        log.info("Fetching product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO, Long userId) {
        log.info("Updating product with id: {} for user id: {}", id, userId);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this product.");
        }

        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setCategory(productRequestDTO.getCategory());
        product.setPrice(productRequestDTO.getPrice());
        product.setCondition(productRequestDTO.getCondition());
        product.setPicturesUrls(productRequestDTO.getPicturesUrls());
        product.setLocation(productRequestDTO.getLocation());
        product.setUpdatedAt(LocalDateTime.now());

        Product updated = productRepository.save(product);
        return mapToDTO(updated);
    }

    @Override
    public void deleteProduct(Long id, Long userId) {
        log.info("Deleting product with id: {} for user id: {}", id, userId);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this product.");
        }

        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getProductsByUser(Long userId) {
        log.info("Fetching products for user id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return productRepository.findByUser(user)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProductResponseDTO mapToDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .condition(product.getCondition())
                .picturesUrls(product.getPicturesUrls())
                .location(product.getLocation())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .userId(product.getUser() != null ? product.getUser().getId() : null)
                .userName(product.getUser() != null
                        ? product.getUser().getFirstName() + " " + product.getUser().getLastName()
                        : null)
                .build();
    }
}