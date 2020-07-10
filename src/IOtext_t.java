import java.io.*;

public class IOtext_t
{
    public static void main(String ...aa) throws IOException
    {
        //   FILEOUTPUTSTREAM
        FileOutputStream file=new FileOutputStream("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/fileIO.txt");
        for(int i=0;i<10;i++)
            file.write(i);
        file.close();
        FileInputStream inp=new FileInputStream("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/fileIO.txt");
        int val;
        while((val=inp.read())!=-1)
            System.out.println(val+" ");
        inp.close();
        //-----DataInputStream
        DataOutputStream op=new DataOutputStream(new FileOutputStream("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/fileIO.txt"));
        op.writeUTF("Jhon");
        op.writeDouble(85.2);
        op.writeUTF("Susan");
        op.writeDouble(185.56);
        op.writeUTF("kin");
        op.writeDouble(1235.666);
        op.close();
        DataInputStream inn=new DataInputStream(new FileInputStream("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/fileIO.txt"));
        //System.out.println(inn.readUTF()+" "+inn.readDouble());
        //System.out.println(inn.readUTF()+" "+inn.readDouble());
        //System.out.println(inn.readUTF()+" "+inn.readDouble());
        //--------END OF FILE
        try{
            while(true)
            {
                System.out.println(inn.readUTF()+" "+inn.readDouble());
            }
        }
        catch (EOFException e)
        {
            System.out.println("All data read, EOF reached!");
        }
    }
}
/*
--Binary IO does not involve encoding or decoding and thus is more efficient than text IO.
-Computer dose not differentiate between binary and text files.
all files are stored in binary format , and thus all files are essentially binary files.
-Encoding and decoding are automatically performed for text IO.
--The abstract InputStream is the root for reading binary data and the abstract OutputStram
is the root class for writing binary data.
--FileInputStream/ FileInputStream is for reading/writing bytes form/to files.
All the methods in these classes are inherited from InputStream and OutputStream.
--An instance of FileInputStream can be used as an argument to construct a Scanner.
--An instance of FileOutputStream can be used as an argument  to construct a printwriter,

new PrintWriter(new FileOutputStream("data.txt"),true));
--FilterInputStream / FilterInputStream are stream that filter bytes for some purpose.
Using  a filter class enables us to read integer, double, and string instead of bytes
and characters.
---   DataInputStream and DataOutputStream coverts into appropriate primitive type value or string.


*/