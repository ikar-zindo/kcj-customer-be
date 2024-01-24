package com.kcurryjibcustomer.repo;

import com.kcurryjibcustomer.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
