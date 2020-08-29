package GEEKS_FOR_GEEKS;

import java.util.Arrays;

public class searchingChapter
{
    static int search(int arr[], int n, int x)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==x)
                return i;
        }
        return -1;
    }

    static int searchInSorted(int arr[], int n, int x)
    {
        int mid=n/2;
        int high=n;
        int low=0;
        while(low<=high)
        {
            if(arr[mid]==x)
                return mid;
            else if(x<arr[mid])
            {
                high=mid-1;
            }
            else {
                low=mid+1;
            }
            mid=(high+low)/2;
        }
        return -1;

    }

    //public static Arrays obj = new Arrays();

    // Function to count number of ones in the binary array
    // n: size of array
    // To access i-th element of array, use obj.arr[i]    111000
    public static int countOnes(int n,int []arr)
    {
        int count=0;
        int low=0;
        int high=n-1;
        int mid;
        while(low<=high)
        {
            /*  mid = l + (h-l)/2;
                if(arr[mid] == 1 && (mid == 0 || arr[mid-1] != 1))
              return n-mid;
             * */
            mid=(low+high)/2;
            System.out.println("mid-val:"+mid+" low: "+low+" hi:"+high);
            try {
                if(arr[mid]==1 && arr[mid+1]==0)
                {   System.out.println("mid:"+mid);
                    return mid+1;
                }
                else if(arr[mid]==0)
                    high=mid-1;
                else low=mid+1;
            }
            catch (Exception e) {return mid+1;}
        }
        return 0;
    }
    public static int leftIndex(int []arr,int k)
    {
        int left=0;
        int right=arr.length-1;             //    1,1,1,1,1,0,0
        int mid;
        while (left<=right)
        {
            mid=(left+right)/2;
            System.out.println("mid-val:"+mid+" low: "+left+" hi:"+right);

            if(arr[mid]==k && ( mid==0 || arr[mid-1]!=k))
                return mid;
            else if(k<=arr[mid])
                right=mid-1;
            else left=mid+1;
            System.out.println("mid-val:"+mid+" low: "+left+" hi:"+right);
            System.out.println("--------------");
        }
        return 0;
    }
    public static int rightIndex(int []arr,int k)
    {
        int left=0;
        int right=arr.length-1;
        int mid;
        while (left<right)
        {
            mid=(left+right)/2;
            System.out.println("mid-val:"+mid+" low: "+left+" hi:"+right);
            if(arr[mid]==k && ( mid==arr.length-1 || arr[mid+1]!=k))
                return mid;
            else if(k>=arr[mid])
                left=mid+1;
            else right=mid-1;
        }
        return 0;
    }
    public static long floorSqrt(long x)
    {
        long sqrt=0;
        sqrt= (long) Math.floor(Math.sqrt(x));
        return sqrt;
    }
    public static int majorityElement(int a[], int size)
    {
        int maj=-1;
        for(int i=0;i<size;i++)
        {
            maj=0;
            for(int j=i;j<size;j++)
            {
                if(a[i]==a[j])
                    maj++;
                System.out.println("maj: "+maj+" a[i]: "+a[i]+" a[j]:"+a[j]);
            }
            System.out.println("---------------");
            if(maj>size/2)
                return a[i];
        }
        return -1;
    }

    public static int findMajority(int []arr,int n)
    {
        int res=0, count=1;
        for (int i=1;i<n;i++)
        {
            System.out.println("arr[res]: "+arr[res]+" arr[i]:"+arr[i]);
            System.out.println("res: "+res+"   c:"+count);
            if(arr[res]==arr[i])
                count++;            //  3,1,3,3,2
            else
                count--;
            if(count==0)        //check the candidate for mjority element
            {
                res=i;count=1;
                System.out.println("res c0: "+res);
            }
        }
        count=0;
        for(int i=0;i<n;i++)
        {
            if(arr[res]==arr[i])        //verify the candidate
                count++;
        }
        if(count<=n/2)
            res=-1;
        return res;
    }

    /*static int findFloor(long arr[], int left, int right, long x)
    {
        int mid=(left+right)/2;
        if(arr[mid]<=x && x>arr[mid-1])
            return mid;
        else if(arr[mid]>x)
            findFloor(arr,mid+1,right,x);
        else findFloor(arr,left,mid-1,x);
        return -1;
    }*/
    public static void repeated(int []arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            int count=0;
            for(int j=i;j<arr.length-1;j++)
            {
                if(arr[i]==arr[j] && arr[j+1]==arr[i])
                    count++;
            }
            if(count>=1)
                System.out.println(arr[i]+" "+count);
        }
    }

    public static void sortedOccurence( int []arr, int k)
    {
        int leftIndex=0,rightIndex=0;           //   2,3,3,3,4,4,4,4,4
        int left=0;
        int right=arr.length-1;             //    1,1,1,1,1,0,0
        int mid;
        while (left<=right)
        {
            mid=(left+right)/2;
            if(arr[mid]==k && ( mid==(arr.length-1) || arr[mid+1]!=k))
                {rightIndex= mid;break;}
            else if(k<arr[mid])
                right=mid-1;
            else left=mid+1;
        }
        System.out.println("rightIndex: "+rightIndex);
        left=0;
        right=arr.length-1;
        while (left<=right)
        {
            mid=(left+right)/2;
            if(arr[mid]==k && ( mid==0 || arr[mid-1]!=k))
                {leftIndex=mid;break;}
            else if(k<=arr[mid])
                right=mid-1;
            else left=mid+1;
        }
        System.out.println("leftIndex: "+leftIndex);
        System.out.println("No of occurance: "+(rightIndex-leftIndex+1));
    }
    public int peakElement(int[] a,int n)
    {
        int low=0;
        int high=n-1;
        if(a.length==2)
        {
            if(a[0]>a[1])
                return 0;
            else return 1;
        }
        while (low<=high)
        {
            int mid=(high+low)/2;
            if(mid==0 || mid==(n-1) || (a[mid]>a[mid-1] && a[mid]>a[mid+1]))
                return a[mid];
            if(a[mid]<a[mid-1])
                high=mid-1;
            else low=mid+1;
        }
        return 0;
    }
    static int findFloor(long arr[], int left, int right, long x)
    {
        int end=right;
        while(left<=right)
        {
            //System.out.println(5<=8);
            int mid=left+(right-left)/2;
            //corner case
            System.out.println("mid: "+mid+ "  strt: "+left+" rigt: "+right);
            if(mid==0 || mid==end)
            {
                if(arr[mid]!=x)
                    return -1;
                else return mid;
            }
            if(x==arr[mid])
                return mid;
            else if(x<arr[mid] && x>=arr[mid-1])
                return mid-1;
            else if(x<arr[mid])
                right=mid-1;
            else left=mid+1;
        }
        return -1;
    }
    public static int leftIndexIter(int []arr,int x)
    {
        int start=0;
        int end=arr.length-1;
        while (start<=end)
        {
            int mid=(start+end)/2;
            if(arr[mid]>x)
                end=mid-1;
            else if(arr[mid]<x)
                start=mid+1;
            else {
                if(mid==0 || arr[mid-1]!=arr[mid])
                    return mid;
                else
                    end=mid-1;
            }
        }
        return -1;
    }
    public static int sqFloor(int n)
    {
        int low=1;
        int high=n;
        int ans=0;
        while (low<=high)
        {
            int mid=(low+high)/2;
            int msq=mid*mid;
            if(msq==n) return mid;
            else if(msq>n)
                high=mid-1;
            else {
                low=mid+1;
                ans=mid;
            }
        }
        return ans;
    }
    public static int sortRotSearch(int []arr,int x)
    {
        int low=0;
        int high=arr.length-1;
        while (low<=high)
        {
            int mid=(high+low)/2;
            System.out.println("low: "+low+" high: "+high+" mid: "+mid+" val: "+arr[mid]);
            if(arr[mid]==x) return mid;
            else if(arr[low]<arr[mid])
            {
                if(x>=arr[low] && x<arr[mid])
                    high=mid-1;
                else
                    low=mid+1;
            }
            else {
                if(x>arr[mid] && x<=arr[high])
                    low=mid+1;
                else
                    high=mid-1;
            }
        }
        return -1;
    }
    public  static int peakEle(int []arr)
    {
        int low=0;
        int high=arr.length-1;
        while (low<=high)
        {
            int mid=(low+high)/2;
            //System.out.println("low: "+low+" high: "+high+" mid: "+mid+" val: "+arr[mid]);
            if((mid==0 || arr[mid-1]<=arr[mid]) &&
                    (mid==arr.length-1 || arr[mid+1]<=arr[mid]))
                return arr[mid];
            if(mid>0 && arr[mid-1]>=arr[mid])
                high=mid-1;
            else
                low=mid+1;
        }
        return -1;
    }
    public static boolean pairSum(int []arr,int sum)
    {
        int low=0;
        int high=arr.length-1;
        while(low<=high)
        {
            int curSum=arr[low]+arr[high];
            if(curSum==sum) {
                System.out.println(arr[low] + "+" + arr[high]+"="+sum);
                return true;
            }
            if(curSum>sum) high--;
            else low++;
        }
        return false;
    }
    public static boolean isPair(int []arr,int start, int end,int sum)
    {
        int low=start;
        int high=end;
        while (low<=high)
        {
            int s=arr[low]+arr[high];
            if(s==sum)
                return true;
            if(s>sum) high--;
            else low++;
        }
        return false;
    }

    public static boolean tripletSum(int[]arr,int sum)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(isPair(arr,i+1,arr.length-1,sum-arr[i]))
                return true;
        }
        return false;
    }
    public static double getMedian(int []a1,int []a2)
    {
        int n1=a1.length; int n2=a2.length;
        int start=0; int end=n1;
        while (start<=end)
        {
            int i1=(start+end)/2;
            int i2=(n1+n2+1)/2-i1;
            int min1=(i1==n1)?Integer.MAX_VALUE:a1[i1];
            int max1=(i1==0)?Integer.MIN_VALUE:a1[i1-1];

            int min2=(i2==n2)?Integer.MAX_VALUE:a2[i2];
            int max2=(i2==0)?Integer.MIN_VALUE:a2[i2-1];

            if(max1<=min2 && max2<=min1)
            {
                if((n1+n2)%2==0)
                    return ((double)(Math.max(max1,max2)+Math.min(min1,min2)))/2;
                else
                    return Math.max(max1,max2);
            }
            else if(max1>min2) end=i1-1;
            else start=i1+1;
        }
        return -1;
    }
    static long minNumber(int arr[], long low, long high)
    {
        while(low<=high)
        {
            long mid=low+(high-low)/2;
            if(arr[(int) mid]<arr[(int) (mid-1)]||high==low)
                return arr[(int) mid];
            else if(arr[(int) mid]>arr[(int) high])
                low=mid+1;
            else
                high=mid-1;
        }
        return arr[0];
    }
    static void twoRepeated(int arr[], int N)
    {
        int count=0;
        int [] op=new int[N];
        for(int i = 0; count<2 && i<arr.length ; i++)
        {
            if(op[arr[i]-1]==-1)
            {
                System.out.println(arr[i]);
                count++;
            }
            else op[arr[i]-1]=-1;
        }
    }
    static int maxStep(int A[], int N)
    {
        int maxH=0;
        int curH=0;
        for(int i=0;i<N-1;i++)
        {
            if(A[i+1]>A[i]) {
                curH++;
                maxH=Math.max(curH,maxH);
            }
            else{
                curH=0;
            }
        }
        return maxH;
    }
    static int maxWater(int arr[], int n)
    {
        int count=0;
        int l=0;
        int h=n-1;
        int max_val=0;
        while(l<=h)
        {
            count=Math.min(arr[l],arr[h])*(h-l-1);
            if(arr[l]>=arr[h])
                h--;
            else
                l++;
            max_val=Math.max(max_val,count);
        }
        return max_val;
    }
    public static Point findRepeating(Integer arr[], int n)
    {
        //You can return point as new Point(x,y)
        int l=0;
        int h=n-1;
        int c=0;
        int e=0;
        //System.out.println(arr[2].equals(arr[2 + 1]) || arr[2].equals(arr[2 - 1]));
        while(l<=h)
        {
            int m=(l+h)/2;
            System.out.println("low: "+l+" mid: "+m+" high: "+h);
            if(arr[m].equals(arr[m - 1]) || arr[m].equals(arr[m + 1]))
            {
                e=arr[m];
                c++;
                for(int i=m+1;i<n;i++)
                {
                    if(arr[i].equals(arr[m]))
                        c++;
                }
                for(int i=m-1;i>0;i--)
                {
                    if(arr[i].equals(arr[m]))
                        c++;
                }
                break;
            }
            else if(arr[m]<m+arr[0])
                h = m - 1;
            else
                l = m + 1;
        }
        return new Point(e,c);
    }
    public static int countOccurence(int[] arr, int n, int k)
    {
        Arrays.sort(arr);
        int count=1;
        int eleCOunt=0;
        for(int i=0;i<n-1;i++)
        {
            if(arr[i]==arr[i+1]) {
                count++;
                continue;
            }
            else if(count>(n/k)) {
                eleCOunt++;
                count=1;
            }
            else count=1;
        }
        if(count>(n/k))
            eleCOunt++;
        return eleCOunt;
    }
    static void subarraySum(int n, int s, int[] m)
    {
        int low=0;
        int high=0;
        int curSum=0;
        for(int i=0;i<n;i++)
        {
            curSum+=m[i];
            if(curSum>=s)
            {
                high=i;
                while (s<curSum && low<high)
                {
                    curSum-=m[low];
                    ++low;
                }
                if(curSum==s)
                {
                    System.out.println((low+1)+" "+(high+1));
                    break;
                }
            }
        }
        if(curSum!=s)
        System.out.println("-1");
    }
    public static boolean possiblePage(int []arr, int n, int mid, int stu)
    {
        System.out.println("fn call Mid: "+mid);
        int student=1;
        int curPage=0;
        for(int i=0;i<n;i++)
        {
            if(curPage+arr[i]>mid)
            {
                curPage=arr[i];
                student++;
                System.out.println("Cur page: "+curPage+" stu: "+stu);
                if(student>stu)
                    return false;
            }
            else curPage+=arr[i];
            System.out.println("Outer Val:"+arr[i]+" Cursum: "+curPage+" i: "+i);
        }
        return true;
    }
    public static int findPages(int[]a,int n,int m)
    {
        int low=0;
        int high=0;
        for(int i:a) {
            high += i;
            low=Math.max(low,i);
        }
        int ans=-1;
        int mid=0;
        while (low<=high)
        {
            mid=(low+high)/2;
            System.out.println("Low: "+low+" Mid: "+mid+" high: "+high);
            if(possiblePage(a,n,mid,m))
            {
                ans=mid;
                high=mid-1;
                System.out.println("Possible- high: "+high+" Ans: "+ans);
            }
            else {
                low=mid+1;
            }
        }
        if(ans!=-1)
            return ans;
        return ans;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String ...aa)
    {
        //System.out.println(countOnes(3,new int[]{1,1,1}));
       //System.out.println(leftIndex(new int[]{1,1,1,1,1,0,0},0));
        //System.out.println(rightIndex(new int[]{1,1,1,1,1,0,0},1));
        //System.out.println(majorityElement(new int[]{3,1,3,3,2},5));
        //System.out.println(findMajority(new int[]{3,1,3,3,2},5));
        //repeated(new int[]{3,1,3,3,2});
        //sortedOccurence(new int[]{5,6,7,10 ,20, 20, 20, 20, 20,20,25,25,25,30,50},25);
        //System.out.println(findFloor(new long[]{1 ,2 ,8, 10, 11, 12, 19},0,6,5));
//
//        System.out.println(leftIndexIter(new int[]{1,10,10,10,20,20,30,30},10));
//        System.out.println(sqFloor(10));
//        System.out.println(sortRotSearch(new int[]{10,20,40,60,4,5,8},4));
//        System.out.println(peakEle(new int[]{2,20,40,30,20,50,60}));
//        System.out.println("Pair sum: "+pairSum(new int[]{3,4,8,9,11,12,20,30},23));
//        System.out.println("Triplet sum: "+tripletSum(new int[]{2,3,4,8,9,20,40},32));
//        System.out.println("Median: "+getMedian(new int[]{1,2,3,4},new int[]{11,12,13,14}));
//        System.out.println("Sorted rot min: "+minNumber(new int[]{10,20,30,40,5,6,9},0,6));
//        System.out.println(findRepeating(new Integer[]{1,2,3,3,4},5));
//        System.out.println(countOccurence(new int[]{3, 1, 2, 2, 1, 2, 3, 3},8,4));
//        subarraySum(10,20,new int[]{1, 2,3,5,6,2,4,8,9,3});
        System.out.println(findPages(new int[]{12,34,67,90},4,2));
    }
    /*
    1. Count pair with a given sum
    2. Count triplet with given sum
    3. Find if there is a triplet a,b,c such that a^2+b^2=c^2(pythagorian triplet)
     */
}
