package com.kcurryjibcustomer.repo;

import com.kcurryjibcustomer.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

   @Query(value = "SELECT * " +
           "FROM cart_product " +
           "WHERE cart_id = :cartId;", nativeQuery = true)
   List<CartProduct> findByCart(@Param("cartId") Long cartId);
}
