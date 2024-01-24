package com.kcurryjibcustomer.entity.enums;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

   ROLE_CUSTOMER("ROLE_CUSTOMER");

   private final String value;

   Role(String value) {
      this.value = value;
   }

   @Override
   public String getAuthority() {
      return value;
   }
}
