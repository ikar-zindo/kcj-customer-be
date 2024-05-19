package com.kcjcustomerbe.exception.list;

public class CustomerNotFoundException extends RuntimeException {

   public CustomerNotFoundException(String message) {
      super(message);
   }
}
