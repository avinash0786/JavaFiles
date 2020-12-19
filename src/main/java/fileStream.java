import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileStream
{
    public static void main(String ...fff) throws IOException
    {
        FileOutputStream op=new FileOutputStream("new.txt");
        for(int i=1;i<10;i++)
        {
            op.write(i);
        }
        op.close();

        FileInputStream inp=new FileInputStream("new.txt");
        int value;
        while((value=inp.read())!=-1)
        {
            System.out.println(value);
        }
        inp.close();
    }
}
