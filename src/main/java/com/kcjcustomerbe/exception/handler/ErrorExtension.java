package com.kcjcustomerbe.exception.handler;

import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Value
public class ErrorExtension {

   String message;
   HttpStatus errorCode;
   Map<String, Object> additionalInfo;

   public ErrorExtension(String message,
                         HttpStatus errorCode,
                         Map<String, Object> additionalInfo) {

      this.message = message;
      this.errorCode = errorCode;
      this.additionalInfo = additionalInfo;
   }
}
