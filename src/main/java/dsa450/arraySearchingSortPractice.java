package dsa450;

import java.util.ArrayList;
import java.util.Arrays;

public class arraySearchingSortPractice {
    public static void main(String[] args) {
        removeDulicates(new int[]{1,2,2,2,3,3,3,4,4,4,4,5,5,6,6});
        System.out.println("Equilibrium point: "+equilibriumPoint(new int[]{1,2,3,4,5,5,10}));
        System.out.println("Leader elements: ");
        leaderElement(new int[]{7,10,4,3,6,5,2});
    }
    //remove duplicate characters from array
    public static void removeDulicates(int[] arr){
        int n=arr.length;
        int dups=1;
        System.out.println(Arrays.toString(arr));

        for (int i = 1; i < n; i++) {
            if (arr[i]!=arr[dups-1]){
                arr[dups]=arr[i];
                dups++;
            }
        }
        System.out.println("No of distinct elements: "+dups);
        System.out.println(Arrays.toString(arr));
    }
    public static int equilibriumPoint(int[] arr){
        int sum=0;
        for (int i : arr) {
            sum+=i;
        }
        int lSum=0;
        int rSum=sum;
        int index=0;
        while (lSum!=rSum && index<arr.length){
            lSum+=arr[index];
            rSum=rSum-arr[index];
            index++;
        }
        return index;
    }

    //leader in an array, element is leader if no element is greater than that on right of it
    public static void  leaderElement(int[] arr){
        int n=arr.length;
        ArrayList<Integer> op=new ArrayList<>();
        op.add(arr[n-1]);
        int curLeader=arr[n-1];
        for (int i = n-1; i >=0 ; i--) {
            if (arr[i]>curLeader){
                op.add(arr[i]);
                curLeader=arr[i];
            }
        }
        System.out.println(op);
    }
    //maximum water we can trap b/t different building
    public static int trappingRainWater(int[] arr){
        int n=arr.length;
        int res=0;
        for (int i = 1; i < n-1; i++) {
            int lMax=arr[i];
            for (int j = 0; j < i; j++)
                lMax=Math.max(lMax,arr[j]);
            int rMax=arr[i];
            for (int j = i; j < n; j++)
                rMax=Math.max(rMax,arr[j]);
            res=res+(Math.min(lMax,res)-arr[i]);
        }
        return res;
    }
    //optimization is to pre compute left max and right max
    //then calculate the max water trap
    public static int trappingRainWater02(int[] arr){
        int n=arr.length;
        int res=0;
        int[] lMax=new int[n];
        int[] rMax=new int[n];
        lMax[0]=arr[0];     //calculating left maximum
        for (int j = 1; j < n; j++)
            lMax[j]=Math.max(lMax[j-1],arr[j]);

        rMax[n-1]=arr[n-1]; //calculating right maximum
        for (int j = n-2; j >=0; j--)
            rMax[j]=Math.max(lMax[j+1],arr[j]);
        //calculating maxx water trapped using pre computer results
        for (int i = 1; i < n-1; i++) {
            res=res+(Math.min(lMax[i],rMax[i])-arr[i]);
        }
        return res;
    }
    public static int maxCircularSubArraySum(int[] arr){    //O(n^2)
        int n=arr.length;
        int res=arr[0];
        for (int i = 0; i < n; i++) {
            int curMax=arr[i];
            int curSum=arr[i];
            for (int j = 0; j < n; j++) {
                int index=(i+j)%n;
                curSum+=arr[index];
                curMax=Math.max(curMax,curSum);
            }
            res=Math.max(res,curMax);
        }
        return res;
    }
    /*
    MAXIMUM SUM SUBARRAY CAN BE FOUND BY FINDING THE MAX SUM SUB ARRAY AND THE
    SUM-MINIMUM SUM SUBARRAY VALUE AND FINDING MAX OF BOTH OF THEM

    TO FIND MIN SUM SUB ARRAY WE JUST NEED TO REPLACE MAX TO MIN IN NORMAL KADENS ALO
    OR WE CAN DO, WE JUST INVERT EACH ELEMENT BY MULTILYING BY -1 AND FIND NORMAL KADENS WILL GIVE
    THE MIN SUM SUB ARRAY
     */

    //majority element: element which is having count greater than n/2
// moore voting algo: if there are pair max a element can appear = n/2 not more than n/2
    // so element not in pair is our majority

}
