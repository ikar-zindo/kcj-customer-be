package com.kcjcustomerbe.dto.customer;

import lombok.Data;

@Data
public class CustomerAfterUpdateDto {

   private String id;

   private String status = "Customer details have been successfully updated";
}
