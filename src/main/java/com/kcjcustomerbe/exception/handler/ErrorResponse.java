package com.kcjcustomerbe.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
      int status,
      String error,
      String message,
      String timestamp,
      Map<String, Object> info) {
}
