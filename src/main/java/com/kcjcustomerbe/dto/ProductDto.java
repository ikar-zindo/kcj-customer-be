package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Value
public class ProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String description;

   @NotNull(message = "{validation.value.null}")
   BigDecimal price;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String imageUrl;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   boolean isAvailable;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cartProducts")
   List<CartProductDto> cartProductsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   List<OrderProductDto> orderProductsDto;
}
