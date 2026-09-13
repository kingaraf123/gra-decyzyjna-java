package com.example.techcorp;

/**
 * Checked exception thrown when a company tries to hire someone
 * it cannot afford. Checked, because the caller (the game loop)
 * is expected to handle this situation explicitly rather than crash.
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
