package com.kcjcustomerbe.controller.interfaces;

import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.dto.customer.CustomerResponseDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import com.kcjcustomerbe.validation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "customer controller", description = "allows you to receive information about customer")
public interface CustomerControllerInterface {

   // CREATE - REGISTRATION NEW CUSTOMER
   @Operation(
         summary = "Create a new customer",
         description = "Create new customer and return him",
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
               description = "The unique identifier of the customer",
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
                                    "username": "johnsnow",
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
                                          "username": "johnsnowuser",
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
                                          "username": "johnsnowuser",
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
                                          "username": "johnsnowuser",
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

         },
         security = {
               @SecurityRequirement(name = "safety requirements")
         }
   )
   ResponseEntity<CustomerResponseDto> registrationCustomer(
         @Valid @RequestBody CustomerCreateDto customerCreateDto);

   // READ - GET CUSTOMER BY ID
   @Operation(
         summary = "Show customer by ID",
         description = "Retrieve an customer by its unique identifier",
         parameters = {
               @Parameter(
                     name = "id",
                     description = "The unique identifier of the customer",
                     required = true,
                     in = ParameterIn.PATH,
                     schema = @Schema(type = "string", format = "string"),
                     examples = {
                           @ExampleObject(
                                 name = "Example request with correct Id",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49a1"
                           ),
                           @ExampleObject(
                                 name = "Example request with non-exist Id",
                                 value = "12345678-170e-42f7-b6ae-435ee56f49a1"
                           ),
                           @ExampleObject(
                                 name = "Example request with invalid Id",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49a1!"
                           )
                     }
               )
         },
         responses = {
               @ApiResponse(
                     responseCode = "200",
                     description = "A customer found and returned",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = CustomerDto.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "400",
                     description = "Invalid ID",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "409",
                     description = "Customer not found",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         },
         security = {
               @SecurityRequirement(name = "safety requirements")
         }
   )
   ResponseEntity<CustomerDto> getCustomerById(@UuidFormatChecker @PathVariable("id") String customerId);


   // UPDATE - CUSTOMER DETAILS
   @Operation(
         summary = "Update details about a customer",
         description = "Update a customer details and return him",
         parameters = {
               @Parameter(
                     name = "id",
                     description = "The customer details have been successfully updated",
                     required = true,
                     in = ParameterIn.PATH,
                     schema = @Schema(type = "string", format = "string"),
                     examples = {
                           @ExampleObject(
                                 name = "Example request with correct Id",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49a1"
                           ),
                           @ExampleObject(
                                 name = "Example request with non-exist Id",
                                 value = "12345678-170e-42f7-b6ae-435ee56f49a1"
                           ),
                           @ExampleObject(
                                 name = "Example request with invalid Id",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49a1!"
                           )
                     }
               )
         },
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
               description = "The customer to be created",
               required = true,
               content = @Content(
                     mediaType = "application/json",
                     schema = @Schema(implementation = CustomerUpdateDto.class),
                     examples = {@ExampleObject(name = "Good request",
                           value = """
                                 {
                                    "firstName": "John",
                                    "lastName": "Snow",
                                    "email": "john@gmail.com",
                                    "username": "johnsnow",
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
                                          "username": "johnsnowuser",
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
                                          "username": "johnsnowuser",
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
                                          "username": "johnsnowuser",
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
                     description = "The customer details have been successfully updated",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = CustomerUpdateDto.class)
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

         },
         security = {
               @SecurityRequirement(name = "safety requirements")
         }
   )
   ResponseEntity<CustomerResponseDto> updateCustomerInfo(@UuidFormatChecker @PathVariable("id") String customerId,
                                                          @Valid @RequestBody CustomerUpdateDto customerUpdateDto);


   // DELETE - BLOCK CUSTOMER
   @Operation(
         summary = "Block Customer by ID",
         description = "Block an customer by its unique identifier",
         parameters = {
               @Parameter(
                     name = "id",
                     description = "The unique identifier of the customer",
                     required = true,
                     in = ParameterIn.PATH,
                     schema = @Schema(type = "string", format = "string"),
                     examples = {
                           @ExampleObject(
                                 name = "Example request with correct Id",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49a1"
                           )
                     }
               )
         },
         responses = {
               @ApiResponse(
                     responseCode = "200",
                     description = "A customer found and returned",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = CustomerDto.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "400",
                     description = "Invalid ID",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "409",
                     description = "Customer not found",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         },
         security = {
               @SecurityRequirement(name = "safety requirements")
         }
   )
   ResponseEntity<CustomerResponseDto> blockCustomerById(@UuidFormatChecker @PathVariable("id") String customerId);
}
