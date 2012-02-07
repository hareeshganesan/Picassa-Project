package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.RGBColor;
import model.Parser;


public class RandomExpression extends Expression
{

    private RGBColor myValue;


    /**
     * Upon instantiation, creates a Random value for expression bounded between
     * 1 and -1
     */
    private RandomExpression (ArrayList<Expression> operands)
    {
        myValue =
            new RGBColor(Math.random() * 2 - 1,
                         Math.random() * 2 - 1,
                         Math.random() * 2 - 1);
    }


    /**
     * Returns value randomly generated at instantiation.
     */
    protected RGBColor evaluate ()
    {
        return myValue;
    }


    public String toString ()
    {
        return "random";
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((random)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new RandomExpression(parseOperands(userInput));
        }
    }

}
