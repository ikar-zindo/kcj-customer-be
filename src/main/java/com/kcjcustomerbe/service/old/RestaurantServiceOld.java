package com.kcjcustomerbe.service.old;

import com.kcjcustomerbe.config.MapperUtil;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.entity.Restaurant;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.IdNotFoundException;
import com.kcjcustomerbe.exception.list.RestaurantNotFoundException;
import com.kcjcustomerbe.exception.list.RestaurantsListException;
import com.kcjcustomerbe.mapper.modelmapper.RestaurantMapper;
import com.kcjcustomerbe.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceOld {

   private final RestaurantRepository repository;

   private final RestaurantMapper mapper;

   @Autowired
   public RestaurantServiceOld(RestaurantRepository repository,
                               RestaurantMapper mapper) {

      this.repository = repository;
      this.mapper = mapper;
   }

   // READ
   public List<RestaurantDto> getAll() {
      List<Restaurant> restaurants = new ArrayList<>(repository.findAll());

      if (!restaurants.isEmpty()) {
         return MapperUtil.convertlist(restaurants, mapper::allInfoRestaurantDto);

      } else {
         throw new RestaurantsListException(ErrorMessage.RESTAURANTS_LIST_IS_EMPTY);
      }
   }

   // READ
   public RestaurantDto getById(Long id) {
      RestaurantDto restaurantDto = null;

      if (id != null) {
         Optional<Restaurant> restaurantOptional = repository.findById(id);

         if (restaurantOptional.isPresent()) {
            restaurantDto = mapper.allInfoRestaurantDto(restaurantOptional.get());

         } else {
            throw new RestaurantNotFoundException(ErrorMessage.RESTAURANT_ID_NOT_FOUND + id);
         }
      } else {
         throw new IdNotFoundException(ErrorMessage.NULL_ID);
      }

      return restaurantDto;
   }

   public int getNumberOfReviewsByRestaurantId(Long id) {
      RestaurantDto restaurantDto = getById(id);

      if (restaurantDto != null && restaurantDto.getReviewsDto() != null) {
         return restaurantDto.getReviewsDto().size();
      }

      return 0;
   }

   // AGGREGATION - AVERAGE RATING
   public BigDecimal getAverageRatingByRestaurantId(Long id) {
      RestaurantDto restaurantDto = getById(id);

      if (restaurantDto != null && restaurantDto.getReviewsDto() != null &&
              !restaurantDto.getReviewsDto().isEmpty()) {

         BigDecimal sum = BigDecimal.ZERO;
         int numberOfReviews = restaurantDto.getReviewsDto().size(); // save the number of reviews

         for (ReviewDto review : restaurantDto.getReviewsDto()) {
            BigDecimal rating = review.getRating();

            if (rating != null) { // check that the rating is not null
               sum = sum.add(rating);
            }
         }

         if (numberOfReviews != 0) { // check that the number of reviews is not equal to avoid division by zero
            return sum.divide(BigDecimal.valueOf(numberOfReviews), 1, RoundingMode.HALF_UP);
         }
      }

      return BigDecimal.ZERO;
   }
}
