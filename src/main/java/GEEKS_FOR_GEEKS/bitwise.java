package GEEKS_FOR_GEEKS;

public class bitwise
{
    public static boolean oddEven(int n)
    {
        if((n&1)==0)
            return true;
        return false;
    }

    public static void binaryVal(int n) {
        int b = 0, c = 0, d;
        int num=n;
        while (n != 0) {
            d = n % 2;
            b = b + d * (int) Math.pow(10, c++);
            n /= 2;
        } //converting decimal to binary
        System.out.println(num+" in - Binary: " + b);
    }

    public static boolean iBitSet(int n,int i)
    {
        int res;
        res=n&(1<<i);
        return !(res == 0);
    }
    public static int setBits(int n)
    {
        int count=0;
        while (n>0)
        {
            if((n&1)>0)
                count++;
            n=n>>1;
        }
        return count;
    }
    public static int setBitsOptimized(int n)
    {
        int count=0;
        while(n>0)
        {
            count++;
            n=n&(n-1);
        }
        return count;
    }
    public static int countSetBits(int n)
    {
        int count=0;
        for(int i=1;i<=n;i++)
        {
            int t=i;
            while (t>0)
            {
                ++count;
                t=t&(t-1);
            }
        }
        return count;
    }
    public static int findOdd(int []arr){
        int res=0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("res: "+res+" val: "+arr[i]);
            res^=arr[i];
        }
        return res;
    }

    public static void main(String ...ss)
    {
        System.out.println(        findOdd(new int []{4,3,4,4,4,5,5}));
//        System.out.println(iBitSet(12,6));
//        System.out.println(setBits(127));
//        //System.out.println(setBitsOptimized(127));
//        System.out.println(countSetBits(17));
//        binaryVal(15);
        /*//  XOR 0 when same , 1 when different
        int x=3;
        int y=6;
        binaryVal(3);
        binaryVal(6);
        binaryVal(x&y);
        /*
* The left-shift and right-shift operators are equivalent to multiplication and division by 2^i respectively.
The & operator can be used to quickly check if a number is odd or even. The value of expression (x & 1)
* would be non-zero only if x is odd, otherwise the value would be zero.
*     binaryVal(6);

        System.out.println("left-Shift:(division 6/2) "+(6>>1));
        System.out.println("right-Shift:(multiply  6*2) "+(6<<1));
        System.out.println("checking odd even");
        System.out.println(oddEven(13));
    */
    }
}
