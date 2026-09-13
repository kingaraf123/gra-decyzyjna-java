package com.example.techcorp;

// rzucam ten wyjątek jak firmy nie stać na zatrudnienie kogoś
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
