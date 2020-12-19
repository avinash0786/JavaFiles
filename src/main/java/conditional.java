public class conditional {
    public static void  main(String [] args)
    {
        int x=10;
        int y=20;
        if (x==10)
        {
            System.out.println("Yes x==10");
        }
        else
        {
            System.out.println("No x!=10 ");
        }
        if ((x>5) && (y<15))            // or || OR operator can be used
        {
            System.out.println("The condition is true");
        }
        else
        {
            System.out.println("The condition is False");
        }

    }
}
/*
* ==   is equal to
* !=   is not equal to
* <    is less than
* >    is greater than
* <=   is less then equal to
* >=   is greater than
*       &&   AND OPERATOR
*       ||   OR OPERATOR
 */