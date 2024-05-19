package com.kcjcustomerbe.exception.list;

public class ProductNotFoundException extends RuntimeException{

   public ProductNotFoundException(String message) {
      super(message);
   }
}
