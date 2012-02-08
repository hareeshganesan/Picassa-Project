package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class AddExpression extends OperatorExpression
{

    private AddExpression (ArrayList<Expression> operands)
    {
        super(operands, "plus");
    }


    /**
     * Adds left operand to right operand by adding individual component parts.
     */
    @Override
    protected RGBColor evaluate ()
    {
        Expression left = myOperands.get(0);
        Expression right = myOperands.get(1);

        return new RGBColor(left.evaluate().getRed() +
                                    right.evaluate().getRed(),
                            left.evaluate().getGreen() +
                                    right.evaluate().getGreen(),
                            left.evaluate().getBlue() +
                                    right.evaluate().getBlue());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((plus|\\+)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new AddExpression(parseOperands(parser));
        }
    }

}
