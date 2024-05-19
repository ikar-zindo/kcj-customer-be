package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReviewDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotNull(message = "{validation.value.null}")
   @DecimalMin(value = "1", message = "{validation.review.rating.min}")
   @DecimalMax(value = "5", message = "{validation.review.rating.max}")
   private BigDecimal rating;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   private String comment;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;
}
