package nl.dvberkel.brainfuck.language.instruction;

import nl.dvberkel.brainfuck.language.Program;
import nl.dvberkel.brainfuck.machine.BrainfuckMachine;

public class BracketInstruction implements Program {
    private final Program program;

    public BracketInstruction(Program program) {
        this.program = program;
    }

    @Override
    public void executeOn(BrainfuckMachine machine) {
        /* TODO provide implementation */
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BracketInstruction that = (BracketInstruction) o;

        if (!program.equals(that.program)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return program.hashCode();
    }
}
