package com.example.techcorp;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private final String name;
    private double cash;
    private final List<Employee> employees = new ArrayList<>();
    private final Project project;

    public Company(String name, double cash, Project project) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nazwa firmy nie może być pusta.");
        }
        if (cash < 0) {
            throw new IllegalArgumentException("Gotówka firmy nie może być ujemna.");
        }
        this.name = name;
        this.cash = cash;
        this.project = project;
    }

    public void hire(Employee employee, double cost) throws InsufficientFundsException {
        // sprawdzam czy w ogóle stać firmę zanim cokolwiek się zmieni
        if (cost > cash) {
            throw new InsufficientFundsException(
                    name + " nie może pozwolić sobie na zatrudnienie " + employee.getName()
                            + " (potrzeba " + cost + ", jest " + cash + ").");
        }
        cash -= cost;
        employees.add(employee);
        project.addEmployee(employee);
    }

    public void workOneTurn() {
        project.workOneTurn();
    }

    public void reduceCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota do odjęcia nie może być ujemna.");
        }
        // Math.max(0, ...) żeby gotówka nie zeszła na minus
        cash = Math.max(0, cash - amount);
    }

    public void addCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota do dodania nie może być ujemna.");
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
