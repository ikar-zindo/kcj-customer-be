package com.kcjcustomerbe.entity.enums;

public enum OrderStatus {

   CREATED("CREATED"),
   COOKING("COOKING"),
   DELIVERING("DELIVERING"),
   COMPLETED("COMPLETED"),
   CANCELLED("CANCELLED");

   public String value;

   OrderStatus(String value) {
      this.value = value;
   }

   public String getColor(OrderStatus status) {
      switch (status) {
         case CREATED:
            return "primary";
         case COOKING:
            return "warning";
         case DELIVERING:
            return "info";
         case COMPLETED:
            return "success";
         case CANCELLED:
            return "danger";
         default:
            return "secondary";
      }
   }
}
