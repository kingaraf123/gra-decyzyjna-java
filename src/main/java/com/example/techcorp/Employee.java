package com.example.techcorp;

// Klasa bazowa dla pracownika - reszta klas po niej dziedziczy
public abstract class Employee {

    private final String name;
    private final int skill;
    private final double salary;

    public Employee(String name, int skill, double salary) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Imię pracownika nie może być puste.");
        }
        if (skill <= 0) {
            throw new IllegalArgumentException("Umiejętność pracownika musi być większa od 0.");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Wynagrodzenie nie może być ujemne.");
        }
        this.name = name;
        this.skill = skill;
        this.salary = salary;
    }

    // każda podklasa sama mówi ile pracy robi w turze
    public abstract int work();

    public String getRoleName() {
        return getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public int getSkill() {
        return skill;
    }

    public double getSalary() {
        return salary;
    }
}
