// Prime number is a number that is greater than 1 and divided by 1 or itself only.
// In other words, prime numbers can't be divided by other numbers than itself or 1

public class prime_no
{
    static public void main(String [] args)
    {
        int i,m=0,flag=0;
        int n=1;
        m=n/2;
        if(n==0 || n==1)
        {
            System.out.println(n+" is not prime!");

        }
        else
        {
            for(i=2;i<=m;i++)
            {
                if(n%i==0)
                {
                    System.out.println(n+" is not prime");
                    flag=1;
                    break;
                }
            }
            if(flag==0)
            {
                System.out.println(n+" is prime number");
            }
        }


    }
}
