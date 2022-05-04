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
    //1920. Build Array from Permutation
    public static int[] buildArray(int[] nums) {
        int len=nums.length;
        for(int i=0;i<len;i++){
            nums[i]=(nums[nums[i]]%len)*len+nums[i];
        }
        for(int i=0;i<len;i++)
            nums[i]/=len;
        return nums;
    }
}
/*
1920. Build Array from Permutation

Given a zero-based permutation nums (0-indexed), build an array ans of the same length where
ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).


Input: nums = [0,2,1,5,3,4]
Output: [0,1,2,4,5,3]
Explanation: The array ans is built as follows:
ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
    = [0,1,2,4,5,3]



*/
