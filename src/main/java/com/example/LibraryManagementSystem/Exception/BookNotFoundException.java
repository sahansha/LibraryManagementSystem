package com.example.LibraryManagementSystem.Exception;


public class BookNotFoundException extends RuntimeException {


    public BookNotFoundException(String message) {
        super(message);
    }
}
