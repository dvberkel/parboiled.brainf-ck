package nl.dvberkel.brainfuck.language.instruction;

import nl.dvberkel.brainfuck.machine.BrainfuckMachine;

public class PlusInstruction extends SingleInstruction {
    @Override
    public void executeOn(BrainfuckMachine machine) {
        machine.plus();
    }
}
