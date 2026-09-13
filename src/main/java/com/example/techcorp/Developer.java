package com.example.techcorp;

/** Pracownik o pełnej efektywności: w każdej turze wykonuje pracę równą pełnej wartości skill. */
public class Developer extends Employee {

    public Developer(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return getSkill();
    }
}
