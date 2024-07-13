package com.kcj_customer_be.exception.list;

public class InvalidIdException extends RuntimeException{
   public InvalidIdException(String message) {
      super(message);
   }
}
