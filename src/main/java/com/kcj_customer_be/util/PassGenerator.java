package com.kcj_customer_be.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGenerator {
   public static void main(String[] args) {

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

      // all customer has pass "1qaz"
      // all employees has pass "qwerty123"
      System.out.println(encoder.encode("12345"));
   }
}