public class calculator
{
    public static void main(String[] args)
    {
        //fn calling
        System.out.println("THE addition is "+sum(3,9));
        System.out.println("THE substraction is "+sub(9,3));
        System.out.println("THE multiplication is "+multiply(3,9));
        System.out.println("THE division is "+divide(27,9));

    }
    public static int sum(int x, int y)
    {
        return (x+y);
    }
    public static int sub(int x, int y)
    {
        return (x-y);
    }
    public static int multiply(int x, int y)
    {
        return (x*y);
    }
    public static double divide(double x, double y)
    {
        return (x/y);
    }
}
