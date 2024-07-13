package com.kcj_customer_be.exception.list;

public class RestaurantNotFoundException extends RuntimeException {
   public RestaurantNotFoundException(String message) {
      super(message);
   }
}
