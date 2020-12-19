import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class oracle
{
    public static void main(String ...oo)
    {
        try{
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Loaded !");

            //step2 create  the connection object
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234asdf");
            System.out.println("Connection Established !");
            //step3 create the statement object
            Statement stmt=con.createStatement();
            //step4 execute query
            ResultSet rs=stmt.executeQuery("select * from student");
            System.out.println("NAME                      ID    ROOM_NO");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getInt(2)+"  "+rs.getInt(3));

            //step5 close the connection object

            ResultSet a1=stmt.executeQuery("SELECT max(tid) FROM grocery");
            int tid=0;
            while(a1.next())
                tid=a1.getInt(1)+1;
            con.close();
            int total=2323;
            String ele="12 X 23,";
            String exe="INSERT into grocery(tid,total,items) values("+(tid)+", "+total+", "+"'"+ele+"')";
            System.out.println(exe);
            System.out.println("Tid: "+tid);

        }catch(Exception e){ System.out.println("Error code:"+e);}
    }
}
