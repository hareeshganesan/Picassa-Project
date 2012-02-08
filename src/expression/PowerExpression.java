package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class PowerExpression extends OperatorExpression
{

    private PowerExpression (ArrayList<Expression> operands)
    {
        super(operands, "exp");

    }


    /**
     * Takes left operand to the right operand by exponentiating individual
     * components
     */
    @Override
    protected RGBColor evaluate ()
    {
        Expression left = myOperands.get(0);
        Expression right = myOperands.get(1);

        return new RGBColor(Math.pow(left.evaluate().getRed(), right.evaluate()
                                                                    .getRed()),
                            Math.pow(left.evaluate().getGreen(),
                                     right.evaluate().getGreen()),
                            Math.pow(left.evaluate().getBlue(),
                                     right.evaluate().getBlue()));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((exp|\\^)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new PowerExpression(parseOperands(parser));
        }
    }
}
