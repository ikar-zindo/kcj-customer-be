package com.kcjcustomerbe.exception.list.product;

import com.kcjcustomerbe.exception.ErrorMessage;

public class ProductNotAvailableException extends RuntimeException {
   public ProductNotAvailableException(Long productId) {
      super(ErrorMessage.PRODUCT_IS_NOT_AVAILABLE + productId);
   }
}
