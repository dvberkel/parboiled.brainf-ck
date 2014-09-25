package nl.dvberkel.brainfuck.parser.grammar;

import nl.dvberkel.brainfuck.language.Program;
import nl.dvberkel.brainfuck.language.instruction.*;
import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.support.Var;

@BuildParseTree
public class BrainfuckGrammar extends BaseParser<Program> {
    public Rule Program() {
        Var<Sequence> s = new Var<Sequence>(new Sequence());
        return Sequence(
                ZeroOrMore(
                        Instructions(),
                        s.get().addInstruction(pop())
                ),
                push(s.get())
        );
    }

    public Rule Instructions() {
        return FirstOf(
                Plus(),
                Minus(),
                Increment(),
                Decrement(),
                Input(),
                Output()
        );
    }

    public Rule Plus() {
        return Sequence("+", push(new PlusInstruction()));
    }

    public Rule Minus() {
        return Sequence("-", push(new MinusInstruction()));
    }

    public Rule Increment() {
        return Sequence(">", push(new IncrementInstruction()));
    }

    public Rule Decrement() {
        return Sequence("<", push(new DecrementInstruction()));
    }

    public Rule Input() {
        return Sequence(",", push(new InputInstruction()));
    }

    public Rule Output() {
        return Sequence(".", push(new OutputInstruction()));
    }

}
