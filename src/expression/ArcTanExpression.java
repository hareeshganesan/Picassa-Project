package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class ArcTanExpression extends OperatorExpression
{

    private ArcTanExpression (ArrayList<Expression> operands)
    {
        super(operands, "atan");

    }


    /**
     * Takes arctangent of each component, returns RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(Math.atan((myOperands.get(0).evaluate().getRed())),
                            Math.atan((myOperands.get(0).evaluate().getGreen())),
                            Math.atan((myOperands.get(0).evaluate().getBlue())));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((atan)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new ArcTanExpression(parseOperands(parser));
        }
    }

}
