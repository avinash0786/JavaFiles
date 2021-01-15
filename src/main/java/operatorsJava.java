public class operatorsJava
{
    public static void main(String[] args) {
        int a = 10;
        a++; // a now equals 11
        a--; // a now equals 10 again

        int x=10;
        System.out.println("x=" + x + " x=" + x++ + " x=" + x); // outputs x=10 x=10 x=11
        System.out.println("x=" + x + " x=" + ++x + " x=" + x); // outputs x=11 x=12 x=12
        System.out.println("x=" + x + " x=" + x-- + " x=" + x); // outputs x=12 x=12 x=11
        System.out.println("x=" + x + " x=" + --x + " x=" + x); // outputs x=11 x=10 x=10

        int word = 0b00101010;
        int mask = 0b00000011;   // Mask for masking out all but the bottom
        // two bits of a word
        int lowBits = word & mask;            // -> 0b00000010
        int highBits = word & ~mask;          // -> 0b00101000
        System.out.println(lowBits);
        System.out.println(highBits);
        /*
        **********DIFF BT & and &&*********
        & is both logical and bitwize operator
        if operator is int it will act bitwize

        && is short circuit operator
        is only logical operator
         */
        byte a11=50;
        a11+=1;
        byte b=50;
        b++;
        //b=(b+1)   ERROR  aritheatic oprator conver all to int and int cannot stored in byte
        System.out.println(b);
        //SIGNED RIGHT SHIFT OPERATOR
        int number1 = 8;
        int number2 = -8;

        // 2 bit signed right shift
        System.out.println(number1 >> 2);    // prints 2
        System.out.println(number2 >> 2);    // prints -2

        //  UNSIGNED RIGHT SHIFT OPERATOR
        //Here, the vacant leftmost position is filled with 0 instead of the sign bit. For example,
        int number3 = 8;
        int number4 = -8;

        // 2 bit signed right shift
        System.out.println(number3 >>> 2);    // prints 2
        System.out.println(number4 >>> 2);    // prints 1073741822

        byte e=50;
        byte d=50;
        //        byte s=e+d;     //byte s=e+d; ERROR, AS + OPERATOR CONVERTS INTO INT
        byte s=(byte) (e+d);
        System.out.println(Integer.toBinaryString(121));
        // prints "1111001"
        System.out.println(Integer.toBinaryString(121 >> 1));
        // prints "111100"
        System.out.println(Integer.toBinaryString(121 >>> 1));
        // prints "111100"
        System.out.println(Integer.toBinaryString(121));
        // prints "1111001"
        System.out.println(Integer.toBinaryString(121 >> 1));
        // prints "111100"
        System.out.println(Integer.toBinaryString(121 >>> 1));
        // prints "111100"


    }
}
/*
>>> is unsigned-shift; it'll insert 0. >> is signed, and will extend the sign bit.
If the operator precedes the variable, the value of the expression is the value of the variable after being
incremented or decremented. If the operator follows the variable, the value of the expression is the value of the
variable prior to being incremented or decremented.

The + symbol can mean three distinct operators in Java:
If there is no operand before the +, then it is the unary Plus operator.
If there are two operands, and they are both numeric. then it is the binary Addition operator.
If there are two operands, and at least one of them is a String, then it it the binary Concatenation operator.

**The << or left shift operator shifts the value given by the ﬁrst operand leftwards by the number of bit positions
    given by the second operand. The empty positions at the right end are ﬁlled with zeros.

**The '>>' or arithmetic shift operator shifts the value given by the ﬁrst operand rightwards by the number of bit
    positions given by the second operand. The empty positions at the left end are ﬁlled by copying the left-most
    bit. This process is known as sign extension.

**The '>>>' or logical right shift operator shifts the value given by the ﬁrst operand rightwards by the number of
    bit positions given by the second operand. The empty positions at the left end are ﬁlled with zeros.

>> is arithmetic shift right, >>> is logical shift right.

In an arithmetic shift, the sign bit is extended to preserve the signedness of the number.

For example: -2 represented in 8 bits would be 11111110 (because the most significant bit has negative weight).
 Shifting it right one bit using arithmetic shift would give you 11111111, or -1.
 Logical right shift, however, does not care that the value could possibly represent a signed number;
 it simply moves everything to the right and fills in from the left with 0s. Shifting our -2 right one bit
 using logical shift would give 01111111.
 */