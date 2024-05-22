package com.kcjcustomerbe.exception.list;

public class ProductNotFoundException extends RuntimeException{
   public ProductNotFoundException(Long productId) {
      super("Product with id " + productId + " not found");
   }
}
