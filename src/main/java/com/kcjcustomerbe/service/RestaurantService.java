package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantService {

   RestaurantDto getRestaurantById(Long id);

   List<RestaurantDto> getAllRestaurants();

   List<ReviewDto> getAllReviewsByRestaurantId(Long restaurantId);

   int getNumberOfReviewsByRestaurantId(Long id);

   BigDecimal getAverageRatingByRestaurantId(Long id);
}
