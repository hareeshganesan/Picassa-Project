package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class CosExpression extends OperatorExpression
{

    private CosExpression (ArrayList<Expression> operands)
    {
        super(operands, "cos");

    }


    /**
     * Takes cosine of each component and returns combined RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(Math.cos((myOperands.get(0).evaluate().getRed())),
                            Math.cos((myOperands.get(0).evaluate().getGreen())),
                            Math.cos((myOperands.get(0).evaluate().getBlue())));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((cos)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new CosExpression(parseOperands(parser));
        }
    }

}
