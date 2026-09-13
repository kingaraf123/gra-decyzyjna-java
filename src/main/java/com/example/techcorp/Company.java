package com.example.techcorp;

import java.util.ArrayList;
import java.util.List;

/** A competitor in the game: has cash, a team, and one project to finish. */
public class Company {

    private final String name;
    private double cash;
    private final List<Employee> employees = new ArrayList<>();
    private final Project project;

    public Company(String name, double cash, Project project) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be null or blank.");
        }
        if (cash < 0) {
            throw new IllegalArgumentException("Company cash cannot be negative.");
        }
        this.name = name;
        this.cash = cash;
        this.project = project;
    }

    /**
     * Hires an employee if the company can afford it.
     * Precondition: cost <= cash. If violated, an InsufficientFundsException
     * is thrown instead of silently letting cash go negative.
     */
    public void hire(Employee employee, double cost) throws InsufficientFundsException {
        if (cost > cash) {
            throw new InsufficientFundsException(
                    name + " cannot afford " + employee.getName()
                            + " (needs " + cost + ", has " + cash + ").");
        }
        cash -= cost;
        employees.add(employee);
        project.addEmployee(employee);
    }

    public void workOneTurn() {
        project.workOneTurn();
    }

    /** Used by negative random events. Cash is clamped at 0, never negative. */
    public void reduceCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to reduce cannot be negative.");
        }
        cash = Math.max(0, cash - amount);
    }

    /** Used by positive random events. */
    public void addCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative.");
        }
        cash += amount;
    }

    public String getName() {
        return name;
    }

    public double getCash() {
        return cash;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Project getProject() {
        return project;
    }
}
