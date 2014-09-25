package nl.dvberkel.brainfuck.parser;

import nl.dvberkel.brainfuck.language.*;
import nl.dvberkel.brainfuck.language.instruction.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static nl.dvberkel.brainfuck.parser.BrainfuckParserTestCase.verifyThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BrainfuckParserTest {
    private final String input;
    private final Program expectedProgram;

    public BrainfuckParserTest(BrainfuckParserTestCase testCase) {
        this.input = testCase.getInput();
        this.expectedProgram = testCase.getProgram();

    }

    @Test
    public void shouldParseBrainfuckPrograms() {
        BrainfuckParser parser = new BrainfuckParser();

        Program program = parser.parse(input);

        assertThat(program, is(not(nullValue())));
        assertThat(program, is(equalTo(expectedProgram)));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(verifyThat("").parsesAsASequenceOf(new DoNothingInstruction()));
        data.add(verifyThat("+").parsesAsASequenceOf(new PlusInstruction()));
        data.add(verifyThat("-").parsesAsASequenceOf(new MinusInstruction()));
        data.add(verifyThat(">").parsesAsASequenceOf(new IncrementInstruction()));
        data.add(verifyThat("<").parsesAsASequenceOf(new DecrementInstruction()));
        data.add(verifyThat(".").parsesAsASequenceOf(new OutputInstruction()));
        data.add(verifyThat(",").parsesAsASequenceOf(new InputInstruction()));
        return data;
    }
}

class BrainfuckParserTestCase {
    public static BrainfuckParserTestCase verifyThat(String input) {
        return new BrainfuckParserTestCase(input);
    }

    private final String input;
    private Program program;

    private BrainfuckParserTestCase(String input) {
        this.input = input;
    }

    public Object[] parsesAsASequenceOf(Program... program) {
        this.program = new Sequence(Arrays.asList(program));
        return new Object[] {this};
    }

    public String getInput() {
        return input;
    }

    public Program getProgram() {
        return program;
    }
}
