package nl.dvberkel.brainfuck.language.instruction;

import nl.dvberkel.brainfuck.machine.BrainfuckMachine;

public class MinusInstruction extends SingleInstruction {
    @Override
    public void executeOn(BrainfuckMachine machine) {
        machine.minus();
    }
}
