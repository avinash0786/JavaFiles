package CP_training;

import java.util.Arrays;

public class feb13 {
    public static void main(String[] args) {
        int[] arr=new int[]{2,5,8,9,12,12,12,12,15,23,29,35};
//        System.out.println("23 found at: "+binSearch(arr,213));
//        System.out.println("First index of 12: "+firstOcc(arr,12));
        System.out.println("Mountain peak: "+peakMount(new int[]{3,5,3,2,0}));
//        System.out.println(searchInsert(new int[]{1,3,5,6,9,12,15,16,18,23,25,29} ,5));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},8)));
        System.out.println(lastPos(new int[]{2,2,2,2},2));
        System.out.println(kthSmallest(new int[]{7,10,4,3,20,15},3));
        System.out.println(nLargest(new int[]{7,10,4,3,20,15},3));
    }
    public static boolean isFeasible(int arr[],int n,int k, int ans){
        int req=1,sum=0;
        for(int i=0;i<n;i++){
            if(sum+arr[i]>ans){
                req++;
                sum=arr[i];
            }
            else{
                sum+=arr[i];
            }
        }
        return (req<=k);
    }

    public static int minPages(int arr[],int n, int k){
        int sum=0,mx=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            mx=Math.max(mx,arr[i]);
        }
        int low=mx,high=sum,res=0;

