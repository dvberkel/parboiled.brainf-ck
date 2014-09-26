package nl.dvberkel.brainfuck;

import nl.dvberkel.brainfuck.language.Program;
import nl.dvberkel.brainfuck.machine.BrainfuckMachine;
import nl.dvberkel.brainfuck.parser.BrainfuckParser;
import nl.dvberkel.util.AlwaysOne;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static nl.dvberkel.util.ByteArrayFactory.ofLength;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ComponentTest {
    private static final int CELL_LENGTH = 10;
    private BrainfuckParser parser;
    private BrainfuckMachine machine;
    private ByteArrayOutputStream output;

    @Before
    public void createParser() {
        parser = new BrainfuckParser();
    }
    
    @Before
    public void createMachine() {
        output = new ByteArrayOutputStream();
        machine = new BrainfuckMachine(CELL_LENGTH, new AlwaysOne(), output);
    }

    @Test
    public void shouldParseAndExecuteCorrectly() {
        Program p = parser.parse("+>++>+++");
        
        p.executeOn(machine);

        assertThat(machine.getCells(), is(ofLength(10).withContent(1,2,3)));
    }
}
