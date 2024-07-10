package com.kcjcustomerbe.exception.list;

import com.kcjcustomerbe.exception.ErrorMessage;

public class ProductIdNotFoundException extends RuntimeException{
   public ProductIdNotFoundException(Long productId) {
      super(ErrorMessage.PRODUCT_ID_NOT_FOUND + productId);
   }
}
