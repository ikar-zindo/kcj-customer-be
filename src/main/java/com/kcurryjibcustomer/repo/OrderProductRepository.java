package com.kcurryjibcustomer.repo;

import com.kcurryjibcustomer.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
