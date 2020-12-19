import java.util.Arrays;

public class CodeNation {
    public int[] twoSum(int[] nums, int target) {
        int []ans=new int[2];
        for(int i=0;i<nums.length-1;i++){
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    ans[0]=i;
                    ans[1]=j;
                    return ans;
                }
            }
        }
        return ans;
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 2.32d;
    }
    public static int macOccuredRange(int []A,int []B){
        int n=0;
        for(int i:A){
            n=Math.max(i,n);
        }
        for(int i:B){
            n=Math.max(i,n);
        }
        int []arr=new int[n+1];
        for(int i:A){
            arr[i]++;
        }
        for(int i:B){
            arr[i]--;
        }
        //prefix sum
        int mxOc=0;
        for (int i = 1; i <= n; i++) {
            arr[i]+=arr[i-1];
            mxOc=Math.max(mxOc,arr[i]);
        }
        return mxOc;
    }
    public static int equilibriumPt(int []arr){
        int n=arr.length;
        if(n==1) return 1;
        int ltSum=0;
        int rtSum=0;
        for(int i:arr){
            rtSum+=i;
        }
        for(int i=0;i<n;i++){
            ltSum+=arr[i];
            rtSum-=arr[i];
            if(ltSum==rtSum){
                return i+1;
            }
        }
        return -1;
    }
    public static int maxSubArraySum(int []arr){
        int sum=arr[0];
        int curSum=arr[0];
        for (int i = 0; i < arr.length; i++) {
            curSum=Math.max(curSum+arr[i],arr[i]);
            sum=Math.max(curSum,sum);
        }
        return sum;
    }
    public static void mergeRange(int[]A, int []B){
        int maxIndex=A[0];
        for (int i : A) {
            maxIndex=Math.max(i,maxIndex);
        }
        for (int i : B) {
            maxIndex=Math.max(i,maxIndex);
        }
        int []arr=new int[maxIndex+2];
        for (int i = 0; i < A.length; i++) {
            arr[A[i]]++;
        }
        for (int i = 0; i < B.length; i++) {
            arr[B[i]]--;
        }
        //prefix sum
        for (int i = 1; i < arr.length; i++) {
            arr[i]+=arr[i-1];
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();

        int iter=1;
        while (iter<arr.length-1){
            if(arr[iter]>=1){
                System.out.println("Start: "+iter);{
                    do{
                        iter++;
                    }
                    while (arr[iter]>=1);
                }
            }
            if(iter>0 && arr[iter]<=0){
                System.out.println("End: "+iter);
                do{
                    iter++;
                }
                while (arr[iter]<=0 && iter<arr.length-1);
            }
        }
    }
    public static void insertionSort(int []arr){
        long a=System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++) {
            int key=arr[i];
            int j=i-1;
            while (j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
        System.out.println("Insertion Sorted in: "+(a-System.currentTimeMillis())+" milSec");
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static void intersectionArr(int []A,int []B){
        int n=A.length;
        int m=B.length;
        int i=0;
        int j=0;
        while (i<n && j< m){
            if(i>0 && A[i]==A[i-1]){
                i++;
                continue;
            }
            if(A[i]>B[j]){
                j++;
            }
            else if(A[i]<B[j]){
                i++;
            }
            else {
                System.out.println(A[i]);
                i++;
                j++;
            }
        }
    }
    public static void swap(int[]arr,int from, int to){
        int t=arr[to];
        arr[to]=arr[from];
        arr[from]=t;
    }
    public static void lomutoPartition(int[] arr,int l,int h,int p){//partition value in end
        if(arr[p]!=arr[h]){
            System.out.println("Adujsting pivot...");
            swap(arr,p,h);
        }
        System.out.println("Pivot: "+arr[h]);
        int i=l-1;
        for (int j = 0; j < h; j++) {
            if(arr[j]<=arr[h]){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,h);
        for (int i1 : arr) {
            System.out.print(i1+" ");
        }
    }
    public static int hoarePartiotion(int[]arr,int l,int h,int p){
        if(arr[p]!=arr[l]){
            System.out.println("Adujsting pivot...");
            swap(arr,p,l);
        }
        int pivot=arr[l];
        System.out.println("Pivot: "+arr[l]);
        int i=l-1;
        int j=h+1;
        while (true){
            do{
                i++;
            }while (arr[i]<pivot);
            do{
                j--;
            }while (arr[j]>pivot);
            if(i>=j) return j;
            swap(arr,i,j);
        }
    }
    public static void segregatePosNeg(int[]arr){
        int i=-1;
        int j=arr.length;
        while (true){
            do{
                i++;
            }while (arr[i]<0);
            do{
                j--;
            }while (arr[j]>=0);
            if (i>=j){
                for (int i1 : arr) {
                    System.out.print(i1+" ");
                }
                return;
            }
            swap(arr,i,j);
        }
    }
    public static void threeWayPartition(int[]arr,int pivot){
        int low=0;
        int mid=0;
        int high=arr.length-1;
        while (mid<=high){
            if(arr[mid]<pivot){
                swap(arr,low,mid);
                low++;
                mid++;
            }
            else if(arr[mid]==pivot)
                mid++;
            else {
                swap(arr,mid,high);
                high--;
            }
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public static int maxGuest(int[]ariv,int []dept){
        Arrays.sort(ariv);
        Arrays.sort(dept);
        int res=1;
        int cur=1;
        int i=1;
        int j=0;
        while (i<ariv.length && j<dept.length){
            if(ariv[i]<dept[j]){
                i++;
                cur++;
            }
            else {
                cur--;
                j++;
            }
            res =Math.max(res,cur);
        }
        return res;
    }


    public static void main(String[] args) {
//        System.out.println(macOccuredRange(new int[]{1, 4, 9}, new int[]{7,8,12}));
//        System.out.println(equilibriumPt(new int[]{1 ,3 ,5 ,2 ,2}));
//        System.out.println(maxSubArraySum(new int[]{-5,1,-2,3,-1,2,-2}));
//        mergeRange(new int[]{1,2,6},new int[]{3,5,8});
//        insertionSort(new int[]{5,1,8,3,4,9,6,12,7});
//        intersectionArr(new int[]{2,4,5,6,8,9},new int[]{1,4,8,9,12,15});
//        lomutoPartition(new int[]{2,3,8,4,6,12,5,7,8,9,13,15,10},0,12,12);
//        System.out.println(hoarePartiotion(new int[]{10,3,8,4,6,12,5,7,8,9,13,15,2},0,12,0));
//        segregatePosNeg(new int[]{2,-4,1,-5,-7,8,-12});
//        threeWayPartition(new int[]{1,7,3,5,4,12,9,6,7,11},7);
        System.out.println(maxGuest(new int[]{600,740,950,1130},new int[]{1200,1350,1400,1300}));

    }
}
