package com.kcj_customer_be.controller;

import com.kcj_customer_be.controller.interfaces.RestaurantControllerInterface;
import com.kcj_customer_be.dto.ProductDto;
import com.kcj_customer_be.dto.RestaurantDto;
import com.kcj_customer_be.dto.ReviewDto;
import com.kcj_customer_be.dto.customer.CustomerDto;
import com.kcj_customer_be.service.interfaces.CustomerService;
import com.kcj_customer_be.service.interfaces.RestaurantService;
import com.kcj_customer_be.service.interfaces.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantControllerInterface {

   private final RestaurantService restaurantService;

   private final ReviewService reviewService;

   private final CustomerService customerService;

   // READ - ALL RESTAURANTS
   @GetMapping
   public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
      List<RestaurantDto> restaurantsDto = restaurantService.getAllRestaurants();
      return ResponseEntity.ok(restaurantsDto);
   }

   // READ - GET RESTAURANT BY ID
   @GetMapping("/{restaurantId}")
   public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable("restaurantId") Long restaurantId) {
      RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
      return ResponseEntity.ok(restaurantDto);
   }

   // READ - GET PRODUCTS OF RESTAURANT BY ID
   @GetMapping("/{restaurantId}/products")
   public ResponseEntity<List<ProductDto>> getAllProductsByRestaurantId(@PathVariable("restaurantId") Long restaurantId) {
      return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId).getProductsDto());
   }

   // READ - GET REVIEWS OF RESTAURANT BY ID
   @GetMapping("/{restaurantId}/reviews")
   public ResponseEntity<List<ReviewDto>> getAllReviewsByRestaurantId(@PathVariable("restaurantId") Long restaurantId) {
      return ResponseEntity.ok(restaurantService.getAllReviewsByRestaurantId(restaurantId));
   }

   // CREATE - ADD A REVIEW FOR THE RESTAURANT
   @PostMapping("/add-review")
   public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto,
                                                 @Valid @RequestParam Long restaurantId) {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);

         ReviewDto dto = reviewService.addReview(reviewDto, customerDto.getId(), restaurantId);
         return ResponseEntity.created(URI.create("/restaurant/" + restaurantId + "/reviews")).body(dto);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }
}
