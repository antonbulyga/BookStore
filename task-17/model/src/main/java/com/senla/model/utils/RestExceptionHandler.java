package com.senla.model.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public String handlerNoResultException(NoResultException exception){
        return exception.getMessage();
    }
}
