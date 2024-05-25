package com.kcjcustomerbe.dto.customer;

import lombok.Data;

@Data
public class CustomerAfterUpdateDto {

   private String id;

   private String status = "The customer details have been successfully updated";
}
