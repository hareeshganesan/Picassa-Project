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
     * @param userInput
     */
    protected ArrayList<Expression> parseOperands (Parser userInput)
    {
        updateParsePosition(userInput);
        ArrayList<Expression> operands = new ArrayList<Expression>();
        while (userInput.currentCharacter() != ')')
        {
            operands.add(userInput.parseExpression());
        }
        if (userInput.currentCharacter() != ')') throw new ParserException("Unexpected character at " +
                                                                                   userInput.currentSubstring(),
                                                                           ParserException.Type.BAD_SYNTAX);
        userInput.advancePosition(1);
        return operands;
    }


    protected void updateParsePosition (Parser userInput)
    {
        Matcher match = EXP_REGEX.matcher(userInput.currentSubstring());
        match.find();
        userInput.advancePosition(match.end() - match.start());
        userInput.skipWhiteSpace();
    }

}
