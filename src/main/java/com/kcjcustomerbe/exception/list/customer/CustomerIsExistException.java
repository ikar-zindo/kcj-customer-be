package com.kcjcustomerbe.exception.list.customer;

public class CustomerIsExistException extends RuntimeException{
   public CustomerIsExistException(String message) {
      super(message);
   }
}
