package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {
}
