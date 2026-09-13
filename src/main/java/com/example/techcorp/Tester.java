package com.example.techcorp;

/** Cheaper worker: contributes half of the skill value each turn. */
public class Tester extends Employee {

    public Tester(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill() / 2;
    }
}
