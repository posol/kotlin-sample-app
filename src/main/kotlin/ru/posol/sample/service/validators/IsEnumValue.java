package ru.posol.sample.service.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValidator.class})
public @interface IsEnumValue {
    String message() default "value is not ENUM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
