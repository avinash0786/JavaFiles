package GEEKS_FOR_GEEKS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class arrayChapter
{
    public static void insertAtEnd(int arr[],int sizeOfArray,int element)
    {
        arr[sizeOfArray-1]=element;
    }
    public static void insertAtIndex(int arr[],int sizeOfArray,int index,int element)
    {
        //Your code here, Geeks
        //System.out.println("size: "+arr.length);
        //System.out.println("index: "+index);
        for(int i:arr)
            System.out.print(i+" ");
        System.out.println();
        for(int i=arr.length-1;i>=index+1;i--)
        {
            //System.out.println("arr[i-1]  :"+arr[i-1]+" arr[i]::"+arr[i]);
            arr[i]=arr[i-1];
            //System.out.println("arr[i-1]  :"+arr[i-1]+" arr[i]::"+arr[i]);
        }
        arr[index]=element;
        System.out.println();
        for(int i:arr)
            System.out.print(i+" ");
    }

    public static int majorityWins(int arr[],int n,int x,int y)
    {
        int count_x=0;//counter to count frequency of x
        int count_y=0;//counter to count frequency of y

        for(int i:arr)
        {
            if(i==x)
                ++count_x;
            if(i==y)
                ++count_y;
        }
        if(count_x>count_y)
            return x;
        else if(count_y>count_x)
            return y;
        else
            return Math.min(x, y);

    }
    static void largestAndSecondLargest(int sizeOfArray, int[] arr){

        int max = 0;
        int max2 = -1;
        for(int i:arr)
        {
            if(i > max)
            {
                max2 = max;
                max = i;
            }

            else if(i > max2 && i != max)
                max2 = arr[i];
        }
        System.out.println(max + " " + max2);
    }
    public static int[] reverse(int a[])
    {
        int low=0;
        int high=a.length-1;
        System.out.println("Array: ");

        while (low<high)
        {
            int t=a[high];
            a[high]=a[low];
            a[low]=t;
            low++;
            high--;
        }
        System.out.println("reverse: ");
        for(int i:a)
            System.out.print(i+" ");
        return a;
    }
    public static int [] removeDuplicate(int []a)
    {

        return a;
    }
    public static int [] c_clk_Rotate(int []a)
    {
        int fst=a[0];
        for(int i=1;i<=a.length-1;i++)
        {
            a[i-1]=a[i];
        }
        a[a.length-1]=fst;
        /*System.out.println("counter rotate-1 : ");
        for(int i:a)
            System.out.print(i+" ");
        System.out.println();*/
        return a;
    }
    public static List<Integer> revArrayList(List<Integer> list)
    {
        int low=0;
        int high=list.size()-1;
        while (low<high)
        {
            int t=list.get(high);
            //a[high]=a[low];
            list.set(high,list.get(low));
            //a[low]=t;
            list.set(low,t);
            low++;
            high--;
        }
        return list;
    }
    public static ArrayList<Integer> reverseInGroups(ArrayList<Integer> mv, int n, int k) {
        // add your code here
        ArrayList<Integer> op = new ArrayList<>();
        System.out.println(mv);
        int left=n%k;
        //System.out.println("lft: "+left);
        for(int i=0;i<=n-left-k;i+=3)
        {
            op.addAll(revArrayList(mv.subList(i,i+3)));
            //System.out.println(op);
            //System.out.println("i:"+i);
            //System.out.println("i+3:"+(i+3));
            //System.out.println("--------------------------");
        }
        op.addAll(revArrayList(mv.subList(n-left+1,n)));
        //System.out.println(mv);
        //System.out.println(revArrayList( mv.subList(2,6)));
        //mv.addAll(revArrayList( mv.subList(2,6)));
        System.out.println(mv);
        return op;
    }
    static void rotateArr(int arr[], int d, int n)
    {
        int []temp=arr;
        for(int i=0;i<n;i++)
        {
            temp=c_clk_Rotate(temp);
        }
        for (int i:temp)
            System.out.print(i+" ");

    }

    public int median(int A[],int N)
    {
        int indx=0;
        Arrays.sort(A);
        if(N%2==0)
        {
            return (A[N/2]+A[(N/2)-1])/2;
        }
        else indx=(N/2);
        return A[indx];
    }

    public int mean(int A[],int N)
    {
        double sum=0;
        for(int i:A)
            sum+=i;
        return (int) (sum/N);
    }

    public static int minAdjDiff(int arr[], int n)
    {
        int min=9999;
        int temp;
        for (int i=0;i<n-1;i++)
        {
            temp=Math.abs(arr[i]-arr[i+1]);
            if(temp<=min)
                min=temp;
        }
        temp=Math.abs(arr[n-1]-arr[0]);
        if(temp<min)
            min=temp;
        return min;
    }
    public static int max3(int a)
    {
        if(a>=max)
        {
            max=a;
            return a;
        }
        return max;
    }
    public static int min3(int a)
    {
        if(a<=min)
        {
            min=a;
            return a;
        }
        return min;
    }
    public static int min=999;
    public static int max=0;
    public static int maxOccured(int L[], int R[], int n, int maxx) {
        int maxoc=0;
        //System.out.println("n: "+n+" maxx: "+maxx);
        int minRange=0;
        int maxRange=0;
        for(int i=0;i<L.length;i++)
        {
            minRange=min3(L[i]);
            maxRange=max3(R[i]);
        }
        int eleCount=0;
        //System.out.println("max: "+maxRange+" min: "+minRange);
        for(int i=minRange;i<maxRange;i++)
        {
            int num=minRange;
            int count=0;
            //System.out.println("num: "+num);
            for(int j=0;j<L.length;j++)
            {
                if(num>=L[j] && num<=R[j])
                    count++;
                //System.out.println("count: "+count);
            }
            if(count>eleCount)
            {
                eleCount=count;
                maxoc=num;
            }
            //System.out.println("maxoc: "+maxoc);
            count=0;
            minRange++;
            //System.out.println("-----------------------");
        }
        return maxoc;
    }

    public static void convertToWave(int arr[], int n)
    {
        if(n%2==0)
        {
            for(int i=0;i<n;i+=2)
            {
                int t=arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=t;
            }
        }
        else
        {
            for(int i=0;i<n-1;i++)
            {
                int t=arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=t;
            }
        }
        for(int i:arr)
            System.out.print(i+" ");
    }
    public static void  printFrequency(int arr[], int n)
    {
        int[] stor =new int[n+1];          //2 ,3, 2, 3, 5
        for(int i=0;i<n;i++)
        {
            int num=arr[i];
            ///System.out.println("num: "+num+" i: "+i);
            stor[num]+=1;
        }
        for(int i=1;i<=n;i++)
            System.out.print(stor[i]+" ");
    }
    public static int equilibriumPoint(long arr[], int n)
    {
        long leftsum=0;
        long rightsum=0;
        for(long i:arr)
            rightsum+=i;
        long fr=rightsum;
        //System.out.println("right sum : "+rightsum);
        if(n==1)
            return 1;
        for(int i=1;i<n-1;i++)
        {   System.out.println("lft: "+leftsum+ " rght: "+rightsum+  "  i: "+i+" a-val:"+arr[i]);
            leftsum=leftsum+arr[i-1];   //increasing left sum
            rightsum=fr-(leftsum+arr[i]);   //decreasing right sum
            System.out.println("lft: "+leftsum+ " rght: "+rightsum+  "  i: "+i);
            System.out.println("-----------------------");
            if(leftsum==rightsum)
                return i+1;
        }
        return -1;
    }
    static ArrayList<Integer> leaders(int arr[], int n)
    {
        ArrayList<Integer> out=new ArrayList<>();
        out.add(arr[n-1]);
        for(int i=n-2;i>-1;i--)
        {
            int lar=out.get(0);
            System.out.println("arr-val: "+arr[i]+" Largest val  : "+lar);
            if(arr[i]>=lar)
            {
                out.add(0,arr[i]);
                System.out.println("adding: "+arr[i]+" new out: "+out);
            }
            System.out.println("IN= Start arraylist: "+out+" size: "+out.size()+" Largest val  : "+out.get(0));
            System.out.println("---------------------------------------");
        }
        return out;
    }
    static ArrayList<Integer> leaderDif(int arr[], int n)
    {
        ArrayList<Integer> out=new ArrayList<>();
        for(int i=0;i<n-1;i++)
        {
            boolean flag=false;
            for (int j=i+1;j<n;j++)
            {
                if(arr[i]<arr[j])
                {
                    flag=true;
                    break;
                }
            }
            if(!flag)
                out.add(arr[i]);
        }
        out.add(arr[n-1]);
        return out;
    }
    static int missingNumber(int arr[], int size)
    {
        int min=Integer.MAX_VALUE;
        int max=0;
        for(int i=0;i<size;i++)
        {
            if(arr[i]>0 && arr[i]<min)
                min=arr[i];
            if(arr[i]>0 && arr[i]>max)
                max=arr[i];
        }
        System.out.println("min: "+min+" max: "+max);
        int tem[]=new int[max+1];
        System.out.println("tem si: "+tem.length);
        for(int i=0,j=0;i<size && j<max; i++)
        {   for(int k:tem)
            System.out.print(k+" ");
            System.out.println();
            if(arr[i]>0)
            {
                System.out.println("val: "+arr[i]+"  i: "+i);
                tem[arr[i]-1]=1;
                j++;
            }
        }
        for(int i:tem)
            System.out.print(i+" ");
        for(int i=0;i<tem.length;i++)
        {
            if(tem[i]==0) {
                return (i+1);
            }
        }
        //         36 20 44 -5 8 2 -42 36 48 -45 -37 -6 -20 -29 -3 -43 8 -50 28 -21 -43 8 3 29 -43 22 25 -40 -48 -46 -42 -10
        //          -24 -48 36 35 -44 -4 -27 -46 1 -13 -49 -17 -40 -2 42
        return max+1;
    }
    public static void rearrange(int arr[], int n)
    {
        ArrayList<Integer> op=new ArrayList<>();
        int max=n-1;
        int min=0;
        //System.out.println(arr.length);
        if(n%2==0)
        {
            for(int i=min,j=max;i<j;i++,j--)
            {
                op.add(arr[j]);
                op.add(arr[i]);
            }
        }
        else {
            for(int i=min,j=max;i!=j;i++,j--)
            {
                op.add(arr[j]);
                op.add(arr[i]);
            }
            op.add(arr[n/2]);
        }
        for(int i=0;i<n;i++)
            arr[i]=op.get(i);
    }
    static void arrange(long arr[], int n)
    {
        ArrayList<Integer> op=new ArrayList<>();
        for (int i=0;i<n;i++)
        {
            int index= (int) arr[i];
            int ins= (int) arr[index];
            op.add(ins);
        }
        System.out.println(op);
        for(int i=0;i<n;i++)
            arr[i]=op.get(i);
    }
    static int maxIndexDiffVAl(int arr[], int n)
    {
        int res=arr[1]-arr[0];
        int minval=arr[0];
        for(int i=1;i<n;i++)
        {
            res=Math.max(res,arr[i]-minval);
            minval=Math.min(minval,arr[i]);
        }
        return res;
    }

    static int trappingWaterNIAVE(int[] arr, int n)
    {
        int res=0;
        for(int i=0;i<n-1;i++)
        {
            int lmax=arr[i];
            for(int j=0;j<i;i++)
                Math.max(lmax,arr[j]);
            int rmax=arr[i];
            for(int j=i+1;j<n;i++)
                Math.max(rmax,arr[j]);
            res=res+(Math.min(lmax,rmax)-arr[i]);
        }
        return res;
    }
    static int trappingWater(int[] arr, int n)
    {
        int res=0;
        int [] lmax=new int[n];
        int [] rmax=new int [n];
        lmax[0]=arr[0];
        for(int i=1;i<n;i++)
        {
            lmax[i]=Math.max(arr[i],lmax[i-1]);
        }
        rmax[n-1]=arr[n-1];
        for(int i=n-2;i>=0;i--)
        {
            rmax[i]=Math.max(arr[i],rmax[i+1]);
        }
        System.out.print("larray::");
        for(int i:lmax)
            System.out.print(i+" ");
        System.out.println();
        System.out.print("R--array::");
        for(int i:rmax)
            System.out.print(i+" ");
        for(int i=1;i<n-1;i++)
            res=res+(Math.min(lmax[i],rmax[i])-arr[i]);
        System.out.println();
        return res;
    }
    static int maxIndexDiff(int[] arr, int n)
    {
        int res=0;
        int c=0;
        for(int i=1;i<=n-2;i++)
        {
            for(int j=n-1;j>=i+1;j--)
            {
                if(arr[i]<=arr[j])
                {
                    if(c==n/3)
                        return res;
                    if(j-1<res)
                    c++;
                    res=Math.max(res,j-i);
                }
            }
        }
        return res;
    }

    static void stockBuySell(int price[], int n)
    {
        if(n==1)
            return;
        int count=0;        // count of solution pairs
        ArrayList<Integer> sol=new ArrayList<>();
        int i=0;
        while (i < n-1)
        {
            // Find Local Minima. Note that the limit is (n-2) as we are
            // comparing present element to the next element.
            while ((i < n-1) && (price[i+1] <= price[i]))
                i++;
            // If we reached the end, break as no further solution possible
            if (i == n-1)
                break;
            // Store the index of minima
            sol.add(i++);
            // Find Local Maxima.  Note that the limit is (n-1) as we are
            // comparing to previous element
            while ((i < n) && (price[i] >= price[i-1]))
                i++;
            // Store the index of maxima
            sol.add(i-1);
            // Increment count of buy/sell pairs
            count++;
            System.out.println(sol);
        }
        if(count==0)
        {
            System.out.println("No Profit");return;
        }
        for(int j=0;j<sol.size();j+=2)
        {
                System.out.print("("+sol.get(j)+" "+sol.get(j+1)+") "  );
        }



        //////////////////////////
        /*int profit=0;
        int buy=0;
        for(int i=1;i<n;i++)
        {
            if(price[i-1]<price[i])
            {
                buy=i-1;
                break;
            }
        }
        ArrayList<Integer> stat=new ArrayList<>();
        stat.add(buy);
        int sell=0;
        int t=0;
        for(int i=buy+1;i<n;i++)
        {
            if(price[i]>=price[i-1])
            {
                //System.out.println("running for: "+i);
                t++;
                profit+=(price[i]-price[i-1]);
            }
            else
            {
                sell=buy+t;
                stat.add(sell);
                buy=i;
                stat.add(buy);
                t=0;
            }
        }
        sell=buy+t;
        stat.add(sell);                         //  (0 3) (5 6) (9 10) (11 13) (14 15)
        if(profit==0)
        {
            System.out.println("No Profit");return;
        }
        for(int i=0;i<stat.size();i+=2)
        {
            if(!(stat.get(i)==stat.get(i+1)))
                System.out.print("("+stat.get(i)+" "+stat.get(i+1)+") "  );
        }
        //System.out.println(stat);
        //System.out.println(profit);*/
    }
    static int consecOnes(int arr[])
    {
        int res=0;
        int temp=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==1)
                temp++;
            else
            {
                res=Math.max(temp,res);
                temp=0;
            }
        }
        return res;
    }

    static int maxSubarraySum(int arr[], int n)
    {
        int sum=arr[0];
        int maxEnding=arr[0];
        for(int i=1;i<n;i++)
        {
            maxEnding=Math.max(maxEnding+arr[i],arr[i]);
            sum=Math.max(sum,maxEnding);
        }
        return sum;
    }
    public static int maxEvenOdd(int arr[], int n)
    {
        int len=1;
        int tem=1;
        for(int i=1;i<n;i++) {
            if(arr[i]%2==0 && arr[i-1]%2!=0 || arr[i]%2!=0 && arr[i-1]%2==0 ) {
                tem++;
                len=Math.max(tem,len);
            }
            else
                tem=1;
        }
        return len;
    }
    static int circularSubarraySum(int a[], int n)
    {
        int maxnormal=kadane(a,n);
        if(maxnormal<0)
            return maxnormal;
        int sum=0;
        for(int i:a)
            sum+=i;
        return Integer.max(kadane(a,n), sum+kadane(reverseKadane(a,n),n));
    }
    static int kadane(int []a,int n)
    {
        int res=a[0];
        int maxNoramal=a[0];
        for(int i=1;i<n;i++)
        {
            maxNoramal=Math.max(a[i],a[i]+maxNoramal);
            res=Math.max(maxNoramal,res);
        }
        return res;
    }
    static int[] reverseKadane(int []a, int n)
    {
        for(int i=0;i<n;i++)
            a[i]*=(-1);
        return a;
    }

    public static boolean checkRotatedAndSorted(int arr[], int n){

        int inc=0;
        if(arr[0]>=arr[n-1])
        {
            inc=1;
            for(int i=1;i<n;i++)
            {
                if(arr[i]<arr[i-1])
                    inc++;
            }
        }
        else {
            return false;
        }
        if(inc==2) return true;
        return false;

    }
