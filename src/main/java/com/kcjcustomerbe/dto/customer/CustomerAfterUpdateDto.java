package com.kcjcustomerbe.dto.customer;

import com.kcjcustomerbe.constant.GlobalConstant;
import lombok.Data;

@Data
public class CustomerAfterUpdateDto {

   private String id;

   private String status = GlobalConstant.CUSTOMER_UPDATED_SUCCESS_MESSAGE;
}
