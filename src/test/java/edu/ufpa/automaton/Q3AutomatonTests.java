package edu.ufpa.automaton;

import edu.ufpa.automaton.model.Delta;
import edu.ufpa.automaton.model.MealyMachine;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@DisplayName("Q3")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Q3AutomatonTests {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Q2AutomatonTests.class);
    MealyMachine machine;

    private static final String VINTE_CINCO = "1";
    private static final String CINQUENTA = "2";
    private static final String CEM = "3";

    @BeforeAll
    public void setup() {
        machine = new MealyMachine(
                Set.of("q0","q25","q50","q75"),
                Set.of(VINTE_CINCO,CINQUENTA,CEM),
                Set.of("0","1"),
                Set.of(
                        new Delta("q0",VINTE_CINCO,"q25"),
                        new Delta("q0",CINQUENTA,"q50"),
                        new Delta("q0",CEM,"q0"),
                        new Delta("q25",VINTE_CINCO,"q50"),
                        new Delta("q25",CINQUENTA,"q75"),
                        new Delta("q25",CEM,"q25"),
                        new Delta("q50",VINTE_CINCO,"q75"),
                        new Delta("q50",CINQUENTA,"q0"),
                        new Delta("q50",CEM,"q50"),
                        new Delta("q75",VINTE_CINCO,"q0"),
                        new Delta("q75",CINQUENTA,"q25"),
                        new Delta("q75",CEM,"q75")
                ),
                Set.of(
                        new Delta("q0",VINTE_CINCO,"0"),
                        new Delta("q0",CINQUENTA,"0"),
                        new Delta("q0",CEM,"1"),
                        new Delta("q25",VINTE_CINCO,"0"),
                        new Delta("q25",CINQUENTA,"0"),
                        new Delta("q25",CEM,"1"),
                        new Delta("q50",VINTE_CINCO,"0"),
                        new Delta("q50",CINQUENTA,"1"),
                        new Delta("q50",CEM,"1"),
                        new Delta("q75",VINTE_CINCO,"1"),
                        new Delta("q75",CINQUENTA,"1"),
                        new Delta("q75",CEM,"1")
                ),
                "q0",
                Set.of("q0","q25","q50","q75")
        );
    }

    @Test
    @DisplayName("Aplicando transdução")
    void transduction() {
        String input =  new StringBuilder()
                .append(CINQUENTA)
                .append(VINTE_CINCO)
                .append(CINQUENTA)
                .append(CEM)
                .append(VINTE_CINCO)
                .append(CINQUENTA)
                .append(CEM)
                .toString();

        Assertions.assertEquals("0011011", machine.applyTransduction(input));
    }
}
