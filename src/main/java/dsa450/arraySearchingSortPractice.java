package dsa450;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class arraySearchingSortPractice {
    public static void main(String[] args) {
        removeDulicates(new int[]{1,2,2,2,3,3,3,4,4,4,4,5,5,6,6});
        System.out.println("Equilibrium point: "+equilibriumPoint(new int[]{1,2,3,4,5,5,10}));
        System.out.println("Leader elements: ");
        leaderElement(new int[]{7,10,4,3,6,5,2});
        rearrangeString("aaabb");
//        apple a1=new apple() {
//            @Override
//            public void color() {
//                System.out.println("Apple color is red");
//            }
//        };
//        a1.color();
    }
    //remove duplicate characters from array
    public static void removeDulicates(int[] arr){
        int n=arr.length;
        int dups=1;
        System.out.println(Arrays.toString(arr));

        for (int i = 1; i < n; i++) {
            if (arr[i]!=arr[dups-1]){
                arr[dups]=arr[i];
                dups++;
            }
        }
        System.out.println("No of distinct elements: "+dups);
        System.out.println(Arrays.toString(arr));
    }
    public static int equilibriumPoint(int[] arr){
        int sum=0;
        for (int i : arr) {
            sum+=i;
        }
        int lSum=0;
        int rSum=sum;
        int index=0;
        while (lSum!=rSum && index<arr.length){
            lSum+=arr[index];
            rSum=rSum-arr[index];
            index++;
        }
        return index;
    }

    //leader in an array, element is leader if no element is greater than that on right of it
    public static void  leaderElement(int[] arr){
        int n=arr.length;
        ArrayList<Integer> op=new ArrayList<>();
        op.add(arr[n-1]);
        int curLeader=arr[n-1];
        for (int i = n-1; i >=0 ; i--) {
            if (arr[i]>curLeader){
                op.add(arr[i]);
                curLeader=arr[i];
            }
        }
        System.out.println(op);
    }
    //maximum water we can trap b/t different building
    public static int trappingRainWater(int[] arr){
        int n=arr.length;
        int res=0;
        for (int i = 1; i < n-1; i++) {
            int lMax=arr[i];
            for (int j = 0; j < i; j++)
                lMax=Math.max(lMax,arr[j]);
            int rMax=arr[i];
            for (int j = i; j < n; j++)
                rMax=Math.max(rMax,arr[j]);
            res=res+(Math.min(lMax,res)-arr[i]);
        }
        return res;
    }
    //optimization is to pre compute left max and right max
    //then calculate the max water trap
    public static int trappingRainWater02(int[] arr){
        int n=arr.length;
        int res=0;
        int[] lMax=new int[n];
        int[] rMax=new int[n];
        lMax[0]=arr[0];     //calculating left maximum
        for (int j = 1; j < n; j++)
            lMax[j]=Math.max(lMax[j-1],arr[j]);

        rMax[n-1]=arr[n-1]; //calculating right maximum
        for (int j = n-2; j >=0; j--)
            rMax[j]=Math.max(lMax[j+1],arr[j]);
        //calculating maxx water trapped using pre computer results
        for (int i = 1; i < n-1; i++) {
            res=res+(Math.min(lMax[i],rMax[i])-arr[i]);
        }
        return res;
    }
    public static int maxCircularSubArraySum(int[] arr){    //O(n^2)
        int n=arr.length;
        int res=arr[0];
        for (int i = 0; i < n; i++) {
            int curMax=arr[i];
            int curSum=arr[i];
            for (int j = 0; j < n; j++) {
                int index=(i+j)%n;
                curSum+=arr[index];
                curMax=Math.max(curMax,curSum);
            }
            res=Math.max(res,curMax);
        }
        return res;
    }
    /*
    MAXIMUM SUM SUBARRAY CAN BE FOUND BY FINDING THE MAX SUM SUB ARRAY AND THE
    SUM-MINIMUM SUM SUBARRAY VALUE AND FINDING MAX OF BOTH OF THEM

    TO FIND MIN SUM SUB ARRAY WE JUST NEED TO REPLACE MAX TO MIN IN NORMAL KADENS ALO
    OR WE CAN DO, WE JUST INVERT EACH ELEMENT BY MULTILYING BY -1 AND FIND NORMAL KADENS WILL GIVE
    THE MIN SUM SUB ARRAY
     */

    //majority element: element which is having count greater than n/2
// moore voting algo: if there are pair max a element can appear = n/2 not more than n/2
    // so element not in pair is our majority


    public static int findRepeating(int[] arr){
        int n=arr.length;
        int slow=arr[0]+1;
        int fast=arr[0]+1;
        do {
            slow=arr[slow]+1;
            fast=arr[arr[fast]+1]+1;
        }
        while (slow!=fast);
        slow=arr[0]+1;
        while (slow!=fast){
            slow=arr[slow]+1;
            fast=arr[fast]+1;
        }
        return slow-1;
    }
    //minimum page allocation
    public static int minPageAllocNaive(int[] pages,int n,int stu){
        if (stu==1)
            return sumUtil(pages,0,n-1);
        if (n==1)
            return pages[0];
        int res=Integer.MAX_VALUE;
        //books this student is going to read= sum(i,n-1)
        //books other student are going to read are rec find( 0,i)
        for (int i = 1; i <n; i++) {
            int curStu=sumUtil(pages,i,n-1);
            int restStu=minPageAllocNaive(pages,i,stu-1);
            int maxPageboth=Math.max(curStu,restStu);
            res=Math.min(res,maxPageboth);
        }
        return res;
    }
    public static int sumUtil(int[] arr,int s,int e){
        int sum=0;
        for (int i = s; i <=e; i++) {
            sum+=arr[i];
        }
        return sum;
    }
    public static int minPageAlocBinSerch(int[] pages,int stu){
        int n=pages.length;
        int sum=0;
        int max=pages[0];
        for (int page : pages) {
            sum+=page;
            max=Math.max(page,max);
        }
        int low=max;
        int high=sum;
        int res=0;
        while (low<=high){
            int mid=(low+high)/2;
            if (feasiblePage(pages,stu,mid)){   //if feasible check more less mid value
                res=mid;
                high=mid-1;
            }
            else
                low=mid+1;      //if not feasible check more greater value then cur mid
        }
        return res;
    }
    public static boolean feasiblePage(int[] pages,int stu,int val){
        int n=pages.length;
        int res=1;
        int curSum=0;
        for (int i = 0; i < n; i++) {   // each student can read max val pages
            if (curSum+pages[i]>val){   // if no of pages cannot be read by cur student allocate to next student
                res++;
                curSum=pages[i];
            }
            else
                curSum+=pages[i];
        }
        return res<=stu;
    }
    //minumum page allocation DP implementations

    public static int minPageAlocDP(int[] pages,int students){      //O(n^3*k)
        int[][] DP_PAGE=new int[students+1][pages.length+1];
        //if there is only one student he need to read all the pages
        for (int i = 1; i <= pages.length; i++) {       //O(n^2)
            DP_PAGE[1][i]=sumUtil(pages,0,i-1);
        }
        //if there is only one page then only that page can be read
        for (int i = 1; i < students; i++) {        //O(k)
            DP_PAGE[i][1]=pages[0];
        }
        int res=Integer.MAX_VALUE;
        for (int i = 2; i <= students; i++) {       //O(n^3*k)
            for (int j = 2; j <= pages.length; j++) {
                int curMax=Integer.MAX_VALUE;
                for (int k = 1; k <j ; k++) {
                    curMax=Math.max(DP_PAGE[i-1][j],sumUtil(pages,k,j-1));
                }
                DP_PAGE[i][j]=curMax;
            }
        }
        return DP_PAGE[students][pages.length];
    }

    //merge overlapping intervals
    public static void mergeOverLappingInterv(interval[] arr){
        int res=0;
        int n=arr.length;
        for (int i = 1; i <n ; i++) {
            if (arr[res].end>=arr[i].start){
                arr[res].end=Math.max(arr[res].end,arr[i].end);
                arr[res].start=Math.min(arr[res].start,arr[i].start);
            }
            else {
                res++;
                arr[res]=arr[i];
            }
        }
        for (int i=0;i<=res;i++) {
            System.out.println("["+arr[i].start+","+arr[i].end+"]");
        }
    }
    static int minimumCostOfBreaking(Integer X[], Integer Y[], int m, int n) {
        int res = 0;

        // sort the horizontal cost in reverse order
        Arrays.sort(X, Collections.reverseOrder());

        // sort the vertical cost in reverse order
        Arrays.sort(Y, Collections.reverseOrder());

        // initialize current width as 1
        int hzntl = 1, vert = 1;

        // loop untill one or both
        // cost array are processed
        int i = 0, j = 0;
        while (i < m && j < n)
        {
            if (X[i] > Y[j])
            {
                res += X[i] * vert;

                // increase current horizontal
                // part count by 1
                hzntl++;
                i++;
            }
            else
            {
                res += Y[j] * hzntl;

                // increase current vertical
                // part count by 1
                vert++;
                j++;
            }
        }

        // loop for horizontal array,
        // if remains
        int total = 0;
        while (i < m)
            total += X[i++];
        res += total * vert;

        // loop for vertical array,
        // if remains
        total = 0;
        while (j < n)
            total += Y[j++];
        res += total * hzntl;

        return res;
    }
    static void Print3Smallest(int array[], int n)
    {
        int firstmin = Integer.MAX_VALUE;
        int secmin = Integer.MAX_VALUE;
        int thirdmin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
        {
                /* Check if current element is less than
                firstmin, then update first, second and
                third */
            if (array[i] < firstmin)
            {
                thirdmin = secmin;
                secmin = firstmin;
                firstmin = array[i];
            }

                /* Check if current element is less than
                secmin then update second and third */
            else if (array[i] < secmin)
            {
                thirdmin = secmin;
                secmin = array[i];
            }

                /* Check if current element is less than
                then update third */
            else if (array[i] < thirdmin)
                thirdmin = array[i];
        }

        System.out.println("First min = " + firstmin );
        System.out.println("Second min = " + secmin );
        System.out.println("Third min = " + thirdmin );
    }
    //Rearrange characters in a string such that no two adjacent are same
    public static void rearrangeString(String str){
        char[] arr=str.toCharArray();
        int l=arr.length;
        int[] count=new int[26];
        for (int i = 0; i < l; i++) {
            count[arr[i]-'a']++;
        }
        PriorityQueue<key> pq=new PriorityQueue<>();
        for (int i = 0; i < l; i++) {
            int index=arr[i]-'a';
            if (count[index]>0){
                pq.add(new key(arr[i],count[index]));
                count[index]=-1;
            }
        }
        System.out.println(pq);
        key prev=new key('#',-1);
        int i=0;
        while (!pq.isEmpty()){
            key temp=pq.poll();
            arr[i++]=temp.val;
            if (prev.freq>0)
                pq.add(prev);
            temp.freq--;
            prev=temp;
        }
        System.out.println(new String(arr));
    }
}
class key implements Comparable<key>{
    char val;
    int freq;
    key(char c,int f){
        this.val=c;
        this.freq=f;
    }

    @Override
    public String toString() {
        return "{"+ val + ", " + freq + '}';
    }

    public int compareTo(key o) {
        if (this.freq>o.freq)
            return 1;
        else if (this.freq<o.freq)
            return -1;
        else
            return 0;
    }
}
interface  apple{
    void  color();
}
class interval implements Comparable<interval>
{
    int start;
    int end;
    public interval(int s,int e){
        this.start=s;
        this.end=e;
    }
    public int compareTo( interval o) {
        return o.start-this.start;
    }
}