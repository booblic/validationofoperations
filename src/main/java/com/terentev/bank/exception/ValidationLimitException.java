package com.terentev.bank.exception;

public class ValidationLimitException extends Exception {

    public ValidationLimitException() {
    }

    public ValidationLimitException(String message) {
        super(message);
    }

    public ValidationLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
