import java.util.Scanner;
public class leap_year
{
    public static void main(String[] args)
    {
        Scanner input=new Scanner(System.in);
        int year=input.nextInt();
        boolean isleapyear=(year % 4==0 && year % 100!=0 ) || (year%400==0);
        System.out.println(year+" is a leap year: "+isleapyear);


        float f1=-32.3f;
        System.out.println(f1);

        int i=10;
        if(i!=10 && i/0==0)
            System.out.println("ssss");
        else
            System.out.println("fff");

        /*
        // A leap year is divisible by 4
        boolean isLeapYear = (year % 4 == 0);
        // A leap year is divisible by 4 but not by 100
        isLeapYear = isLeapYear && (year % 100 != 0);
        // A leap year is divisible by 4 but not by 100 or divisible by 400
        isLeapYear = isLeapYear || (year % 400 == 0);
        Or you can combine all these expressions into one like this:
        isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);/
         */
    }
}
