package com.kcjcustomerbe.service;

import com.kcjcustomerbe.config.MapperUtil;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.entity.Restaurant;
import com.kcjcustomerbe.exception.list.RestaurantException;
import com.kcjcustomerbe.mapper.RestaurantMapper;
import com.kcjcustomerbe.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

   private final RestaurantRepository repository;

   private final RestaurantMapper mapper;

   @Autowired
   public RestaurantService(RestaurantRepository repository,
                            RestaurantMapper mapper) {

      this.repository = repository;
      this.mapper = mapper;
   }

   // READ
   public List<RestaurantDto> getAll() throws RestaurantException {
      List<Restaurant> restaurants = new ArrayList<>(repository.findAll());

      return MapperUtil.convertlist(restaurants, mapper::allInfoRestaurantDto);
   }

   // READ
   public RestaurantDto getById(Long id) throws RestaurantException {
      RestaurantDto restaurantDto = null;

      if (id != null) {
         Optional<Restaurant> restaurantOptional = repository.findById(id);

         if (restaurantOptional.isPresent()) {
            restaurantDto = mapper.allInfoRestaurantDto(restaurantOptional.get());

         } else {
            throw new RestaurantException(
                    String.format("Restaurant not found in database with id=%d",
                            id));
         }
      } else {
         throw new RestaurantException("There is no restaurant ID to search for!");
      }

      return restaurantDto;
   }

   public int getNumberOfReviewsByRestaurantId(Long id) throws RestaurantException {
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
