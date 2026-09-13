package com.example.techcorp;

/** Positive event: the project gains extra progress directly (e.g. a lucky breakthrough). */
public class ProductivityBoostEvent implements GameEvent {

    private final int amount;

    public ProductivityBoostEvent(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Boost amount must be positive.");
        }
        this.amount = amount;
    }

    @Override
    public void apply(Company company) {
        company.getProject().addProgress(amount);
    }

    @Override
    public String getDescription() {
        return "Productivity boost: +" + amount + " progress";
    }
}
