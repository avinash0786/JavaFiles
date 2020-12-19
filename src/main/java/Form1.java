import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Form1 extends JFrame implements ActionListener
{
    JButton submitBt, cancelBt;
    JTextField nameTf, rollnoTf, stateTf;
    JLabel nameLb, rollnoLb,stateLb;
    Connection con;
    Statement smt;
    Form1()
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234asdf");
            smt=con.createStatement();
            ResultSet rs=smt.executeQuery("select * from student_dev");
            while(rs.next())
            {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        }
        catch(Exception e) {
            System.out.println("SQL Exception raised");
            e.printStackTrace();
        }
        nameLb=new JLabel("Name");
        rollnoLb=new JLabel("Roll NO:");
        stateLb=new JLabel("State");
        nameTf=new JTextField(20);
        rollnoTf=new JTextField(20);
        stateTf=new JTextField(20);
        submitBt=new JButton("submit");
        submitBt.addActionListener(this);
        cancelBt=new JButton("Cancel");
        cancelBt.addActionListener(this);
        setLayout(null);
        //setLayout(new FlowLayout());
        nameLb.setBounds(10,10, 50, 20);
        add(nameLb);
        nameTf.setBounds(70,10,50,20);
        add(nameTf);
        add(submitBt);
        cancelBt.setBounds(120,100,100,20);
        add(cancelBt);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==submitBt)
        {
            String name=nameTf.getText();
            String rollno=rollnoTf.getText();
            String state=stateTf.getText();
            System.out.println("button has been pressed");
            System.out.println("Name :"+name);
            System.out.println("RollNo :"+rollno);
            System.out.println("State  :"+state);
            String query="insert into stu_table(name, rollno, state) values('"+name+"','"+rollno+"','"+state+"');";
            try
            {
                smt.executeUpdate(query);
            }
            catch(Exception e1) {System .out.println(e1);}
        }
        if(e.getSource()==cancelBt)
        {
            System.out.println("cancel button pressed");
            System.exit(0);
        }
    }
    public static void main(String args[])
    {
        System.out.println("test print");
        Form1 f1=new Form1();
        f1.setSize(600,400);
        f1.setVisible(true);
    }
}