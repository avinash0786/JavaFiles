import java.util.Scanner;

public class mins
{
    public static void main(String ...ff)
    {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int count=1;
        int val;
        int temp= (int) Math.sqrt(n);
        val=temp*temp;
        for(int i=temp-1;i>0;i--)
        {
            if(val+(i*i)<=n)
            {
                val=val+(i*i);
                count+=1;
            }
        }
        System.out.println(count);
    }
}
