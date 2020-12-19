import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class k1Sol
{
    public static void main(String[]args)
    {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=in.nextInt();
        int result[]=new int [t];
        for(int i=0;i<t;i++)
        {
            int peaks=0;
            int check=in.nextInt();
            int checkpoints[]=new int[check];
            for(int j=0;j<check;j++)
                checkpoints[j]=in.nextInt();
            for(int k=1;k<checkpoints.length-1;k++)
            {
                if(checkpoints[k]>checkpoints[k-1] && checkpoints[k]>checkpoints[k+1])
                    peaks++;
            }
            result[i]=peaks;
        }
        for(int i=0;i<result.length;i++)
            System.out.println("Case #"+(i+1)+": "+result[i]); //Case #4: 0
    }
}
