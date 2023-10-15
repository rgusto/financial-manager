package com.ricardo.financialmanager.core.validation;


import com.ricardo.financialmanager.domain.exception.EntityInUseException;
import com.ricardo.financialmanager.domain.exception.UserEmailAlreadyExistsException;
import com.ricardo.financialmanager.domain.exception.UserLoginAlreadyExistsException;
import com.ricardo.financialmanager.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    @Autowired
    private MessageSource messageSource;


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormError> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FormError> list = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            FormError error = new FormError(e.getField(), message);
            list.add(error);
        });

        return list;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseError handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityInUseException.class)
    public ResponseError handleEntityInUseException(EntityInUseException exception) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseError handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException exception) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserLoginAlreadyExistsException.class)
    public ResponseError handleUserLoginAlreadyExistsException(UserLoginAlreadyExistsException exception) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseError handleRuntimeException(RuntimeException exception) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

}
