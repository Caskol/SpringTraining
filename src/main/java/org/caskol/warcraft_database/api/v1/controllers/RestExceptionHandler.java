package org.caskol.warcraft_database.api.v1.controllers;

import jakarta.validation.ValidationException;
import org.caskol.warcraft_database.api.v1.exceptions.NoSuchElementFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementFoundException.class)
    private ResponseEntity<String> handleNoSuchElement(NoSuchElementFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<String> handleValidationException(ValidationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    public static String getBindingErrors(BindingResult bindingResult){
        StringBuilder errorsString = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()){
            if (error instanceof FieldError)
                errorsString.append(((FieldError) error).getField()+" ");
            errorsString.append(error.getDefaultMessage());
            errorsString.append("\n");
        }
        return errorsString.toString();
    }

}
