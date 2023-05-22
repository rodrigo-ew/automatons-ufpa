package edu.ufpa.automaton.model;

public record Delta(
        String initialState,
        String symbol,
        String finalState
) {

    @Override
    public String toString() {
        return "(" +initialState +", "+symbol+") -> "+finalState;
    }
}
