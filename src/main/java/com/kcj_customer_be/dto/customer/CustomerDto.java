package com.kcj_customer_be.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcj_customer_be.dto.CartDto;
import com.kcj_customer_be.dto.OrderDto;
import com.kcj_customer_be.dto.ReviewDto;
import com.kcj_customer_be.entity.enums.RolesName;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

   UUID id;

   String firstName;

   String lastName;

   String email;

   String phoneNumber;

   String address;

   String postalCode;

   LocalDateTime createdAt;

   LocalDateTime updatedAt;

   RolesName role;

   Boolean isBlocked;

   @JsonProperty("cart")
   CartDto cartDto;

   @JsonProperty("order")
   List<OrderDto> ordersDto;

   @JsonProperty("review")
   List<ReviewDto> reviewsDto;
}
