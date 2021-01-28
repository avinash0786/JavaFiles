public class practiceSearchingLearn {
    public static void main(String[] args) {
//        System.out.println("BIn search: "+binSearchIter(new int[]{10,20,30,50,56,89},56));
//        System.out.println("BIn search: "+binSearchRec(new int[]{10,20,30,50,56,89},10,0,6));
//        System.out.println("Square root floor: "+sqRootFloor(14));
        System.out.println("Sorted rotated : "+sortedRotatedSearch(new int[]{10,20,30,40,60,5,8,9},8));
    }
    public static int findRepLinkedList(int[] arr){
        int slow=arr[0]+1;
        int fast=arr[0]+1;
        do {
            slow=arr[slow]+1;
            fast=arr[arr[fast]]+1;
        }while (slow!=fast);
        slow=arr[0]+1;
        while (slow!=fast){
            fast=arr[fast]+1;
            slow=arr[slow]+1;
        }
        return slow-1;
    }

    /*
    All the element in the left half should be smaller than all the
    element in the right half;
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1=nums1.length;
        int n2=nums2.length;
        int begin=0;
        int end=n1;
        while (begin<=end){
            int i1=(begin+end)/2;
            int i2=((n1+n2+1)/2)-i1;

            int min1=(i1==n1)?Integer.MAX_VALUE:nums1[i1];
            int max1=(i1==0)?Integer.MIN_VALUE:nums1[i1-1];

            int min2=(i2==n2)?Integer.MAX_VALUE:nums2[i2];
            int max2=(i2==0)?Integer.MIN_VALUE:nums2[i2-1];

            if (max1<=min2 && max2<=min1){
                if ((n1+n2)%2==0)
                    return ((double) Math.max(max1,max2)+Math.min(min1,min2))/2;
                else
                    return  Math.max(max1,max2);
            }
            else if (max1>min2)
                end=i1-1;
            else
                begin=i1+1;
        }
        return 0;
    }
    public static int sortedRotatedSearch(int[] arr, int key){
        int low=0,high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (arr[mid]==key)
                return mid;
            if (arr[low]<arr[mid]){
                if (key>=arr[low] && key<arr[mid])
                    high=mid-1;
                else
                    low=mid+1;
            }
            else {
                if (key>arr[mid] && key<=arr[high])
                    low=mid+1;
                else
                    high=mid-1;
            }
        }
        return -1;
    }
    public static int firstOccurance(int[]arr, int key){
        int low=0,high=arr.length;
        while (low<=high){
            int mid=(low+high)/2;
            if (arr[mid]>key)
                high=mid-1;
            else if (arr[mid]<key)
                low=mid+1;
            else {
                if (mid==0 || arr[mid-1]!=arr[mid])
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
    public static int sqRootFloor(int x){
        int low=1, high=x,ans=-1;
        while (low<=high){
            int mid=(low+high)/2;
            int mdSq=mid*mid;
            if (mdSq==x)
                return mid;
            else if (mdSq>x)
                high=mid-1;
            else {
                low=mid+1;
                ans=mid;
            }
        }
        return ans;
    }

    public static int binSearchRec(int[]arr, int key,int low, int high){
        if (low>high)
            return -1;
        int mid=(low+high)/2;
        if (arr[mid]==key)
            return mid;
        else if (arr[mid]>key)
            return binSearchRec(arr,key,low,mid-1);
        else
            return binSearchRec(arr,key,mid+1,high);
    }

    public static int binSearchIter(int[] arr, int key){
        int low=0,high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (arr[mid]==key)
                return mid;
            else if (arr[mid]>key)
                high=mid-1;
            else
                low=mid+1;
        }
        return -1;
    }
}

