package nl.dvberkel.brainfuck.machine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class BrainfuckMachine {
    private final InputStream input;
    private final OutputStream output;
    private final byte[] cells;
    private int cellPointer;

    public BrainfuckMachine(int numberOfCells, InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
        this.cells = new byte[numberOfCells];
        this.cellPointer = 0;
    }

    public byte[] getCells() {
        return Arrays.copyOf(cells, cells.length);
    }

    public void plus() {
        cells[cellPointer]++;
    }

    public void minus() {
        cells[cellPointer]--;
    }

    public void increment() {
        cellPointer = (cellPointer < (cells.length - 1)) ? cellPointer + 1 : 0;
    }

    public void decrement() {
        cellPointer = (cellPointer > 0) ? cellPointer - 1 : cells.length - 1;
    }

    public byte value() {
        return cells[cellPointer];
    }

    public void input() {
        try {
            cells[cellPointer] = (byte) input.read();
        } catch (IOException e) {
            /* TODO handle exception gracefully */
        }
    }

    public void output() {
        try {
            output.write(cells[cellPointer]);
        } catch (IOException e) {
            /* TODO handle exception gracefully */
        }
    }
}