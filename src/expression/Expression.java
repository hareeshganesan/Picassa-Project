package expression;

import java.util.regex.Pattern;
import model.RGBColor;


public abstract class Expression
{

    protected Pattern EXP_REGEX;


    /**
     * Evaluates expression using component operands if necessary. Capable of
     * operating on coordinate variables using input parameters.
     */
    protected abstract RGBColor evaluate ();


    /**
     * Method sets x and y coordinates as variables and continues to evaluate
     * the expression originally used.
     */
    public RGBColor evaluateWithVariables (double x, double y, double t)
    {
        VariableExpression.Factory.getInstance().setCoordinates(x, y, t);
        return evaluate();
    }

}
