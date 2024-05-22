package com.kcjcustomerbe.exception.list.customer;

public class CustomerNotFoundException extends RuntimeException {
   public CustomerNotFoundException(String message) {
      super(message);
   }
}
