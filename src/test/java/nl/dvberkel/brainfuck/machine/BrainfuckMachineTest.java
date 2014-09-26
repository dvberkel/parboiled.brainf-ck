package nl.dvberkel.brainfuck.machine;

import org.junit.Before;
import org.junit.Test;

import static nl.dvberkel.brainfuck.machine.ByteArrayFactory.ofLength;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BrainfuckMachineTest {
    private static final int CELL_LENGTH = 3;
    private BrainfuckMachine machine;

    @Before
    public void createBrainfuckMachine() {
        machine = new BrainfuckMachine(CELL_LENGTH);
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
}

class ByteArrayFactory {
    public static ByteArrayFactory ofLength(int length) {
        return new ByteArrayFactory(length);
    }

    private final int length;

    public ByteArrayFactory(int cellSize) {
        this.length = cellSize;
    }

    public byte[] withContent(int... content) {
        byte[] result = new byte[length];
        for (int index = 0; index < content.length && index < length; index++) {
            result[index] = (byte) content[index];
        }
        return result;
    }

    public byte[] withContentInReverse(int... content) {
        byte[] result = new byte[length];
        for (int index = 0; index < content.length && index < length; index++) {
            result[length - 1 - index] = (byte) content[index];
        }
        return result;
    }
}
