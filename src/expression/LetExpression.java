package expression;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;
import model.Parser;


public class LetExpression extends OperatorExpression
{

    private LetExpression (ArrayList<Expression> operands)
    {
        super(operands, "let");

    }


    /**
     * Sets value of first operand (variable) as second expression's value,
     * evaluates third expression given the first operand variable, then returns
     * value of third expression as the value of the let expression. Maintains
     * what the prior value of the variable was to restore variable registry
     * after the destination expression is evaluated.
     */
    @Override
    protected RGBColor evaluate ()
    {
        RGBColor old = null;
        try
        {
            old = myOperands.get(0).evaluate();
        }
        catch (ParserException e)
        {}

        VariableExpression var = (VariableExpression) myOperands.get(0);
        RGBColor value = myOperands.get(1).evaluate();
        var.setValue(value);
        RGBColor output = myOperands.get(2).evaluate();
        var.setValue(old);

        return output;
    }

    public static class Factory extends OperatorExpressionFactory
    {
        public Factory ()
        {
            EXP_REGEX = Pattern.compile("\\((let)");
        }


        @Override
        public Expression parseExpression (Parser parser)
        {
            return new LetExpression(parseOperands(parser));
        }
    }
}
