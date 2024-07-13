package com.kcj_customer_be.repo;

import com.kcj_customer_be.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

   Optional<Product> findByIdAndIsAvailableTrue(Long productId);

   Optional<List<Product>> findAllByIsAvailableTrue();
}
