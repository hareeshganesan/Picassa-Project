package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;
import model.Parser;


public class ProductExpression extends OperatorExpression
{

    private ProductExpression (ArrayList<Expression> operands)
    {
        myOperands = operands;
        myCommand = "product";
    }


    /**
     * Takes the product of all operands by multiplying successive component
     * parts
     */
    @Override
    protected RGBColor evaluate ()
    {
        double r = 1;
        double g = 1;
        double b = 1;

        if (myOperands.size() == 0) throw new ParserException("Not enough operands",
                                                              ParserException.Type.BAD_SYNTAX);
        for (Expression operand : myOperands)
        {
            RGBColor eval = operand.evaluate();
            r *= eval.getRed();
            g *= eval.getGreen();
            b *= eval.getBlue();
        }

        return new RGBColor(r, g, b);
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((product)");
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            return new ProductExpression(parseOperands(userInput));
        }
    }

}
