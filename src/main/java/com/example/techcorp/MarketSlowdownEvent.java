package com.example.techcorp;

// zły event - zabiera gotówkę
public class MarketSlowdownEvent implements GameEvent {

    private final double amount;

    public MarketSlowdownEvent(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota spowolnienia musi być dodatnia.");
        }
        this.amount = amount;
    }

    @Override
    public void apply(Company company) {
        company.reduceCash(amount);
    }

    @Override
    public String getDescription() {
        return "Spowolnienie rynku: -" + (int) amount + " gotówki";
    }
}
