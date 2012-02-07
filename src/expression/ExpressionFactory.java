package expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Parser;


public abstract class ExpressionFactory
{
    protected Pattern EXP_REGEX;


    /**
     * Identifies whether the inputted string at the passed in position index is
     * an instance of the associated expression.
     * 
     * @param parser TODO
     * @return
     */
    public boolean isInstance (Parser parser)
    {
        Matcher expMatcher = EXP_REGEX.matcher(parser.currentSubstring());
        if (expMatcher.lookingAt()) return true;
        return false;
    }


    /**
     * Parses the expression specified in the input string starting at the
     * specified position
     * 
     * @param parser TODO
     * @return
     */
    public abstract Expression parseExpression (Parser parser);

}
