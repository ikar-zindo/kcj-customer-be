package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.validation.UuidFormatChecker;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

   ReviewDto addReview(ReviewDto reviewDto, @UuidFormatChecker UUID customerId, Long restaurantId);

   ReviewDto getReviewById(Long reviewId);

   List<ReviewDto> getAllReviews();

   ReviewDto updateReview(ReviewDto reviewDto);

   void deleteReview(Long reviewId);
}
