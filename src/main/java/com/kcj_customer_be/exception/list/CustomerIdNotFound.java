package com.kcj_customer_be.exception.list;

import com.kcj_customer_be.exception.ErrorMessage;

import java.util.UUID;

public class CustomerIdNotFound extends RuntimeException {
   public CustomerIdNotFound(UUID customerId) {
      super(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
   }
}
