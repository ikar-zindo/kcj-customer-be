package com.kcjcustomerbe.service.impl;

import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.entity.Restaurant;
import com.kcjcustomerbe.entity.Review;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.customer.CustomerNotFoundException;
import com.kcjcustomerbe.exception.list.IdNotFoundException;
import com.kcjcustomerbe.exception.list.restaurant.RestaurantNotFoundException;
import com.kcjcustomerbe.exception.list.review.ReviewEmptyException;
import com.kcjcustomerbe.mapper.ReviewMapper;
import com.kcjcustomerbe.repo.CustomerRepository;
import com.kcjcustomerbe.repo.RestaurantRepository;
import com.kcjcustomerbe.repo.ReviewRepository;
import com.kcjcustomerbe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

   public final ReviewRepository reviewRepository;

   public final CustomerRepository customerRepository;

   public final RestaurantRepository restaurantRepository;

   public final ReviewMapper reviewMapper;

   // CREATE
   @Override
   public ReviewDto addReview(ReviewDto reviewDto, UUID customerId, Long restaurantId) throws IdNotFoundException {
      Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId));

      Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(ErrorMessage.RESTAURANT_ID_NOT_FOUND + restaurantId));

      if (reviewDto != null) {
      Review review = reviewMapper.mapReviewToCreateReviewDto(reviewDto);

      review.setCustomer(customer);
      review.setRestaurant(restaurant);

      Review savedReview = reviewRepository.save(review);
      return reviewMapper.mapReviewToReviewDto(savedReview);

      } else {
         throw new ReviewEmptyException(ErrorMessage.REVIEW_BODY_IS_EMPTY);
      }

   }

   // READ
   @Override
   public ReviewDto getReviewById(Long reviewId) throws IdNotFoundException {
      Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new IdNotFoundException(ErrorMessage.REVIEW_ID_NOT_FOUND + reviewId));

      return reviewMapper.mapReviewToReviewDto(review);
   }

   // READ
   @Override
   public List<ReviewDto> getAllReviews() {
      List<Review> reviews = reviewRepository.findAll();

      if (!reviews.isEmpty()) {
      return reviewMapper.mapReviewsToReviewsDto(reviews);
      } else {
         throw new ReviewEmptyException(ErrorMessage.REVIEWS_NOT_FOUND);
      }
   }

   // UPDATE
   @Override
   public ReviewDto updateReview(ReviewDto reviewDto) {
      Review review = reviewMapper.mapReviewToUpdateReviewDto(reviewDto);
      Review updatedReview = reviewRepository.save(review);
      return reviewMapper.mapReviewToReviewDto(updatedReview);
   }

   // DELETE
   @Override
   public void deleteReview(Long reviewId) {
      reviewRepository.deleteById(reviewId);
   }
}
