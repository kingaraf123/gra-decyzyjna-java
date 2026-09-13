package com.example.techcorp;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private final String name;
    private final int requiredWork;
    private int progress;
    private final List<Employee> team = new ArrayList<>();

    public Project(String name, int requiredWork) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nazwa projektu nie może być pusta.");
        }
        if (requiredWork <= 0) {
            throw new IllegalArgumentException("Wymagana ilość pracy musi być większa od 0.");
        }
        this.name = name;
        this.requiredWork = requiredWork;
        this.progress = 0;
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Pracownik nie może być null.");
        }
        team.add(employee);
    }

    public void workOneTurn() {
        // każdy z zespołu robi swoją część, sumujemy do progress
        for (Employee employee : team) {
            progress += employee.work();
        }

        // pilnuję żeby nie przeskoczyć ponad wymaganą pracę
        if (progress > requiredWork) {
            progress = requiredWork;
        }
    }

    // to jest do zdarzeń losowych - dodaje progres bez liczenia pracy zespołu
    public void addProgress(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Wartość postępu nie może być ujemna.");
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
