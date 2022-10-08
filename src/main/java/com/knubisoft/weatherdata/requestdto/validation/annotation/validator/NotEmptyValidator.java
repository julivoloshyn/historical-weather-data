package com.knubisoft.weatherdata.requestdto.validation.annotation.validator;

import com.knubisoft.weatherdata.requestdto.validation.annotation.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext constraintValidatorContext) {
        if(contactField == null || contactField.trim().equals("")){
            return false;
        }
        return true;
    }
}
