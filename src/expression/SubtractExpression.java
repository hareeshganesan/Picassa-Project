package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class SubtractExpression extends OperatorExpression
{

    private SubtractExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "minus";
    }


    /**
     * Subtracts left operand from right operand by subtracting individual
     * component parts.
     */
    @Override
    protected RGBColor evaluate ()
    {
        Expression left = myOperands.get(0);
        Expression right = myOperands.get(1);

        return new RGBColor(left.evaluate().getRed() -
                                    right.evaluate().getRed(),
                            left.evaluate().getGreen() -
                                    right.evaluate().getGreen(),
                            left.evaluate().getBlue() -
                                    right.evaluate().getBlue());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((minus|\\-)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new SubtractExpression(parseOperands(userInput));
        }
    }

}
