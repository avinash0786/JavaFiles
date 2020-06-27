import java.util.Scanner;

/*
 * byte (number, 1 byte)
 * short (number, 2 byte)
 * int (number, 4 byte)
 * long (number, 8 byte)
 * float (float number, 4 byte)
 * double (float number, 8 byte)
 * char (a character, 2 byte)
 * boolean (true/ false, 1 byte)
 * */

public class data_type
{
    public static void main(String[] args)
    {
        short shortvar=10;
        float floatvar=(float)4.5;
        double doublevar=11.565;
        char charvar='A';
        boolean boolvar=true;
        /*System.out.println(shortvar);
        System.out.println(floatvar);
        System.out.println(doublevar);
        System.out.println(charvar);
        System.out.println(boolvar);*/
// taking input from user
        Scanner scan=new Scanner(System.in);
        System.out.println("ENter some number!");
        int myvar=scan.nextInt();
        System.out.println("the entered value is ");
        System.out.print(myvar);

        //--------------------------------------
        Scanner s= new Scanner (System.in);
        int x=s.nextInt();
        System.out.println(x);

        //--------------------------------------
        Scanner str=new Scanner(System.in);
        System.out.println("ENter some text!");
        String string=str.nextLine();
        System.out.println("the entered text ");
        System.out.println(string);



    }
}
