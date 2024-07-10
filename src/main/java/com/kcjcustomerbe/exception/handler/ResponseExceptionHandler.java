package com.kcjcustomerbe.exception.handler;

import com.kcjcustomerbe.exception.list.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
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
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                 HttpHeaders headers,
                                                                 HttpStatusCode status,
                                                                 WebRequest request) {
      Map<String, Object> errors = new HashMap<>();
      errors.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

      Map<String, String[]> requestInfo = request.getParameterMap();
      for (Map.Entry<String, String[]> entry : requestInfo.entrySet()) {
         String requestName = entry.getKey();
         String[] requestValue = entry.getValue();

         if (requestValue.length == 1) {
            errors.put(requestName, requestValue[0]);
         } else {
            errors.put(requestName, requestValue);
         }
      }

      exception.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError) error).getField();
         String errorMessage = error.getDefaultMessage();
         errors.put(fieldName, errorMessage);
      });

      ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            errors
      );

      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(IdNullException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleIdCannotBeNullException(IdNullException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(IdNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleIdNotFoundException(IdNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(InvalidDataException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleIdInvalidDataException(InvalidDataException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }


   // CUSTOMER EXCEPTIONS
   @ExceptionHandler(CustomerNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(INTERNAL_SERVER_ERROR)
   public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            INTERNAL_SERVER_ERROR.value(),
            INTERNAL_SERVER_ERROR.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(CustomerIsExistException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(CONFLICT)
   public ResponseEntity<ErrorResponse> handleCustomerIsExistException(CustomerIsExistException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            CONFLICT.value(),
            CONFLICT.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, CONFLICT);
   }

   @ExceptionHandler(CustomerIdNotFound.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleCustomerIdNotFound(CustomerIdNotFound exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }


   // CART EXCEPTIONS
   @ExceptionHandler(CartNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleCartNotFoundException(CartNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(CartIdNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleCartIdNotFoundException(CartIdNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(DifferentRestaurantException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleDifferentRestaurantException(DifferentRestaurantException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(CartException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(INTERNAL_SERVER_ERROR)
   public ResponseEntity<ErrorResponse> handleCartException(CartException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            INTERNAL_SERVER_ERROR.value(),
            INTERNAL_SERVER_ERROR.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(CartIsEmptyException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleCartIsEmptyException(CartIsEmptyException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }


   // PRODUCT EXCEPTIONS
   @ExceptionHandler(ProductIdNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleProductIdNotFoundException(ProductIdNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(ProductNotAvailableException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(CONFLICT)
   public ResponseEntity<ErrorResponse> handleProductNotAvailableException(ProductNotAvailableException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            CONFLICT.value(),
            CONFLICT.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, CONFLICT);
   }

   @ExceptionHandler(ProductsNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleProductsNotFoundException(ProductsNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }


   // ORDER EXCEPTIONS
   @ExceptionHandler(OrderException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(INTERNAL_SERVER_ERROR)
   public ResponseEntity<ErrorResponse> handleOrderException(OrderException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            INTERNAL_SERVER_ERROR.value(),
            INTERNAL_SERVER_ERROR.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(ProductsFromDifferentRestaurantsException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleProductsFromDifferentRestaurantsException(ProductsFromDifferentRestaurantsException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }


   // PAYMENT EXCEPTIONS
   @ExceptionHandler(PaymentException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(PAYMENT_REQUIRED)
   public ResponseEntity<ErrorResponse> handlePaymentException(PaymentException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            PAYMENT_REQUIRED.value(),
            PAYMENT_REQUIRED.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, PAYMENT_REQUIRED);
   }


   // RESTAURANT EXCEPTIONS
   @ExceptionHandler(RestaurantNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }

   @ExceptionHandler(RestaurantsListException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleRestaurantsListException(RestaurantsListException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }


   // REVIEW EXCEPTIONS
   @ExceptionHandler(ReviewException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleReviewException(ReviewException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(ReviewNotFoundException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleReviewNotFoundException(ReviewNotFoundException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(ReviewEmptyException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(NOT_FOUND)
   public ResponseEntity<ErrorResponse> handleReviewEmptyException(ReviewEmptyException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            NOT_FOUND.value(),
            NOT_FOUND.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, NOT_FOUND);
   }


   // CATCHING INVALID UUID
   @ExceptionHandler(ConstraintViolationException.class)
   protected ResponseEntity<Object> handleConstraintViolationException(RuntimeException exception, WebRequest request) {
      Map<String, Object> info = new LinkedHashMap<>();
      info.put("path", request.getDescription(false));

      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            info
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }

   @ExceptionHandler(InvalidIdException.class)
   @org.springframework.web.bind.annotation.ResponseStatus(BAD_REQUEST)
   public ResponseEntity<ErrorResponse> handleInvalidIdException(InvalidIdException exception) {
      ErrorResponse errorResponse = new ErrorResponse(
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            exception.getMessage(),
            LocalDateTime.now().toString(),
            null
      );
      return new ResponseEntity<>(errorResponse, BAD_REQUEST);
   }
}