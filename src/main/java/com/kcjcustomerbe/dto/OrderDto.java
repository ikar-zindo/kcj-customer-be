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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

   UUID id;

   @JsonProperty("customer")
   CustomerDto customerDto;

   @JsonProperty("restaurant")
   RestaurantDto restaurantDto;

   LocalDateTime createdAt;

   LocalDateTime updateAt;

   String deliveryAddress;

   String postalCode;

   BigDecimal totalAmount;

   OrderStatus orderStatus;

   @JsonProperty("orderProducts")
   List<OrderProductDto> orderProductsDto;
}
