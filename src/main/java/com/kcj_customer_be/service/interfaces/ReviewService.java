package com.kcj_customer_be.service.interfaces;

import com.kcj_customer_be.dto.ReviewDto;
import com.kcj_customer_be.validation.UuidFormatChecker;

import java.util.List;
import java.util.UUID;

public interface ReviewService {


   /**
    * Adds a new review for a restaurant by a customer.
    *
    * @param reviewDto    The ReviewDto object containing review details.
    * @param customerId   The UUID of the customer adding the review.
    * @param restaurantId The ID of the restaurant receiving the review.
    * @return The ReviewDto object representing the added review.
    */
   ReviewDto addReview(ReviewDto reviewDto, @UuidFormatChecker UUID customerId, Long restaurantId);

   /**
    * Retrieves a review by its unique ID.
    *
    * @param reviewId The ID of the review to retrieve.
    * @return The ReviewDto object representing the retrieved review.
    */
   ReviewDto getReviewById(Long reviewId);

   /**
    * Retrieves a list of all reviews in the system.
    *
    * @return List of ReviewDto objects representing all reviews.
    */
   List<ReviewDto> getAllReviews();

   /**
    * Updates an existing review with new details.
    *
    * @param reviewDto The ReviewDto object containing updated review details.
    * @return The ReviewDto object representing the updated review.
    */
   ReviewDto updateReview(ReviewDto reviewDto);

   /**
    * Deletes a review from the system by its ID.
    *
    * @param reviewId The ID of the review to delete.
    */
   void deleteReview(Long reviewId);
}
