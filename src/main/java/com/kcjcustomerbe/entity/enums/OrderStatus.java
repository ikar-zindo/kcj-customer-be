package com.kcjcustomerbe.entity.enums;

public enum OrderStatus {

   CREATED("CREATED"),
   COOKING("COOKING"),
   DELIVERING("DELIVERING"),
   COMPLETED("COMPLETED"),
   CANCELLED("CANCELLED");

   public final String value;

   OrderStatus(String value) {
      this.value = value;
   }
}
