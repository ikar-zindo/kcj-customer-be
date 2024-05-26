package com.kcjcustomerbe.dto.customer;

import com.kcjcustomerbe.constant.GlobalConstant;
import lombok.Data;

@Data
public class CustomerAfterCreateDto {

   private String id;

   public String status = GlobalConstant.CUSTOMER_CREATED_SUCCESS_MESSAGE;
}
