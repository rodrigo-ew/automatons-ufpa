package edu.ufpa.automaton;

import edu.ufpa.automaton.model.Delta;
import edu.ufpa.automaton.model.Automaton;
import org.junit.jupiter.api.*;

import java.util.Set;

@DisplayName("Q1.a)")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Q1AAutomatonTests {

    Automaton automaton;

    @BeforeAll
    public void setup() {
        automaton = new Automaton(
                Set.of("q0", "q1", "q2"),
                Set.of("a", "b", "c"),
                Set.of(
                        new Delta("q0", "a", "q1"),
                        new Delta("q1", "a", "q1"),
                        new Delta("q1", "b", "q1"),
                        new Delta("q1", "c", "q2"),
                        new Delta("q2", "a", "q1"),
                        new Delta("q2", "c", "q2")
                ),
                "q0",
                Set.of("q0", "q1", "q2")
        );
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - aaca")
    public void accepts1() {
        Assertions.assertTrue(automaton.accepts("aaca"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - aa")
    public void accepts2() {
        Assertions.assertTrue(automaton.accepts("aa"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - ab")
    public void accepts3() {
        Assertions.assertTrue(automaton.accepts("ab"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - aac")
    public void accepts4() {
        Assertions.assertTrue(automaton.accepts("aac"));
    }

    @Test
    @DisplayName("Deve aceitar a cadeia - abca")
    public void accepts5() {
        Assertions.assertTrue(automaton.accepts("abca"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - aaabacabacb")
    public void rejects1() {
        Assertions.assertFalse(automaton.accepts("aaabacabacb"));
    }

    @Test
    @DisplayName("Deve rejeitar a cadeia - b")
    public void rejects2() {
        Assertions.assertFalse(automaton.accepts("b"));
    }
}