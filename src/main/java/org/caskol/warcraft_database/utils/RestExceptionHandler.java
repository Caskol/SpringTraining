package org.caskol.warcraft_database.utils;

import jakarta.validation.ValidationException;
import org.caskol.warcraft_database.api.v1.exceptions.NoSuchElementFoundException;
import org.hibernate.TransientObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String VALIDATION_EXCEPTION_MSG = "Поля объекта были неверно заполнены. Дополнительная информация: ";
    public static final String REFERENCE_ID_NOT_FOUND = "Чтобы изменить связь с этим объектом необходимо указывать верный ID этого объекта";
    @ExceptionHandler(NoSuchElementFoundException.class)
    private ResponseEntity<String> handleNoSuchElement(NoSuchElementFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<String> handleValidationException(ValidationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TransientObjectException.class)
    private ResponseEntity<String> handleTransientException(TransientObjectException e){
        return new ResponseEntity<>("Нельзя создавать объекты будучи внутри другого объекта.", HttpStatus.BAD_REQUEST);
    }
    public static String getBindingErrorString(BindingResult bindingResult){
        StringBuilder errorsString = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()){
            if (error instanceof FieldError)
                errorsString.append(((FieldError) error).getField()+" ");
            errorsString.append(error.getDefaultMessage()+"\n");
        }
        return errorsString.toString();
    }
    public static String getValidationErrorString(Errors errors){
        StringBuilder errorString = new StringBuilder();
        for (FieldError fieldError: errors.getFieldErrors()){
            errorString.append(fieldError.getField()+" " + fieldError.getDefaultMessage() + "\n");
        }
        return errorString.toString();
    }
}
