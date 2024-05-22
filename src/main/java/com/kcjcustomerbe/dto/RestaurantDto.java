package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;

@Value
public class RestaurantDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String address;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String openingHours;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String cuisineType;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String description;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String socialMediaLinks;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   boolean isOpen;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("products")
   List<ProductDto> productsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("reviews")
   List<ReviewDto> reviewsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orders")
   List<OrderDto> ordersDto;
}
