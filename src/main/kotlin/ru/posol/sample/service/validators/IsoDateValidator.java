package ru.posol.sample.service.validators;

import ru.posol.sample.utils.DateConverterKt;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validator class for validate input date
 */
public class IsoDateValidator implements ConstraintValidator<IsIsoDate, String> {

    @Override
    public void initialize(IsIsoDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;

        try {
            DateConverterKt.convertIsoStrToDate(value);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

}