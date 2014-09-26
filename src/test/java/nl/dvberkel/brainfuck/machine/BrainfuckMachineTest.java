package nl.dvberkel.brainfuck.machine;

import nl.dvberkel.util.AlwaysOne;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static nl.dvberkel.util.ByteArrayFactory.ofLength;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BrainfuckMachineTest {
    private static final int CELL_LENGTH = 3;
    private BrainfuckMachine machine;
    private ByteArrayOutputStream output;

    @Before
    public void createBrainfuckMachine() {
        output = new ByteArrayOutputStream();
        machine = new BrainfuckMachine(CELL_LENGTH, new AlwaysOne(), output);
    }

    @Test
    public void shouldHaveCells() {
        byte[] cells = machine.getCells();

        assertThat(cells.length, is(CELL_LENGTH));
        assertThat(cells, is(ofLength(CELL_LENGTH).withContent()));
    }

    @Test
    public void shouldDoPlusOperation() {
        machine.plus();

        byte[] cells = machine.getCells();

        assertThat(cells, is(ofLength(CELL_LENGTH).withContent(1)));
    }

    @Test
    public void shouldDoMinusOperation() {
        machine.minus();

        byte[] cells = machine.getCells();

        assertThat(cells, is(ofLength(CELL_LENGTH).withContent(-1)));
    }

    @Test
    public void shouldDoIncrementOperation() {
        machine.increment();
        machine.plus();

        byte[] cells = machine.getCells();

        assertThat(cells, is(ofLength(CELL_LENGTH).withContent(0, 1)));
    }

    @Test
    public void shouldDoDecrementOperation() {
        machine.increment();
        machine.decrement();
        machine.plus();

        byte[] cells = machine.getCells();

        assertThat(cells, is(ofLength(CELL_LENGTH).withContent(1)));
    }

    @Test
    public void decrementShouldRollOver() {
        machine.decrement();
        machine.plus();

        byte[] cells = machine.getCells();

        assertThat(cells, is(ofLength(CELL_LENGTH).withContentInReverse(1)));
    }

    @Test
    public void incrementShouldRollOver() {
        machine.decrement();
        machine.increment();
        machine.plus();

        byte[] cells = machine.getCells();

        assertThat(cells, is(ofLength(CELL_LENGTH).withContent(1)));
    }

    @Test
    public void inputShouldTakeAByte() {
        machine.input();

        byte[] cells = machine.getCells();

        assertThat(cells, is(ofLength(CELL_LENGTH).withContent(1)));
    }

    @Test
    public void outputShouldProvideAByte() {
        machine.plus();
        machine.output();

        byte[] data = output.toByteArray();

        assertThat(data, is(ofLength(1).withContent(1)));
    }

    @Test
    public void cellsShouldReturnACopy() {
        byte[] cells = machine.getCells();

        cells[0] = 0;

        assertThat(machine.getCells(), is(ofLength(CELL_LENGTH).withContent()));
    }
}

