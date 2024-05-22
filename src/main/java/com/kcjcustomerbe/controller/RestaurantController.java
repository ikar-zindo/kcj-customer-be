package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

   private final RestaurantService restaurantService;

   @GetMapping
   public ResponseEntity<List<RestaurantDto>> getRestaurants() {
      List<RestaurantDto> restaurantsDto = restaurantService.getAll();
      return ResponseEntity.ok(restaurantsDto);
   }

   @GetMapping("/{id}")
   public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable("id") Long restaurantId) {
      RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
      return ResponseEntity.ok(restaurantDto);
   }

   @GetMapping("/{id}/reviews")
   public int getNumberOfReviewsByRestaurantId(@PathVariable Long id) {
      return restaurantService.getNumberOfReviewsByRestaurantId(id);
   }
}
