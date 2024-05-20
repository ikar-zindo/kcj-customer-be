package com.kcjcustomerbe.exception.handler;

import com.kcjcustomerbe.exception.list.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ResponseExceptionHandler {

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
   @Description(value = "Catching an invalid UUID using ConstraintViolationException.class")
   @ExceptionHandler(value = { ConstraintViolationException.class, InvalidIdException.class})
   public ResponseEntity<Object> handleInvalidIdException(RuntimeException e, WebRequest request) {
      String errorMessage = e.getMessage();
      HttpStatus errorCode = HttpStatus.BAD_REQUEST;

      if (e instanceof ConstraintViolationException) {
         errorMessage = ((ConstraintViolationException) e).getMessage();
      }

      ErrorExtension errorExtension = new ErrorExtension(errorMessage, errorCode);
      return new ResponseEntity<>(errorExtension, errorCode);
   }
}
