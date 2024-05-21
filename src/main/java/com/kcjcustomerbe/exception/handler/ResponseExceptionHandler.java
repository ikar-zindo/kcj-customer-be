package com.kcjcustomerbe.exception.handler;

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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

   // GLOBAL EXCEPTION
   @ExceptionHandler(IdNullException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleIdCannotBeNullException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), BAD_REQUEST),
              BAD_REQUEST);
   }

   @ExceptionHandler(IdNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleIdNotFoundException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), HttpStatus.NOT_FOUND),
              NOT_FOUND);
   }

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatusCode status,
                                                                 WebRequest request) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = error.getDefaultMessage();
         errors.put(fieldName, errorMessage);
      });
      ErrorExtension errorExtension = new ErrorExtension(errors.toString(), BAD_REQUEST);
      return new ResponseEntity<>(errorExtension, BAD_REQUEST);
   }

   // CUSTOMER EXCEPTIONS
   @ExceptionHandler(CustomerNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleCustomerNotFoundException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), HttpStatus.NOT_FOUND),
              NOT_FOUND);
   }

   // CART EXCEPTIONS
   @ExceptionHandler(CartNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleCartNotFoundException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), HttpStatus.NOT_FOUND),
              NOT_FOUND);
   }

   @ExceptionHandler(DifferentRestaurantException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleDifferentRestaurantException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), BAD_REQUEST),
              BAD_REQUEST);
   }

   @ExceptionHandler(CartException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleCartException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), BAD_REQUEST),
              BAD_REQUEST);
   }

   // PRODUCT EXCEPTIONS
   @ExceptionHandler(ProductNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleProductNotFoundException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), HttpStatus.NOT_FOUND),
              NOT_FOUND);
   }

   // ORDER EXCEPTIONS
   @ExceptionHandler(OrderException.class)
   @ResponseStatus(INTERNAL_SERVER_ERROR)
   public ResponseEntity<ErrorExtension> handleOrderException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), INTERNAL_SERVER_ERROR),
              INTERNAL_SERVER_ERROR);
   }

   // PAYMENT EXCEPTIONS
   @ExceptionHandler(PaymentException.class)
   @ResponseStatus(PAYMENT_REQUIRED)
   public ResponseEntity<ErrorExtension> handlePaymentException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), PAYMENT_REQUIRED),
              PAYMENT_REQUIRED);
   }

   // RESTAURANT EXCEPTIONS
   @ExceptionHandler(RestaurantNotFoundException.class)
   @ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorExtension> handleRestaurantNotFoundException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), HttpStatus.NOT_FOUND),
              NOT_FOUND);
   }

   // REVIEW EXCEPTIONS
   @ExceptionHandler(ReviewException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleReviewException(Exception e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), BAD_REQUEST),
              BAD_REQUEST);
   }

   // CATCHING INVALID UUID
   @ExceptionHandler(InvalidIdException.class)
   @ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorExtension> handleInvalidIdException(InvalidIdException e) {
      return new ResponseEntity<>(new ErrorExtension(
              e.getMessage(), BAD_REQUEST),
              BAD_REQUEST);
   }

   @ExceptionHandler(ConstraintViolationException.class)
   protected ResponseEntity<Object> handleConstraintViolationException(RuntimeException ex, WebRequest request) {
      String errorMessage = ex.getMessage();
      HttpStatus errorCode = BAD_REQUEST;
      ErrorExtension errorExtension = new ErrorExtension(errorMessage, errorCode);
      return new ResponseEntity<>(errorExtension, errorCode);
   }
}
