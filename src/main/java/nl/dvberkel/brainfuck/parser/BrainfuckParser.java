package nl.dvberkel.brainfuck.parser;

import nl.dvberkel.brainfuck.language.Program;
import nl.dvberkel.brainfuck.language.instruction.*;
import nl.dvberkel.brainfuck.parser.grammar.BrainfuckGrammar;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;

import java.util.HashMap;
import java.util.Map;

public class BrainfuckParser {
    private static final BrainfuckGrammar parser = Parboiled.createParser(BrainfuckGrammar.class);

    public Program parse(String input) {
        ParsingResult<Program> result = new ReportingParseRunner<Program>(parser.Program()).run(input);
        return result.parseTreeRoot.getValue();
    }
}
