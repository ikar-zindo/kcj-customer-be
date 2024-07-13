package com.kcj_customer_be.exception.list;

import com.kcj_customer_be.exception.ErrorMessage;

public class ProductNotAvailableException extends RuntimeException {
   public ProductNotAvailableException(Long productId) {
      super(ErrorMessage.PRODUCT_IS_NOT_AVAILABLE + productId);
   }
}
