package com.kcjcustomerbe.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CustomerUpdateDto {

   @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{validation.customer.name}")
   String firstName;

   @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{validation.customer.name}")
   String lastName;

   @Email(message = "{validation.customer.email}")
   String email;

   @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$", message = "{validation.customer.password}")
   String password;

   @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "{validation.customer.phoneNumber}")
   String phoneNumber;

   @Pattern(regexp = "^[A-Za-z\\s,.-]+\\s\\d+$", message = "{validation.customer.address}")
   String address;

   @Pattern(regexp = "^\\d{5}$", message = "{validation.customer.postalCode}")
   String postalCode;

   LocalDateTime updatedAt;

   Boolean isBlocked;
}
