package com.kcj_customer_be.exception.list;

import com.kcj_customer_be.exception.ErrorMessage;

public class CartIsEmptyException extends RuntimeException{
   public CartIsEmptyException(String message) {
      super(ErrorMessage.CART_IS_EMPTY);
   }
}
