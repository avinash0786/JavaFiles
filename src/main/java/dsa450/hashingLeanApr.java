package dsa450;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class hashingLeanApr {
    public static void main(String[] args) {
        System.out.println("Subarray Sum 0: "+subArrayZeroSum(new int[]{1,4,13,-3,-10,5}));
        System.out.println("Longest equal zero and one subarray: "+longestSubArray(new int[]{1, 0, 0, 1, 0, 1, 1}));
    }
    //subaray with sum 0 using prefix sum and hashing
    // if the sum is zero the prefix sum will no change as the sum is zero
    //and that sum will be available already in the hashTable
    public static boolean subArrayZeroSum(int[] arr){
        HashSet<Integer> pref=new HashSet<>();
        int preSum=arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum+=arr[i];
            if (preSum==0)
                return true;
            if (pref.contains(preSum))
                return true;
            else
                pref.add(preSum);
        }
        return false;
    }
    //subaray with given sum  using prefix sum and hashing
    public static boolean subArraySum(int[] arr,int sum){
        HashSet<Integer> pref=new HashSet<>();
        int preSum=arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum+=arr[i];
            if (preSum==sum)
                return true;
            if (pref.contains(preSum-sum))
                return true;
            else
                pref.add(preSum);
        }
        return false;
    }
    //Longest subaray with equal on of zero and one
    public static int longestSubArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(arr[i]==0)?-1:arr[i];
        }
        HashMap<Integer,Integer> index=new HashMap<>();
        int res=0;
        int prefSum=0;
        for (int i = 0; i < arr.length; i++) {
            prefSum+=arr[i];
            if(!index.containsKey(prefSum))
                index.put(prefSum,i);
            if(index.containsKey(prefSum))
                res=Math.max(res,i-index.get(prefSum));
        }
        return res;
    }

    //longest common span with same sum in both array

    //count distinct element in every window of size k
    public static void countDistWondow(int[] arr, int k){
        int n=arr.length;
        for (int i = 0; i <= n-k; i++) {
            int count=0;
            for (int j = 0; j < k; j++) {
                boolean flag=false;
                int cur=arr[i+j];
                for (int l = 0; l < j; l++) {
                    if (cur==arr[i+l]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    count++;
            }
            System.out.println(count);
        }
    }
    //EFFICIENT:    USE SLIDING WINDOW WITH HASHING

    //count of elment having count greater than n/k
    public static void moreNbyKOcc(int[]arr,int k){
        int n=arr.length;
        HashMap<Integer,Integer> index=new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (index.containsKey(arr[i]))
                index.replace(arr[i],index.get(arr[i])+1);
            else if (index.size()<(k-1))
                index.put(arr[i],1);
            else {
                index.replaceAll((key, val) -> val--);
                for(Map.Entry<Integer,Integer> ent:index.entrySet()){
                    if (ent.getValue()==0)
                        index.remove(ent.getKey());
                }
            }
        }
        for(Map.Entry<Integer,Integer> ent:index.entrySet()){
            if (ent.getValue()>n/k)
                System.out.println(ent.getKey()+"-"+ent.getValue()+" ");
        }
    }

}
