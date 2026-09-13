package com.example.techcorp;

/** Coś, co może losowo przytrafić się firmie w danej turze. */
public interface GameEvent {

    void apply(Company company);

    String getDescription();
}
