package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 25, message = "{validation.length.max.25}")
   private String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 1000, message = "{validation.length.max.1000}")
   private String description;

   @NotNull(message = "{validation.value.null}")
   @DecimalMin(value = "0.01", message = "{validation.product.price}")
   @DecimalMax(value = "10000", message = "{validation.product.price.value}")
   private BigDecimal price;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String imageUrl;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private boolean isAvailable;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   @NotNull(message = "{validation.value.null}")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cartProducts")
   private List<CartProductDto> cartProductsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   private List<OrderProductDto> orderProductsDto;
}
