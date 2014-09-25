package nl.dvberkel.brainfuck.language.instruction;

import nl.dvberkel.brainfuck.language.Program;

public class SingleInstruction implements Program {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        return this.getClass().equals(o.getClass());
    }
}
