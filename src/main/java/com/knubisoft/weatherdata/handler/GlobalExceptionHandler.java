package com.knubisoft.weatherdata.handler;

import com.knubisoft.weatherdata.handler.exception.EqualsDatesException;
import com.knubisoft.weatherdata.handler.exception.IncorrectPeriodException;
import com.knubisoft.weatherdata.handler.exception.IncorrectStartDateTimeException;
import com.knubisoft.weatherdata.handler.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidatorException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPeriodException.class)
    public ResponseEntity<Object> handlePeriod() {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("Incorrect period. Period must be less then six days.");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectStartDateTimeException.class)
    public ResponseEntity<Object> handleStartDateTime() {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("Incorrect period. Start date time must be earlier then end date time.");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EqualsDatesException.class)
    public ResponseEntity<Object> handleEqualsDates() {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("Incorrect period. Dates can not be equals");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
