package com.example.techcorp;

/** Zdarzenie pozytywne: firma otrzymuje dodatkową gotówkę (np. premia od inwestora). */
public class BonusPaymentEvent implements GameEvent {

    private final double amount;

    public BonusPaymentEvent(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota premii musi być dodatnia.");
        }
        this.amount = amount;
    }

    @Override
    public void apply(Company company) {
        company.addCash(amount);
    }

    @Override
    public String getDescription() {
        return "Premia finansowa: +" + (int) amount + " gotówki";
    }
}
