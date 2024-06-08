package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
