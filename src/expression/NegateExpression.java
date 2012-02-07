package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class NegateExpression extends OperatorExpression
{

    private NegateExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "neg";
    }


    /**
     * Inverts color by inverting individual component channels.
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(-myOperands.get(0).evaluate().getRed(),
                            -myOperands.get(0).evaluate().getGreen(),
                            -myOperands.get(0).evaluate().getBlue());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((neg|\\!)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new NegateExpression(parseOperands(userInput));
        }
    }
}
