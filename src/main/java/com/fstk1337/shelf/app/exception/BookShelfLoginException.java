package com.fstk1337.shelf.app.exception;

public class BookShelfLoginException extends Exception {

    private final String message;

    public BookShelfLoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
