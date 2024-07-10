package com.kcjcustomerbe.exception.list;

public class ProductsNotFoundException extends RuntimeException {
   public ProductsNotFoundException(String message) {
      super(message);
   }
}
