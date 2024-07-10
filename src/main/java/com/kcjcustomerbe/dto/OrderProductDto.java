package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderProductDto {

   UUID id;

   int quantity;

   @JsonProperty("order")
   OrderDto orderDto;

   @JsonProperty("product")
   ProductDto productDto;
}


