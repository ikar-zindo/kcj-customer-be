package com.kcj_customer_be.repo;

import com.kcj_customer_be.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
}
