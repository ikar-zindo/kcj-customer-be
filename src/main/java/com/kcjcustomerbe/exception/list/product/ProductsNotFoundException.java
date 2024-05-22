package com.kcjcustomerbe.exception.list.product;

public class ProductsNotFoundException extends RuntimeException {
   public ProductsNotFoundException(String message) {
      super(message);
   }
}
