import java.util.Scanner;

public class DATE
{
    public static void main(String [] args)
    {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter an integer: ");
        long sec=System.currentTimeMillis();
        long min=sec/60;
        long rem=sec%60;
        System.out.println(sec+ " seconds is "+min + " minutes and "+rem+" seconds");
        System.out.println(System.currentTimeMillis());
    }
}
