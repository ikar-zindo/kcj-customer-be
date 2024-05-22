package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.annotation.UuidFormatChecker;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.exception.list.IdNotFoundException;
import com.kcjcustomerbe.service.impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

   private final ReviewServiceImpl reviewService;

   @GetMapping
   public List<ReviewDto> getAllReviews() {
      return reviewService.getAllReviews();
   }

   @GetMapping("/{id}")
   public ReviewDto getReviewById(@PathVariable("id") Long reviewId) throws IdNotFoundException {
      return reviewService.getReviewById(reviewId);
   }
   
   @PostMapping
   public ResponseEntity<ReviewDto> addReview(@RequestBody @Valid ReviewDto reviewDto,
                                             @UuidFormatChecker @RequestParam String customerId,
                                             @Valid @RequestParam Long restaurantId) throws IdNotFoundException {

      return ResponseEntity.ok(reviewService.addReview(reviewDto, UUID.fromString(customerId), restaurantId));
   }
}
