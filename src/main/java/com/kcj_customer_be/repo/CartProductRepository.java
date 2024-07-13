package com.kcj_customer_be.repo;

import com.kcj_customer_be.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {

   Optional<List<CartProduct>> findByCartId(UUID cartId);

   Optional<CartProduct> findByCartIdAndProductId(UUID cartId, Long productId);

   @Transactional
   void deleteByCartId(UUID cartId);
}
