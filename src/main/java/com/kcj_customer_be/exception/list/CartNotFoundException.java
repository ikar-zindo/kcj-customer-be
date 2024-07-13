package com.kcj_customer_be.exception.list;

public class CartNotFoundException extends RuntimeException {
   public CartNotFoundException(String message) {
      super(message);
   }
}
