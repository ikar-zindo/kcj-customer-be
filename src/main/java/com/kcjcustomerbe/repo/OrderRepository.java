package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {
}
