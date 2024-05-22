package com.kcjcustomerbe.service.impl;

import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.entity.Restaurant;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.IdNullException;
import com.kcjcustomerbe.exception.list.restaurant.RestaurantNotFoundException;
import com.kcjcustomerbe.exception.list.restaurant.RestaurantsListException;
import com.kcjcustomerbe.mapper.RestaurantMapper;
import com.kcjcustomerbe.repo.RestaurantRepository;
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

   private final RestaurantMapper restaurantMapper;

   // READ - GET ALL
   @Override
   public List<RestaurantDto> getAll() {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      if (!restaurants.isEmpty()) {
         return restaurantMapper.mapRestaurantsToRestaurantsDto(restaurants);

      } else {
         throw new RestaurantsListException(ErrorMessage.RESTAURANTS_LIST_IS_EMPTY);
      }
   }

   // READ - GET RESTAURANT BY ID
   @Override
   public RestaurantDto getRestaurantById(Long id) {
      RestaurantDto restaurantDto = null;

      if (id != null) {
         Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

         if (restaurantOptional.isPresent()) {
            restaurantDto = restaurantMapper.mapRestaurantToRestaurantDto((restaurantOptional.get()));

         } else {
            throw new RestaurantNotFoundException(ErrorMessage.RESTAURANT_ID_NOT_FOUND + id);
         }
      } else {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }

      return restaurantDto;
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