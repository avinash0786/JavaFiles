import java.util.Random;

public class random
{
    public static void main(String ...aa)
    {
        Random r1=new Random(2);
        for(int i=0;i<10;i++)
        {
            System.out.println(r1.nextInt(100));
        }
    }
}
