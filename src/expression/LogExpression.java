package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class LogExpression extends OperatorExpression
{

    private LogExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "log";
    }


    /**
     * Takes log of each component in operand and returns the combined RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(Math.log((myOperands.get(0).evaluate().getRed())),
                            Math.log((myOperands.get(0).evaluate().getGreen())),
                            Math.log((myOperands.get(0).evaluate().getBlue())));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((log)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new LogExpression(parseOperands(parser));
        }
    }

}
