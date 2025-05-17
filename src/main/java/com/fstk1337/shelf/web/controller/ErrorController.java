package com.fstk1337.shelf.web.controller;

import com.fstk1337.shelf.app.exception.BookShelfLoginException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(Model model) {
        model.addAttribute("errorMessage", "404 Page Not Found");
        return "error/404";
    }

    @ExceptionHandler(BookShelfLoginException.class)
    public String handleBookShelfLoginException(Model model, BookShelfLoginException exception) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error/404";
    }
}
