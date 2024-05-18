package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
