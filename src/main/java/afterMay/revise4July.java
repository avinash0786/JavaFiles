package afterMay;

public class revise4July {
    public static void main(String[] args) {

    }
    //minimum page allocation and aggressive cows
    //it uses the concept of: binary search on answer

    //we will set min and max, and take mid, if there are more no of student possible to
    //allocate the bool we will lower the bound, of no possible we will try to expand the no of books
    public static int minPageAllocation(int[] arr,int k){
        int sum=0,max=0;
        for (int i : arr) {
            sum+=i;
            max=Math.max(max,i);
        }
        int low=max,high=sum,res=0;
        while(low<=high){
            int mid=(high+low)/2;
            if (isFeasible(arr,k,mid)){
                res=mid;
                high=mid-1;
            }
            else
                low=mid+1;
        }
        return res;
    }
    public static boolean isFeasible(int[] arr,int k, int ans){
        int posStu=1,sum=0;
        for (int i = 0; i < arr.length; i++) {
            if (sum+arr[i]>ans){
                posStu++;
                sum=arr[i];
            }
            else
                sum+=arr[i];
        }
        return posStu<=k;
    }

    //aggressive cows, given positions of n cows, maximize the distance bt those cows
    //we will use the binary search on answer, min dist bt 2 cows can be 1, and max can be arr[n-1]-arr[0]
    public static int aggressiveCows(int[] arr,int cows){
        int n=arr.length;
        int low=1;
        int high=arr[n-1]-arr[0];
        while (low<=high){
            int mid=(low+high)/2;
            if (cowsPlace(arr,mid)>=cows && cowsPlace(arr,mid+1)<cows)
                return mid;
            else if (cowsPlace(arr,mid)>=cows)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }
    public static int cowsPlace(int[] arr,int dist){
        int cows=0;
        int prevCowPos=arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]-prevCowPos>=dist){
                cows++;
                prevCowPos=arr[i];
            }
        }
        return cows;
    }


}
