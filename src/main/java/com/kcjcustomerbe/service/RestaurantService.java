package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.RestaurantDto;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantService {

   RestaurantDto getRestaurantById(Long id);

   List<RestaurantDto> getAll();

   int getNumberOfReviewsByRestaurantId(Long id);

   BigDecimal getAverageRatingByRestaurantId(Long id);
}
