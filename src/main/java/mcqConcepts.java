public class mcqConcepts
{
    public static void main(String[] args) {

        //STRING INTERN
        String s1="apple";
        String s2="apple";
        s1=s1+"ok";
        System.out.println(s1);
        String s3=new String("apple");
        String s4=new String("apple");
        String inter=s3.intern();
        System.out.println("s1==s2: "+(inter==s1));
        System.out.println("s1.equals(s2): "+(s4.equals(s3)));
        /*
        String s1="apple";
        String s2="apple";
        String s3=new String("apple");
        String s4=new String("apple");

        (s1==s2) : true
        (s1==s3) : false
        (s4==s3)  : false

        (s1.equals(s2)) : true
         */


        //  operators
        int x=10;
        System.out.println("x=" + x + " x=" + x++ + " x=" + x); // outputs x=10 x=10 x=11
        System.out.println("x=" + x + " x=" + ++x + " x=" + x); // outputs x=11 x=12 x=12
        System.out.println("x=" + x + " x=" + x-- + " x=" + x); // outputs x=12 x=12 x=11
        System.out.println("x=" + x + " x=" + --x + " x=" + x); // outputs x=11 x=10 x=10

        int xa = 0;
        xa = xa++ + 1 + xa++;      // x = 0 + 1 + 1
        // do not do this - the last increment has no effect (bug!)
        System.out.println(xa);  // prints 2 (not 3!)

        //The Bitwise and Logical Operators (~, &, |, ^)
        int d=1;
        if ((3<d) && (3<d++))
            System.out.println("ok dne d: "+d);
        else
            System.out.println("no done d: "+d);

        /*
The + symbol can mean three distinct operators in Java:
If there is no operand before the +, then it is the unary Plus operator.
If there are two operands, and they are both numeric. then it is the binary Addition operator.
If there are two operands, and at least one of them is a String, then it it the binary Concatenation operator.
         */

        /*
        Operand and result types, and numeric promotion
The operators require numeric operands and produce numeric results. The operand types can be any primitive
numeric type (i.e. byte, short, char, int, long, float or double) or any numeric wrapper type deﬁne in java.lang;
i.e. (Byte, Character, Short, Integer, Long, Float or Double.
---The result type is determined base on the types of the operand or operands, as follows:
1. If either of the operands is a double or Double, then the result type is double.
2. Otherwise, if either of the operands is a float or Float, then the result type is float.
3. Otherwise, if either of the operands is a long or Long, then the result type is long.
4. Otherwise, the result type is int. This covers byte, short and char operands as well as `int.
         */
        //  LITERALS
        /*
         Java 7 it has been possible to use one or more underscores (_) for
         separating groups of digits in a primitive
            number literal to improve their readability.
            FORBID AT:-
           1. At the beginning or end of a number (e.g. _123 or 123_ are not valid)
           2.Adjacent to a decimal point in a ﬂoating point literal (e.g. 1._23 or 1_.23 are not valid)
           3. Prior to an F or L suﬃx (e.g. 1.23_F or 9999999_L are not valid)
           4. In positions where a string of digits is expected (e.g. 0_xFFFF is not valid)
         */
/*
The Shift Operators (<<, >> and >>>)
The << or left shift operator shifts the value given by the ﬁrst operand leftwards by the number of bit positions
    given by the second operand. The empty positions at the right end are ﬁlled with zeros.
The '>>' or arithmetic shift operator shifts the value given by the ﬁrst operand rightwards by the number of bit
    positions given by the second operand. The empty positions at the left end are ﬁlled by copying the left-most
    bit. This process is known as sign extension.
The '>>>' or logical right shift operator shifts the value given by the ﬁrst operand rightwards by the number of
    bit positions given by the second operand. The empty positions at the left end are ﬁlled with zeros.
 */

        //The Assignment Operators (=, +=, -=, *=, /=, %=,
        //<<=, >>= , >>>=, &=, |= and ^=)
/*
A compound assignment expression of the form E1 op= E2 is equivalent to
E1 = (T) ((E1) op (E2)),
where T is the type of E1, except that E1 is evaluated only once.
****there is an implicit type-cast before the ﬁnal assignment.
This means that either the types must be
the same, or the right operand type must be convertible to the left operands type by a combination of boxing,
unboxing or widening.
 */


        int iq=123456;
        int j=123_456;
        System.out.println(iq==j);
        int dec = 110;            // no prefix   --> decimal literal
        int bin = 0b1010;      // '0b' prefix --> binary literal
        int oct = 0156;           // '0' prefix  --> octal literal
        int hex = 0x6E;           // '0x' prefix --> hexadecimal literal
        System.out.println("Binary value: "+bin);
/*
 1.0E1    // this means 1.0 x 10^1 ... or 10.0 (double)
 1E-1D    // this means 1.0 x 10^(-1) ... or 0.1 (double)
 1.0e10f  // this means 1.0 x 10^(10) ... or 10000000000.0 (float)
 */
        char c=1;
        char v=2;
        System.out.println(c);
        System.out.println((int)c);
/*
        BASIC NUMERIC PROMOTION
    char char1 = 1, char2 = 2;
    short short1 = 1, short2 = 2;
    int int1 = 1, int2 = 2;
    float float1 = 1.0f, float2 = 2.0f;
    // char1 = char1 + char2;      // Error: Cannot convert from int to char;
    // short1 = short1 + short2;   // Error: Cannot convert from int to short;
    int1 = char1 + char2;          // char is promoted to int.
    int1 = short1 + short2;        // short is promoted to int.
    int1 = char1 + short2;         // both char and short promoted to int.
    float1 = short1 + float2;      // short is promoted to float.
    int1 = int1 + int2;            // int is unchanged.
 */


        char p='A';
        System.out.println(p);  // A
        p++;
        System.out.println(p);  // B
        System.out.println((int) p);    // 66
        p+='A';
        System.out.println(p);      // 
        System.out.println((int) p);    // 131

        byte b=6;
        b+=8;
        System.out.println(b);
//        b=b+7;    //ERROR : type error
        /*
        int or smaller expressions always resulting in an int.
        So compiler complain about Type mismatch: cannot convert from int to byte for b = b+7;
        But b += 7; // No problem because +=, -=, *=, and /= will all put in an implicit cast.
        b += 7 is same as b = (byte)b+7 so compiler not complain.
         */
        System.out.println(b);
//        byte ss=128;    //cannot store int in a byte ERRROR
        System.out.println("Max byte value: "+Byte.MAX_VALUE);
        float f=(1f/4)*10;
        System.out.println(1f/4);
        int i=Math.round(f);
        System.out.println(i);

        short xx=10;
//        xx=xx*5;      //ERROR
        System.out.println(xx);
        System.out.println(Short.MAX_VALUE);


        //-----------------------------------------
        // -----------------------------------------
//        byte a;
//        int i=258;
//        /*
//        -129=   11111111111111111111111101111111
//        +129=   10000001
//         */
//        System.out.println(Integer.toBinaryString(i));
//        a=(byte)i;
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(a);

//        int x=0,y=0,z=0;
//        //  1   0       1
//        x=(++x + y--) *x++;
//        System.out.println(x);        op: 1
//
//        if (1+1+1+1+1==5)
//            System.out.println("ok");
//        System.out.println(4245%10);    //op :5
//        System.out.println(42.25%10);   //op: 2.25
//        int aa=5;
//        aa*=3+7;
//        /*
//        x *= 3 + 7; is same as x = x * (3 +7) = 5 * (10) = 50
//        because expression on the right side is always placed inside parentheses
//         */
//        System.out.println(aa);
//
//        int t=1;
//        t+=1;
//        System.out.println(t);
//        System.out.println(t++);
//        System.out.println(t++);
//
//        int q=2,e=3,r=5;
//        System.out.println("VAl is: "+e+r);     ///  all string concatination as one is strig and no is in paranthesis
//        System.out.println(q+e+r);  //  all int addition as no one is string
//        System.out.println("String: "+(q+r));   // string as well as int additon as in paranthesis
    }
}
