package GEEKS_FOR_GEEKS;

public class bitwise
{
    public static void main(String ...ss)
    {       //  XOR 0 when same , 1 when different
        int x=3;
        int y=6;
        binaryVal(3);
        binaryVal(6);
        binaryVal(x&y);
        /*
* The left-shift and right-shift operators are equivalent to multiplication and division by 2 respectively.
The & operator can be used to quickly check if a number is odd or even. The value of expression (x & 1)
* would be non-zero only if x is odd, otherwise the value would be zero.
* */    binaryVal(6);

        System.out.println("left-Shift:(division 6/2) "+(6>>1));
        System.out.println("right-Shift:(multiply  6*2) "+(6<<1));
        System.out.println("checking odd even");
        System.out.println(oddEven(13));

    }
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
}
