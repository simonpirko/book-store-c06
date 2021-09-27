package com.example.book_store_user_account.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
