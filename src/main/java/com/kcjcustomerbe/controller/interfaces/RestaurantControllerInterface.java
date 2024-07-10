package com.kcjcustomerbe.controller.interfaces;

import com.kcjcustomerbe.config.SwaggerConfig;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
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

@Tag(name = SwaggerConfig.RESTAURANT, description = "allows you to receive information about restaurants and his reviews")
public interface RestaurantControllerInterface {

   // READ - ALL RESTAURANTS
   @Operation(
         summary = "Information about all restaurants",
         description = "Allows you to get information about all active restaurants, not require authorization."
   )
   ResponseEntity<List<RestaurantDto>> getAllRestaurants();

   // READ - GET RESTAURANT BY ID
   @Operation(
         summary = "Information about restaurant with selected id",
         description = "Allows you to get information about restaurant with selected id, not require authorization.",
         parameters = {
               @Parameter(
                     name = "restaurantId",
                     description = "Restaurant ID Param",
                     required = true,
                     in = ParameterIn.QUERY,
                     schema = @Schema(type = "integer", format = "int64"),
                     examples = {
                           @ExampleObject(
                                 name = "Example request with correct ID",
                                 value = "1"
                           ),
                           @ExampleObject(
                                 name = "Example request with non-existent ID",
                                 value = "0"
                           )
                     }
               )
         },
         responses = {
               @ApiResponse(
                     responseCode = "200",
                     description = "Restaurant was returned",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ReviewDto.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "404",
                     description = "Restaurant not found",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         }
   )
   ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable(name = "restaurantId") Long restaurantId);

   // READ - GET REVIEWS OF RESTAURANT BY ID
   @Operation(
         summary = "Information about reviews with selected id by restaurant",
         description = "Allows you to get information about reviews with selected id by restaurant, not require authorization."
   )
   ResponseEntity<List<ReviewDto>> getAllReviewsByRestaurantId(@PathVariable(name = "restaurantId") Long restaurantId);

   // CREATE - ADD A REVIEW FOR THE RESTAURANT
   @Operation(
         summary = "Add a review for a restaurant",
         description = "Allows you to create a new product, requires the role of customer.",
         parameters = {
               @Parameter(
                     name = "restaurantId",
                     description = "Restaurant ID Param",
                     required = true,
                     in = ParameterIn.QUERY,
                     schema = @Schema(type = "integer", format = "int64"),
                     examples = {
                           @ExampleObject(
                                 name = "Example request with correct ID",
                                 value = "1"
                           ),
                           @ExampleObject(
                                 name = "Example request with non-existent ID",
                                 value = "0"
                           )
                     }
               )
         },
         requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
               description = "Create Review Request",
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
                     responseCode = "201",
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
                     description = "Restaurant not found",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         }
   )
   ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto,
                                          @Valid @RequestParam Long restaurantId);
}
