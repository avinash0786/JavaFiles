package restart;
import java.lang.reflect.Array;
import java.util.*;
public class firstDay {
    public static void main(String[] args) {
//        threeSum(new int[]{-1,0,1,2,-1,-4});
//        int[] arr=new int[]{1,3,2};
//        nextPermutation(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(firstPosition(new int[]{5,7,7,8,8,10},8));
//        System.out.println("Binary search: "+binarySearch(new int[]{1,2,3,4,5,6,7,8,9},9,0,8));
//        System.out.println("Binary search, rotated sorted: "+searchInRotatedSorted(new int[]{5,1,3},5));
        System.out.println(firstMissingPositive(new int[]{1,2,0}));
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        permutBacktrack(nums,new ArrayList<>(),result,new boolean[nums.length]);
        return result;
    }
    public static void permutBacktrack(int[] nums,List<Integer> temp,List<List<Integer>> res,boolean[] visited){
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i])continue;
            temp.add(nums[i]);
            visited[i]=true;
            permutBacktrack(nums,temp,res,visited);
            visited[i]=false;
            temp.remove(temp.size()-1);
        }
    }
    //get all combination of array of elements which give the sum equal to target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result=new ArrayList<>();
        combinBacktrack(candidates,0,target,new ArrayList<>(),result);
        return result;
    }
    public static void combinBacktrack(int[] candidates,int index,int target,List<Integer> temp,List<List<Integer>> result){
        if (target<0)
            return;
        if (target==0){
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i=index;i< candidates.length;i++){
            temp.add(candidates[i]);
            combinBacktrack(candidates,i,target-candidates[i],temp,result);
            temp.remove(temp.size()-1);
        }
    }

    public static int firstMissingPositive(int[] nums) {
        for(int i=0;i< nums.length;i++){
            int nextPos=nums[i]-1;
            System.out.println(i+" Out next pos: "+nextPos);
            while(nums[i]>=1 && nums[i]<= nums.length && nums[i]!=nums[nextPos]){
                System.out.println("cur val: "+nextPos+" replacement val: "+nums[nextPos]);
                int temp=nums[nextPos];
                nums[nextPos]=nums[i];
                nums[i]=temp;
                nextPos=nums[i]-1;
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=i+1)
                return i+1;
        }
        return nums.length;
    }
    public static int searchInRotatedSorted(int[] arr, int target) {
        int left=0,right= arr.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            System.out.println("left: "+left+" mid: "+mid+" right: "+right);
            if (arr[mid]==target)
                return mid;
            //if left part is sorted
            if (arr[left]<=arr[mid]) {
                if (arr[left]<target && target<arr[mid])
                    right=mid-1;
                else
                    left=mid+1;
            }
            if (arr[mid]<=arr[right]){
                if (target>arr[mid] && target<arr[right])
                    left=mid+1;
                else
                    right=mid-1;
            }
        }
        return -1;
    }
    public static int binarySearch(int[] arr,int target,int left,int right){
        System.out.println("Bin search: ");
        while(left<=right){
            int mid=(left+right)/2;
            if (arr[mid]<target)
                left=mid+1;
            else if (arr[mid]>target)
                right=mid-1;
            else
                return mid;
        }
        return -1;
    }
    public static int firstPosition(int[] arr,int target){
        int left=0,right=arr.length;
        while (left<right){
            int mid=(left+right)/2;
            if (arr[mid]<target)
                left=mid+1;
            else if (arr[mid]>target)
                right=mid-1;
            else if (mid==0 || arr[mid-1]!=arr[mid])
                return mid;
            else
                left=mid-1;
        }
        return -1;
    }
    public static int lastPosition(int[] arr,int target){
        int left=0,right=arr.length;
        while (left<right){
            int mid=(left+right)/2;
            if (arr[mid]<target)
                left++;
            else if (arr[mid]>target)
                right--;
            else if (mid==arr.length-1 || arr[mid]!=arr[mid-1])
                return mid;
        }
        return -1;
    }

    public static void nextPermutation(int[] nums) {
        int n= nums.length;
        int pointOfInf=-1;
        for (int i = n-1; i >0 ; i--) {
            if (nums[i-1]<nums[i]){
                pointOfInf=i-1;
                break;
            }
        }
        if (pointOfInf==-1){
            reverse(nums,0,n-1);
            return;
        }
        System.out.println("Point of inf: "+pointOfInf);
        for (int i = n-1; i >= pointOfInf; i--) {
            if (nums[i]>nums[pointOfInf]){
                System.out.println("swap: i "+i+" poi: "+pointOfInf);
                swap(nums,i,pointOfInf);
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
        reverse(nums,pointOfInf+1,n-1);
    }
    public static List<List<Integer>> threeSum1(int[] nums) {
        Set<ArrayList<Integer>> finalSet=new HashSet<>();
        Arrays.sort(nums);
        int n= nums.length;
        for (int i = 0; i < n; i++) {
            int left=i+1,right=n-1;
            while (left<right){
                int curSum=nums[left]+nums[right];
                if (curSum>-nums[i])
                    right--;
                else if (curSum<-nums[i])
                    left++;
                else
                    finalSet.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                left++;
                right--;
            }
        }
        return new ArrayList<>(finalSet);
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        int n= nums.length-1;
        System.out.println(Arrays.toString(nums));
        Set<Integer[]> resSet=new HashSet<>();
        ArrayList<List<Integer>> result=new ArrayList<>();
        for (int i = 0; i < nums.length-1; i++) {
//            System.out.println("Cur val: "+nums[i]+" req val: "+(-nums[i]));
            int[] twoSum=twoSum(nums,i+1,-nums[i]);
            int sum=twoSum[0]+twoSum[1];
            if (sum+nums[i]==0) {
                System.out.println("Triplet found: "+Arrays.toString(twoSum)+nums[i]);
                resSet.add(new Integer[]{twoSum[0],twoSum[1],nums[i]});
            }
        }
        for(Integer[] ar:resSet)
            System.out.println(Arrays.toString(ar));
        return result;
    }
    public static int[] twoSum(int[] nums,int left,int target){
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=left;i<nums.length;i++){
            if(map.containsKey(target-nums[i]))
                return new int[]{nums[i],target-nums[i]};
            else
                map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void reverse(int[] arr,int start,int end){
        for (int left = start,right=end; left < right; left++,right--) {
            swap(arr,left,right);
        }
    }
}
