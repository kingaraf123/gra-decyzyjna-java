package com.example.techcorp;

// dobry event, dodaje progres od razu bez zatrudniania nikogo
public class ProductivityBoostEvent implements GameEvent {

    private final int amount;

    public ProductivityBoostEvent(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Wartość wzrostu musi być dodatnia.");
        }
        this.amount = amount;
    }

    @Override
    public void apply(Company company) {
        company.getProject().addProgress(amount);
    }

    @Override
    public String getDescription() {
        return "Wzrost produktywności: +" + amount + " postępu";
    }
}
