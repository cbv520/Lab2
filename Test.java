public class Test
{
    public static void main(String args[])
    {
        EquationSolver es = new EquationSolver();
        double x = es.solve("(10*(14+3))/(5+2-4*3)");
        System.out.println(x);
    }
}
