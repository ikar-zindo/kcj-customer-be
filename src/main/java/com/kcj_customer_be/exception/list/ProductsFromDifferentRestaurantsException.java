package com.kcj_customer_be.exception.list;

public class ProductsFromDifferentRestaurantsException extends RuntimeException {
   public ProductsFromDifferentRestaurantsException(String message) {
      super(message);
   }
}
