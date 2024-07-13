package com.kcj_customer_be.util;

import java.util.Objects;

/**
 * Utility class for mapping operations.
 * <p>
 * Provides helper methods for mapping fields, particularly for handling null or empty values.
 * This class is intended to be used in conjunction with MapStruct for custom mapping logic.
 *
 * @author Ivan Bukrieiev
 * @since v1.0.0
 */
public class MappingUtils {

   public static String mapNullOrEmpty(String newValue, String existingValue) {
      return Objects.isNull(newValue) || newValue.isEmpty() ? existingValue : newValue;
   }
}