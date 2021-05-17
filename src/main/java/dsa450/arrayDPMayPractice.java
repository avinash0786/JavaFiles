package dsa450;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class arrayDPMayPractice {
    public static void main(String[] args) {

    }
    //find pair of given sum
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int rem=target-nums[i];
            if (map.containsKey(rem))
                return new int[]{map.get(rem),i};
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
    //counting number of subarray of sum k
    public static int countSubArraySumK(int[] arr,int sum){
        int count=0;
        int curSum=0;
        HashMap<Integer,Integer> prefSum=new HashMap<>();
        prefSum.put(0,1);
        for (int i = 0; i < arr.length; i++) {
            curSum+=arr[i];
            if (prefSum.containsKey(curSum-sum)){
                count+=prefSum.get(curSum-sum);
            }
            prefSum.put(curSum,prefSum.getOrDefault(curSum,0)+1);
        }
        return count;
    }
    //find all triplet with sum ==0
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> op=new ArrayList<>();
        Arrays.sort(nums);
        int n=nums.length;
        for (int i = 0; i < n; i++) {
            int start=i+1;
            int end=n-1;
            while (start<end){
                int cur2Sum=nums[start]+nums[end];
                if (cur2Sum>-nums[i])
                    end--;
                else if (cur2Sum<-nums[i])
                    start++;
                else {
                    op.add(new ArrayList<>(Arrays.asList(nums[i],nums[start],nums[end])));
                }
            }
        }
        return op;
    }
    public int threeSumClosest(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
    public static void rotate(int[][] matrix) {
        int n=matrix.length;
        //take transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j <n; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
        //reverse rows
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[i][n-1-j];
                matrix[i][n-1-j]=temp;
            }
        }
    }
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums.length==0)
            return 0;
        int minLen=Integer.MAX_VALUE;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(nums[0],0);
        for (int i = 1; i < nums.length; i++) {
            nums[i]=nums[i]+nums[i-1];
            System.out.println(Arrays.toString(nums));
            System.out.println(map);
            if (nums[i]>=target){
                if (map.containsKey(nums[i]-target)){
                    minLen=Math.min(minLen,i-map.get(nums[i]-target));
                }
            }
            map.put(nums[i],i);
        }
        return minLen==Integer.MAX_VALUE?0:minLen;
    }
    public static int minSubArraySum(int[] arr,int target){
        int i=0,j=0,curSum=0,min=Integer.MAX_VALUE;
        while (j<arr.length){
            curSum+=arr[j];
            j++;
            while (curSum>=target){
                min=Math.min(min,j-i);
                curSum=curSum-arr[i];
                i++;
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    //Game of life
    // dead to live mark as 2
    //live to dead mark as -1
    public static void gameOflife(int[][] grid){
         int R=grid.length;
         int C=grid[0].length;

        int[] dx=new int[]{1,1,0,-1,-1,-1,0,1};
        int[] dy=new int[]{0,1,1,1,0,-1,-1,-1};

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int liveCount=0;
                for (int k = 0; k < 8; k++) {
                    if (isSafeGameOfLife(i+dx[k],j+dy[k],R,C) && Math.abs(grid[i+dx[k]][j+dy[k]])==1)
                        liveCount++;
                }
                if (grid[i][j]==0 && liveCount==3)
                    grid[i][j]=2;
                if (grid[i][j]==1 && (liveCount<2 || liveCount>3))
                    grid[i][j]=-1;
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                grid[i][j]=grid[i][j]>0?1:0;
            }
        }
    }
    private static boolean isSafeGameOfLife(int x,int y,int R,int C){
        return (x>=0 && x<R && y>=0 && y<C);
    }
}
