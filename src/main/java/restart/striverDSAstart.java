package restart;

import java.util.*;

public class striverDSAstart {
    public static void main(String[] args) {
//        setMatrixZero(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
//        int[] unsorted=new int[]{3,1,4,5,9,12,0,7,13,2,8};
//        System.out.println(Arrays.toString(unsorted));
//        mergeSort(unsorted,0,unsorted.length-1);
//        System.out.println(Arrays.toString(unsorted));
    }
    public static int longestSubarraySubZero(int[] arr,int n){
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        int maxLen=0;
        int prefSum=0;
        for (int i = 0; i < n; i++) {
            prefSum+=arr[i];
            if (arr[i] == 0 && maxLen == 0)
                maxLen = 1;
            if (prefSum == 0)
                maxLen = i + 1;
            if (hashMap.containsKey(prefSum)){
                maxLen=Math.max(maxLen,i-hashMap.get(prefSum));
            }else{
                hashMap.put(prefSum,i);
            }
        }
        return maxLen;
    }
    public static int longestConsecutive(int[] nums) {
        HashMap<Integer, Boolean> hashMap=new HashMap<>();
        for (int num : nums) {
            hashMap.put(num,true);
        }
        for (int num : nums) {
            if (hashMap.containsKey(num-1))
                hashMap.put(num,false);
        }
        int maxLenght=0;
        for (Integer integer : hashMap.keySet()) {
            if (hashMap.get(integer)){
                maxLenght=Math.max(maxLenght,maxSubSqlLen(hashMap,integer));
            }
        }
        return maxLenght;
    }
    public static int maxSubSqlLen(HashMap<Integer, Boolean> hm,int key){
        int ans=0;
        while (hm.containsKey(key)){
            ans++;
            key++;
        }
        return ans;
    }
    public static boolean searchInSortedMatrix(int[][] matrix, int target){
        int row=matrix.length-1,col=matrix[0].length-1;
            int i=0,j=col;
            while (i<=row && j>=0){
            if (matrix[i][j]==target)
                return true;
            if (matrix[i][j]>target)
                j--;
            else
                i++;
        }
        return false;
    }
    /*
    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    */
    public static void mergeSort(int[] arr,int l,int r){
        if(l<r){
            int mid=l+(r-l)/2;
            mergeSort(arr,l,mid);
            mergeSort(arr,mid+1,r);
            merge(arr,l,mid,r);
        }
    }
    public static void merge(int[] arr,int low,int mid, int high){
        int n1=mid-low+1;
        int n2=high-mid;
        int[] left=new int[n1];
        int[] right=new int[n2];
        for (int i = 0; i < n1; i++) {
            left[i]=arr[low+i];
        }
        for (int i = 0; i < n2; i++) {
            right[i]=arr[mid+i+1];
        }
        int i=0,j=0,k=low;
        while (i<n1 && j<n2){
            if (left[i]<=right[j])
                arr[k++]=left[i++];
            else
                arr[k++]=right[j++];
        }
        while (i<n1)
            arr[k++]=left[i++];
        while (j<n2)
            arr[k++]=right[j++];
    }
    public static int[][] mergeOverlappingInterval(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
//        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        LinkedList<int[]> merged=new LinkedList<>();
        for (int[] ints : intervals) {
            if (merged.isEmpty() || merged.getLast()[1]<ints[0]){
                merged.add(ints);
            }else{
                merged.getLast()[1]=Math.max(ints[1], merged.getLast()[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
    public static void sort012(int[] arr){
        int low=0,mid=0;
        int high=arr.length-1;
        while (mid<=high){
            if (arr[mid]==0){
                swap(arr,mid,low);
                low++;mid++;
            }
            else if (arr[mid]==1){
                mid++;
            }
            else {
                swap(arr,mid,high);
                high--;
            }
        }
    }
    public static int maxSubArray(int[] nums) {
        int sum,maxSum=nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum=Math.max(nums[i],nums[i]+maxSum );
            maxSum=Math.max(maxSum,sum);
        }
        return maxSum;
    }
    public static void  numberNextPermutation(int[] arr){
        int n=arr.length;
        int pointOfInfl=-1;
        for (int i = n-1; i >0 ; i--) {    //find point of inflection
            if (arr[i-1]<arr[i]){
                pointOfInfl=i-1;
                break;
            }
        }
        if (pointOfInfl==-1){   //if no point of inflection exist , revrese entire number
            reverse(arr,0,n-1);
            return;
        }
        for (int i = n-1; i > pointOfInfl; i--) {
            if (arr[i]>arr[pointOfInfl]){
                swap(arr,i,pointOfInfl);
                break;
            }
        }
        reverse(arr,pointOfInfl+1,n-1);
    }
    public static void  swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void reverse(int[] arr,int start,int end){
        for (int i=start, j=end;i<j;i++,j--){
            swap(arr,i,j);
        }
    }
    public static void  setMatrixZero(int[][] arr){
        int[] rowData=new int[arr[0].length];
        int[] colData=new int[arr.length];
        Arrays.fill(rowData,-1);
        Arrays.fill(colData,-1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j]==0){
                    rowData[j]=0;
                    colData[i]=0;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(rowData[j]==0 || colData[i]==0)
                    arr[i][j]=0;
            }
        }
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
