package com.daniel.shopychip.repository;

import com.daniel.shopychip.model.Product;
import com.daniel.shopychip.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
    List<Product> findByCategory(String category);
    List<Product> findByCondition(String condition);
    List<Product> findByNameContainingIgnoreCase(String name);
}