package com.example.techcorp;

/** Positive event: the company receives extra cash (e.g. an investor bonus). */
public class BonusPaymentEvent implements GameEvent {

    private final double amount;

    public BonusPaymentEvent(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Bonus amount must be positive.");
        }
        this.amount = amount;
    }

    @Override
    public void apply(Company company) {
        company.addCash(amount);
    }

    @Override
    public String getDescription() {
        return "Bonus payment: +" + (int) amount + " cash";
    }
}
