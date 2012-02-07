package expression;

import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class NumberExpression extends Expression
{

    private RGBColor myValue;


    private NumberExpression (String myInput)
    {
        myValue = new RGBColor(Double.parseDouble(myInput));
    }


    /**
     * Returns number value as RGBColor
     */
    @Override
    protected RGBColor evaluate ()
    {
        return myValue;
    }


    @Override
    public String toString ()
    {
        return Double.toString(myValue.getRed());
    }

    public static class Factory extends SingleValueExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
            PAREN_REGEX =
                Pattern.compile("(\\(\\-?[0-9]+(\\.[0-9]+)?\\))|(\\(\\.[0-9]+\\))");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new NumberExpression(matchValue(userInput));
        }
    }
}
