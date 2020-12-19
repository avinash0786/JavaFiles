import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class countdown
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=in.nextInt();
        for(int i=0;i<t;i++)
        {
            int nos=0;
            int n=in.nextInt();         //no of elements
            int arr[]=new int[n];
            int countdown=in.nextInt();     //countdown to find
            for(int q=0;q<n;q++)
                arr[q]=in.nextInt();           //getting array elements
            //------MAIN RUNNER CODE----------------------

            for(int j=0;j<arr.length;j++)
            {   System.out.println("run j- "+j);
                if(arr[j]==countdown)
                {
                    int temp=countdown;
                    System.out.println("found: "+j);
                    int satisfy=0;
                    for(int k=j;k<countdown+j;k++)
                    {
                        System.out.println("k: "+k);
                        if(arr[k]==temp)
                        {   System.out.println("sat: "+satisfy+" k: "+k);
                            satisfy++;
                            temp--;
                        }
                        else continue;
                    }
                    if(satisfy==countdown)
                        {
                            nos++;
                            j=j+countdown-1;
                        }
                }
            }

            System.out.println("Case #"+(i+1)+": "+nos); //Case #4: 0

        }
    }
}
