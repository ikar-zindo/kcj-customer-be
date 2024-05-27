package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.controller.interfaces.RestaurantControllerInterface;
import com.kcjcustomerbe.validation.UuidFormatChecker;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.service.RestaurantService;
import com.kcjcustomerbe.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantControllerInterface {

   private final RestaurantService restaurantService;

   private final ReviewService reviewService;

   // READ - ALL RESTAURANTS
   @GetMapping
   public ResponseEntity<List<RestaurantDto>> getAllRestaurantsDto() {
      List<RestaurantDto> restaurantsDto = restaurantService.getAllRestaurants();
      return ResponseEntity.ok(restaurantsDto);
   }

   // READ - GET RESTAURANT BY ID
   @GetMapping("/{id}")
   public ResponseEntity<RestaurantDto> getRestaurantDtoById(@PathVariable("id") Long restaurantId) {
      RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
      return ResponseEntity.ok(restaurantDto);
   }

   // READ - GET REVIEWS OF RESTAURANT BY ID
   @GetMapping("/{id}/reviews")
   public ResponseEntity<List<ReviewDto>> getAllReviewsByRestaurantId(@PathVariable("id") Long restaurantId) {
      return ResponseEntity.ok(restaurantService.getAllReviewsByRestaurantId(restaurantId));
   }

   // CREATE - ADD A REVIEW FOR THE RESTAURANT
   @PostMapping("/{id}/reviews")
   public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto,
                                                 @UuidFormatChecker @RequestParam String customerId,
                                                 @Valid @PathVariable("id") Long restaurantId) {

      ReviewDto dto = reviewService.addReview(reviewDto, UUID.fromString(customerId), restaurantId);
      return ResponseEntity.created(URI.create("/restaurant/" + restaurantId + "/reviews")).body(dto);
   }
}
