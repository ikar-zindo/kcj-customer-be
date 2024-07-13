package com.kcj_customer_be.exception.list;

import com.kcj_customer_be.exception.ErrorMessage;

public class ProductIdNotFoundException extends RuntimeException{
   public ProductIdNotFoundException(Long productId) {
      super(ErrorMessage.PRODUCT_ID_NOT_FOUND + productId);
   }
}
