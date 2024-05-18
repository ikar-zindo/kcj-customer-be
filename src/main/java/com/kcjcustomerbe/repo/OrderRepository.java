package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
