public class noOfDigit
{
    public static int digits(int n)
    {
        int c=0;
        while(n!=0)
        {
            n/=10;
            ++c;
        }
        return c;                               //BigO(noOfDigits)
    }
    public static int logDigits(int n)
    {
        return (int) Math.floor(Math.log10(n)+1);   // bigO(1)
    }
    public static void main(String ...a)
    {
        System.out.println(digits(1251551));
        System.out.println(logDigits(1251551));
    }
}


