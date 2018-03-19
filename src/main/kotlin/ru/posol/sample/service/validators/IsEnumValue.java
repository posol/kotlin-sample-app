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
@Constraint(validatedBy = { EnumValidator.class })
public @interface IsEnumValue {
    String message() default "value is not ENUM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};



}
