package com.kcjcustomerbe.validation.constraint;

import com.kcjcustomerbe.validation.annotation.UuidFormatChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UuidFormatCheckerConstraint implements ConstraintValidator<UuidFormatChecker, String> {

   private static final String UUID_PATTERN = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

   @Override
   public void initialize(UuidFormatChecker constraintAnnotation) {
      ConstraintValidator.super.initialize(constraintAnnotation);
   }

   @Override
   public boolean isValid(String uuid, ConstraintValidatorContext constraintValidatorContext) {

      return Optional.ofNullable(uuid)
              .filter(el -> !el.isBlank())
              .map(el -> el.matches(UUID_PATTERN))
              .orElse(false);
   }
}