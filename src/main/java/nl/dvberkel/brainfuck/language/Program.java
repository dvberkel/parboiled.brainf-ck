package nl.dvberkel.brainfuck.language;

import nl.dvberkel.brainfuck.machine.BrainfuckMachine;

public interface Program {
    void executeOn(BrainfuckMachine machine);
}
