package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class WrapExpression extends OperatorExpression
{

    private WrapExpression (ArrayList<Expression> operands)
    {
        super(operands, "wrap");
    }


    /**
     * Wraps operand color components between -1 and 1. (i.e. 1.5 -> -0.5)
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
            EXP_REGEX = Pattern.compile("\\((wrap)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new WrapExpression(parseOperands(parser));
        }
    }

}
