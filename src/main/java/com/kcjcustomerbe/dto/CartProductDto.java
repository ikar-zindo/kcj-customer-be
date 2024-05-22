package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class CartProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cart")
   CartDto cartDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("product")
   ProductDto productDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   int quantity;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   LocalDateTime cratedAt;
}
