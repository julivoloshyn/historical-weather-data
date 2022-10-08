package com.knubisoft.weatherdata.requestdto.validation.annotation;

import com.knubisoft.weatherdata.requestdto.validation.annotation.validator.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NotEmptyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {

    String message() default "Invalid parameter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}