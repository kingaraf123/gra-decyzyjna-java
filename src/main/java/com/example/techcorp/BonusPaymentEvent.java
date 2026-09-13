package com.example.techcorp;

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
