package edu.ufpa.automaton;

import edu.ufpa.automaton.model.Automaton;
import edu.ufpa.automaton.model.Delta;
import edu.ufpa.automaton.model.MealyMachine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@DisplayName("Q2")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Q2AutomatonTests {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Q2AutomatonTests.class);
    MealyMachine machine;

    @BeforeAll
    public void setup() {
        new Automaton(
                Set.of("q0", "q1", "q2","q3","q4","q5","q6","q7","q8","q9","q10","q11"),
                Set.of("."),
                Set.of(
                        new Delta("q0"," ","q1"),
                        new Delta("q0","[^\\s]","q0"),
                        new Delta("q1","c","q2"),
                        new Delta("q1","[^c]","q0"),
                        new Delta("q2","o","q3"),
                        new Delta("q2","[^o]","q0"),
                        new Delta("q3","m","q4"),
                        new Delta("q3","[^m]","q0"),
                        new Delta("q4","p","q5"),
                        new Delta("q4","[^p]","q0"),
                        new Delta("q5","u","q6"),
                        new Delta("q5","[^u]","q0"),
                        new Delta("q6","t","q7"),
                        new Delta("q6","[^t]","q0"),
                        new Delta("q7","a","q8"),
                        new Delta("q7","[^a]","q0"),
                        new Delta("q8","d","q9"),
                        new Delta("q8","[^d]","q0"),
                        new Delta("q9","o","q10"),
                        new Delta("q9","[^o]","q0"),
                        new Delta("q10","r","q11"),
                        new Delta("q10","[^r]","q0"),
                        new Delta("q11"," ","q0"),
                        new Delta("q11","[^\\s]","q0")
                ),
                "q0",
                Set.of("q0", "q1", "q2","q3","q4","q5","q6","q7","q8","q9","q10","q11")
        );

        machine = new MealyMachine(
                Set.of("q0", "q1", "q2","q3","q4","q5","q6","q7","q8","q9","q10","q11"),
                Set.of("."),
                Set.of("0","1"),
                Set.of(
                        new Delta("q0"," ","q1"),
                        new Delta("q0","[^\\s]","q0"),
                        new Delta("q1","c","q2"),
                        new Delta("q1","[^c]","q0"),
                        new Delta("q2","o","q3"),
                        new Delta("q2","[^o]","q0"),
                        new Delta("q3","m","q4"),
                        new Delta("q3","[^m]","q0"),
                        new Delta("q4","p","q5"),
                        new Delta("q4","[^p]","q0"),
                        new Delta("q5","u","q6"),
                        new Delta("q5","[^u]","q0"),
                        new Delta("q6","t","q7"),
                        new Delta("q6","[^t]","q0"),
                        new Delta("q7","a","q8"),
                        new Delta("q7","[^a]","q0"),
                        new Delta("q8","d","q9"),
                        new Delta("q8","[^d]","q0"),
                        new Delta("q9","o","q10"),
                        new Delta("q9","[^o]","q0"),
                        new Delta("q10","r","q11"),
                        new Delta("q10","[^r]","q0"),
                        new Delta("q11"," ","q0"),
                        new Delta("q11","[^\\s]","q0")
                ),
                Set.of(
                        new Delta("q0"," ","0"),
                        new Delta("q0","[^\\s]","0"),
                        new Delta("q1","c","0"),
                        new Delta("q1","[^c]","0"),
                        new Delta("q2","o","0"),
                        new Delta("q2","[^o]","0"),
                        new Delta("q3","m","0"),
                        new Delta("q3","[^m]","0"),
                        new Delta("q4","p","0"),
                        new Delta("q4","[^p]","0"),
                        new Delta("q5","u","0"),
                        new Delta("q5","[^u]","0"),
                        new Delta("q6","t","0"),
                        new Delta("q6","[^t]","0"),
                        new Delta("q7","a","0"),
                        new Delta("q7","[^a]","0"),
                        new Delta("q8","d","0"),
                        new Delta("q8","[^d]","0"),
                        new Delta("q9","o","0"),
                        new Delta("q9","[^o]","0"),
                        new Delta("q10","r","0"),
                        new Delta("q10","[^r]","0"),
                        new Delta("q11"," ","1"),
                        new Delta("q11","[^\\s]","0")
                ),
                "q0",
                Set.of("q0", "q1", "q2","q3","q4","q5","q6","q7","q8","q9","q10","q11")
        );
    }

    @Test
    @DisplayName("Aplicando transdução")
    void transduction() {
        String text =
                "O computador é uma máquina capaz de variados tipos de tratamento automático de"
                        +" informações ou processamento de dados. Entende-se por computador um sistema físico que"
                        +"realiza algum tipo de computação. Assumiu-se que os computadores pessoais e laptops são "
                        +"ícones da era da informação. O primeiro computador eletromecânico foi construído por "
                        +"Konrad Zuse (1910–1995). Atualmente, um microcomputador é também chamado "
                        +"computador pessoal ou ainda computador doméstico.";

        char[] str = machine.applyTransduction(text).toCharArray();
        List<String> positions = new ArrayList<>();
        for(int i = 0; i < str.length; i++) {
            if(str[i] == '1')
                positions.add((i - 10)+ "-"+(i-2));
        }

        log.info("A palavra computador foi reconhecida nas seguintes posições: {}", positions);
    }
}
