package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Value
public class OrderProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   int quantity;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("order")
   OrderDto orderDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("product")
   ProductDto productDto;
}


