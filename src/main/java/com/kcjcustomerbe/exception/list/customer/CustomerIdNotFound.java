package com.kcjcustomerbe.exception.list.customer;

import com.kcjcustomerbe.exception.ErrorMessage;

import java.util.UUID;

public class CustomerIdNotFound extends RuntimeException {
   public CustomerIdNotFound(UUID customerId) {
      super(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
   }
}
