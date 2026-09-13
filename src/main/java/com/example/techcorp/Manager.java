package com.example.techcorp;

/** Least direct output, but the cheapest role to hire. */
public class Manager extends Employee {

    public Manager(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill() / 3;
    }
}
