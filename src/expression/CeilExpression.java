package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class CeilExpression extends OperatorExpression
{

    private CeilExpression (ArrayList<Expression> operands)
    {
        super(operands, "ceil");

    }


    /**
     * Rounds up each component and returns RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(Math.ceil(myOperands.get(0).evaluate().getRed()),
                            Math.ceil(myOperands.get(0).evaluate().getGreen()),
                            Math.ceil(myOperands.get(0).evaluate().getBlue()));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((ceil)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new CeilExpression(parseOperands(parser));
        }
    }

}
