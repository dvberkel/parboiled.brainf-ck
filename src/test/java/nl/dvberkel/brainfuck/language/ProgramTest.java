package nl.dvberkel.brainfuck.language;

import nl.dvberkel.brainfuck.language.instruction.PlusInstruction;
import nl.dvberkel.brainfuck.machine.BrainfuckMachine;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProgramTest {
    @Test
    public void shouldExecuteOnMachine() {
        Program program = new PlusInstruction();
        BrainfuckMachine machine = new BrainfuckMachine(3);

        program.executeOn(machine);

        byte[] expected = new byte[3];
        expected[0] = 1;
        assertThat(machine.getCells(), is(expected));
    }
}
