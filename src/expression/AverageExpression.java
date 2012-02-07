package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;
import model.Parser;


public class AverageExpression extends OperatorExpression
{

    private AverageExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "average";
    }


    /**
     * Averages all operands by adding individual component parts.
     */
    @Override
    protected RGBColor evaluate ()
    {
        double r = 0;
        double g = 0;
        double b = 0;

        if (myOperands.size() == 0) throw new ParserException("Not enough operands",
                                                              ParserException.Type.BAD_SYNTAX);
        
        for (Expression operand : myOperands)
        {
            RGBColor eval = operand.evaluate();
            r += eval.getRed();
            g += eval.getGreen();
            b += eval.getBlue();
        }

        return new RGBColor(r / myOperands.size(),
                            g / myOperands.size(),
                            b / myOperands.size());

    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((average)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new AverageExpression(parseOperands(userInput));
        }
    }

}
