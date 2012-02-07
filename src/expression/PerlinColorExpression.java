package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;
import model.util.PerlinNoise;


public class PerlinColorExpression extends OperatorExpression
{

    private PerlinColorExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "perlinColor";
    }


    /**
     * Creates a Perlin Noise gray scale color given two operands
     */
    @Override
    protected RGBColor evaluate ()
    {

        return PerlinNoise.colorNoise(myOperands.get(0).evaluate(),
                                      myOperands.get(1).evaluate());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((perlinColor)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new PerlinColorExpression(parseOperands(userInput));
        }
    }

}
