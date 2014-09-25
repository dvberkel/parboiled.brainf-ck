package nl.dvberkel.demonstration.grammar;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

@BuildParseTree
public class CalculatorParser extends BaseParser<Object> {

    public Rule Expression() {
        return Sequence(
                Term(),
                ZeroOrMore(AnyOf("+-"), Term())
        );
    }

    public Rule Term() {
        return Sequence(
                Factor(),
                ZeroOrMore(AnyOf("*/"), Factor())
        );
    }

    public Rule Factor() {
        return FirstOf(
                Number(),
                Sequence('(', Expression(), ')')
        );
    }

    public Rule Number() {
        return OneOrMore(CharRange('0', '9'));
    }
}
