package ru.posol.sample.service.validators;

import ru.posol.sample.utils.DateConverter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator class for validate input date
 */
public class IsoDateValidator implements ConstraintValidator<IsIsoDate, String> {

    private DateConverter dateConverter;

    @Override
    public void initialize(IsIsoDate constraintAnnotation) {
        dateConverter = new DateConverter();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;

        try {
            dateConverter.convertIsoStrToDate(value);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

}