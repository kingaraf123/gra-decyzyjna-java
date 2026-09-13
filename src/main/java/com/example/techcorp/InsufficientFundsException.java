package com.example.techcorp;

/**
 * Wyjątek sprawdzany (checked), rzucany, gdy firma próbuje zatrudnić kogoś,
 * na kogo jej nie stać. Jest sprawdzany, ponieważ oczekujemy, że wołający
 * (pętla gry) obsłuży tę sytuację jawnie, zamiast pozwolić programowi się wywalić.
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
