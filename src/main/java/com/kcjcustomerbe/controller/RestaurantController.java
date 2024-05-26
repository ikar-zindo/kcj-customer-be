package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.annotation.CreateReview;
import com.kcjcustomerbe.annotation.UuidFormatChecker;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.service.RestaurantService;
import com.kcjcustomerbe.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

   private final RestaurantService restaurantService;

   private final ReviewService reviewService;

   // READ - ALL RESTAURANTS
   @GetMapping
   public ResponseEntity<List<RestaurantDto>> getRestaurants() {
      List<RestaurantDto> restaurantsDto = restaurantService.getAll();
      return ResponseEntity.ok(restaurantsDto);
   }

   // READ - GET RESTAURANT BY ID
   @GetMapping("/{id}")
   public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable("id") Long restaurantId) {
      RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
      return ResponseEntity.ok(restaurantDto);
   }

   // READ - GET REVIEWS OF RESTAURANT BY ID
   @GetMapping("/{id}/reviews")
   public int getNumberOfReviewsByRestaurantId(@PathVariable("id") Long restaurantId) {
      return restaurantService.getNumberOfReviewsByRestaurantId(restaurantId);
   }

   // CREATE - ADD A REVIEW FOR THE RESTAURANT
   @CreateReview(path = "/{id}/reviews")
   @ResponseStatus(CREATED)
   public ReviewDto addReview(@Valid @RequestBody ReviewDto reviewDto,
                                              @UuidFormatChecker @RequestParam String customerId,
                                              @Valid @PathVariable("id") Long restaurantId) {

      return reviewService.addReview(reviewDto, UUID.fromString(customerId), restaurantId);
   }
}
