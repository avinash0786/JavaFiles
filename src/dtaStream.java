import java.io.*;

public class dtaStream
{
    public static void main(String ...ss) throws IOException
    {
        DataOutputStream oo=new DataOutputStream(new FileOutputStream("new.txt"));
        oo.writeUTF("JOhn");
        oo.writeDouble(45.1351516);
        oo.writeUTF("apple is red");
        oo.close();

        DataInputStream in=new DataInputStream(new FileInputStream("new.txt"));
        System.out.println("Read utf: "+in.readUTF());
        System.out.println("Read Double: "+in.readDouble());
        System.out.println("Read utf: "+in.readUTF());

    }
}
