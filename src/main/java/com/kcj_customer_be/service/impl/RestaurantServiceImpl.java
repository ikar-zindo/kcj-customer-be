package com.kcj_customer_be.service.impl;

import com.kcj_customer_be.dto.RestaurantDto;
import com.kcj_customer_be.dto.ReviewDto;
import com.kcj_customer_be.entity.Restaurant;
import com.kcj_customer_be.exception.ErrorMessage;
import com.kcj_customer_be.exception.list.IdNullException;
import com.kcj_customer_be.exception.list.RestaurantNotFoundException;
import com.kcj_customer_be.exception.list.RestaurantsListException;
import com.kcj_customer_be.exception.list.ReviewNotFoundException;
import com.kcj_customer_be.mapper.RestaurantMapper;
import com.kcj_customer_be.mapper.ReviewMapper;
import com.kcj_customer_be.repo.RestaurantRepository;
import com.kcj_customer_be.repo.ReviewRepository;
import com.kcj_customer_be.service.interfaces.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
         restaurantDto = restaurantMapper.mapToRestaurantDto((restaurantOptional.get()));
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

      List<ReviewDto> reviewsDto = getRestaurantById(restaurantId).getReviewsDto().stream()
            .sorted(Comparator.comparing(ReviewDto::getCreatedAt).reversed())
            .collect(Collectors.toList());
      if (reviewsDto.isEmpty()) {
         throw new ReviewNotFoundException(ErrorMessage.REVIEWS_NOT_FOUND);
      }
      return reviewsDto;
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