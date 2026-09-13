package com.example.techcorp;

// manager jest najsłabszy ale najtańszy do zatrudnienia
public class Manager extends Employee {

    public Manager(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill() / 3;
    }
}
