package com.kcurryjibcustomer.mapper;

import com.kcurryjibcustomer.dto.ProductDto;
import com.kcurryjibcustomer.dto.RestaurantDto;
import com.kcurryjibcustomer.entity.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

   @Autowired
   private ModelMapper mapper;

   @Autowired
   private ReviewMapper reviewMapper;

   @Autowired
   private ProductMapper productMapper;

   // convert to DTO
   public RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
   }

   public RestaurantDto allInfoRestaurantDto(Restaurant restaurant) {
      RestaurantDto restaurantDto = mapper.map(restaurant, RestaurantDto.class);

      restaurantDto.setReviewsDto(reviewMapper.convertToReviewsDto(restaurant.getReviews()));
      restaurantDto.setProductsDto(productMapper.convertToProductsDto(restaurant.getProducts()).stream()
              .filter(ProductDto::isAvailable)
              .sorted(Comparator.comparing(ProductDto::getCreatedAt).reversed())
              .collect(Collectors.toList()));

      return restaurantDto;
   }

   // convert to entity
   public Restaurant convertToRestaurant(RestaurantDto restaurantDto) {
      Restaurant restaurant = mapper.map(restaurantDto, Restaurant.class);

      return restaurant;
   }

   public List<Restaurant> convertToRestaurants(List<RestaurantDto> restaurantsDto) {
      return restaurantsDto.stream()
              .map(this::convertToRestaurant)
              .collect(Collectors.toList());
   }
}
