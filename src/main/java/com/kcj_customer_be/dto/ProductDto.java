package com.kcj_customer_be.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

   Long id;

   String name;

   String description;

   BigDecimal price;

   String imageUrl;

   LocalDateTime createdAt;

   Boolean isAvailable;

   @JsonProperty("restaurant")
   RestaurantDto restaurantDto;

   @JsonProperty("cartProducts")
   List<CartProductDto> cartProductsDto;

   @JsonProperty("orderProducts")
   List<OrderProductDto> orderProductsDto;
}
