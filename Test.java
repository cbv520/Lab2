public class Test
{
    public static void main(String args[])
    {
        EquationSolver es = new EquationSolver();
        double x;
        String eq;

        System.out.println("Testing example equations - Should pass");
        System.out.println("=======================================");
        eq = "(10*(14+3))/(5+2-4*3)";
        x = es.solve(eq);
        System.out.println(eq + " = " + x + "\n");

        eq = "4/5+6+3/2*(2+(5/(6+34)))";
        x = es.solve(eq);
        System.out.println(eq + " = " + x + "\n");

        eq = "4.4/(3.2+(4.6-3.5*(4.7+43.9/(3.1*2.42))/55.2))";
        x = es.solve(eq);
        System.out.println(eq + " = " + x + "\n");

        eq = "(3-6)+4/6+(4*(3-10)/5*(4+(3/2+3)))";
        x = es.solve(eq);
        System.out.println(eq + " = " + x + "\n");

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
            System.out.println(eq + ": " + e.getMessage() + "\n");
        }

        try
        {
            eq = "(3-6)+a/6+(4*(3-10)/5*(4+(3/2+3)))";
            x = es.solve(eq);
            System.out.println(eq + " = " + x);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(eq + ": " + e.getMessage() + "\n");
        }

        try
        {
            eq = "3+3)";
            x = es.solve(eq);
            System.out.println(eq + " = " + x);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(eq + ": " + e.getMessage() + "\n");
        }

    }
}
