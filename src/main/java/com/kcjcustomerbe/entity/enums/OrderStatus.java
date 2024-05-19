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

   public String getColor(OrderStatus status) {
      return switch (status) {
         case CREATED -> "primary";
         case COOKING -> "warning";
         case DELIVERING -> "info";
         case COMPLETED -> "success";
         case CANCELLED -> "danger";
         default -> "secondary";
      };
   }
}
