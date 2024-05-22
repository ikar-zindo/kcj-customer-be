package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Value
public class CartDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cartProducts")
   List<CartProductDto> cartProductsDto;
}
