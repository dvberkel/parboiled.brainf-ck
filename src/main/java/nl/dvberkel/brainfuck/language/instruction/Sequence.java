package nl.dvberkel.brainfuck.language.instruction;

import nl.dvberkel.brainfuck.language.Program;

import java.util.Collection;


public class Sequence implements Program {
    private final Collection<Program> instructions;

    public Sequence(Collection<Program> instructions) {
        this.instructions = instructions;
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
