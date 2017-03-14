public class TestEquationSolver
{
    public static void main(String args[])
    {
        EquationSolver es = new EquationSolver();
        double x;
        String eq;

        System.out.println("");
        System.out.println("Testing example equations - Should pass");
        System.out.println("=======================================");
        eq = "(10.0*(14.0+3.0))/(5.0+2.0-4.0*3.0)";
        x = es.solve(eq);
        System.out.println(eq + " = " + x);
        System.out.println("Expected answer: " + (10.0*(14.0+3.0))/(5.0+2.0-4.0*3.0) + "\n");

        eq = "4.0/5.0+6.0+3.0/2.0*(2.0+(5.0/(6.0+34.0)))";
        x = es.solve(eq);
        System.out.println(eq + " = " + x);
        System.out.println("Expected answer: " + (4.0/5.0+6.0+3.0/2.0*(2.0+(5.0/(6.0+34.0)))) + "\n");

        eq = "4.4/(3.2+(4.6-3.5*(4.7+43.9/(3.1*2.42))/55.2))";
        x = es.solve(eq);
        System.out.println(eq + " = " + x);
        System.out.println("Expected answer: " + (4.4/(3.2+(4.6-3.5*(4.7+43.9/(3.1*2.42))/55.2))) + "\n");

        eq = "(3.0-6.0)+4.0/6.0+(4.0*(3.0-10.0)/5.0*(4.0+(3.0/2.0+3.0)))";
        x = es.solve(eq);
        System.out.println(eq + " = " + x);
        System.out.println("Expected answer: " + ((3.0-6.0)+4.0/6.0+(4.0*(3.0-10.0)/5.0*(4.0+(3.0/2.0+3.0)))) + "\n");

        System.out.println("Testing bad input");
        System.out.println("=======================================");
        try
        {
            eq = "(3-6)/(3+6-9)";
            x = es.solve(eq);
            System.out.println(eq + " = " + x);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(eq + ": " + e.getMessage());
        }
        System.out.println("Expected: division by 0" + "\n");

        try
        {
            eq = "(3-6)+a/6+(4*(3-10)/5*(4+(3/2+3)))";
            x = es.solve(eq);
            System.out.println(eq + " = " + x);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(eq + ": " + e.getMessage());
        }
        System.out.println("Expected: invalid symbol" + "\n");

        try
        {
            eq = "3+3)";
            x = es.solve(eq);
            System.out.println(eq + " = " + x);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(eq + ": " + e.getMessage());
        }
        System.out.println("Expected: parentheses mismatch" + "\n");

    }
}
