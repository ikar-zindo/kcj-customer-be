package com.kcjcustomerbe.controller.interfaces;

import com.kcjcustomerbe.config.SwaggerConfig;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.dto.customer.CustomerResponseDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import com.kcjcustomerbe.security.exception.UnauthorizedAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = SwaggerConfig.CUSTOMER, description = "allows you to receive information about customer")
public interface CustomerControllerInterface {

   // READ - GET CUSTOMER BY ID
   @Operation(
         summary = "Show customer info",
         description = "Show information about authorized customer",
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
                     responseCode = "401",
                     description = "Unauthorized",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = UnauthorizedAuthenticationEntryPoint.class)
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
         }
   )
   ResponseEntity<CustomerDto> getCustomerByEmail();

   // UPDATE - CUSTOMER DETAILS
   @Operation(
         summary = "Update customer info",
         description = "Update details about a customer and return him",
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
               description = "Update Customer Request",
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
                                    "password": "Snow12345!",
                                    "phoneNumber": "+31950908",
                                    "address": "Alexanderstr. 1",
                                    "postalCode": "12345"
                                 }
                                 """),
                           @ExampleObject(name = "Request with existing email",
                                 value = """
                                       {
                                          "email": "john@gmail.com"
                                       }
                                       """),
                           @ExampleObject(name = "Invalidate password",
                                 value = """
                                       {
                                          "password": "Snow12345"
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
                     description = "Not valid data",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         }
   )
   ResponseEntity<CustomerResponseDto> updateCustomerInfo(@Valid @RequestBody CustomerUpdateDto customerUpdateDto);

   // DELETE - BLOCK CUSTOMER
   @Operation(
         summary = "Block Customer",
         description = "Block authorized customer",
         responses = {
               @ApiResponse(
                     responseCode = "200",
                     description = "Customer Account Block",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = CustomerDto.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "401",
                     description = "Unauthorized",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = UnauthorizedAuthenticationEntryPoint.class)
                     )
               )
         }
   )
   ResponseEntity<CustomerResponseDto> blockCustomerById();
}
