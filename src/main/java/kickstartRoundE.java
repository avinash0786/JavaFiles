import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class kickstartRoundE
{
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=in.nextInt();
        for (int k = 0; k <t ; k++)
        {
            System.out.println("Run: "+k);
            int n=in.nextInt();
            int []arr=new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]=in.nextInt();
            }
            int p=0;
            int q=1;
            int curDif=arr[q]-arr[q-1];
            int maxDif=1;
            while (q<n){

                System.out.println("P: "+p+" Q: "+q+" Diff: "+(arr[q]-arr[q-1])+" Cur Dif: "+curDif);
                if(curDif==(arr[q]-arr[q-1])){
                    maxDif=Math.max(q-p,maxDif);
                    q++;
                    System.out.println("Max dif: "+maxDif);
                }
                else {
                    p=q-1;
                    //q++;
                    curDif=arr[q]-arr[q-1];
                    System.out.println("P update: "+p+" Cur Dif: "+curDif);
                }
            }
            System.out.println("Case #"+(k+1)+": "+(maxDif+1));
        }

    }
}
//System.out.println("Case #"+(i+1)+": "+result[i]); //Case #4: 0
/*
1
7
10 7 4 6 8 10 11
 */