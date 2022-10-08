package com.knubisoft.weatherdata.requestdto.validation.annotation.validator;

import com.knubisoft.weatherdata.requestdto.validation.annotation.DateTimeFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateTimeFormatValidator implements ConstraintValidator<DateTimeFormat, String> {

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDate.parse(contactField);
        } catch (DateTimeParseException | NullPointerException e){
            return false;
        }

        return true;
    }
}
