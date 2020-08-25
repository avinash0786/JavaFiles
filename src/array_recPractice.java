public class array_recPractice
{
    public static void stringSubset(String str,String cur,int index)
    {
        if(index==str.length()) {
            System.out.println(cur + " ");
            return;
        }
        stringSubset(str,cur,index+1);
        stringSubset(str,cur.concat(String.valueOf(str.charAt(index))),index+1);
    }
    public static int josh(int n,int k)
    {
        if(n==1) return 1;
        return (josh(n - 1, k) + k-1) % n + 1;
    }
    static int counter=2;
    public static boolean isLucky(int n)
    {
        if(n>counter) return true;
        if(n%counter==0) return false;
        int nexPos=n;
        nexPos-=n/counter;
        counter++;
        return isLucky(nexPos);
    }

    static void possibleWords(int a[], int N)
    {
        newPossibleWords(a,"",0);
    }
    public static String[] words ={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    static void newPossibleWords(int a[], String next, int index)   //N - no of elements
    {
        if(next.length()==a.length)
        {
            System.out.print(next+" ");
            return;
        }
        int num=a[index];
        String wrds=words[num];
        for(int i=0;i<wrds.length();i++)
        {
            newPossibleWords(a,next+wrds.charAt(i),index+1);
        }
    }
    public static void arrayRotate(int []arr,int s,int e)
    {
        if(s>=e) return;
        int temp=arr[s];
        arr[s]=arr[e];
        arr[e]=temp;
        arrayRotate(arr,s+1,e-1);
    }
    public static int maxConsSumWindowSlid(int []arr,int k)
    {
        int maxSum=0;
        for(int i=0;i<k;i++)
        {
            maxSum+=arr[i];
        }
        int curSum=maxSum;
        for(int i=k;i<arr.length;i++)
        {
            curSum+=(arr[i]-arr[i-k]);
            maxSum=Math.max(maxSum,curSum);
        }
        return maxSum;
    }
    public static int [] prefixSum(int []arr)
    {
        for(int i=01;i<arr.length;i++)
        {
            arr[i]=arr[i]+arr[i-1];
        }
        return arr;
    }
    /*
    Sample Problem: Consider an array of size N with all initial values as 0. Perform given 'm' add operations from index 'a' to 'b'
    and evaluate highest element in array. An add operation adds 100 to all elements from index a to b (both inclusive).
     */
    public static int maxSumPrefix(int[] a,int[] b,int add)
    {
        int []arr=new int[20];
        for(int i=0;i<a.length;i++)
        {
            arr[a[i]]+=add;
            arr[b[i]+1]-=add;
        }
        int max=0;
        for(int i:prefixSum(arr))
            max=Math.max(max,i);
        return max;
    }
    public static int maxOc(int []a,int []b)
    {
        int []arr=new int[50];
        for(int i=0;i<a.length;i++)
        {
            arr[a[i]]++;
            arr[b[i]+1]--;
        }
        int index=0;
        int max=0;
        for(int i=1;i<arr.length;i++)
        {
            arr[i]+=arr[i-1];
            if(arr[i]>max)
            {
                max=arr[i];
                index=i;
            }
        }
        return index;
    }
    public static int [] merge2Sort(int []a,int []b)
    {
        int []arr=new int [a.length+b.length];
        int i=0;int j=0;int k=0;
        while(i<a.length && j<b.length)
        {
            if(a[i]<b[j])
                arr[k++]=a[i++];
            else
                arr[k++]=b[j++];
        }
        while (i<a.length)
            arr[k++]=a[i++];
        while (j<b.length)
            arr[k++]=b[j++];
        return arr;
    }
    public static void leader(int []a)
    {
        int max=Integer.MIN_VALUE;
        for(int i=a.length-1;i>=0;i--)
        {
            if(a[i]>max)
                //System.out.println("Leader: "+a[i]);
            max=Math.max(a[i],max);
        }
    }
    public static int buySell_naive(int[]price, int start,int end)
    {
        if(start>=end) return 0;
        int profit=0;
        for(int i=start;i<end;i++)
        {
            for(int j=i;i<=end;j++)
            {
                if(price[i]>price[j])
                {
                    int curProfit=price[j]-price[i]+buySell_naive(price,start, i-1)+buySell_naive(price,j+1,end);
                    profit=Math.max(curProfit,profit);
                }
            }
        }
        return profit;
    }

    public static int maxProfit(int []price)
    {
        int profit=0;
        for(int i=1;i<price.length;i++)
        {
            if(price[i]>price[i-1])
                profit+=(price[i]-price[i-1]);
        }
        return profit;
    }
    public static int trappingWater(int[] arr)
    {
        int res=0;
        int []lmax=new int[arr.length];
        int []rmax=new int[arr.length];
        lmax[0]=arr[0];
        for(int i=1;i<arr.length;i++)
            lmax[i]=Math.max(arr[i],lmax[i-1]);

        rmax[arr.length-1]=arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--)
            rmax[i]=Math.max(arr[i],rmax[i+1]);
        for(int i=1;i<arr.length-1;i++)
            res+=(Math.min(rmax[i],lmax[i])-arr[i]);
        return res;
    }
    public static void main(String ...a)
    {
        //stringSubset("abc","",0);
        System.out.println(josh(5,3));
        System.out.println(isLucky(19));
        System.out.println(maxConsSumWindowSlid(new int[]{100,200,300,400,20,10,30},2));
        System.out.println(maxSumPrefix(new int[]{2,1,1},new int[]{4,3,2},100));
        System.out.println(maxOc(new int[]{2,1,1},new int[]{4,3,2}));
        for(int i:merge2Sort(new int[]{1,4,7,8,9},new int[]{2,3,5,7,9,10,11}))
            System.out.print(i+" ");
        leader(new int[]{1,15,6,13,1,6,9,4,2,3});
        System.out.println();
        System.out.println(maxProfit(new int[]{1,5,3,8,12}));
        System.out.println(trappingWater(new int[]{5,0,6,2,3}));
    }
}
