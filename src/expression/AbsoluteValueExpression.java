package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class AbsoluteValueExpression extends OperatorExpression
{

    private AbsoluteValueExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "abs";
    }


    /**
     * Returns color with each component being absolute-valued
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(Math.abs(myOperands.get(0).evaluate().getRed()),
                            Math.abs(myOperands.get(0).evaluate().getGreen()),
                            Math.abs(myOperands.get(0).evaluate().getBlue()));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((abs)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new AbsoluteValueExpression(parseOperands(parser));
        }
    }

}
