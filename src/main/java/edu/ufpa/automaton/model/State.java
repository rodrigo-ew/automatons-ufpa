package edu.ufpa.automaton.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class State {
    private String stateName;

    public String get() {
        return stateName;
    }

    public static State of(String s) {
        return new State(s);
    }

    @Override
    public String toString() {
        return stateName;
    }
}
