package com.example.bookstorec06.exception;

public class MyNotFoundException extends RuntimeException {

    public MyNotFoundException() {

    }
    public MyNotFoundException(String message) {
        super(message);
    }
}
