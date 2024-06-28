package com.kcjcustomerbe.controller.interfaces;

import com.kcjcustomerbe.config.SwaggerConfig;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerResponseDto;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = SwaggerConfig.CUSTOMER)
public interface RegistrationControllerInterface {

   // CREATE - REGISTRATION NEW CUSTOMER
   @Operation(
         summary = "Registration a new customer",
         description = "Create a new customer and return him",
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
               description = "Create Customer Request",
               required = true,
               content = @Content(
                     mediaType = "application/json",
                     schema = @Schema(implementation = Customer.class),
                     examples = {@ExampleObject(name = "Good request",
                           value = """
                                 {
                                    "firstName": "John",
                                    "lastName": "Snow",
                                    "email": "john@gmail.com",
                                    "password": "Snow12345!",
                                    "phoneNumber": "+31950908",
                                    "address": "Alexanderstr. 1",
                                    "postalCode": "12345"
                                 }
                                 """),
                           @ExampleObject(name = "Request with existing email",
                                 value = """
                                       {
                                          "firstName": "John",
                                          "lastName": "Snow",
                                          "email": "john@gmail.com",
                                          "password": "Snow12345!",
                                          "phoneNumber": "+31950908",
                                          "address": "Alexanderstr. 1",
                                          "postalCode": "12345"
                                       }
                                       """),
                           @ExampleObject(name = "Request with existing email",
                                 value = """
                                       {
                                          "firstName": "John",
                                          "lastName": "Snow",
                                          "email": "johnsnow@gmail.com",
                                          "password": "Snow12345!",
                                          "phoneNumber": "+31950908",
                                          "address": "Alexanderstr. 1",
                                          "postalCode": "12345"
                                       }
                                       """),
                           @ExampleObject(name = "Not validate data",
                                 value = """
                                       {
                                          "firstName": "John",
                                          "lastName": "Snow",
                                          "email": "john@gmail.com",
                                          "password": "Snow12345!",
                                          "phoneNumber": "+31950908",
                                          "address": "Alexanderstr. 1",
                                          "postalCode": "12345"
                                       }
                                       """)
                     }
               )
         ),
         responses = {
               @ApiResponse(
                     responseCode = "200",
                     description = "The customer was created successfully.",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = CustomerCreateDto.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "409",
                     description = "A customer with the same username already exists",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "409",
                     description = "A customer with the same email already exists",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "400",
                     description = "Not valid date",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         }
   )
   ResponseEntity<CustomerResponseDto> registrationCustomer(
         @Valid @RequestBody CustomerCreateDto customerCreateDto);
}
