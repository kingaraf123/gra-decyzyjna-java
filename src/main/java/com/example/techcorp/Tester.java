package com.example.techcorp;

/** Tańszy pracownik: w każdej turze wykonuje połowę wartości skill. */
public class Tester extends Employee {

    public Tester(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill() / 2;
    }
}
