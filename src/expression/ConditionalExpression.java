package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class ConditionalExpression extends OperatorExpression
{

    private ConditionalExpression (ArrayList<Expression> operands)
    {
        super(operands, "if");

    }


    /**
     * Sums all operands by adding individual component parts.
     */
    @Override
    protected RGBColor evaluate ()
    {
        if (myOperands.get(0).evaluate().compareToAvg(new RGBColor(0.0)) > 0) return myOperands.get(1)
                                                                                               .evaluate();
        else return myOperands.get(2).evaluate();
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((if)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new ConditionalExpression(parseOperands(parser));
        }
    }

}
