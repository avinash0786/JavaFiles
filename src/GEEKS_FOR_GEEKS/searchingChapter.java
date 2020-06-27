package GEEKS_FOR_GEEKS;

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
        System.out.println(findFloor(new long[]{1 ,2 ,8, 10, 11, 12, 19},0,6,5));
    }
}
