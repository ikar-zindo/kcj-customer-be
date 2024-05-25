package com.kcjcustomerbe.mapper;


import java.util.Objects;

public class MappingUtils {
   public static String mapNullOrEmpty(String newValue, String existingValue) {
      return Objects.isNull(newValue) || newValue.isEmpty() ? existingValue : newValue;
   }
}