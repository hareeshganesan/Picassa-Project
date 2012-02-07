package model;

import java.util.ArrayList;
import expression.AbsoluteValueExpression;
import expression.AddExpression;
import expression.ArcTanExpression;
import expression.AverageExpression;
import expression.CeilExpression;
import expression.ClampExpression;
import expression.ColorExpression;
import expression.ConditionalExpression;
import expression.CosExpression;
import expression.DivisionExpression;
import expression.Expression;
import expression.ExpressionFactory;
import expression.FloorExpression;
import expression.LetExpression;
import expression.LogExpression;
import expression.LuminancetoRGBExpression;
import expression.MaxExpression;
import expression.MinExpression;
import expression.ModExpression;
import expression.MultExpression;
import expression.NegateExpression;
import expression.NumberExpression;
import expression.PerlinBWExpression;
import expression.PerlinColorExpression;
import expression.PowerExpression;
import expression.ProductExpression;
import expression.RGBtoLuminanceExpression;
import expression.RandomExpression;
import expression.SinExpression;
import expression.SubtractExpression;
import expression.SumExpression;
import expression.TangentExpression;
import expression.VariableExpression;
import expression.WrapExpression;


public class Parser
{
    private String myInput;
    private int myCurrentPosition;
    private ArrayList<ExpressionFactory> possibleExpressions =
        new ArrayList<ExpressionFactory>();


    public Parser (String input)
    {
        myInput = input;
        myCurrentPosition = 0;
    }

    /**
     * Allows client to further position of the Parser along input string by
     * incrementing myCurrentPosition
     */
    public void advancePosition (int increment)
    {
        myCurrentPosition += increment;
    }


    public void skipWhiteSpace ()
    {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }


    public String currentSubstring ()
    {
        return myInput.substring(myCurrentPosition);
    }


    public char currentCharacter ()
    {
        int pos = myCurrentPosition;
        if (myInput.length() <= pos) throw new ParserException("Malformed end of expression",
                                                               ParserException.Type.BAD_SYNTAX);
        return myInput.charAt(myCurrentPosition);
    }


    public boolean notAtEndOfString ()
    {
        return myCurrentPosition < myInput.length();
    }


    /**
     * Converts given string into expression tree.
     * 
     * @return expression tree representing the given formula
     */
    public Expression makeExpression ()
    {
        initializePossibleExpressions(possibleExpressions);
        Expression result = parseExpression();
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                              myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }


    /**
     * Parses the expression in the Parse myInput field by coordinating parsing
     * between multiple Expression factories.
     * 
     * @return expression tree representing the given formula
     */
    public Expression parseExpression ()
    {
        skipWhiteSpace();
        for (ExpressionFactory expFactory : possibleExpressions)
        {
            if (expFactory.isInstance(this))
            {
                Expression parsed = expFactory.parseExpression(this);
                skipWhiteSpace();
                return parsed;
            }
        }
        throw new ParserException("Unexpected expression type " +
                                          myInput.substring(myCurrentPosition),
                                  ParserException.Type.UNKNOWN_COMMAND);
    }


    private ArrayList<ExpressionFactory> initializePossibleExpressions (ArrayList<ExpressionFactory> possibleExpressions)
    {
        possibleExpressions.add(new AddExpression.Factory());
        possibleExpressions.add(new SubtractExpression.Factory());
        possibleExpressions.add(new DivisionExpression.Factory());
        possibleExpressions.add(new MultExpression.Factory());
        possibleExpressions.add(new PowerExpression.Factory());
        possibleExpressions.add(new ModExpression.Factory());
        possibleExpressions.add(new ColorExpression.Factory());
        possibleExpressions.add(new NegateExpression.Factory());
        possibleExpressions.add(new NumberExpression.Factory());
        possibleExpressions.add(new RandomExpression.Factory());
        possibleExpressions.add(new FloorExpression.Factory());
        possibleExpressions.add(new CeilExpression.Factory());
        possibleExpressions.add(new AbsoluteValueExpression.Factory());
        possibleExpressions.add(new FloorExpression.Factory());
        possibleExpressions.add(new CeilExpression.Factory());
        possibleExpressions.add(new ClampExpression.Factory());
        possibleExpressions.add(new WrapExpression.Factory());
        possibleExpressions.add(new SinExpression.Factory());
        possibleExpressions.add(new CosExpression.Factory());
        possibleExpressions.add(new LetExpression.Factory());
        possibleExpressions.add(new TangentExpression.Factory());
        possibleExpressions.add(new ArcTanExpression.Factory());
        possibleExpressions.add(new LogExpression.Factory());
        possibleExpressions.add(new LuminancetoRGBExpression.Factory());
        possibleExpressions.add(new RGBtoLuminanceExpression.Factory());
        possibleExpressions.add(new PerlinColorExpression.Factory());
        possibleExpressions.add(new PerlinBWExpression.Factory());
        possibleExpressions.add(new MinExpression.Factory());
        possibleExpressions.add(new MaxExpression.Factory());
        possibleExpressions.add(new SumExpression.Factory());
        possibleExpressions.add(new AverageExpression.Factory());
        possibleExpressions.add(new ProductExpression.Factory());
        possibleExpressions.add(new ConditionalExpression.Factory());
        possibleExpressions.add(VariableExpression.Factory.getInstance());
        return possibleExpressions;
    }
}
