package com.kcj_customer_be.service.interfaces;

import com.kcj_customer_be.dto.RestaurantDto;
import com.kcj_customer_be.dto.ReviewDto;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantService {

   /**
    * Retrieves restaurant information based on the provided restaurant ID.
    *
    * @param id The ID of the restaurant to retrieve.
    * @return The RestaurantDto containing restaurant details.
    */
   RestaurantDto getRestaurantById(Long id);

   /**
    * Retrieves a list of all restaurants available in the system.
    *
    * @return List of RestaurantDto objects representing all restaurants.
    */
   List<RestaurantDto> getAllRestaurants();

   /**
    * Retrieves all reviews associated with the restaurant specified by its ID.
    *
    * @param restaurantId The ID of the restaurant to retrieve reviews for.
    * @return List of ReviewDto objects representing reviews for the restaurant.
    */
   List<ReviewDto> getAllReviewsByRestaurantId(Long restaurantId);

   /**
    * Retrieves the number of reviews associated with the restaurant specified by its ID.
    *
    * @param id The ID of the restaurant to count reviews for.
    * @return The number of reviews for the restaurant.
    */
   int getNumberOfReviewsByRestaurantId(Long id);

   /**
    * Calculates the average rating of reviews for the restaurant specified by its ID.
    *
    * @param id The ID of the restaurant to calculate the average rating for.
    * @return The average rating of reviews for the restaurant.
    */
   BigDecimal getAverageRatingByRestaurantId(Long id);
}
