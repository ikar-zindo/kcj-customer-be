package com.kcjcustomerbe.controller.interfaces;

import com.kcjcustomerbe.config.SwaggerConfig;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import com.kcjcustomerbe.validation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = SwaggerConfig.RESTAURANT, description = "allows you to receive information about restaurants adn his reviews")
public interface RestaurantControllerInterface {

   // READ - ALL RESTAURANTS
   @Operation(
         summary = "information about all restaurants",
         description = "allows you to get information about all active restaurants, not require authorization"
   )
   ResponseEntity<List<RestaurantDto>> getAllRestaurantsDto();

   // READ - GET RESTAURANT BY ID
   @Operation(
         summary = "information about restaurant with selected id",
         description = "allows you to get information about restaurant with selected id, not require authorization"
   )
   ResponseEntity<RestaurantDto> getRestaurantDtoById(@PathVariable(name = "id") Long restaurantId);

   // READ - GET REVIEWS OF RESTAURANT BY ID
   @Operation(
         summary = "information about reviews with selected id by restaurant",
         description = "allows you to get information about reviews with selected id by restaurant, not require authorization"
   )
   ResponseEntity<List<ReviewDto>> getAllReviewsByRestaurantId(@PathVariable(name = "id") Long restaurantId);

   // CREATE - ADD A REVIEW FOR THE RESTAURANT
   @Operation(
         summary = "Add a review for a restaurant",
         description = "allows you to create a new product, requires the role of customer",
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
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
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
         }
   )
   ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto,
                                          @UuidFormatChecker @RequestParam String customerId,
                                          @Valid @PathVariable("id") Long restaurantId);
}
