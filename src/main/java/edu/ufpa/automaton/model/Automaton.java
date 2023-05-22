package edu.ufpa.automaton.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class Automaton {

    Set<State> states;
    Set<String> alphabet;
    Set<Delta> transitions;
    State initialState;
    Set<State> finalStates;

    public Automaton(
            Set<String> states,
            Set<String> alphabet,
            Set<Delta> transitions,
            String initialState,
            Set<String> finalStates
    ){
        this.states = states.stream().map(State::new).collect(Collectors.toSet());
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = State.of(initialState);
        this.finalStates = finalStates.stream().map(State::new).collect(Collectors.toSet());

        try {
            validateFinalStates(finalStates);
            validateInitialState(initialState);
            validateTransitions(transitions);
        } catch (RuntimeException e) {
            log.error("Autômato Inválido! Erro: {}", e.getMessage());
        }
        log.info("Autômato construído com sucesso: {}", getDefinition());
    }

    public Boolean accepts(String sentence) {
        var movements = new ArrayList<Delta>();
        State actualState = initialState;
        try {
            for(Character ch : sentence.toCharArray()) {
                Delta transition = consume(actualState, ch);
                actualState = State.of(transition.finalState());
                movements.add(transition);
            }
        } catch (RuntimeException e) {
            log.error("Cadeia {} não aceita! {}. Transições: {}", sentence, e.getMessage(), parseMovements(movements,sentence,actualState));
            return false;
        }

        var result = containsState(finalStates,this.getLastState(movements).get());

        if(!result) {
            log.info("Cadeia {} não aceita! Transições: {}", sentence,parseMovements(movements,sentence,actualState));
        } else {
            log.info("Cadeia {} aceita! Transições: {}", sentence,parseMovements(movements,sentence,actualState));
        }
        return result;
    }

    public String getDefinition(){
        String def = "({"+this.states+"},"
                    +"{"+this.alphabet+"},"
                    +"{"+this.transitions+"},"
                    +this.initialState+","
                    +"{"+this.finalStates+"})";
        return def.replace("[","").replace("]","");
    }

    /*
    O trecho abaixo pode ser refatorado
    para emitir múltiplas transições
    e paralelizar o processamento de
    autômatos não determinísticos.
     */
    protected Delta consume(State actualState, Character c) {
        Delta transition = null;
        Pattern pattern = null;
        Set<Delta> validTransitions = transitions.stream().filter(t -> t.initialState().equals(actualState.get())).collect(Collectors.toSet());

        for(Delta t : validTransitions) {
            // Usando grafo de transições com regex para efeito de simplificação do conjunto de transições
            pattern = Pattern.compile(t.symbol());
            if (pattern.matcher(c.toString()).matches()) {
                transition = t;
                break;
            }
        }
        if(Objects.isNull(transition))
            throw new RuntimeException("Impasse! Não foi possível consumir o caractere ["+c.toString()+"]");
        return transition;
    }

    private State getLastState(List<Delta> list) {
        return State.of(list.get(list.size() - 1).finalState());
    }

    private void validateFinalStates(Set<String> finalStates) {
        finalStates.forEach(s -> {
            if(!containsState(states,s))
                throw new RuntimeException("estado final "+s+" não pertence ao conjunto de estados!");
        });
    }

    private void validateInitialState(String initialState) {
        if(!containsState(states,initialState))
            throw new RuntimeException("estado inicial "+initialState+" não pertence ao conjunto de estados!");
    }

    private void validateTransitions(Set<Delta> transitions) {
        transitions.forEach(transition -> {
            if (containsState(states,transition.initialState())
                    && containsState(states,transition.finalState())
                    && alphabet.contains(transition.symbol()))
                return;

            throw new RuntimeException("função de transição "+transition.toString()+" inválida!");
        });
    }

    private boolean containsState(Set<State> states, String s){
        var contains = false;
        for(State state : states)
            if(state.get().equals(s))
                contains = true;
        return contains;
    }

    private String parseMovements(ArrayList<Delta> movements, String sentence, State finalState){
        StringBuilder str = new StringBuilder();
        char[] charSequence = sentence.toCharArray();
        for(int i = 0; i < movements.size(); i++) {
            str.append("(")
                .append(movements.get(i).initialState())
                .append(", ")
                .append(charSequence[i])
                .append(")");
            if(!(movements.size() == i + 1))
                str.append(" |- ");
            if(movements.size() == i + 1
            && movements.size() == charSequence.length)
                str.append(" |- (").append(finalState.get()).append(", ε)");
        }
        return str.toString();
    }
}
