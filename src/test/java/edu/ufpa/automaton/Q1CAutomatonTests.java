package edu.ufpa.automaton;

import edu.ufpa.automaton.model.Delta;
import edu.ufpa.automaton.model.Automaton;
import org.junit.jupiter.api.*;

import java.util.Set;

@DisplayName("Q1.c)")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Q1CAutomatonTests {

    Automaton automaton;

    @BeforeAll
    public void setup() {
        automaton = new Automaton(
                Set.of("q0", "q1", "q2","q3","q4","q5"),
                Set.of("a", "b"),
                Set.of(
                        new Delta("q0","a","q1"),
                        new Delta("q1","b","q2"),
                        new Delta("q2","b","q2"),
                        new Delta("q1","a","q3"),
                        new Delta("q3","a","q3"),
                        new Delta("q3","b","q4"),
                        new Delta("q0", "b", "q5")
                ),
                "q0",
                Set.of("q1", "q2","q4","q5")
        );
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - aaaaaaaab")
    public void accepts1() {
        Assertions.assertTrue(automaton.accepts("aaaaaaaab"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - b")
    public void accepts2() {
        Assertions.assertTrue(automaton.accepts("b"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - aba")
    public void accepts3() {
        Assertions.assertFalse(automaton.accepts("aba"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - ba")
    public void accepts4() {
        Assertions.assertFalse(automaton.accepts("ba"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - abbbbbbb")
    public void accepts5() {
        Assertions.assertTrue(automaton.accepts("abbbbbbb"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - aaa")
    public void rejects1() {
        Assertions.assertFalse(automaton.accepts("aaa"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - b")
    public void rejects2() {
        Assertions.assertTrue(automaton.accepts("b"));
    }
}