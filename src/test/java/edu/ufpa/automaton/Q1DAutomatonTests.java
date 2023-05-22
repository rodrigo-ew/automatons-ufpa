package edu.ufpa.automaton;

import edu.ufpa.automaton.model.Delta;
import edu.ufpa.automaton.model.Automaton;
import org.junit.jupiter.api.*;

import java.util.Set;

@DisplayName("Q1.d)")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Q1DAutomatonTests {

    Automaton automaton;

    @BeforeAll
    public void setup() {
        automaton = new Automaton(
                Set.of("q0", "q1", "q2","q3","q4"),
                Set.of("a", "b","c"),
                Set.of(
                        new Delta("q0","a","q1"),
                        new Delta("q1","a","q1"),
                        new Delta("q1","c","q4"),
                        new Delta("q2","b","q2"),
                        new Delta("q2","a","q3"),
                        new Delta("q3","c","q4"),
                        new Delta("q1","b","q2"),
                        new Delta("q0","b","q2"),
                        new Delta("q4","c","q4")
                ),
                "q0",
                Set.of("q1", "q3","q4")
        );
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - a")
    public void accepts1() {
        Assertions.assertTrue(automaton.accepts("a"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - acccc")
    public void accepts2() {
        Assertions.assertTrue(automaton.accepts("acccc"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - c")
    public void accepts3() {
        Assertions.assertFalse(automaton.accepts("c"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - abaac")
    public void accepts4() {
        Assertions.assertFalse(automaton.accepts("abaac"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - bba")
    public void accepts5() {
        Assertions.assertTrue(automaton.accepts("bba"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - ccba")
    public void rejects1() {
        Assertions.assertFalse(automaton.accepts("ccba"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - aaabbacccc")
    public void rejects2() {
        Assertions.assertTrue(automaton.accepts("aaabbacccc"));
    }
}