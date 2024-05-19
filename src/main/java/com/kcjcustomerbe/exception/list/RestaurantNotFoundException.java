package com.kcjcustomerbe.exception.list;

public class RestaurantNotFoundException extends RuntimeException {

   public RestaurantNotFoundException(String message) {
      super(message);
   }
}
