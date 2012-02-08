package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;
import model.util.PerlinNoise;


public class PerlinBWExpression extends OperatorExpression
{

    private PerlinBWExpression (ArrayList<Expression> operands)
    {
        super(operands, "perlinBW");
    }


    /**
     * Creates a Perlin Noise gray scale color given two operands
     */
    @Override
    protected RGBColor evaluate ()
    {

        return PerlinNoise.greyNoise(myOperands.get(0).evaluate(),
                                     myOperands.get(1).evaluate());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((perlinBW)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new PerlinBWExpression(parseOperands(parser));
        }
    }

}