/*
* After the previous Hint, the question only remains to check for any other irregularity.

In case of Increasingly Sorted,

Check if array is increasing upto Max. Element
Check if array is increasing again after Min Element
Check if Last Element is smaller than the first element
In case of Decreasingly Sorted,

Check if array is decreasing upto Min. Element
Check if array is decreasing again after Max Element
Check if Last Element is larger than the first element
If all these conditions meet, the array is sorted and rotated*/
    public static int slidingMaxSum(int [] arr, int k)
    {
        int maxSum=0;
        int curSum=0;
        for(int i=0;i<k;i++)
            curSum+=arr[i];
        maxSum=curSum;
        for (int i : arr) {
            System.out.print(i+" ");
        }
        for(int i=k;i<arr.length;i++)
        {
            curSum+=(arr[i]-arr[i-k]);
            maxSum=Math.max(curSum,maxSum);
        }
        return maxSum;
    }
    public static int [] prefixSum(int []arr)
    {
        for(int i=1;i<arr.length;i++)
            arr[i]+=arr[i-1];
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        return arr;
    }
    public static int queryPrefix(int []arr,int s,int e)
    {
        int []sum=prefixSum(arr);
        if(s!=0)
            return sum[e]-sum[s-1];
        return sum[e];
    }
    public static int equilibrium(int [] arr)
    {
        int[] pre=prefixSum(arr);
        for(int i=1;i<arr.length-1;i++)
        {
            if(pre[i-1]==(pre[arr.length-1]-pre[i]))
                return i;
        }
        return -1;
    }
    public static boolean subarraySum(int []arr, int n, int sum)
    {
        int cur_sum=arr[0];
        int s=0;
        int e=0;
        for(e=1;e<n;e++)
        {
            while (cur_sum>sum && s<n-1)          //clean previous window
                {cur_sum-=arr[s];s++;}
            if(cur_sum==sum)
            {
                System.out.println("start: "+s+" end: "+(e-1));
                return true;
            }
            if(sum<cur_sum)
                cur_sum+=arr[e];
        }
        System.out.println("start: "+s+" end: "+e);
        return cur_sum==sum;
    }
    public static void nBonacci(int n, int k )   //n n-bonacci  ,k  no if elements
    {           // 0 0 1
        ArrayList<Integer> op=new ArrayList<>();
        for(int i=0;i<n-1;i++)
            op.add(0);
        op.add(1);
        //System.out.println(op);
        for(int i=n;i<k;i++)
        {
            int ins=0;
            for(int j=i-1;j>=(i-n);j--)
                ins+=op.get(j);
            op.add(ins);
        }
        System.out.println(op);
    }
    public static void n_bonacci(int n,int k)
    {
        ArrayList<Integer> op=new ArrayList<>();
        for(int i=0;i<n-1;i++)
            op.add(0);
        op.add(1);
        int finSum=1;
        for(int i=n;i<k+n;i++)
        {
            op.add(finSum);
            finSum+=finSum;
            finSum-=op.get(i-n);
        }
        System.out.println(op);
    }

    public static void minGroupFlip(int []binarr)
    {
        for(int i=1;i<binarr.length;i++)
        {
            if(binarr[i]!=binarr[i-1])
            {
                if(binarr[i]!=binarr[0])
                    System.out.println("From: "+i);
                else
                    System.out.println("to: "+(i-1));
            }
        }
        if(binarr[binarr.length-1]!=binarr[0])
            System.out.println("to: "+(binarr.length-1));
    }
    public static int maxOcRange(int []a,int []b)  //maximum range value ==20
    {
        int[] arr =new int[20];
        for(int i=0;i<a.length;i++)
        {
            arr[a[i]]++;
            arr[b[i]+1]--;
        }
        for (int i : arr) {
            System.out.print(i+" ");
        }
        int max=0;
        int index=0;
        for(int i=1;i<arr.length;i++)
        {
            arr[i]+=arr[i-1];
            if(max<arr[i])
            {
                max=arr[i];
                index=i;
            }
        }
        return index;
    }
    public static boolean checkRotatedAndSortedNEW(int arr[], int n)
    {
        int inc=0;
        if(arr[0]>=arr[n-1])
        {
            inc=1;
            for(int i=1;i<n;i++)
            {
                if(arr[i]<arr[i-1])
                    inc++;
            }
        }
        else {
            return false;
        }
        if(inc==2) return true;
        return false;
    }
    ///////////////////////////--- MAIN RUNNING----///////////////////////////////////////////////////////////////////////
    public static void main(String ...ss)
    {
        //insertAtIndex(new int[]{2,5,6,7,8,9,0,0},8,2,43);
        //largestAndSecondLargest(8,new int[]{2,5,6,7,8,9,0,0});
        //reverse(new int[]{2,5,6,7,8,9,25});
        //c_clk_Rotate(new int[]{2,5,6,7,8,9,25});  //1 time counter clock rotate
        ArrayList<Integer> l1=new ArrayList<>();        //linked list
        l1.add(12);
        l1.add(89);
        l1.add(76);
        l1.add(56);
        l1.add(23);
        l1.add(56);
        l1.add(55);
        l1.add(33);
//        reverseInGroups(l1,8,3);
//        rotateArr(new int[]{2, 4, 6 ,8 ,10, 12, 14, 16 ,18 ,20},10,3);
        //System.out.println(minAdjDiff(new int[]{8 ,-8 ,9 ,-9, 10, -11, 12},7));
//        System.out.println(maxOccured(new int[]{1 ,5 ,9 ,13 ,21},new int[]{15 ,8 ,12 ,20 ,30},5,30));
//        convertToWave(new int[]{2, 4 ,7 ,8, 9, 10},6);
//        printFrequency(new int[]{2 ,3, 2, 3, 5},5);
//        System.out.println(equilibriumPoint(new long[]{1 ,3 ,5 ,2 ,2},5));
//        System.out.println(leaders(new int[]{16, 17, 4, 3, 5, 2},6));
//        System.out.println(missingNumber(new int[]{1, 2,0},3));   //Wrong
        //rearrange(new int[]{10 ,20, 30, 40, 50, 60, 70, 80, 90, 100, 110},11);
        //arrange(new long[]{10 ,20, 30, 40, 50, 60, 70, 80, 90, 100, 110},11);
//        System.out.println(maxIndexDiffVAl(new int[]{1,3,4,5,9,12},6));
//        System.out.println(trappingWater(new int[]{3,0,0,2,0,4},6));
//        stockBuySell(new int[]{71, 94, 70, 74, 55, 51, 33, 37, },8);
        //      (0 3) (5 6) (9 10) (11 13) (14 15)
//        System.out.println(maxSubarraySum(new int[]{-5,1,-2,3,-1,2,-2},6));
//        System.out.println(consecOnes(new int[]{1,0,1,1,1,1,0,0,1,1}));
//        System.out.println(circularSubarraySum(new int []{8 ,-8 ,9 ,-9, 10, -11, 12},7));
//        System.out.println(slidingMaxSum(new int []{ 1,8,30,-5,20,7},3));
//        prefixSum(new int[]{10,20,10,5,15});
        //System.out.println(checkRotatedAndSorted(new int[]{71 ,75, 75, 77, 89, 89, 92, 3, 11, 13, 24, 28, 33, 58, 61, 66},16));
        //System.out.println(subarraySum(new int[]{ 1,4,20,3,10,5},6,33));
//        nBonacci(4,10);
        minGroupFlip(new int[]{1,1,0,0,1,1,1,0,0,1});
//        n_bonacci(3,5);
//        System.out.println(queryPrefix(new int[]{5,6,8,11,2,3},2,5));
//        System.out.println(equilibrium(new int[]{3,4,8,-9,20,6}));
//        System.out.println(maxOcRange(new int[]{1,2,3},new int[]{3,5,7}));
    }
}
