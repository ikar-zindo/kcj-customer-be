package com.kcjcustomerbe.exception.list;

import com.kcjcustomerbe.exception.ErrorMessage;

import java.util.UUID;

public class CartIdNotFoundException extends RuntimeException {
   public CartIdNotFoundException(UUID cartId) {
      super(ErrorMessage.CART_ID_NOT_FOUND + cartId);
   }
}
