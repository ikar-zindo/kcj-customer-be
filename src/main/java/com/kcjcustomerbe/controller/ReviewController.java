package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

   private final ReviewService reviewService;

   // READ - GET ALL REVIEWS
   @GetMapping
   public ResponseEntity<List<ReviewDto>> getAllReviews() {
      List<ReviewDto> reviewsDto = reviewService.getAllReviews();

      return ResponseEntity.ok(reviewsDto);
   }

   // READ - GET REVIEW BY ID
   @GetMapping("/{id}")
   public ResponseEntity<ReviewDto> getReviewById(@PathVariable("id") Long reviewId) {
      ReviewDto reviewDto = reviewService.getReviewById(reviewId);

      return ResponseEntity.ok(reviewDto);
   }
}
