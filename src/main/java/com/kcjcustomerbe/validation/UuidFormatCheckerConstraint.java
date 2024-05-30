package com.kcjcustomerbe.validation;

import com.kcjcustomerbe.exception.ErrorMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;
import java.util.regex.Pattern;

public class UuidFormatCheckerConstraint implements ConstraintValidator<UuidFormatChecker, String> {

    private final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    @Override
    public void initialize(UuidFormatChecker constraintAnnotation) {
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext context) {
        if(uuid!=null) {

            return Optional.of(uuid)
                    .filter(i -> !i.isBlank())
                    .map(el -> Pattern.compile(UUID_PATTERN).matcher(el).matches())
                    .orElse(false);
        }else {
            throw new NullPointerException(ErrorMessage.NULL_POINTER);
        }
    }
}
