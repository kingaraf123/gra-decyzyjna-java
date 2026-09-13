package com.example.techcorp;

/** Full-effectiveness worker: contributes the whole skill value each turn. */
public class Developer extends Employee {

    public Developer(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill();
    }
}
