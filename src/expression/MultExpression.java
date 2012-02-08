package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class MultExpression extends OperatorExpression
{

    private MultExpression (ArrayList<Expression> operands)
    {
        super(operands, "mul");

    }


    /**
     * Multiplies left operand by right operand by multiplying individual
     * component parts.
     */
    @Override
    protected RGBColor evaluate ()
    {
        Expression left = myOperands.get(0);
        Expression right = myOperands.get(1);

        return new RGBColor(left.evaluate().getRed() *
                                    right.evaluate().getRed(),
                            left.evaluate().getGreen() *
                                    right.evaluate().getGreen(),
                            left.evaluate().getBlue() *
                                    right.evaluate().getBlue());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((mul|\\*)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new MultExpression(parseOperands(parser));
        }
    }

}
