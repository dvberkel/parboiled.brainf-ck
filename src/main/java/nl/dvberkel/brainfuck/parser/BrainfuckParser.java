package nl.dvberkel.brainfuck.parser;

import nl.dvberkel.brainfuck.language.*;
import nl.dvberkel.brainfuck.language.instruction.*;

import java.util.HashMap;
import java.util.Map;

public class BrainfuckParser {
    private static final Map<String, SingleInstruction> instructions = new HashMap<String, SingleInstruction>();

    static {
        instructions.put("+", new PlusInstruction());
        instructions.put("-", new MinusInstruction());
        instructions.put(">", new IncrementInstruction());
        instructions.put("<", new DecrementInstruction());
        instructions.put(".", new OutputInstruction());
        instructions.put(",", new InputInstruction());
    }

    public Program parse(String input) {
        if (instructions.containsKey(input)) {
            return instructions.get(input);
        }
        return new DoNothingInstruction();
    }
}
