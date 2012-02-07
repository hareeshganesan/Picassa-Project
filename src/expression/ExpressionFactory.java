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
     * @param userInput TODO
     * @return
     */
    public boolean isInstance (Parser userInput)
    {
        Matcher expMatcher = EXP_REGEX.matcher(userInput.currentSubstring());
        if (expMatcher.lookingAt()) return true;
        return false;
    }


    /**
     * Parses the expression specified in the input string starting at the
     * specified position
     * 
     * @param userInput TODO
     * @return
     */
    public abstract Expression parseExpression (Parser userInput);

}
