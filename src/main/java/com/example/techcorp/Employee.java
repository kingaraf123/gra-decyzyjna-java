package com.example.techcorp;

/**
 * Common base class for every worker in the game.
 * Holds the data shared by all roles and defines the work() contract
 * that every subclass must implement in its own way.
 */
public abstract class Employee {

    private final String name;
    private final int skill;
    private final double salary;

    public Employee(String name, int skill, double salary) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be null or blank.");
        }
        if (skill <= 0) {
            throw new IllegalArgumentException("Employee skill must be greater than 0.");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Employee salary cannot be negative.");
        }
        this.name = name;
        this.skill = skill;
        this.salary = salary;
    }

    /** Amount of work points this employee produces in a single turn. */
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
