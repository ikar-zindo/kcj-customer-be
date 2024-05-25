package com.kcjcustomerbe.annotation;

import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Create a new customer",
        description = "Create new customer and return him",
        tags = {"CUSTOMER"},
        requestBody = @RequestBody(
                description = "The customer to be created",
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
                                schema = @Schema(implementation = Customer.class)
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
public @interface CreateCustomer {
   @AliasFor(annotation = RequestMapping.class, attribute = "path")
   String[] path() default {};
}
