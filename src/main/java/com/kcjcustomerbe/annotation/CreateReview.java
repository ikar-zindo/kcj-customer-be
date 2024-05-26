package com.kcjcustomerbe.annotation;

import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.exception.handler.ErrorResponse;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
      summary = "Add a review for a restaurant",
      description = "Add a review for a restaurant and return the review details",
      tags = {"REVIEW"},
      parameters = {
            @Parameter(
                  name = "id",
                  description = "The ID of the restaurant",
                  required = true,
                  in = ParameterIn.PATH,
                  schema = @Schema(type = "integer", format = "int64"),
                  examples = {
                        @ExampleObject(
                              name = "Example request with correct Id",
                              value = "1"
                        ),
                        @ExampleObject(
                              name = "Example request with non-existent Id",
                              value = "999"
                        ),
                        @ExampleObject(
                              name = "Example request with invalid Id",
                              value = "abc"
                        )
                  }
            ),
            @Parameter(
                  name = "customerId",
                  description = "The ID of the customer",
                  required = true,
                  in = ParameterIn.QUERY,
                  schema = @Schema(type = "string", format = "string"),
                  examples = {
                        @ExampleObject(
                              name = "Example request with correct Id",
                              value = "d234d99d-170e-42f7-b6ae-435ee56f49a1"
                        ),
                        @ExampleObject(
                              name = "Example request with invalid Id",
                              value = "d234d99d-170e-42f7-b6ae-435ee56f49a1!"
                        )
                  }
            )
      },
      requestBody = @RequestBody(
            description = "The review to be added",
            required = true,
            content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ReviewDto.class),
                  examples = {
                        @ExampleObject(
                              name = "Good request",
                              value = """
                                      {
                                         "rating": "5",
                                         "comment": "Great restaurant!"
                                      }
                                      """
                        ),
                        @ExampleObject(
                              name = "Request with invalid rating",
                              value = """
                                      {
                                         "rating": "6",
                                         "comment": "Great restaurant!"
                                      }
                                      """
                        ),
                        @ExampleObject(
                              name = "Request with missing comment",
                              value = """
                                      {
                                         "rating": "5"
                                      }
                                      """
                        )
                  }
            )
      ),
      responses = {
            @ApiResponse(
                  responseCode = "200",
                  description = "Review added successfully",
                  content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ReviewDto.class)
                  )
            ),
            @ApiResponse(
                  responseCode = "400",
                  description = "Invalid input",
                  content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ResponseExceptionHandler.class)
                  )
            ),
            @ApiResponse(
                  responseCode = "404",
                  description = "Customer or restaurant not found",
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
public @interface CreateReview {
   @AliasFor(annotation = RequestMapping.class, attribute = "path")
   String[] path() default {};
}
