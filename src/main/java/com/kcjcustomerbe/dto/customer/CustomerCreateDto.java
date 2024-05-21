package com.kcjcustomerbe.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class CustomerCreateDto {

   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{validation.customer.name}")
   String firstName;

   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{validation.customer.name}")
   String lastName;

   @NotEmpty(message = "{validation.length.empty}")
   @Email(message = "{validation.customer.email}")
   String email;

   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{validation.customer.username}")
   String username;

   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$", message = "{validation.customer.password}")
   String password;

   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "{validation.customer.phoneNumber}")
   String phoneNumber;

   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^[A-Za-z\\s,.-]+\\s\\d+$", message = "{validation.customer.address}")
   String address;

   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^\\d{5}$", message = "{validation.customer.postalCode}")
   String postalCode;
}
