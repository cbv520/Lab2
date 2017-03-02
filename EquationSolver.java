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
                case '+': case '-': case '*': case '/':
                    /******************************************************************************************
                    2.	If the stack is empty or contains a left parenthesis on top, push the incoming operator onto the stack.
                    *******************************************************************************************/
                    if(opStack.isEmpty() || ((Character)opStack.top()).equals(Character.valueOf('(')))
                    {
                        opStack.push(Character.valueOf(charToken));
                    }


                    /******************************************************************************************
                    5.	If the incoming symbol has higher precedence than the top of the stack, push it on the stack.
                    *******************************************************************************************/
                    else if(precedenceOf(((Character)opStack.top()).charValue()) < precedenceOf(charToken))
                    {
                        opStack.push(Character.valueOf(charToken));
                    }


                    /******************************************************************************************
                    6.	If the incoming symbol has equal precedence with the top of the stack, use association.
                        If the association is left to right, pop and print the top of the stack and then push the
                        incoming operator. If the association is right to left, push the incoming operator.
                    *******************************************************************************************/
                    else if(precedenceOf(((Character)opStack.top()).charValue()) == precedenceOf(charToken))
                    {
                        postfixQueue.enqueue(opStack.pop());
                        opStack.push(Character.valueOf(charToken));
                    }

                    /******************************************************************************************
                    7.	If the incoming symbol has lower precedence than the symbol on the top of the stack, pop
                    the stack and print the top operator. Then test the incoming operator against the new top of stack.
                    *******************************************************************************************/
                    else
                    {
                        /*
                        postfixQueue.enqueue(opStack.pop());
                        while(((Character)opStack.top()).charValue() <= precedenceOf(charToken))
                        {
                            if(((Character)opStack.top()).charValue() == precedenceOf(charToken))
                            {
                                postfixQueue.enqueue(opStack.pop());
                            }
                            opStack.push(Character.valueOf(charToken));
                        }
                        */

                                opStack.push(Character.valueOf(charToken));



                    }

                    break;


                /******************************************************************************************
                3.	If the incoming symbol is a left parenthesis, push it on the stack..
                *******************************************************************************************/
                case '(':
                    opStack.push(Character.valueOf(charToken));
                    break;


                /******************************************************************************************
                4.	If the incoming symbol is a right parenthesis, pop the stack and print the operators
                    until you see a left parenthesis. Discard the pair of parentheses.
                *******************************************************************************************/
                case ')':
                    while(!(((Character)opStack.top()).equals(Character.valueOf('('))))
                    {
                            postfixQueue.enqueue(opStack.pop());
                    }
                    opStack.pop();
                    break;


                /******************************************************************************************
                1.	Print operands as they arrive.
                *******************************************************************************************/
                default:
                    postfixQueue.enqueue(Double.parseDouble(thisToken));
            }
        }

        /******************************************************************************************
        8.	At the end of the expression, pop and print all operators on the stack.
            (No parentheses should remain.)
        *******************************************************************************************/
        while(!opStack.isEmpty())
        {
            Character remainingChar = (Character)opStack.pop();
            if(!remainingChar.equals(Character.valueOf('(')))
            {
                postfixQueue.enqueue(remainingChar);
            }
        }
        postfixQueue.print();
        System.out.println("=============================");
        return postfixQueue;
    }

    private double evaluatePostfix(DSAQueue postfixQueue)
    {
        DSAStack numStack = new DSAStack();
        double result = 0;
        Object currentItem = postfixQueue.dequeue();

        while(!postfixQueue.isEmpty() && currentItem != null)
        {
            System.out.println(currentItem);
            if(currentItem instanceof Double)
            {
                numStack.push(currentItem);
            }
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
