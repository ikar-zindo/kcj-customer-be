package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.entity.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
public class OrderDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   LocalDateTime updateAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String deliveryAddress;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String postalCode;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   BigDecimal totalAmount;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   OrderStatus orderStatus;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   List<OrderProductDto> orderProductsDto;
}
