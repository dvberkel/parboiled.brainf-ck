package nl.dvberkel.brainfuck.language;

import nl.dvberkel.brainfuck.language.instruction.PlusInstruction;
import nl.dvberkel.brainfuck.machine.BrainfuckMachine;
import org.junit.Test;

import static nl.dvberkel.util.ByteArrayFactory.ofLength;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProgramTest {
    @Test
    public void shouldExecuteOnMachine() {
        Program program = new PlusInstruction();
        BrainfuckMachine machine = new BrainfuckMachine(3);

        program.executeOn(machine);

        assertThat(machine.getCells(), is(ofLength(3).withContent(1)));
    }
}
