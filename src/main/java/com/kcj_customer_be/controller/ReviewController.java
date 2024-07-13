package com.kcj_customer_be.controller;

import com.kcj_customer_be.controller.interfaces.ReviewControllerInterface;
import com.kcj_customer_be.dto.ReviewDto;
import com.kcj_customer_be.service.interfaces.ReviewService;
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
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerInterface {

   private final ReviewService reviewService;

   // READ - GET ALL REVIEWS
   @GetMapping
   public ResponseEntity<List<ReviewDto>> getAllReviewsDto() {
      List<ReviewDto> reviewsDto = reviewService.getAllReviews();
      return ResponseEntity.ok(reviewsDto);
   }

   // READ - GET REVIEW BY ID
   @GetMapping("/{id}")
   public ResponseEntity<ReviewDto> getReviewDtoById(@PathVariable("id") Long reviewId) {
      ReviewDto reviewDto = reviewService.getReviewById(reviewId);
      return ResponseEntity.ok(reviewDto);
   }
}
