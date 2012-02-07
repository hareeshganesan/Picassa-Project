package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class ClampExpression extends OperatorExpression
{

    private ClampExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "clamp";
    }


    /**
     * Automatically clamps max and min values to 1 and -1. Components with
     * extreme values are clipped at -1 and 1.
     */
    @Override
    protected RGBColor evaluate ()
    {

        RGBColor eval = myOperands.get(0).evaluate();
        eval.clamp();
        return eval;
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((clamp)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new ClampExpression(parseOperands(parser));
        }
    }
}
