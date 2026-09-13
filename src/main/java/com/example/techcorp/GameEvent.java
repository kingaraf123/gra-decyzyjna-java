package com.example.techcorp;

public interface GameEvent {

    void apply(Company company);

    String getDescription();
}
