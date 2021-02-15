package CP_training;

public class feb13 {
    public static void main(String[] args) {
        int[] arr=new int[]{2,5,8,9,12,12,12,12,15,23,29,35};
//        System.out.println("23 found at: "+binSearch(arr,213));
        System.out.println("First index of 12: "+firstOcc(arr,12));
        System.out.println("Mountain peak: "+peakMount(new int[]{1,6,10,15,35,45,18,15,9}));

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
        while (min<=max){
            int mid=(min+max)/2;
            if (cntLess(arr,mid)>=k && cntLess(arr,mid-1)<k)
                return mid;
            else if (cntLess(arr,mid)<k)
                min=mid+1;
            else
                max=mid-1;
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
            else if (arr[mid]>arr[mid-1])
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
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
}
/*

 */