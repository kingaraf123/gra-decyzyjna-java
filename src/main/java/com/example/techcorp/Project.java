package com.example.techcorp;

import java.util.ArrayList;
import java.util.List;

/** A project that a company is racing to complete. */
public class Project {

    private final String name;
    private final int requiredWork;
    private int progress;
    private final List<Employee> team = new ArrayList<>();

    public Project(String name, int requiredWork) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be null or blank.");
        }
        if (requiredWork <= 0) {
            throw new IllegalArgumentException("Required work must be greater than 0.");
        }
        this.name = name;
        this.requiredWork = requiredWork;
        this.progress = 0;
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        team.add(employee);
    }

    /** Every employee on the team contributes work; progress cannot exceed requiredWork. */
    public void workOneTurn() {
        for (Employee employee : team) {
            progress += employee.work();
        }
        if (progress > requiredWork) {
            progress = requiredWork;
        }
    }

    /**
     * Adds progress directly (e.g. from a random event).
     * Precondition: amount >= 0. Postcondition: 0 <= progress <= requiredWork.
     */
    public void addProgress(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Progress amount cannot be negative.");
        }
        progress += amount;
        if (progress > requiredWork) {
            progress = requiredWork;
        }
    }

    public boolean isFinished() {
        return progress >= requiredWork;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public int getRequiredWork() {
        return requiredWork;
    }
}
