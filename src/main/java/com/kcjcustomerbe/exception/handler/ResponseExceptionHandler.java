package com.kcjcustomerbe.exception.handler;

import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.*;
import com.kcjcustomerbe.exception.list.cart.CartException;
import com.kcjcustomerbe.exception.list.cart.CartNotFoundException;
import com.kcjcustomerbe.exception.list.customer.CustomerIsExistException;
import com.kcjcustomerbe.exception.list.customer.CustomerNotFoundException;
import com.kcjcustomerbe.exception.list.order.OrderException;
import com.kcjcustomerbe.exception.list.order.PaymentException;
import com.kcjcustomerbe.exception.list.product.ProductIdNotFoundException;
import com.kcjcustomerbe.exception.list.product.ProductNotAvailableException;
import com.kcjcustomerbe.exception.list.restaurant.DifferentRestaurantException;
import com.kcjcustomerbe.exception.list.restaurant.RestaurantNotFoundException;
import com.kcjcustomerbe.exception.list.review.ReviewEmptyException;
import com.kcjcustomerbe.exception.list.review.ReviewException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleIdCannotBeNullException(IdNullException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", BAD_REQUEST.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(IdNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleIdNotFoundException(IdNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", NOT_FOUND.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, NOT_FOUND);
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
      additionalInfo.put("detail", BAD_REQUEST.value());

      ErrorResponse errorResponse = new ErrorResponse(
            errors.toString(),
            BAD_REQUEST,
            additionalInfo);

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   // CUSTOMER EXCEPTIONS
   @ExceptionHandler(CustomerNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", NOT_FOUND.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(CustomerIsExistException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(CONFLICT)
   public ResponseEntity<ErrorResponse> handleCustomerIsExistException(CustomerIsExistException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", CONFLICT.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            CONFLICT,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, CONFLICT);
   }

   // CART EXCEPTIONS
   @ExceptionHandler(CartNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleCartNotFoundException(CartNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", NOT_FOUND.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(DifferentRestaurantException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleDifferentRestaurantException(DifferentRestaurantException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", BAD_REQUEST.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(CartException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleCartException(CartException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", BAD_REQUEST.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   // PRODUCT EXCEPTIONS
   @ExceptionHandler(ProductIdNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleProductIdNotFoundException(ProductIdNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", NOT_FOUND.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(ProductNotAvailableException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(CONFLICT)
   public ResponseEntity<ErrorResponse> handleProductNotAvailableException(ProductNotAvailableException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", CONFLICT.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            CONFLICT,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, CONFLICT);
   }

   // ORDER EXCEPTIONS
   @ExceptionHandler(OrderException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(INTERNAL_SERVER_ERROR)
   public ResponseEntity<ErrorResponse> handleOrderException(OrderException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", INTERNAL_SERVER_ERROR.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            INTERNAL_SERVER_ERROR,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
   }

   // PAYMENT EXCEPTIONS
   @ExceptionHandler(PaymentException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(PAYMENT_REQUIRED)
   public ResponseEntity<ErrorResponse> handlePaymentException(PaymentException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", PAYMENT_REQUIRED.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            PAYMENT_REQUIRED,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, PAYMENT_REQUIRED);
   }

   // RESTAURANT EXCEPTIONS
   @ExceptionHandler(RestaurantNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", NOT_FOUND.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   // REVIEW EXCEPTIONS
   @ExceptionHandler(ReviewException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleReviewException(ReviewException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", BAD_REQUEST.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(ReviewEmptyException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleReviewEmptyException(ReviewEmptyException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", NOT_FOUND.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            HttpStatus.NOT_FOUND,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   // CATCHING INVALID UUID
   @ExceptionHandler(ConstraintViolationException.class)
   protected ResponseEntity<Object> handleConstraintViolationException(RuntimeException ex, WebRequest request) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", BAD_REQUEST.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo);

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(InvalidIdException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleInvalidIdException(InvalidIdException ex) {
      Map<String, Object> additionalInfo = new LinkedHashMap<>();
      additionalInfo.put("timestamp", LocalDateTime.now());
      additionalInfo.put("detail", BAD_REQUEST.value());

      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            BAD_REQUEST,
            additionalInfo
      );

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
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
