package com.knubisoft.weatherdata.requestdto.validation.annotation;

import com.knubisoft.weatherdata.requestdto.validation.annotation.validator.DateTimeFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DateTimeFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeFormat {

    String message() default "Invalid date time format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

