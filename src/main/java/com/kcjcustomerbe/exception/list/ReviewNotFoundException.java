package com.kcjcustomerbe.exception.list;

import com.kcjcustomerbe.exception.ErrorMessage;

public class ReviewNotFoundException extends ReviewException {
   public ReviewNotFoundException(String message) {
      super(ErrorMessage.REVIEW_NOT_FOUND);
   }
}
