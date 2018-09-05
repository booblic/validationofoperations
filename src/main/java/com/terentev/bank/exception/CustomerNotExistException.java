package com.terentev.bank.exception;

public class CustomerNotExistException extends Exception {

    public CustomerNotExistException() {
    }

    public CustomerNotExistException(String message) {
        super(message);
    }

    public CustomerNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
