package com.kcjcustomerbe.exception.list;

public class CartNotFoundException extends RuntimeException {
   public CartNotFoundException(String message) {
      super(message);
   }
}
