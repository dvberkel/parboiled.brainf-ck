package nl.dvberkel.demonstration;

import nl.dvberkel.demonstration.grammar.CalculatorParser;
import org.junit.Before;
import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParboiledTest {

    private CalculatorParser parser;

    @Before
    public void createParser() {
        parser = Parboiled.createParser(CalculatorParser.class);
    }

    @Test
    public void shouldCorrectlyParse() {
        ParsingResult<?> result = ReportingParseRunner.run(parser.Expression(), "1+2");

        String parseTreeOutput = ParseTreeUtils.printNodeTree(result);

        assertThat(parseTreeOutput, is("[Expression] '1+2'\n  [Term] '1'\n    [Factor] '1'\n      [Number] '1'\n        [0..9] '1'\n    [ZeroOrMore]\n  [ZeroOrMore] '+2'\n    [Sequence] '+2'\n      [[+-]] '+'\n      [Term] '2'\n        [Factor] '2'\n          [Number] '2'\n            [0..9] '2'\n        [ZeroOrMore]\n"));
    }
}

