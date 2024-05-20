package com.kcjcustomerbe.validation.annotation;

import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.validation.constraint.UuidFormatCheckerConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UuidFormatCheckerConstraint.class)
public @interface UuidFormatChecker {

   String message() default ErrorMessage.INVALID_ID;

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}