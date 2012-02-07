package expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Parser;


public abstract class SingleValueExpressionFactory extends ExpressionFactory
{

    protected Pattern PAREN_REGEX;


    @Override
    public boolean isInstance (Parser userInput)
    {
        Matcher varMatcher = EXP_REGEX.matcher(userInput.currentSubstring());
        Matcher parenMatcher =
            PAREN_REGEX.matcher(userInput.currentSubstring());
        if (varMatcher.lookingAt() || parenMatcher.lookingAt()) return true;
        return false;
    }


    /**
     * Parses and returns valued expression (commands with no arguments) at the
     * specified position of the input string. Advances parser appropriately in
     * process.
     * 
     * @param userInput TODO
     * @return
     */
    protected String matchValue (Parser userInput)
    {
        Matcher varMatcher = EXP_REGEX.matcher(userInput.currentSubstring());
        Matcher parenMatcher =
            PAREN_REGEX.matcher(userInput.currentSubstring());
        varMatcher.find();
        String match =
            userInput.currentSubstring().substring(varMatcher.start(),
                                                   varMatcher.end());
        if (parenMatcher.lookingAt()) userInput.advancePosition(match.length() + 2);
        else userInput.advancePosition(match.length());

        return match;
    }

}
