package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;
import model.Parser;


public class TangentExpression extends OperatorExpression
{

    private TangentExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "tan";
    }


    /**
     * Takes the tangent of each component and returns the combined-component
     * RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {

        double r = Math.tan((myOperands.get(0).evaluate().getRed()));
        double g = Math.tan((myOperands.get(0).evaluate().getGreen()));
        double b = Math.tan((myOperands.get(0).evaluate().getBlue()));

        if (r == Double.NaN || g == Double.NaN || b == Double.NaN) throw new ParserException("Divide by zero exception",
                                                                                             ParserException.Type.BAD_SYNTAX);

        return new RGBColor(r, g, b);
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((tan)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new TangentExpression(parseOperands(userInput));
        }
    }

}
