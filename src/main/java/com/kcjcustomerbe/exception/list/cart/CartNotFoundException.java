package com.kcjcustomerbe.exception.list.cart;

public class CartNotFoundException extends RuntimeException {
   public CartNotFoundException(String message) {
      super(message);
   }
}
