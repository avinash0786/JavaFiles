package CP_training;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

class LargerNumberComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        String order1 = a + b;
        String order2 = b + a;
        return order2.compareTo(order1);
    }
}
public class arrSorSerPractice {
    public static void main(String[] args) {
//        genBalParam("",3,3);
//        System.out.println("Left index: "+leftIndex(new int[]{3,3,4,4,5,7,7,7,7,8,8,9,9,10,12},7));
//        System.out.println("Right index: "+rightIndex(new int[]{3,3,4,4,5,7,7,7,7,8,8,9,9,10,12},7));
//        System.out.println(Arrays.toString(searchRange(new int[]{1}, 1)));
////        System.out.println(findExtra(new int[]{2,4,6,8,10,12},new int[]{2,4,6,8,10,},6));
//        System.out.println("max profit: "+stockBuyMaxProfit(new int[]{1,5,3,8,12}));
        minGroupFlip(new int[]{0,0,1,1,0,0,1,1,1});
//        System.out.println(isSubSum(new int[]{1,9,5,4,2,8,3},3));
//        System.out.println("Missing first: "+firstMissingPositive(new int[]{3,4,-1,1}));
//        System.out.println("Aggr cows: "+aggrCowsBruteF(new int[]{1,2,4,8,9},3,5));
//        System.out.println("Aggr cows effic: "+aggeCows(new int[]{0,3,4,7,9,10},6,3));
//
//        System.out.println("23".compareTo("32"));
//        System.out.println(largestNumber(new int[]{3,30,34,5,9}));
//        removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3});
//        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    }
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //Comparing according to [[a,b],[c,d]]   a,c of to arrays
//        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
    public static int removeDuplicates(int[] nums) {
        int dups=1;
        int cnt=1;
        for (int i = 1; i < nums.length; i++) {
            if (cnt<=1 && nums[i]==nums[dups-1]) {
                nums[dups++] = nums[i];
                cnt++;
            }
            if (nums[i]!=nums[dups-1]) {
                nums[dups++] = nums[i];
                cnt=1;
            }
        }
        return dups;
    }
    public static String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }
        System.out.println(Arrays.toString(asStrs));

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());
        System.out.println(Arrays.toString(asStrs));
        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        StringBuilder largestNumberStr = new StringBuilder();
        for (String numAsStr : asStrs) {
            largestNumberStr.append(numAsStr);
        }
        String op="";
        for (String asStr : asStrs) {
            op+=asStr;
        }

        return op;
    }
    public static int chocDistMinDif(int[] arr, int n, int m){
        Arrays.sort(arr);
        if (m>n)
            return -1;
        int res=arr[m-1]-arr[0];
        for (int i = 1; i+m-1<n; i++) {
            res=Math.max(res,(arr[i+m-1]-arr[i]));
        }
        return res;
    }
    public static int findRepeating(int[] arr, int n){
        int slow=arr[0]+1;
        int fast=arr[0]+1;
        do {
            slow=arr[slow]+1;
            fast=arr[arr[fast]+1]+1;
        }while (slow!=fast);
        slow=0;
        while (slow!=fast){
            fast=arr[fast]+1;
            slow=arr[slow]+1;
        }
        return slow-1;
    }

    public static int cowsPlace(int[] arr, int dist){
        int cow=1;
        int lastCow=arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]-lastCow>=dist){
                cow++;
                lastCow=arr[i];
            }
        }
        return cow;
    }
    //  BINARY SEARCH ON ANSWER
    public static int  aggeCows(int[] arr, int n, int c){
        int low=1;
        int high=arr[n-1]-arr[0];
        while (low<=high){
            int mid=(low+high)/2;
            if (cowsPlace(arr,mid)>=c && cowsPlace(arr,mid+1)<c)
                return mid;
            else if (cowsPlace(arr,mid)>=c)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }
    public static int aggrCowsBruteF(int[] arr, int c, int n){
        int low=arr[0];
        int high=arr[arr.length-1];
        int max=high-low;
        int lastCow;
        int cnt=-1;
        for (int dist = low; dist <max ; dist++) {
            int cow=1;
            lastCow=arr[0];
            for (int i = 1; i < n; i++) {   //we count how many cows we can place with distance dist
                if (arr[i]-lastCow>=dist){
                    cow++;
                    lastCow=arr[i];
                }
            }
            if (cow>=c) // no no of cows we can place is greater then th no of cows we update the min dist
                cnt=dist;
            else
                break;  //if no of cows is less than the cows , now we dont need to count out last dist is answer
        }
        return cnt;
    }

    public static int firstMissingPositive(int[] nums) {
        int n=nums.length;
        for (int i = 0; i < n; i++) {
            int corPos=nums[i]-1;   // Eg: num 3 will go to index 2
            while (nums[i]>=1 && nums[i]<=n && nums[i]!=nums[corPos]){
                int tmp=nums[i];
                nums[i]=nums[corPos];
                nums[corPos]=tmp;
                corPos=nums[i]-1;   // update the corPos to place the new number to its correct pos
                //like 1 to index 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if ((i+1)!=nums[i])
                return i+1;
        }
        return n+1;
    }
    public static boolean isSubSum(int[] arr, int sum){
        int n=arr.length;
        int curSum=arr[0];
        int start=0;
        for (int i = 1; i <=n ; i++) {
            while (curSum>sum && start<i-1){
                curSum-=arr[start];
                start++;
            }
            if (curSum==sum) {
                System.out.println("Sum found bt: "+start+" to: "+(i-1));
                return true;
            }
            if (i<n)
                curSum+=arr[i];
        }
        return false;
    }
    public static void minGroupFlip(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]!=arr[i-1]){
                if (arr[i]!=arr[0])
                    System.out.print("From: "+i+" to: ");
                else
                    System.out.println(i-1);
            }
        }
        if (arr[arr.length-1]!=arr[0])
            System.out.println((arr.length-1));
    }
    public static int maxCircularSum(int[] arr){
        int res=arr[0];
        int n=arr.length;
        for (int i = 0; i <n; i++) {
            int curSum=arr[i];
            int curMax=arr[i];
            for (int j = 1; j <n; j++) {
                int index=(i+j)%n;
                curSum+=arr[index];
                curMax=Math.max(curMax,curSum);
            }
            res=Math.max(res,curMax);
        }
        return res;
    }

    public static int stockBuyMaxProfit(int[] arr){
        int profit=0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]>arr[i-1])
                profit+=arr[i];
        }
        return profit;
    }
    public static int findExtra(int a[], int b[], int n) {
        int index=n-1;
        int low=0;
        int high=n-2;
        while (low<=high){
            int mid=(low+high)/2;
            System.out.println("Low: "+low+" high: "+high+" mid: "+mid);
            if (a[mid]==b[mid])
                low=mid+1;
            else {
                index=mid;
                high=mid-1;
            }
        }
        return index;
    }

    public static int[] searchRange(int[] arr, int key){
        if (arr.length==0){
            return new int[]{-1,-1};
        }
        int[] op=new int[]{-1,-1};
        int low=0;
        int high=arr.length-1;
        int mid=0;
        while (low<=high){
            mid=(low+high)/2;
            if (arr[mid]>key)
                high=mid-1;
            else if (arr[mid]<key)
                low=mid+1;
            else {
                if (mid==0 || arr[mid]!=arr[mid-1])
                    break;
                else
                    high=mid-1;
            }
        }
        if (arr[mid]!=key){
            return new int[]{-1,-1};
        }
        op[0]=mid;

        while (mid<arr.length &&arr[mid]==key)
            mid++;
        op[1]=mid-1;
        return op;
    }
    public static int leftIndex(int[] arr, int key){
        System.out.println(Arrays.toString(arr));
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (arr[mid]>key)
                high=mid-1;
            else if (arr[mid]<key)
                low=mid+1;
            else {
                if (mid==0 || arr[mid]!=arr[mid-1])
                    return mid;
                else
                    high=mid-1;
            }
        }
        return -1;
    }
    public static int rightIndex(int[] arr, int key){
//        System.out.println(Arrays.toString(arr));
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if (arr[mid]>key)
                high=mid-1;
            else if (arr[mid]<key)
                low=mid+1;
            else {
                if (mid==arr.length-1 || arr[mid]!=arr[mid+1])
                    return mid;
                else
                    low=mid+1;
            }
        }
        return -1;
    }

    //Balanced parenthesis generate
    public static void genBalParam(String str,int open,int close){
        System.out.println("Cur: "+str+" open: "+open+" close: "+close);
        if (open==0 && close==0){
            System.out.println(str);
            return;
        }
        if (open>0)
            genBalParam(str.concat("("), open-1, close);
        if (close>open)
            genBalParam(str.concat(")"), open, close-1);
    }
}
