public class exc_hand
{
    public static int quotient(int a,int b)
    {
        if(b==0)
            throw new ArithmeticException("Divisor cannot be 0 i.e b");
        return a/b;
    }
    public static void main(String ...a)
    {
        try
        {
            int aaa=10;
            //int b=aaa/0;
            //System.out.println(b);
            int c=quotient(12,0);
        }
        catch (ArithmeticException e)
        {
            System.out.println("Error code:"+e);
        }
    }
}
