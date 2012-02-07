package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class SinExpression extends OperatorExpression
{

    private SinExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "sin";
    }


    /**
     * Takes the sine of each component and returns the combined-component
     * RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(Math.sin((myOperands.get(0).evaluate().getRed())),
                            Math.sin((myOperands.get(0).evaluate().getGreen())),
                            Math.sin((myOperands.get(0).evaluate().getBlue())));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((sin)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new SinExpression(parseOperands(userInput));
        }
    }

}
