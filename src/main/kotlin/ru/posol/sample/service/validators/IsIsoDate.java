package ru.posol.sample.service.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { IsoDateValidator.class })
public @interface IsIsoDate {
    String message() default "value is not ISO date format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}


