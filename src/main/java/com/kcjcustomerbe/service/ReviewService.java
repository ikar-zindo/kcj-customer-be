package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

   ReviewDto addReview(ReviewDto reviewDto, UUID customerId, Long restaurantId);

   ReviewDto getReviewById(Long reviewId);

   List<ReviewDto> getAllReviews();

   ReviewDto updateReview(ReviewDto reviewDto);

   void deleteReview(Long reviewId);
}
