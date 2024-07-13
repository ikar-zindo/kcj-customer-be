package com.kcj_customer_be.exception.list;

import com.kcj_customer_be.exception.ErrorMessage;

import java.util.UUID;

public class CartIdNotFoundException extends RuntimeException {
   public CartIdNotFoundException(UUID cartId) {
      super(ErrorMessage.CART_ID_NOT_FOUND + cartId);
   }
}
