package com.kcjcustomerbe.dto.customer;

import lombok.Data;

@Data
public class CustomerAfterCreateDto {

   private String id;

   private String status = "The customer was successfully created";
}
