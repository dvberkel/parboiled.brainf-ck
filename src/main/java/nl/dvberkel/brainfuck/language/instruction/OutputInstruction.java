package nl.dvberkel.brainfuck.language.instruction;

import nl.dvberkel.brainfuck.machine.BrainfuckMachine;

public class OutputInstruction extends SingleInstruction {
    @Override
    public void executeOn(BrainfuckMachine machine) {
        machine.output();
    }
}
