package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
