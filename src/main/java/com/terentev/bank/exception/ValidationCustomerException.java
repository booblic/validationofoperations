package com.terentev.bank.exception;

public class ValidationCustomerException extends Exception {

    public ValidationCustomerException() {
    }

    public ValidationCustomerException(String message) {
        super(message);
    }

    public ValidationCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
