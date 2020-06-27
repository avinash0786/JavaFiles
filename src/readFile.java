import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class readFile
{
    public static void main(String...ff) throws IOException
    {
        File f1=new File("new.txt");
        Scanner inp=new Scanner(f1);  //reading from a file in scanner object
        while (inp.hasNext())
        {
            String firstname=inp.next();
            String lstname=inp.next();
            int age=inp.nextInt();
            System.out.println("Frist name: "+firstname+" Last name: "+lstname+ " age: "+age);
        }
    }
}
