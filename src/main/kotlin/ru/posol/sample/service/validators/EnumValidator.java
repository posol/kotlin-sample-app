package ru.posol.sample.service.validators;

import ru.posol.sample.domain.LogLevel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator class for validate input ENUM
 */
public class EnumValidator implements ConstraintValidator<IsEnumValue, String> {

    @Override
    public void initialize(IsEnumValue constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;

        try {
            LogLevel logLevel = LogLevel.valueOf(value);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

}