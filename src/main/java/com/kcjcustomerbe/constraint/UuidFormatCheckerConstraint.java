package com.kcjcustomerbe.constraint;

import com.kcjcustomerbe.annotation.UuidFormatChecker;
import com.kcjcustomerbe.exception.ErrorMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UuidFormatCheckerConstraint implements ConstraintValidator<UuidFormatChecker, String> {

    private final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    @Override
    public void initialize(UuidFormatChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext context) {
        if(uuid!=null) {

            return Optional.of(uuid)
                    .filter(i -> !i.isBlank())
                    .map(el -> el.matches(UUID_PATTERN))
                    .orElse(false);
        }else {
            throw new NullPointerException(ErrorMessage.NULL_POINTER );
        }
    }
}
