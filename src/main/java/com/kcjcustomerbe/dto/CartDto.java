package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto {

   UUID id;

   @JsonProperty("customer")
   CustomerDto customerDto;

   @JsonProperty("cartProducts")
   List<CartProductDto> cartProductsDto;
}
