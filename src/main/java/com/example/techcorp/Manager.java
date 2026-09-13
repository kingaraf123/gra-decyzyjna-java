package com.example.techcorp;

/** Najmniejszy bezpośredni wkład pracy, ale najtańszy w zatrudnieniu. */
public class Manager extends Employee {

    public Manager(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill() / 3;
    }
}
