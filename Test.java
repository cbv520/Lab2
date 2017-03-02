public class Test
{
    public static void main(String args[])
    {
        EquationSolver es = new EquationSolver();
       // double x = es.solve("(10*(14+3))/(5+2-4*3)");
        //x = es.solve("4/5+6+3/2*(2+(5/(6+34)))");
        //double x = es.solve("4.4/(3.2+(4.6-3.5*(4.7+43.9/(3.1*2.42))/55.2))");
        double x = es.solve("(3-6)+4/6+(4*(3-10)/5*(4+(3/2+3)))");
        //double x = es.solve("(3-6)/(3+6-9)");
        System.out.println(x);
    }
}
