package com.kcj_customer_be.repo;

import com.kcj_customer_be.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CartRepository extends JpaRepository<Cart, UUID> {
}
