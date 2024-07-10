package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;

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
