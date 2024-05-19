package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcjcustomerbe.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CustomerDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.60}")
   private String firstName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.60}")
   private String lastName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Email(message = "{validation.value.email}")
   @Length(max = 60, message = "{validation.length.max.60}")
   private String email;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 60, message = "{validation.length.max.60}")
   private String username;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 60, message = "{validation.length.max.60}")
   private String password;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^\\+\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$", message = "{validation.value.phoneNumber}")
   private String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   private String address;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 5, min = 5, message = "{validation.length.max.5}")
   private String postalCode;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Role role;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Boolean isBlocked;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cart")
   private CartDto cartDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("order")
   private List<OrderDto> ordersDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("review")
   private List<ReviewDto> reviewsDto;
}
