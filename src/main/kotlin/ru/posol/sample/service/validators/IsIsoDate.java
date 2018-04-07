package ru.posol.sample.service.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsoDateValidator.class})
public @interface IsIsoDate {
    String message() default "value is not ISO date format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}


