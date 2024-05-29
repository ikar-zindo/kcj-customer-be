package com.kcjcustomerbe.exception.list;

import com.kcjcustomerbe.exception.ErrorMessage;

import java.util.UUID;

public class CartIsEmptyException extends RuntimeException{
   public CartIsEmptyException(String message) {
      super(ErrorMessage.CART_IS_EMPTY);
   }
}
