package expression;

import java.util.ArrayList;


public abstract class OperatorExpression extends Expression
{

    protected ArrayList<Expression> myOperands;
    protected String myCommand;


    @Override
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(" " + myCommand + " ");
        for (Expression myOperand : myOperands)
        {
            result.append(myOperand.toString() + " ");
        }
        result.append(")");
        return result.toString();
    }

}
