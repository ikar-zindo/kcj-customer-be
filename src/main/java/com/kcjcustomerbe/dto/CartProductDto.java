package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CartProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cart")
   private CartDto cartDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("product")
   private ProductDto productDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private int quantity;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime cratedAt;
}
