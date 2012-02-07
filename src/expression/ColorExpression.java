package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class ColorExpression extends OperatorExpression
{

    private ColorExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "color";
    }


    /**
     * Returns new RGBColor with operands specifying component values
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(myOperands.get(0).evaluate().getRed(),
                            myOperands.get(1).evaluate().getGreen(),
                            myOperands.get(2).evaluate().getBlue());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((color)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new ColorExpression(parseOperands(userInput));
        }
    }
}
