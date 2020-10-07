package com.senla.model.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class NoResultExceptionHandler {

    @ExceptionHandler
    public String handlerNoResultException(NoResultException exception){
        return exception.getMessage();
    }
}
