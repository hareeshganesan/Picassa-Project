package expression;

import java.util.ArrayList;
import java.util.regex.Matcher;
import model.ParserException;
import model.Parser;


public abstract class OperatorExpressionFactory extends ExpressionFactory
{
    /**
     * TODO kill command length
     */
    protected int commandLength;


    /**
     * Parses operand components of OperatorExpressions and returns ArrayList of
     * operand expressions
     * 
     * @param parser
     */
    protected ArrayList<Expression> parseOperands (Parser parser)
    {
        updateParsePosition(parser);
        ArrayList<Expression> operands = new ArrayList<Expression>();
        while (parser.currentCharacter() != ')')
        {
            operands.add(parser.parseExpression());
        }
        if (parser.currentCharacter() != ')') throw new ParserException("Unexpected character at " +
                                                                                   parser.currentSubstring(),
                                                                           ParserException.Type.BAD_SYNTAX);
        parser.advancePosition(1);
        return operands;
    }


    protected void updateParsePosition (Parser parser)
    {
        Matcher match = EXP_REGEX.matcher(parser.currentSubstring());
        match.find();
        parser.advancePosition(match.end() - match.start());
        parser.skipWhiteSpace();
    }

}
