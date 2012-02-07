package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;
import model.util.ColorModel;


public class RGBtoLuminanceExpression extends OperatorExpression
{

    private RGBtoLuminanceExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "rgbToYCrCb";
    }


    /**
     * Converts color space from RGB to Luminance and returns RGBColor with
     * these new component values.
     */
    @Override
    protected RGBColor evaluate ()
    {

        return ColorModel.rgb2ycrcb(new RGBColor(myOperands.get(0).evaluate()));
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((rgbToYCrCb)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new RGBtoLuminanceExpression(parseOperands(parser));
        }
    }
}
