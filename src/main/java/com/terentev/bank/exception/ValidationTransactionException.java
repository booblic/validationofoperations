package com.terentev.bank.exception;

public class ValidationTransactionException extends Exception {

    public ValidationTransactionException() {
    }

    public ValidationTransactionException(String message) {
        super(message);
    }

    public ValidationTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
