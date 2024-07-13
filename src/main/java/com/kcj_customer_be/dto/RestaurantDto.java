package com.kcj_customer_be.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDto {

   Long id;

   String name;

   String address;

   String phoneNumber;

   String openingHours;

   String cuisineType;

   String description;

   String socialMediaLinks;

   Boolean isOpen;

   @JsonProperty("products")
   List<ProductDto> productsDto;

   @JsonProperty("reviews")
   List<ReviewDto> reviewsDto;

   @JsonProperty("orders")
   List<OrderDto> ordersDto;
}
