package com.kcj_customer_be.repo;

import com.kcj_customer_be.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
