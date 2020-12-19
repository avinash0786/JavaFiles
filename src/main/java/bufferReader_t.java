import java.io.*;

public class bufferReader_t
{
    public static void main(String ...a) throws IOException
    {
        DataOutputStream out=new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/fileIO.txt")));
        out.writeUTF("apple is red");
        out.writeUTF("This is new line");
        out.writeUTF("The new buffer reader is best");
        out.close();
        DataInputStream inn=new DataInputStream(new BufferedInputStream(new FileInputStream("C:/Users/AVINASH/IdeaProjects/JAVAfILES/src/fileIO.txt")));
        try
        {
            while (true)
                System.out.println(inn.readUTF());
        }
        catch (EOFException e)
        {
            System.out.println("Reached EOF !");
        }
    }
}
/*
--BufferReader can speed up the input and output by reducing the number of disk reads and writes.
--Using BufferedInputStream, the whole block of data on the disk is read into the buffer
in the memory once.
- THe individual data are then delivered to your program from the buffer.

 */