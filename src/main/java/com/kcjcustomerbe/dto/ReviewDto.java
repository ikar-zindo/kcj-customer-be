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
public class ReviewDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   BigDecimal rating;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String comment;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   LocalDateTime createdAt;
}
