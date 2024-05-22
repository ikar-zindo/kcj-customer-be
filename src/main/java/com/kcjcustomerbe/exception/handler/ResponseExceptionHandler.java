package com.kcjcustomerbe.exception.handler;

import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

   // GLOBAL EXCEPTION
   @ExceptionHandler(IdNullException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleIdCannotBeNullException(IdNullException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   @ExceptionHandler(IdNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleIdNotFoundException(IdNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, NOT_FOUND);
   }

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatusCode status,
                                                                 WebRequest request) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = getErrorMessage(fieldName);
         errors.put(fieldName, errorMessage);
      });

      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            errors.toString(),
            BAD_REQUEST,
            additionalInfo);

      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   // CUSTOMER EXCEPTIONS
   @ExceptionHandler(CustomerNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleCustomerNotFoundException(CustomerNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, NOT_FOUND);
   }

   @ExceptionHandler(CustomerIsExistException.class)
   @ResponseStatus(CONFLICT)
   public ResponseEntity<ErrorExtension> handleCustomerIsExistException(CustomerIsExistException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            CONFLICT,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, CONFLICT);
   }

   // CART EXCEPTIONS
   @ExceptionHandler(CartNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleCartNotFoundException(CartNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, NOT_FOUND);
   }

   @ExceptionHandler(DifferentRestaurantException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleDifferentRestaurantException(DifferentRestaurantException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   @ExceptionHandler(CartException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleCartException(CartException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   // PRODUCT EXCEPTIONS
   @ExceptionHandler(ProductNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleProductNotFoundException(ProductNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, NOT_FOUND);
   }

   // ORDER EXCEPTIONS
   @ExceptionHandler(OrderException.class)
   @ResponseStatus(INTERNAL_SERVER_ERROR)
   public ResponseEntity<ErrorExtension> handleOrderException(OrderException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            INTERNAL_SERVER_ERROR,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, INTERNAL_SERVER_ERROR);
   }

   // PAYMENT EXCEPTIONS
   @ExceptionHandler(PaymentException.class)
   @ResponseStatus(PAYMENT_REQUIRED)
   public ResponseEntity<ErrorExtension> handlePaymentException(PaymentException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            PAYMENT_REQUIRED,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, PAYMENT_REQUIRED);
   }

   // RESTAURANT EXCEPTIONS
   @ExceptionHandler(RestaurantNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleRestaurantNotFoundException(RestaurantNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, NOT_FOUND);
   }

   // REVIEW EXCEPTIONS
   @ExceptionHandler(ReviewException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleReviewException(ReviewException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   @ExceptionHandler(ReviewEmptyException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleReviewEmptyException(ReviewEmptyException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            HttpStatus.NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, NOT_FOUND);
   }

   // CATCHING INVALID UUID
   @ExceptionHandler(ConstraintViolationException.class)
   protected ResponseEntity<Object> handleConstraintViolationException(RuntimeException ex, WebRequest request) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo);

      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   @ExceptionHandler(InvalidIdException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleInvalidIdException(InvalidIdException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());

      ErrorExtension errorExtension = new ErrorExtension(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   // CUSTOM METHODS
   private String getErrorMessage(String fieldName) {
      return switch (fieldName) {
         case "id" -> ErrorMessage.ID_NOT_FOUND;
         case "lastname" -> ErrorMessage.INVALID_LASTNAME;
         case "firstName" -> ErrorMessage.INVALID_FIRST_NAME;
         case "email" -> ErrorMessage.INVALID_EMAIL;
         case "password" -> ErrorMessage.INVALID_PASSWORD;
         case "address" -> ErrorMessage.INVALID_ADDRESS;
         case "postalCode" -> ErrorMessage.INVALID_POSTAL_CODE;
         default -> ErrorMessage.INVALID_USERNAME;
      };
   }
}
