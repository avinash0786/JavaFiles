package dsa450;

import java.util.Arrays;

public class twoPointerMay {
    public static void main(String[] args) {

    }
    //longest substring without repeating charcter
    public static int longestDistinctChar(String str){
        char[] arr=str.toCharArray();
        int n=arr.length;
        int[] prevOc=new int[256];
        Arrays.fill(prevOc,-1);
        int res=0;
        int start=0;
        for (int i = 0; i < n; i++) {
            start=Math.max(start,prevOc[arr[i]]+1);
            int len=i-start+1;
            res=Math.max(res,len);
            prevOc[arr[i]]=i;
        }
        return res;
    }
    //to delete the next node we need to be one step back; so the dist bt both node inc by 1
    //
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        while (n-->=0)
            first = first.next;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
    public static int sum3Closest(int[] arr,int target){
        int n=arr.length;
        int result=arr[0]+arr[1]+arr[n-1];
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            int lft=i+1;
            int rgt=n-1;
            while (lft<rgt){
                int sum=arr[i]+arr[lft]+arr[rgt];
                if (sum>target)
                    rgt--;
                else if (sum<target)
                    lft++;
                if (Math.abs(sum-target)<Math.abs(result-target))
                    result=sum;
            }
        }
        return result;
    }
    public static void sort123(int[] arr){
        int low=0,mid=0,high=arr.length-1;
        while (mid<=high){
            if (arr[mid]==0){
                swap(arr,mid,low);
                low++;
                mid++;
            }
            else if (arr[mid]==1)
                mid++;
            else {
                swap(arr,mid,high);
                high--;
            }
        }
    }
    private static void swap(int[] arr, int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void moveZeros(int[] arr){
        if (arr.length==0 || arr.length==1)
            return;
        int left=0,right=0;
        while (right<arr.length){
            if (arr[right]==0)
                right++;
            else {
                swap(arr,left,right);
                left++;
                right++;
            }
        }
    }
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i])]>0)
                nums[Math.abs(nums[i])]=nums[Math.abs(nums[i])]*(-1);
            else
                return Math.abs(nums[i]);
        }
        return 0;
    }
    public static int countSubArrProd(int[] arr,int k){
        int n=arr.length;
        if (n==0 || k<1)
            return 0;
        int prod=1;
        int res=0;
        int l=0,r=0;
        while (r<n){
            prod=prod*arr[r];
            while (prod>=k)  //shrink the window
                prod=prod/arr[l++];
            res+=r-l+1;
            r++;
        }
        return res;
    }
}
