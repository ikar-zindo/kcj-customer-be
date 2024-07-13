package com.kcj_customer_be.controller.interfaces;

import com.kcj_customer_be.config.SwaggerConfig;
import com.kcj_customer_be.dto.ReviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = SwaggerConfig.REVIEW, description = "allows you to receive information about reviews")
public interface ReviewControllerInterface {

   // READ - GET ALL REVIEWS
   @Operation(
         summary = "information about all reviews",
         description = "allows you to get information about all active products, not require authorization"
   )
   ResponseEntity<List<ReviewDto>> getAllReviewsDto();

   // READ - GET REVIEW BY ID
   @Operation(
         summary = "information about review with selected id",
         description = "allows you to get information about product with selected id, not require authorization"
   )
   ResponseEntity<ReviewDto> getReviewDtoById(@PathVariable(name = "id") Long id);
}