        while(low<=high){
            int mid=(low+high)/2;
            if(isFeasible(arr,n,k,mid)){
                res=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return res;
    }
    public static void matrixSearchSorted(int[][] mat, int k){  //  O(m+n)
        int row=0;
        int col=mat[0].length-1;
        while (row<mat.length && col>=0){
            if (mat[row][col]==k) {
                System.out.println("Found at i: " + row + " j: " + col);
                break;
            }
            if (mat[row][col]>k)
                col--;
            else
                row++;
        }
    }
    public static int cntLess(int[]arr,int x){
        int cnt=0;
        for (int i : arr) {
            cnt+=(i>x)?1:0;
        }
        System.out.println("Cnt less "+x+" is: "+cnt);
        return cnt;
    }
    //n-th largest element
    public static int nLargest(int[]arr,int k){
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for (int i : arr) {
            min= Math.min(i, min);
            max=Math.max(i,max);
        }
        System.out.println(Arrays.toString(arr));
        while (min<=max){
            int mid=min+(max-min)/2;
            System.out.println("min: "+min+" mid: "+mid+" max: "+max);
            if (cntLess(arr,mid)>=k && cntLess(arr,mid-1)<k)
                return mid;
            else if (cntLess(arr,mid)<k)
                max=mid-1;
            else
                min=mid+1;
        }
        return -1;
    }

    /*
    Matrix NxM
    sorted rows, sorted columns
    last element of any row is < than first element of next row
    perform serach in that matrix()
     */
    public static int matrixSortedSearch (int[][] matrix, int key){
        int row=matrix[0].length;
        int col=matrix.length;
        // Binary search on column using matrix[mid][row]
        int low=0;
        int high=col-1;
        int searchCol=0;
        while (low<=high){
            int mid=(low+high)/2;
            if (matrix[mid][0]>key && matrix[mid-1][0]<=key){
                searchCol=mid-1;
                break;
            }
            else if (matrix[mid][0]<=key)
                low=mid+1;
            else
                high=mid-1;
        }


        return -1;
    }
    //aggressive cows
    public static int aggrCowsNaive(int[] arr,int c,int n){ //  O(n*(max-min))
        int low=arr[0];
        int high=arr[arr.length-1];
        int max=high-low;
        int lastCow=-1;
        int cnt=-1;
        for (int i = low; i < max; i++) {
            int cow=0;
            lastCow=arr[0];
            for (int j = low; j <n-1 ; j++) {
                if (arr[j]-lastCow>=i){
                    cow++;
                    lastCow=arr[j];
                }
            }
            if (cow>c)
                cnt=i;
            else
                break;
        }
        return cnt;
    }
    public static int cows(int[] arr,int dist){
        int cow=0;
        int lastCow=arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]-lastCow>=dist){
                cow++;
                lastCow=arr[i];
            }
        }
        return cow;
    }
    //array sort
    public static int aggresiveCows(int[] arr, int c,int n){
        int low=1;
        int high=arr[arr.length-1]-arr[0];
        while (low<=high){
            int mid=(low+high)/2;
            if (cows(arr,mid)>=c && cows(arr,mid+1)<c)
                return mid;
            else if (cows(arr,mid)>=c)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }
    //max level formed from n blocks given
    public static int  maxLevel(int n){
        int low=0;
        int high=n;
        while (low<=high){
            int mid=(low+high)/2;
            if (mid*(mid-1)/2<=n && (mid+1)*(mid+2)/2>n)
                return mid;
            else if (mid*(mid+1)/2>n)
                high=mid-1;
            else
                low=mid+1;
        }
        return -1;
    }

    public static int kthSmallest(int[]arr, int k){
        int low=Integer.MAX_VALUE;
        int high=Integer.MIN_VALUE;
        for (int i : arr) {
            low=Math.min(low,i);
            high=Math.max(high,i);
        }
        while (low<=high){
            int mid=low+(high-low)/2;
//            System.out.println("Low: "+low+" mid: "+mid+" high: "+high);
            int cntLess=0,cntEqual=0;
            for (int i : arr) {
                if (i<mid)
                    cntLess++;
                else if (i==mid)
                    cntEqual++;
            }
//            System.out.println("CntLess: "+cntLess+" cntEqual: "+cntEqual);
            //if no of element cntLess is less than k
            // and cntLEss+cntEq >=k then this is our k th smallest element
            if (cntLess<k && (cntLess+cntEqual)>=k)
                return mid;
            else if (cntLess>=k) {
//                System.out.println("Go left");
                high = mid - 1;
            }
            else if (cntLess+cntEqual<k) {
//                System.out.println("Go right");
                low = mid + 1;
            }
//            System.out.println();
        }
        return -1;
    }
    //finding element occ one time in doble occ arr
    //like 1,1,4,4,5,5,7,8,8,9,9,
    /*
    if
    firs occurance of mid is not multiple of 2 then we go accordingly
     */
    public static int singleElement(int[] arr){
        int n=arr.length;
        int left=0;
        int right=n-1;
        while (left<=right){
            int mid=(left+right)/2;
            if (arr[mid]!=arr[mid-1] && arr[mid]!=arr[mid+1])
                return mid;
            //if first occurance
            //if at even index we check if arr[mid+1==arr[mid]
            // if at odd index we check if arr[mid-1==arr[mid]
            if ((mid%2==0 && arr[mid+1]==arr[mid]) || (mid%2==1 && arr[mid-1]==arr[mid]))
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }
    /*
    QUESTION: generalize for n-similar elements
     */

    //peak of mountain array
    public static int peakMount(int[] arr){
        int n=arr.length;
        int left=0;
        int right=n-1;
        while (left<=right){
            int mid=(left+right)/2;
            // return the element greater than its both the adjacent
            if (arr[mid]>arr[mid+1] && arr[mid]>arr[mid-1])
                return mid;
            else if (arr[mid]<arr[mid+1])
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }
    public int findInMountainArray(int target, int[] mountainArr) {
        int index=-1;
        int n=mountainArr.length-1;
        int low=0;
        int high=n;
        int mid = 0;
        while (low<=high){
            mid=(low+high)/2;
            int mVal=mountainArr[mid];
            if (mVal>mountainArr[mid+1] && mVal>mountainArr[mid-1]){
                break;
            }
            else if (mVal<mountainArr[mid+1])
                low=mid+1;
            else
                high=mid-1;
        }
        low=0;
        high=mid;
        int tmp=mid;
        while (low<=high){
            mid=(low+high)/2;
            if (mountainArr[mid]==target)
                return mid;
            else if (mountainArr[mid]<target)
                low=mid+1;
            else
                high=mid-1;
        }
        low=tmp;
        high=n;
        while (low<=high){
            mid=(low+high)/2;
            if (mountainArr[mid]==target)
                return mid;
            else if (mountainArr[mid]<target)
                high=mid-1;
            else
                low=mid+1;
        }
        return index;
    }

    //first occurance of a element
    public static int firstOcc(int[]arr,int k){
        int n=arr.length;
        int left=0;
        int right=n-1;
        while (left<=right){
            int mid=(left+right)/2;
            if (arr[mid]==k && (mid==0 || arr[mid-1]<k))
                return mid;
            else if (arr[mid]<k)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }


    public static int binSearch(int[] arr, int k){
        int n=arr.length;
        int left=0;
        int right=n-1;
        while (left<=right){
            int mid=(left+right)/2;
            if (arr[mid]==k)
                return mid;
            else if (arr[mid]<k)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }
    public static int searchInsert(int[] nums, int target) {
        int mid=0;
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            mid=(low+high)/2;
            System.out.println("low: "+low+" mid: "+mid+" high: "+high);
            if (nums[mid]==target)
                return mid;
            else if (nums[mid]<target)
                low=mid+1;
            else
                high=mid-1;
        }
        if (nums[mid]<target)
            return mid+1;
        return mid;
    }
    public static int lastPos(int[] nums, int target){
        int n=nums.length-1;
        int low=0;
        int high=n;
        while (low<=high){
            int mid=(low+high)/2;
            if (nums[mid]<target)
                low=mid+1;
            else if (nums[mid]>target)
                high=mid-1;
            else {
                if (mid==n || nums[mid]!=nums[mid+1])
                    return mid;
                else
                    low=mid+1;
            }

        }
        return -1;
    }
    public static int firstPos(int[] nums, int target){
        int n=nums.length-1;
        int low=0;
        int high=n;
        while (low<=high){
            int mid=(low+high)/2;
            if (nums[mid]<target)
                low=mid+1;
            else if (nums[mid]>target)
                high=mid-1;
            else {
                if (mid==0 || nums[mid]!=nums[mid-1])
                    return mid;
                else
                    high=mid-1;
            }

        }
        return -1;
    }
    public static int lastOccurance(int[]arr, int key){
        int low=0,high=arr.length;
        while (low<=high){
            int mid=(low+high)/2;
            if (arr[mid]<key){
                low=mid+1;
            }
            else if (arr[mid]>key){
                high=mid-1;
            }
            else {
                if (mid==arr.length-1 || arr[mid]!=arr[mid+1])
                    return mid;
                else
                    low=mid+1;
            }
        }
        return -1;
    }
    public static int[] searchRange(int[] nums, int target) {
        int[] op=new int[2];
        op[0]=firstPos(nums,target);   //left index
        op[1]=lastPos(nums,target);   //right index

        return op;
    }
}
/*

 */