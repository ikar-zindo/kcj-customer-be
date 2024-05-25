package com.kcjcustomerbe.exception.handler;

import lombok.Value;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Value
public class ErrorResponse {

   String message;
   HttpStatus errorCode;
   Map<String, Object> additionalInfo;

   public ErrorResponse(String message,
                        HttpStatus errorCode,
                        Map<String, Object> additionalInfo) {

      this.message = message;
      this.errorCode = errorCode;
      this.additionalInfo = additionalInfo;
   }
}
