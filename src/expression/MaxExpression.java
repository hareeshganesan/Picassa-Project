package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;
import model.Parser;


public class MaxExpression extends OperatorExpression
{

    private MaxExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "max";
    }


    /**
     * Sums all operands by adding individual component parts.
     */
    @Override
    protected RGBColor evaluate ()
    {
        RGBColor max = new RGBColor(RGBColor.COLOR_MIN);

        if (myOperands.size() == 0) throw new ParserException("Not enough operands",
                                                              ParserException.Type.BAD_SYNTAX);
        
        for (Expression operand : myOperands)
        {
            RGBColor eval = operand.evaluate();
            if (eval.compareToAvg(max) > 0) max = eval;
        }

        return max;

    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((max)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new MaxExpression(parseOperands(userInput));
        }
    }

}
