package dsa450;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class kicksartRoundC {
    public static void main(String[] args) {
        Scanner in=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();

            System.out.println("Case #" + i + ": " );
        }
    }
    public static int findWays(int inc,int cur,int target){
        if (cur==target)
            return 1;
        if (cur>target || inc>target)
            return 0;
        System.out.println("Cur: "+cur+" inc: "+inc);
        int take=findWays(inc+1,cur+inc+1,target);//carray on
        int dontTake=findWays(inc,inc+1,target);      //new start
        return take+dontTake;
    }
}
