package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class ModExpression extends OperatorExpression
{

    private ModExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "mod";
    }


    /**
     * Finds remainder components for an RGBColor object from dividing left
     * components by right components
     */
    @Override
    protected RGBColor evaluate ()
    {
        Expression left = myOperands.get(0);
        Expression right = myOperands.get(1);

        return new RGBColor(left.evaluate().getRed() %
                                    right.evaluate().getRed(),
                            left.evaluate().getGreen() %
                                    right.evaluate().getGreen(),
                            left.evaluate().getBlue() %
                                    right.evaluate().getBlue());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((mod|\\%)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new ModExpression(parseOperands(parser));
        }
    }
}
