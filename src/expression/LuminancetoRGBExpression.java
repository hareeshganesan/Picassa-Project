package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;
import model.util.ColorModel;


public class LuminancetoRGBExpression extends OperatorExpression
{

    private LuminancetoRGBExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "yCrCbtoRGB";
    }


    /**
     * Converts color space from Luminance to RGB and returns RGBColor with
     * these values.
     */
    @Override
    protected RGBColor evaluate ()
    {

        return ColorModel.ycrcb2rgb(myOperands.get(0).evaluate());
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((yCrCbtoRGB)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new LuminancetoRGBExpression(parseOperands(userInput));
        }
    }

}
