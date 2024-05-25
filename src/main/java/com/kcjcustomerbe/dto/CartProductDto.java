package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartProductDto {

   UUID id;

   @JsonProperty("cart")
   CartDto cartDto;

   @JsonProperty("product")
   ProductDto productDto;

   int quantity;

   LocalDateTime cratedAt;
}
