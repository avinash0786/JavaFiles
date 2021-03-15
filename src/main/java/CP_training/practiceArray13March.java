package CP_training;

import java.util.Arrays;

public class practiceArray13March {
    public static void main(String[] args) {
//        System.out.println(minJumps(new int[]{2,3,1,1,2,4,2,0,1,1}));
    }
    static int minJumps(int[] arr){
        int jump=0;
        int step=arr[0];
        int index=0;
        int n=arr.length-1;
        System.out.println("n: "+n);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i <= n; i++) {
            if (arr[i]==0) return jump-1;
            if (i==index){
                step=arr[i];
                index=i+step;
                jump++;
                System.out.println("step: "+step+" index: "+index+" jump: "+jump+" i: "+i);
            }
        }
        return jump-1;
    }
    public static int getMinDiff(int[] arr, int n, int k) {
        // code here
        return 0;
    }
}
