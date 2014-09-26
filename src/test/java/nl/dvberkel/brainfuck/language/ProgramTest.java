package nl.dvberkel.brainfuck.language;

import nl.dvberkel.brainfuck.language.instruction.*;
import nl.dvberkel.brainfuck.machine.BrainfuckMachine;
import nl.dvberkel.util.AlwaysOne;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static nl.dvberkel.brainfuck.language.ProgramTestCase.verifyThat;
import static nl.dvberkel.util.ByteArrayFactory.ofLength;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ProgramTest {
    private final Program program;
    private final byte[] result;
    private ByteArrayOutputStream output;
    private BrainfuckMachine machine;

    public ProgramTest(ProgramTestCase testCase) {
        this.program = testCase.getProgram();
        this.result = testCase.getResult();
    }

    @Before
    public void createBrainfuckMachine() {
        output = new ByteArrayOutputStream();
        machine = new BrainfuckMachine(3, new AlwaysOne(), output);
    }

    @Test
    public void shouldExecuteOnMachine() {
        program.executeOn(machine);

        assertThat(machine.getCells(), is(result));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(verifyThat(new PlusInstruction()).executionOnMachineResultsInCells(ofLength(3).withContent(1)));
        data.add(verifyThat(new MinusInstruction()).executionOnMachineResultsInCells(ofLength(3).withContent(-1)));
        data.add(verifyThat(new Sequence(new IncrementInstruction(), new PlusInstruction())).executionOnMachineResultsInCells(ofLength(3).withContent(0, 1)));
        data.add(verifyThat(new Sequence(new IncrementInstruction(), new MinusInstruction())).executionOnMachineResultsInCells(ofLength(3).withContent(0, -1)));
        data.add(verifyThat(new Sequence(new DecrementInstruction(), new PlusInstruction())).executionOnMachineResultsInCells(ofLength(3).withContentInReverse(1)));
        data.add(verifyThat(new Sequence(new IncrementInstruction(), new PlusInstruction())).executionOnMachineResultsInCells(ofLength(3).withContent(0, 1)));
        data.add(verifyThat(
                new Sequence(
                        new PlusInstruction(),
                        new BracketInstruction(new Sequence(
                                new PlusInstruction(),
                                new IncrementInstruction()
                        )),
                        new PlusInstruction()
                )).executionOnMachineResultsInCells(ofLength(3).withContent(2, 1)));
        return data;
    }
}

class ProgramTestCase {
    public static ProgramTestCase verifyThat(Program program) {
        return new ProgramTestCase(program);
    }

    private final Program program;
    private byte[] result;

    public ProgramTestCase(Program program) {
        this.program = program;
    }

    public Object[] executionOnMachineResultsInCells(byte[] result) {
        this.result = result;
        return new Object[]{this};
    }

    public Program getProgram() {
        return program;
    }

    public byte[] getResult() {
        return result;
    }

}
