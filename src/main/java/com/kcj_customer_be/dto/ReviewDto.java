package com.kcj_customer_be.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcj_customer_be.dto.customer.CustomerDto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto {

   Long id;

   @JsonProperty("restaurant")
   RestaurantDto restaurantDto;

   @JsonProperty("customer")
   CustomerDto customerDto;

   @NotNull(message = "{validation.value.null}")
   @Min(value = 1, message = "{validation.review.rating.min}")
   @Max(value = 5, message = "{validation.review.rating.max}")
   BigDecimal rating;

   @NotBlank(message = "{validation.length.empty}")
   @Pattern(regexp = "^[\\w\\s.,!\"'()\\-]{1,500}$", message = "{validation.review.comment}")
   String comment;

   LocalDateTime createdAt;

   LocalDateTime updatedAt;
}
