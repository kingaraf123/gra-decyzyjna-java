package com.example.techcorp;

/** Negative event: the company loses cash (e.g. a market downturn). */
public class MarketSlowdownEvent implements GameEvent {

    private final double amount;

    public MarketSlowdownEvent(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Slowdown amount must be positive.");
        }
        this.amount = amount;
    }

    @Override
    public void apply(Company company) {
        company.reduceCash(amount);
    }

    @Override
    public String getDescription() {
        return "Market slowdown: -" + (int) amount + " cash";
    }
}
