import java.util.*;

public class EquationSolver
{
    public double solve(String equation)
    {
        double result;
        equation = equation.replaceAll("\\s","");
        DSAQueue postfixQueue = parseInfixtoPostfix(equation);
        result = evaluatePostfix(postfixQueue);
        return result;
    }

    private DSAQueue parseInfixtoPostfix(String equation)
    {
        DSAQueue postfixQueue = new DSAQueue();
        String thisToken = "";
        DSAStack opStack = new DSAStack();
        StringTokenizer strTok = new StringTokenizer(equation, "+-*/()", true);

        while(strTok.hasMoreTokens())
        {
            thisToken = strTok.nextToken();
            char charToken = thisToken.charAt(0);
            switch(charToken)
            {
                //if current operator has higher precedence than top of stack then push onto stack
                //else keep popping operators off stack until either empty stack, opening parenthesis reached or operator of higher precedence reached
                //then push current operator onto stack
                case '+': case '-': case '*': case '/':
                   //while(opStack isnt empty &&  top of stack isnt an opening parenthesis && precedence of current token <= top of stack)
                   while(!(opStack.isEmpty()) && !(((Character)opStack.top()).equals(Character.valueOf('('))) && (precedenceOf(charToken) <= precedenceOf(((Character)opStack.top()).charValue())))
                   {
                       postfixQueue.enqueue(opStack.pop());
                   }
                   opStack.push(Character.valueOf(charToken));
                   break;

                //push ( onto stack
                case '(':
                    opStack.push(Character.valueOf(charToken));
                    break;

                //pop off operand from stack until an opening parenthesis is reached
                //discard opening parenthesis
                case ')':
                    while(!opStack.isEmpty() && !(((Character)opStack.top()).equals(Character.valueOf('('))))
                    {
                            postfixQueue.enqueue(opStack.pop());
                    }
                    if(opStack.isEmpty())
                    {
                        throw new IllegalArgumentException("Parentheses mismatch");
                    }
                    opStack.pop();
                    break;

                //enqueue operands immediately
                default:
                    try
                    {
                        postfixQueue.enqueue(Double.parseDouble(thisToken));
                    }
                    catch(NumberFormatException e)
                    {
                        throw new IllegalArgumentException("Invalid operand: literal values only");
                    }
            }
        }

        //pop off any remaining operators
        while(!opStack.isEmpty())
        {
            Character remainingChar = (Character)opStack.pop();
            if(!remainingChar.equals(Character.valueOf('(')))
            {
                postfixQueue.enqueue(remainingChar);
            }
        }
        return postfixQueue;
    }

    private double evaluatePostfix(DSAQueue postfixQueue)
    {
        DSAStack numStack = new DSAStack();
        double result = 0;
        Object currentItem = postfixQueue.dequeue();

        while(currentItem != null)
        {
            //push operands immediately onto stack
            if(currentItem instanceof Double)
            {
                numStack.push(currentItem);
            }
            //pop of top 2 operands and perform appropriate operation
            //first pop = op2, sencond pop = op1
            else if(currentItem instanceof Character)
            {
                double op2 = ((Double)numStack.pop()).doubleValue();
                double op1 = ((Double)numStack.pop()).doubleValue();
                numStack.push(executeOperation(((Character)currentItem).charValue(), op1, op2));
            }
            currentItem = postfixQueue.dequeue();
        }
        result = ((Double)numStack.pop()).doubleValue();
        return result;
    }

    private int precedenceOf(char operator)
    {
        int precedence;

        if(operator == '*' || operator == '/')
            precedence = 2;
        else if(operator == '+' || operator == '-')
            precedence = 1;
        else
            throw new IllegalArgumentException("invalid operator");

        return precedence;
    }

    private double executeOperation(char op, double op1, double op2)
    {
        double result = 0;

        switch(op)
        {
            case '+':
                result = op1 + op2;
                break;
            case '-':
                result = op1 - op2;
                break;
            case '*':
                result = op1 * op2;
                break;
            case '/':
                if(op2 == 0)
                {
                    throw new IllegalArgumentException("division by 0");
                }
                else
                {
                    result = op1 / op2;
                }
                break;
            default:
                throw new IllegalArgumentException("invalid operator");

        }

        return result;
    }
}
