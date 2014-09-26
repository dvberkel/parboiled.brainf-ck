package nl.dvberkel.brainfuck.machine;

import java.util.Arrays;

public class BrainfuckMachine {
    private final byte[] cells;
    private int cellPointer;

    public BrainfuckMachine(int numberOfCells) {
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
}
