package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;
import model.Parser;


public class MinExpression extends OperatorExpression
{

    private MinExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "min";
    }


    /**
     * Finds the minimum all operands by adding individual component parts.
     */
    @Override
    protected RGBColor evaluate ()
    {
        RGBColor min = new RGBColor(RGBColor.COLOR_MAX);

        if (myOperands.size() == 0) throw new ParserException("Not enough operands",
                                                              ParserException.Type.BAD_SYNTAX);
        
        for (Expression operand : myOperands)
        {
            RGBColor eval = operand.evaluate();

            if (eval.compareToAvg(min) < 0) min = eval;
        }

        return min;

    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((min)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new MinExpression(parseOperands(userInput));
        }
    }

}
