package com.kcj_customer_be.exception.list;

public class InvalidDataException extends RuntimeException{
   public InvalidDataException(String message) {
      super(message);
   }
}
