package com.kcjcustomerbe.annotation;


import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping(method = RequestMethod.DELETE)
@Operation(
      summary = "Block Customer by ID",
      description = "Block an customer by its unique identifier",
      tags = {"CUSTOMER"},
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
                        schema = @Schema( implementation = ResponseExceptionHandler.class)
                  )
            )
      },
      security = {
            @SecurityRequirement(name = "safety requirements")
      }
)

public @interface BlockCustomer {
   @AliasFor(annotation = RequestMapping.class, attribute = "path")
   String[] path() default {};
}

