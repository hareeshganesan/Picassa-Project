package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class FloorExpression extends OperatorExpression
{

    private FloorExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "floor";
    }


    /**
     * Rounds down value of each component to nearest integer and returns
     * combined RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {

        return new RGBColor(Math.floor(myOperands.get(0).evaluate().getRed()),
                            Math.floor(myOperands.get(0).evaluate().getGreen()),
                            Math.floor(myOperands.get(0).evaluate().getBlue()));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((floor)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new FloorExpression(parseOperands(parser));
        }
    }

}
