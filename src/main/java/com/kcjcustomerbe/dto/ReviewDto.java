package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

   BigDecimal rating;

   String comment;

   LocalDateTime createdAt;
}
