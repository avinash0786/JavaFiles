import java.util.Scanner;

public class div
{
    public void main(String[] args)
    {
        Scanner input=new Scanner(System.in);

        int pin =2312;
        for(int i=1;i<4;i++)
        {
            System.out.println("enter pin:");
            int y=input.nextInt();
            if(pin==y)
                System.out.println("pin is correct....welcome ");
            else
                System.out.println("password is not correct....no of trials left:"+(3-i));
        }

    }
}
