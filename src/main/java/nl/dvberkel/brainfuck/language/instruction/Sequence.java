package nl.dvberkel.brainfuck.language.instruction;

import nl.dvberkel.brainfuck.language.Program;

import java.util.ArrayList;
import java.util.Collection;


public class Sequence implements Program {
    private final Collection<Program> instructions = new ArrayList<Program>();

    public Sequence() { }

    public Sequence(Collection<Program> instructions) {
        this.instructions.addAll(instructions);
    }

    public boolean addInstruction(Program instruction) {
        this.instructions.add(instruction);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sequence sequence = (Sequence) o;

        if (!instructions.equals(sequence.instructions)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return instructions.hashCode();
    }
}
