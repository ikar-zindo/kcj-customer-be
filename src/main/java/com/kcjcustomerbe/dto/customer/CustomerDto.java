package com.kcjcustomerbe.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
public class CustomerDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String firstName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String lastName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String email;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String username;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String address;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   String postalCode;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   LocalDateTime updatedAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   Role role;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   Boolean isBlocked;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cart")
   CartDto cartDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("order")
   private List<OrderDto> ordersDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("review")
   private List<ReviewDto> reviewsDto;
}
