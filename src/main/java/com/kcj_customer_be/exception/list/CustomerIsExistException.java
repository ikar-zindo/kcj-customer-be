package com.kcj_customer_be.exception.list;

public class CustomerIsExistException extends RuntimeException{
   public CustomerIsExistException(String message) {
      super(message);
   }
}
