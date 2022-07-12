package com.fstk1337.shelf.web.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class NoHandlerFoundControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle() {
        return "error/404";
    }
}
