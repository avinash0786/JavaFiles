import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class groceryc1
{   static int tid=0;
    static boolean data=false;
    static String element="";
    static Scanner in=new Scanner(System.in);

    static int [][] items={{120,7,60,80,200},{100,20,152},{160,34,29,73}};
    static int total=0;
    static int [][] hist=new int [12][2];
    static int count=0;

    public static void menu()
    {
        System.out.println("--------------MENU--------------");
        System.out.println("DAIRY ");
        System.out.println("01: Cheese - Rs 120 ");
        System.out.println("02: Egg - Rs 7 ");
        System.out.println("03: Milk - Rs 60 ");
        System.out.println("04: Yogurt- Rs 80 ");
        System.out.println("05: Butter- Rs 200 ");
//////////////////////////////////////////////////////
        System.out.println("LAUNDRY ");
        System.out.println("11: Detergent- Rs 100");
        System.out.println("12: Dish Wash- Rs 20 ");
        System.out.println("13: Toilet Cleaner- Rs 152 ");

//////////////////////////////////////////////////////
        System.out.println("PERSONAL CARE ");
        System.out.println("21: Shampoo -Rs 160");
        System.out.println("22: Soap- Rs 34 ");
        System.out.println("23: Hand Soap - Rs 29 ");
        System.out.println("24: Shaving Cream -Rs 73 ");

        System.out.println("-----------------------------");

        System.out.println("ENTER INDEX TO ADD TO ORDERS");
        boolean run=true;
        System.out.println("to final order  ENTER index 999: ");
        System.out.println("Final & View TRANSACTION  ENTER index 998: ");
        while (run)
        {
            int choice= in.nextInt();
            if (choice==999)
            {
                run=false;
                System.out.println("Your order total: "+total);
                break;
            }
            if (choice==998)
            {
                run=false;
                data=true;
                System.out.println("Your order total: "+total);
                break;
            }

            System.out.println("ENTER QUANTITY: ");
            int quantity= in.nextInt();
            order(choice,quantity);
        }
        bill();   ////////////////////bill printing
    }
    ///////////////FUNCTION////////////////
    public static void order(int index,int nos)
    {  history(index,nos);
        int a=index/10;
        int b=index%10-1;
        total=total + items[a][b]*nos;
        System.out.println("Price breakup : "+items[a][b]+" * "+nos+" = "+items[a][b]*nos);
        System.out.println("Total SUM: "+total);
    }

    //FN TO STORE ITEMS
    public static void history(int index,int nos)
    {
        hist[count][0]=index;
        hist[count][1]=nos;
        count++;
    }
    //FN TO PRINT BILL///////////
    public static void bill()
    {
        for(int i=0;i<12;i++)
        {
            if(hist[i][0] !=0)
            {
                System.out.println("Item Index : "+hist[i][0]+" Quantity: " +hist[i][1]);
                //String temp=hist[i][0]+" X "+hist[i][1];
                element=element.concat(hist[i][0]+" X "+hist[i][1] +", ");
            }
        }

    }
    public static void main(String a[])
    {
        //calling function
        menu();
        //DATABASE SAVE DATA
        try{
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //System.out.println("Driver Loaded !");

            //step2 create  the connection object
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234asdf");
            //System.out.println("Connection Established !");
            //step3 create the statement object
            Statement stmt=con.createStatement();
            ResultSet a1=stmt.executeQuery("SELECT max(tid) FROM grocery");
            while(a1.next())
                tid=a1.getInt(1)+1;

            //step4 execute query
            //INSERT into datadata values(tid,total,))
            String exe="INSERT into grocery(tid,total,ITMEM_NOS) values("+(tid)+", "+total+", "+"'"+element+"')";
            ResultSet rs=stmt.executeQuery(exe);
            System.out.println("Data successfully SAVED !");
            System.out.println("----------------------------------");

            ResultSet a2=stmt.executeQuery("select * from grocery");
            while(a2.next() && data)
                System.out.println("TID: "+a2.getInt(1)+"   Rs: "+a2.getInt(2)+"  Item: "+a2.getString(3));
            con.close();
        }catch(Exception e){ System.out.println("Error code:"+e);}
    }
}
