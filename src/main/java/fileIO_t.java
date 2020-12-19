import java.io.IOException;
import java.util.Scanner;

public class fileIO_t
{
    public static void main(String ...ss) throws IOException
    {
        java.io.File file=new java.io.File("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/fileIO.txt");
        if(file.exists())
        {
            System.out.println("File already exists!");
            //System.exit(1);
        }
        java.io.PrintWriter op=new java.io.PrintWriter(file); // creating file if dosen't exixt
        // writing formatted output to file
        op.print("This is the first line!");
        op.println(5654);
        op.print(true);
        op.println("this is the new line println");
        op.print("THis is apple");
        System.out.println("Write success!");
        op.close();

        //Reading from file
        // to read from keyboard
        //Scanner ip=new Scanner(System.in);
        //TO read from a file
        Scanner inp=new Scanner(file);
        while(inp.hasNext())
        {
            System.out.println(inp.nextLine());
        }
        inp.close();
    }
}
/*
**-- Use the Scanner class for reading text data form a file and PrintWriter class for
writing text data to a file.
--A File object encapsulates the properties of a file or a path , but it does not contain,
the method for creating on file and or writing/ reading data to/form a file
(referred to as data input and output, or I/O )




 */