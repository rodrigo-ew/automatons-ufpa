package edu.ufpa.automaton.model;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class MealyMachine extends Automaton {

    private Set<String> upperDelta;
    private Set<Delta> lambda;

    public MealyMachine(Set<String> states, Set<String> alphabet, Set<String> upperDelta, Set<Delta> transitions, Set<Delta> lambda, String initialState, Set<String> finalStates) {
        super();
        log.info("Alfabeto de saída: {}", upperDelta);
        log.info("Funnções de transdução: {}", lambda);
        this.upperDelta = upperDelta;
        this.lambda = lambda;
        this.states = states.stream().map(State::new).collect(Collectors.toSet());
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = State.of(initialState);
        this.finalStates = finalStates.stream().map(State::new).collect(Collectors.toSet());
    }

    public String applyTransduction(String sequence) {
        StringBuilder str = new StringBuilder();
        State actualState = initialState;
        for(Character ch : sequence.toCharArray()) {
            Delta transition = consume(actualState, ch);
            str.append(findLambda(actualState,ch.toString()));
            actualState = State.of(transition.finalState());
        }
        log.info("Cadeia original: {} \n Transdução: {}", sequence, str);
        return str.toString();
    }

    public String findLambda(State actualState, String ch) {
        String str = null;
        Pattern pattern;
        Set<Delta> validLambdas = lambda.stream().filter(l -> l.initialState().equals(actualState.get())).collect(Collectors.toSet());
        for(Delta l : validLambdas) {
            pattern = Pattern.compile(l.symbol());
            if(l.initialState().equals(actualState.get())
            && pattern.matcher(ch).matches())
                str = l.finalState();
        }
        return str;
    }
}
