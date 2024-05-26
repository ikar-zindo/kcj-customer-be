package com.kcjcustomerbe.service.impl;

import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.entity.Restaurant;
import com.kcjcustomerbe.entity.Review;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.IdNullException;
import com.kcjcustomerbe.exception.list.RestaurantNotFoundException;
import com.kcjcustomerbe.exception.list.RestaurantsListException;
import com.kcjcustomerbe.exception.list.ReviewNotFoundException;
import com.kcjcustomerbe.mapper.RestaurantMapper;
import com.kcjcustomerbe.mapper.ReviewMapper;
import com.kcjcustomerbe.repo.RestaurantRepository;
import com.kcjcustomerbe.repo.ReviewRepository;
import com.kcjcustomerbe.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

   private final RestaurantRepository restaurantRepository;

   private final ReviewRepository reviewRepository;

   private final RestaurantMapper restaurantMapper;

   private final ReviewMapper reviewMapper;

   // READ - GET ALL RESTAURANTS
   public List<RestaurantDto> getAllRestaurants() {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      if (!restaurants.isEmpty()) {
         return restaurantMapper.mapRestaurantsToRestaurantsDto(restaurants);

      } else {
         throw new RestaurantsListException(ErrorMessage.RESTAURANTS_LIST_IS_EMPTY);
      }
   }

   // READ - GET RESTAURANT BY ID
   @Override
   public RestaurantDto getRestaurantById(Long restaurantId) {
      RestaurantDto restaurantDto = null;

      if (restaurantId == null) {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }

      Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

      if (restaurantOptional.isPresent()) {
         restaurantDto = restaurantMapper.mapRestaurantToRestaurantDto((restaurantOptional.get()));
         return restaurantDto;

      } else {
         throw new RestaurantNotFoundException(ErrorMessage.RESTAURANT_ID_NOT_FOUND + restaurantId);
      }
   }

   // READ - GET ALL REVIEWS BY RESTAURANT ID
   public List<ReviewDto> getAllReviewsByRestaurantId(Long restaurantId) {
      if (restaurantId == null) {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }

      Optional<List<Review>> reviews = reviewRepository.findAllByRestaurantId(restaurantId);

      if (reviews.isEmpty()) {
         throw new ReviewNotFoundException(ErrorMessage.REVIEWS_NOT_FOUND);
      }

      return reviewMapper.mapReviewsToReviewsDto(reviews.get());
   }

   // AGGREGATION - GET NUMBER OF RESTAURANT BY RESTAURANT ID
   @Override
   public int getNumberOfReviewsByRestaurantId(Long id) throws IdNullException {
      RestaurantDto restaurantDto = getRestaurantById(id);

      if (restaurantDto != null && restaurantDto.getReviewsDto() != null) {
         return restaurantDto.getReviewsDto().size();
      }

      return 0;
   }

   // AGGREGATION - AVERAGE RATING
   @Override
   public BigDecimal getAverageRatingByRestaurantId(Long id) throws IdNullException {
      RestaurantDto restaurantDto = getRestaurantById(id);

      if (restaurantDto != null && restaurantDto.getReviewsDto() != null &&
            !restaurantDto.getReviewsDto().isEmpty()) {

         BigDecimal sum = BigDecimal.ZERO;
         int numberOfReviews = restaurantDto.getReviewsDto().size(); // save the number of reviews

         for (ReviewDto review : restaurantDto.getReviewsDto()) {
            BigDecimal rating = review.getRating();

            if (rating != null) { // check that the rating is not null
               sum = sum.add(rating);
               return sum.divide(BigDecimal.valueOf(numberOfReviews), 1, RoundingMode.HALF_UP);
            }
         }
      }

      return BigDecimal.ZERO;
   }
}