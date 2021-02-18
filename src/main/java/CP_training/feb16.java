package CP_training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class feb16 {
    public static void main(String[] args) {
        pairSum(new int[]{1,3,5,7,8,10,11,13,15},13);
        pairDiff(new int[]{1,3,5,7,8,10,11,13,15},8);
        minBoats(new int[]{1,3,5,6,11,18,19,20},20);
        System.out.println(longestNonRepSubString("aaass"));
        System.out.println(minSwapGroupK(new int[]{1,8,6,2,8,4,8},4));
        System.out.println(contMostWater(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("Pair exist: "+pairExist(new int[]{3,1,6,7,5,10},11));
        subarraySum(new int[]{1,2,-3,6,4},0);
        subarraySum(new int[]{1,2,-3,-2,6,4},1);

    }
    public static void pairSum(int[]arr, int sum){
        int low=0;
        int high=arr.length-1;
        System.out.println(Arrays.toString(arr));
        while (low<=high){
            if (arr[low]+arr[high]==sum){
                System.out.println("Low: "+arr[low]+" high: "+arr[high]+" sum: "+sum);
                return;
            }
            else if (arr[low]+arr[high]>sum)
                high--;
            else
                low++;
        }
    }
    public static void pairDiff(int[]arr, int diff){
        int low=0;
        int high=0;
        while (low<arr.length){
            if (arr[high]-arr[low]==diff){
                System.out.println("Low: "+arr[low]+" high: "+arr[high]+" Diff: "+diff);
                return;
            }
            else if (arr[high]-arr[low]<diff)
                high++;
            else
                low++;
        }
    }

    public static void pairDiffM02(int[]arr, int diff){
        int low=0;
        int high=0;
        while (low<arr.length){
            while (high<arr.length && arr[high]-arr[low]<diff){
                high++;
            }
            if (arr[high]-arr[low]==diff){
                System.out.println("Low: "+arr[low]+" high: "+arr[high]+" Diff: "+diff);
                return;
            }
            else
                low++;
        }
    }
    public static int  minBoats(int[] weights, int limit){
        int low=0;
        int high=weights.length-1;
        int count=0;
        while (low<high){
            if (weights[low]+weights[high]<=limit){
                System.out.println(weights[low]+" - "+weights[high]);
                count++;
                low++;
                high--;
            }
            else {
                System.out.println(weights[high]);
                count++;
                high--;
            }
        }
        if (low==high) {
            System.out.println(weights[high]);
            count++;
        }
        return count;
    }
    public static int longestNonRepSubString(String str){   //  tc:- O(n) sc:- O(1)
        int low=0;
        int high=0;
        int n=str.length();
        int ans=0;
        char[] s=str.toCharArray();
        HashSet<Character> chr=new HashSet<>();
        while (high<n){
            // while low< n and we have not found the next element
            while (low<n && !chr.contains(s[low])){
                chr.add(s[low]);
                low++;
            }
            ans=Math.max(ans,chr.size());
            chr.remove(s[high]);
            high++;
        }
        return ans;
    }
    //FIXED SIZE SLIDING WINDOW
    public static int minSwapGroupK(int[]arr, int k){
        int total=0;
        for (int i : arr) {
            total+=(i<=k)?1:0;
        }
        int curWinLess=0;
        for (int i = 0; i < total; i++) {
            curWinLess+=(arr[i]<=k)?1:0;
        }
        System.out.println(Arrays.toString(arr));
        int min=total-curWinLess;
        System.out.println("Total: "+total+" curles: "+curWinLess+" min: "+min);
        for (int i = total; i < arr.length; i++) {
            if (arr[i]<=k){
                curWinLess++;
            }
            if (arr[i-total]>k){
                curWinLess--;
            }

            System.out.println("start: "+(i-total)+" end: "+i+" cur: "+curWinLess);
            min=Math.min(min,total-curWinLess);
        }
        return min;
    }
    public static int contMostWater(int[] arr){
        int low=0;
        int high=arr.length-1;
        int ans=0;
        System.out.println(Arrays.toString(arr));
        while (low<high){
            int cur=Math.min(arr[low],arr[high])*(high-low);
            ans=Math.max(ans,cur);
            System.out.println("Cur: "+cur+" ans: "+ans+" low: "+low+" high: "+high+ "  val:  "+arr[low]+" - "+arr[high]);
            if (arr[low]==arr[high]){
                low++;
                high--;
            }
            else if (arr[low]>arr[high])
                high--;
            else
                low++;
        }
        return ans;
    }
    //  --------------HASHING-----------------------//
    public static boolean pairExist(int[]arr, int sum){
        HashMap<Integer,Integer> map=new LinkedHashMap<>();
        for (int i = 0; i <arr.length; i++) {
            if (map.containsKey(sum-arr[i])){
                System.out.println("Found at: "+i+" and: "+map.get(sum-arr[i]));
                return true;
            }
            map.put(arr[i],i);
        }
        return false;
    }
    // subarray with given sum
    // maximum sub array sum
    public static void  subarraySum(int[]arr, int sum){
        HashMap<Integer, Integer> map=new HashMap<>();
        int s=0;
//        System.out.println(Arrays.toString(arr));
        map.put(0,0);
        s+=arr[0];
        map.put(s,1);
        for (int i = 1; i < arr.length; i++) {
            s+=arr[i];
//            System.out.println("Sum: "+s+" val i: "+arr[i]);
            if (map.containsKey(s-sum)){
                System.out.println("Subarray sum: "+sum+" Found at: "+map.get(s-sum)+" to: "+i);
                break;
            }
            map.put(s,(i+1));
        }
    }

}
