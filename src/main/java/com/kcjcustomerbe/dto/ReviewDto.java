package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.dto.customer.CustomerDto;
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

   @Min(value = 1, message = "{validation.review.rating.min}")
   @Max(value = 5, message = "{validation.review.rating.max}")
   BigDecimal rating;

   @NotBlank(message = "{validation.review.comment.notBlank}")
   @Pattern(regexp = "^[\\w\\s.,!\"'()\\-]{1,500}$", message = "{validation.review.comment}")
   String comment;

   LocalDateTime createdAt;

   LocalDateTime updatedAt;
}
