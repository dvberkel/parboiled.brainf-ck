package nl.dvberkel.demonstration;

import nl.dvberkel.demonstration.grammar.CalculatorParser;
import org.junit.Before;
import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class ParboiledTest {

    private CalculatorParser parser;

    @Before
    public void createParser() {
        parser = Parboiled.createParser(CalculatorParser.class);
    }

    @Test
    public void shouldCorrectlyDetermineResult() {
        ParsingResult<?> result = ReportingParseRunner.run(parser.Expression(), "1+2");

        Object value = result.parseTreeRoot.getValue();

        assertThat(value, is(not(nullValue())));
    }
}

