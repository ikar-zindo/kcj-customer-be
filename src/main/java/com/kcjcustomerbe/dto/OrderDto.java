package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.entity.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime updateAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String deliveryAddress;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String postalCode;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private BigDecimal totalAmount;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private OrderStatus orderStatus;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   private List<OrderProductDto> orderProductsDto;
}
