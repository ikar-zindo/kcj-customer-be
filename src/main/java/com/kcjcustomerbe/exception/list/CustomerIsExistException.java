package com.kcjcustomerbe.exception.list;

public class CustomerIsExistException extends RuntimeException{
   public CustomerIsExistException(String message) {
      super(message);
   }
}
