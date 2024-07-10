package com.kcjcustomerbe.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.entity.enums.RolesName;
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
