import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class testProduct1
{
    public static int setBits(int n)
    {
        int count=0;
        while(n>0)
        {
            if((n&1)>0)
                count++;
            n=n>>1;
        }
        return count;
    }
    //findig the eement hacing the maximum no of 1 in its binary rep in the group ok k
    //returning the element having max set bit in group
    public static void main(String ...a)
    {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int N=in.nextInt();
        int K=in.nextInt();
        int arr[]=new int[N];
        int bitsCount[]=new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = in.nextInt();
            bitsCount[i]=setBits(arr[i]);
        }
        for(int i=0;i<N-K+1;i++)
        {
            int max=0;
            int maxEle=arr[i];
            for(int j=i;j<K+i;j++)
            {
                if(bitsCount[j]>max)
                {
                    max=bitsCount[j];
                    maxEle=arr[j];
                }
                else if(bitsCount[j]==max)  maxEle=arr[j];
            }
            System.out.print(maxEle+" ");
            //System.out.println();
        }
/*

5 2
1 2 3 4 5

 */


    }
}

