package expression;

import java.util.HashMap;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;
import model.Parser;


public class VariableExpression extends Expression
{

    private String myVariable;
    private RGBColor myValue;


    /**
     * Initializes VariableExpression with variable input and value
     */
    private VariableExpression (String variable, RGBColor value)
    {
        myVariable = variable;
        myValue = value;
    }


    /**
     * Evaluates VariableExpression by identifying if the variable already
     * exists and returning the value if it exists. If the variable does not
     * exist in the variable registry, an exception for unfound variable is
     * thrown.
     */
    @Override
    protected RGBColor evaluate ()
    {
        if (myValue != null) return myValue;
        else throw new ParserException("unexpected variable: " + myVariable,
                                       ParserException.Type.UNKNOWN_COMMAND);
    }


    public void setValue (RGBColor value)
    {
        myValue = value;
    }


    @Override
    public String toString ()
    {
        return myVariable;
    }

    public static class Factory extends SingleValueExpressionFactory
    {
        HashMap<String, VariableExpression> varRegistry;
        private static Factory instance;


        private Factory ()
        {
            varRegistry = new HashMap<String, VariableExpression>();
            varRegistry.put("x", new VariableExpression("x", null));
            varRegistry.put("y", new VariableExpression("y", null));
            varRegistry.put("t", new VariableExpression("t", null));
            EXP_REGEX = Pattern.compile("([a-zA-Z]+(?=(\\s|\\))))");
            PAREN_REGEX = Pattern.compile("\\(([a-z]+)\\)");
        }


        /**
         * Implements Singleton class interface for VariableExpression factory.
         * Necessary to allow Parser to dynamically input the coordinate values,
         * rather than recompiling for each new call to evaluate.
         * 
         * @return
         */
        public static Factory getInstance ()
        {
            if (instance == null) instance = new Factory();
            return instance;
        }


        /**
         * Allows client to set the x and y coordinates for the expression to be
         * evaluated.
         * 
         * @param x
         * @param y
         * @param t
         */
        public void setCoordinates (double x, double y, double t)
        {
            varRegistry.get("x").setValue(new RGBColor(x));
            varRegistry.get("y").setValue(new RGBColor(y));
            varRegistry.get("t").setValue(new RGBColor(t));
        }


        @Override
        public Expression parseExpression (Parser userInput)
        {
            String var = matchValue(userInput);
            if (varRegistry.containsKey(var)) return varRegistry.get(var);
            else
            {
                VariableExpression newInstance =
                    new VariableExpression(var, null);
                varRegistry.put(var, newInstance);
                return newInstance;
            }
        }
    }

}
