package com.kcj_customer_be.exception.list;

public class ProductsNotFoundException extends RuntimeException {
   public ProductsNotFoundException(String message) {
      super(message);
   }
}
