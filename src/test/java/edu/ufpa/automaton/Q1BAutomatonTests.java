package edu.ufpa.automaton;

import edu.ufpa.automaton.model.Delta;
import edu.ufpa.automaton.model.Automaton;
import org.junit.jupiter.api.*;

import java.util.Set;

@DisplayName("Q1.b)")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Q1BAutomatonTests {

    Automaton automaton;

    @BeforeAll
    public void setup() {
        automaton = new Automaton(
                Set.of("q0", "q1", "q2","q3","q4","q5","q6","q7"),
                Set.of("a", "b", "c"),
                Set.of(
                        new Delta("q0","a","q1"),
                        new Delta("q1","a","q2"),
                        new Delta("q2","a","q3"),
                        new Delta("q4","a","q5"),
                        new Delta("q5","a","q6"),
                        new Delta("q6","a","q7"),
                        new Delta("q3","b","q3"),
                        new Delta("q0","b","q4"),
                        new Delta("q4","b","q4"),
                        new Delta("q3","c","q3"),
                        new Delta("q0","c","q4"),
                        new Delta("q4","c","q4")
                        ),
                "q0",
                Set.of("q3", "q7")
        );
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - aaacbcbcbcbcbcbcbc")
    public void accepts1() {
        Assertions.assertTrue(automaton.accepts("aaacbcbcbcbcbcbcbc"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - aaa")
    public void accepts2() {
        Assertions.assertTrue(automaton.accepts("aaa"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - ab")
    public void accepts3() {
        Assertions.assertFalse(automaton.accepts("ab"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - aaabccccaaa")
    public void accepts4() {
        Assertions.assertFalse(automaton.accepts("aaabcccccaaa"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - bcbcbcbcaaa")
    public void accepts5() {
        Assertions.assertTrue(automaton.accepts("bcbcbcbcaaa"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - aaabcbccbaccc")
    public void rejects1() {
        Assertions.assertFalse(automaton.accepts("aaabcbccbaccc"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - b")
    public void rejects2() {
        Assertions.assertFalse(automaton.accepts("b"));
    }
}