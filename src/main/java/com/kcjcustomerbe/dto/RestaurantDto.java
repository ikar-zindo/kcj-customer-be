package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RestaurantDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotBlank(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotBlank(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String address;

   @JsonInclude(JsonInclude.Include.NON_NULL)
//   @Pattern(regexp = "\\\\+49\\\\d{10}", message = "{validation.restaurant.phoneNumber.length}")
   @Length(max = 20, message = "{validation.length.max.20}")
   private String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.length.max.30}")
   private String openingHours;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.length.max.30}")
   private String cuisineType;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 1000, message = "{validation.length.max.1000}")
   private String description;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 200, message = "{validation.length.max.200}")
   private String socialMediaLinks;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private boolean isOpen;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("products")
   private List<ProductDto> productsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("reviews")
   private List<ReviewDto> reviewsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orders")
   private List<OrderDto> ordersDto;
}
