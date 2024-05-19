package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CartRepository extends JpaRepository<Cart, UUID> {
}
