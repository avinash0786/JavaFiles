package afterMay;

import dsa450.TreeNode;

import java.util.*;

public class binarySearchPrac {
    public static void main(String[] args) {
//        System.out.println("Starting index in sorted array: "+startIndex(new int[]{5,7,7,8,8,8,8,10},7));
//        System.out.println("Search rotated sorted dup: "+searchRotatedDuplicate(new int[]{7,9,12,13,14,13,2,2,3,3,4},3));
        System.out.println("maximum envelopes: "+maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
    }
    //find first and last position in sorted array
    public static int startIndex(int[] arr,int key){
        int low=0;
        int high=arr.length-1;
        int mid=(low+high)/2;
        while (low<=high){
            mid=(low+high)/2;
            if (arr[mid]<key)
                low=mid+1;
            else if (arr[mid]>key)
                high=mid-1;
            else {
                if (mid==0 || arr[mid]!=arr[mid-1])
                    break;
                else
                    high=mid-1;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("mid: "+mid);
        return mid;
    }
    public static boolean searchRotatedDuplicate(int[] arr, int target){
        int low=0;
        int high=arr.length-1;
        System.out.println(Arrays.toString(arr));
        while (low<=high){
            int mid=(low+high)/2;
            System.out.println("mid: "+mid+" low: "+low+" high: "+high);
            if (arr[mid]==target)
                return true;
            if (arr[low]<arr[mid]){
                if (target>=arr[low] && target<arr[mid])
                    high=mid;
                else
                    low=mid+1;
            }
            else if (arr[mid]<arr[low]){
                if (target>arr[mid] && target<=arr[high])
                    low=mid+1;
                else
                    high=mid;
            }
            else
                low++;
        }
        return false;
    }
    //count no of nice subarray: a subarray is nice if no of odd ele is==k in that subarray
    //use sliding window, shrink and expand
    public static int numberOfSubarrays(int[] nums, int k) {
        int left=0,right=-1,evenLeft=0,evenRight=0,countK=0,n=nums.length;
        int nice=0;
        while (right<n-1){
            right++;
            if (nums[right]%2==1) //count the no of odd element and expand the window
                countK++;
            //if cur subarray is a nice subarray, use this to calc adjacent nice subarray
            if (countK==k){
                evenLeft=1;evenRight=1;
                //count even element in left part
                while (left<=right && nums[left]%2==0){
                    evenLeft++;
                    left++;
                }
                //count even element in right part
                while (right<n-1 && nums[right+1]%2==0){
                    evenRight++;
                    right++;
                }
                //we count only even element count on both right and left if the odd count==k subarray
                //the ending of the odd count ==k subarray will  be odd only
                //each of the right can form subarray with left as odd count==k with the right subarray
                nice+=(evenLeft*evenRight);
                left++;
                countK--;
            }
        }
        return nice;
    }
    //maxiumum length subarray having all unique elements , return sum of elements of all in subarray
    public static int maximumUniqueSubarray(int[] nums) {
        int n=nums.length;
        int[] prev=new int[100000];
        int[] prefSum=new int[n];prefSum[0]=nums[0];
        for (int i = 1; i < n; i++) {
            prefSum[i]=nums[i]+prefSum[i-1];
        }
        System.out.println(Arrays.toString(prefSum));
        Arrays.fill(prev,-1);
        int res=0;
        int start=0;
        for (int i = 0; i < n; i++) {
            start=Math.max(start,prev[nums[i]]+1);
            int maxEnd=prefSum[i]-prefSum[start];
            System.out.println("i: "+i+" start:"+start+" sum: "+maxEnd);
            res=Math.max(res,maxEnd);
            prev[nums[i]]=i;
        }
        return res;
    }
    //find no of pairs whose sum is equal to the power of 2
    public static int countPairs(int[] deliciousness) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int count=0;
        for (int i = 0; i < deliciousness.length; i++) {
            int pow=1;
            for (int j = 0; j <=21; j++) {
                if (map.containsKey(pow-deliciousness[i])){
                    count+=map.get(pow-deliciousness[i]);
                    count=count%1000000007;
                }
                pow=pow*2;
            }
            map.put(deliciousness[i],map.getOrDefault(deliciousness[i],0)+1);
        }
        return count;
    }
    public static int maxDistance(int[] nums1, int[] nums2) {
        int ans=0;
        int i=0,j=0;
        while (i<nums1.length && j<nums2.length){
            if (nums1[i]>nums2[j])
                i++;
            else {
                ans=Math.max(ans,j-i);
                j++;
            }
        }
        return ans;
    }
    //counts[i] is the number of smaller elements to the right of nums[i].
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res=new ArrayList<>();
        int n=nums.length;
        TreeNode root=new TreeNode(nums[n-1]);
        res.add(0);
        for (int i = n-2; i >=0; i--) {
            int count=insertBstUtil(root,nums[i]);
            res.add(0,count);
        }
        return res;
    }
    private static int insertBstUtil(TreeNode root,int data){
        int smaller=0;
        TreeNode temp=new TreeNode(data);
        boolean isLinked=false;
        while (!isLinked){
            if (temp.val<=root.val){
                root.count++;   //incrementing the count of smaller element as, this temp is also smaller
                if (root.left==null){
                    root.left=temp;
                    isLinked=true;
                }
                else
                    root=root.left;
            }
            else {
                smaller+=root.count;
                if (root.right==null){
                    root.right=temp;
                    isLinked=true;
                }
                else
                    root=root.right;
            }
        }
        return smaller;
    }
    //find the max lenght of the russian dolls we can make i.e put ont inside other
    //envelopes = [[5,4],[6,4],[6,7],[2,3]]
    //max length of russian dolls 3 ([2,3] => [5,4] => [6,7]).
    //we can put on env inside other if both the width and height of one envelope
    // are greater than the other envelope's width and height.
    public static int maxEnvelopes(int[][] envelopes) { //[width,height]
        int count=1;
        int n=envelopes.length;
        System.out.println(Arrays.deepToString(envelopes));
        //sort by width and find the longest increasing subsequence based on the height value
        //sort by asc of width and desc of height
        Arrays.sort(envelopes, Comparator.comparingInt(a->a[0]));
        int[] lis=new int[n];
        Arrays.fill(lis,1);
        System.out.println(Arrays.deepToString(envelopes));
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0]>envelopes[j][0] && envelopes[i][1]>envelopes[j][1] && lis[i]<lis[j]+1)
                    lis[i]=lis[j]+1;
                count=Math.max(count,lis[i]);
            }
        }
        return count;
    }
    //a staircase consists of k rows where the ith row has exactly i
    //find no of complete rows we can build
    //1 2 3 4 5 6 7 8 , sum of values n(n+1)/2
    //so find the max val og k such that k(k+1)2<=n
    public static int arrangeCoins(int n) {
        long l=0,r=n;
        while (l<=r){
            long mid=l+(r-l)/2;
            if (n>=(mid*(mid+1)/2))
                l=mid+1;
            else
                r=mid-1;
        }
        return (int) l-1;
    }
    public static int arrangeCoinsopt(int n) {
        return (int) (Math.sqrt(2*(long)n+0.25)-0.5);
    }
    //find k closest element to x
    //|a - x| < |b - x|, or
    //|a - x| == |b - x| and a < b
    //use a priority que with each element represented by cal and its gap -> gap=|x-arr[i]|
    //we cannot use sorting as O(nlogn) we can do in O(n) using priority queue
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> kClosest=new ArrayList<>();
        int n=arr.length;
        int[] gap=new int[n];
        for (int i = 0; i < n; i++)
            gap[i]=Math.abs(x-arr[i]);
        //pair (x,y) -> (val,gap)
        PriorityQueue<closePair> pq=new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            if (pq.size()<k)
                pq.add(new closePair(arr[i],gap[i]));
            else {
                if (gap[i]<pq.peek().gap){
                    pq.poll();
                    pq.add(new closePair(arr[i],gap[i]));
                }
            }
        }
        while (!pq.isEmpty())
            kClosest.add(pq.poll().val);
        Collections.sort(kClosest);
        return kClosest;
    }
}

class closePair implements Comparable<closePair>{
    int val;
    int gap;
    closePair(int v,int g){
        this.val=v;
        this.gap=g;
    }

    public int compareTo( closePair o) {
        if (this.gap==o.gap)
            return this.val-o.val;
        else
            return this.gap-o.gap;
    }
}