import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class cjQ3
{
    public static void main(String ...ff) {
        Scanner inp = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = inp.nextInt();  //no of testCase
        System.out.println();
        for (int i = 0; i < t; i++) {
            boolean overlapping=false;
            int N = inp.nextInt();// no of events
            assert N>=2;
            char[] alloc=new char[N];
            int[][][] arr = new int[t][N][2];   //array to store timings of events   c:1  j:2

            for (int j = 0; j < N; j++) {
                int start = inp.nextInt();
                int end = inp.nextInt();
                arr[i][j][0] = start;
                arr[i][j][1] = end;
            }
            //allocating schedule
            int c_start=0;
            int c_end=0;
            int j_start=0;
            int j_end=0;
            for(int j=0;j<N;j++)
            {
                //System.out.println("j: "+j);
                if(overlapping)break;

                if(j==0)
                {   //System.out.println("run 1");
                    alloc[j]='C';
                    c_start=arr[i][j][0];
                    c_end=arr[i][j][1];
                }
                if(j==1)
                {   //System.out.println("run 2");
                    if(arr[i][j][0]>=arr[i][j-1][1])
                    {
                        alloc[j]='C';
                        c_start=arr[i][j][0];
                        c_end=arr[i][j][1];
                    }
                    else
                    {
                        alloc[j]='J';
                        j_start=arr[i][j][0];
                        j_end=arr[i][j][1];
                    }
                }
                if(j>1)
                {   //System.out.println("run >2");
                    if(alloc[j-1]=='C')
                    {
                        if(arr[i][j][0]>=arr[i][j-1][1])
                        {
                            alloc[j]='C';
                            c_start=arr[i][j][0];
                            c_end=arr[i][j][1];
                        }
                        else
                        {
                            if(arr[i][j][0]>=j_end || arr[i][j][1]<=j_start)
                            {
                                alloc[j]='J';
                                j_start=arr[i][j][0];
                                j_end=arr[i][j][1];
                            }
                            else
                            {
                                overlapping=true;
                            }
                        }
                    }
                    else
                    {
                        if(arr[i][j][0]>=arr[i][j-1][1])
                        {
                            alloc[j]='J';
                            j_start=arr[i][j][0];
                            j_end=arr[i][j][1];
                        }
                        else
                        {
                            if(arr[i][j][0]>=c_end || arr[i][j][1]<=c_start)
                            {
                                alloc[j]='C';
                                c_start=arr[i][j][0];
                                c_end=arr[i][j][1];
                            }
                            else
                            {
                                overlapping=true;
                            }
                        }
                    }
                }
            }
            String aa=new String(alloc);
           if(!overlapping) System.out.println("Case #" + (i+1) + ":"+aa );
           else System.out.println("Case #" + (i+1) + ":IMPOSSIBLE");
        }

        //  System.out.println("Case #" + (i+1) + ": " + trace + " " + rowrep+" "+colrep);

    }
}
