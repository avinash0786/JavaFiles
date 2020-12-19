public class bitwiseBookProfess
{
    private static final long FIRST_BIT = 1L << 0;
    private static final long SECOND_BIT = 1L << 1;
    private static final long THIRD_BIT = 1L << 2;
    private static final long FOURTH_BIT = 1L << 3;
    private static final long FIFTH_BIT = 1L << 4;
    private static final long BIT_55 = 1L << 54;

    private static void checkBitMask(long bitmask) {
        System.out.println("FIRST_BIT: " + ((bitmask & FIRST_BIT) != 0));
        System.out.println("SECOND_BIT: " + ((bitmask & SECOND_BIT) != 0));
        System.out.println("THIRD_BIT: " + ((bitmask & THIRD_BIT) != 0));
        System.out.println("FOURTh_BIT: " + ((bitmask & FOURTH_BIT) != 0));
        System.out.println("FIFTH_BIT: " + ((bitmask & FIFTH_BIT) != 0));
        System.out.println("BIT_55: " + ((bitmask & BIT_55) != 0));
    }
//    If an integer x is a power of 2, only one bit is set, whereas x-1 has all bits set after that. For example: 4 is 100 and 3
//    is 011 as binary number, which satisﬁes the aforementioned condition. Zero is not a power of 2 and has to be
//    checked explicitly.

    boolean isPowerOfTwo(int x)
    {
        return (x != 0) && ((x & (x - 1)) == 0);
    }
    public static int pow2(int n){
        return 1<<n;
    }
    public static void main(String[] args) {
        System.out.println(pow2(3));


//        checkBitMask(FIRST_BIT | THIRD_BIT | FIFTH_BIT | BIT_55);
    }
    /*

        (i & 1 << n) != 0 // checks bit 'n'
        i |= 1 << n;      // sets bit 'n' to 1
        i &= ~(1 << n);   // sets bit 'n' to 0
        i ^= 1 << n;      // toggles the value of bit 'n'

     */
}
