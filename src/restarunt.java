import java.util.Scanner;

public class restarunt
{
    static Scanner in=new Scanner(System.in);

    static int [][] items={{49,63,63},{99,133,119}};
    static int total=0;

    public static void menu()
    {
        System.out.println("--------------MENU--------------");
        System.out.println("INDIAN BREAD ");
        System.out.println("01: Tandori Roti - Rs 49 ");
        System.out.println("02: Butter Roti - Rs 63 ");
        System.out.println("03: Plain Naan - Rs 63 ");

        System.out.println("DESSERTS");
        System.out.println("11: Gulab Jamun - Rs 99 ");
        System.out.println("12: Gajar halwa - Rs 133 ");
        System.out.println("13: Rasmalai - Rs 119 ");
        System.out.println("-----------------------------");

        System.out.println("ENTER INDEX TO ADD TO ORDERS");
        boolean run=true;
        System.out.println("to final ENTER index 999: ");
        while (run)
        {   char ex='X';
            int choice= in.nextInt();
            if (choice==999)
            {
                run=false;
                System.out.println("Your order total: "+total);
                break;
            }
            System.out.println("ENTER QUANTITY: ");
            int quantity= in.nextInt();
            order(choice,quantity);
        }
    }

    public static void order(int index,int nos)
    {
        int a=index/10;
        int b=index%10-1;
        //System.out.println("a:"+a);
        //System.out.println("b:"+b);
        total=total + items[a][b]*nos;
        System.out.println("Price breakup : "+items[a][b]+" * "+nos+" = "+items[a][b]*nos);
        System.out.println("Total SUM: "+total);
    }
    public static void main(String a[])
    {
        //calling function
        menu();
    }
}
