package com.kcj_customer_be.exception.list;

public class DifferentRestaurantException extends RuntimeException{
   public DifferentRestaurantException(String message) {
      super(message);
   }
}
