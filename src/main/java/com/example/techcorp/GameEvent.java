package com.example.techcorp;

/** Something that can randomly happen to a company during a turn. */
public interface GameEvent {

    void apply(Company company);

    String getDescription();
}
