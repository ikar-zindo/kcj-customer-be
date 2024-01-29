package com.kcurryjibcustomer.repo;

import com.kcurryjibcustomer.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

   Optional<List<CartProduct>> findByCartId(Long cartId);

   Optional<CartProduct> findByCartIdAndProductId(Long cartId, Long productId);

   @Transactional
   void deleteByCartId(Long cartId);
}
