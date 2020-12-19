import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class writeFile
{
    public static void main(String...ss) throws IOException
    {
        File f2=new File("new.txt");
        if(f2.exists())
            System.out.println("File ready to use");
        PrintWriter op=new PrintWriter(f2);
        op.print("Avinash");
        op.println(" Kumar");
        op.print(19);
        op.close();
        //////second file//////
        File f3=new File("new2.txt");
        PrintWriter p=new PrintWriter(f3);
        p.write("THis is seconf file content");
        p.println(1526);
        p.close();

    }
}
