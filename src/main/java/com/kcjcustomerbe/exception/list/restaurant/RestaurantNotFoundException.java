package com.kcjcustomerbe.exception.list.restaurant;

public class RestaurantNotFoundException extends RuntimeException {
   public RestaurantNotFoundException(String message) {
      super(message);
   }
}
