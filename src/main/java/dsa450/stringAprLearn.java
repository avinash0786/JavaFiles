package dsa450;

import java.util.HashMap;

public class stringAprLearn {
    public static void main(String[] args) {

    }
    public static int lengthOfLastWord(String s) {
        int count=0;
        s=s.trim();
        int i=s.length()-1;
        while (i>=0 && s.charAt(i)!=' '){
            count++;
            i--;
        }
        return count;
    }
    //path sum 3
    //in a binary tree finding the number of paths having the sum equal to given sum
    static int total=0;
    public static int pathSum(TreeNode root,int sum){
        if (root==null)
            return 0;
        findPathSumUtil(root,sum,0);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
        return total;
    }
    private static void findPathSumUtil(TreeNode root, int sum,int curSum){
        if (root==null)
            return;
        curSum+=root.val;
        if (curSum==sum)
            total++;
        findPathSumUtil(root.left,sum,curSum);
        findPathSumUtil(root.right,sum,curSum);
    }
    static int totalPaths=0;
    static HashMap<Integer,Integer> prefSum;
    public static int pathSum3optimized(TreeNode root,int sum){
        prefSum=new HashMap<>();
        prefSum.put(0,1);
        ptSum03Util(root,0,sum);
        return totalPaths;
    }
    private static void ptSum03Util(TreeNode root,int curSum,int sum){
        if (root==null)
            return;
        curSum+=root.val;
        if (prefSum.containsKey(curSum-sum)){
            totalPaths+=prefSum.get(curSum-sum);
        }
        prefSum.put(curSum,prefSum.getOrDefault(curSum,0)+1);
        ptSum03Util(root.left,curSum,sum);
        ptSum03Util(root.right,curSum,curSum);

        prefSum.put(curSum,prefSum.get(curSum)-1);
    }

    //finding no of subarray with sum k
    public static int countSubArraySumK(int[] arr,int sum){
        int count=0;
        int curSum=0;
        HashMap<Integer,Integer> prefSum=new HashMap<>();
        prefSum.put(0,1);
        for (int i = 1; i < arr.length; i++) {
            curSum+=arr[i];
            if (prefSum.containsKey(curSum-sum)){
                count+=prefSum.get(curSum-sum);
            }
            prefSum.put(curSum,prefSum.getOrDefault(curSum,0)+1);
        }
        return count;
    }


}
