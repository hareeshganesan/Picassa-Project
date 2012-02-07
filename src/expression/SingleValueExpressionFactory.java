package expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Parser;


public abstract class SingleValueExpressionFactory extends ExpressionFactory
{

    protected Pattern PAREN_REGEX;


    @Override
    public boolean isInstance (Parser parser)
    {
        Matcher varMatcher = EXP_REGEX.matcher(parser.currentSubstring());
        Matcher parenMatcher =
            PAREN_REGEX.matcher(parser.currentSubstring());
        if (varMatcher.lookingAt() || parenMatcher.lookingAt()) return true;
        return false;
    }


    /**
     * Parses and returns valued expression (commands with no arguments) at the
     * specified position of the input string. Advances parser appropriately in
     * process.
     * 
     * @param parser TODO
     * @return
     */
    protected String matchValue (Parser parser)
    {
        Matcher varMatcher = EXP_REGEX.matcher(parser.currentSubstring());
        Matcher parenMatcher =
            PAREN_REGEX.matcher(parser.currentSubstring());
        varMatcher.find();
        String match =
            parser.currentSubstring().substring(varMatcher.start(),
                                                   varMatcher.end());
        if (parenMatcher.lookingAt()) parser.advancePosition(match.length() + 2);
        else parser.advancePosition(match.length());

        return match;
    }

}
