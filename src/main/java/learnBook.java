import java.util.Objects;

public class learnBook
{
    /*
    We need an interpretor to run java program. the program are compiled into JVM code i.e bytecode using compiler.
    The byte code is machine-independent and can run on any machine that has a java interpretor,
    wihch is a part of JVM.
    Copiler is used to convert java code to java.class file

    and java.class is run using the interpretor
     */
    public static void main(String[] args) {
        int i1=12345;
        int i2=123_45;
        System.out.println("Two nos are equal: "+(i1==i2));
        /*
        Since Java 7 it has been possible to use one or more underscores (_) for separating groups of digits in a primitive
        number literal to improve their readability.
         */

        int i=2147483647;
        long l1=i+1;
        System.out.println("L1: "+l1);  //-2147483648 as perporemd using 32 bit arithm
        long l2=i+1L;
        System.out.println("L2: "+l2);

        Float f=2.0E2f;
        System.out.println("F: "+f); //f: 200.0  2.0 x 10^2 = 2.0 x 100= 200
        System.out.println(Float.POSITIVE_INFINITY);
        System.out.println(Float.NEGATIVE_INFINITY);

        // PRIMITIVE DATA TYPES byte, short, int ,long, char, boolean, float, double
        // are the types that store the most raw numerical data in java programs.

        float floatVAlue=23.23f;
        System.out.println(floatVAlue);
        float res=23.56f*12.23f;
        System.out.println(res);
        float notExact=3.1415926f;
        System.out.println(notExact);  //3.1415925
        //widening converison
        int a=23;
        double ss=a;  //conversion int to double;
        //narrowing conversion
        double d=23.156;
//        int n=d;   //ERROR : invalid conversion
        int bb=(int) d;  // type-casting

        String abb="Apple";
        String bba="Apple";
        System.out.println(abb==bba);
        System.out.println(abb.equals(bba));
        String str="Apple ; is ; red; and ; i ; live ; in ; India";
        String []arrs=str.split(";");
        for(String s:arrs){
            System.out.print(s+" ");
        }
        System.out.println();
        String joined=String.join("*",arrs);
        System.out.println(joined);

        //Type conversion: 1. Implicit: when source type is smaller than target
        //2. when source has larger type than target type

        byte byteVar=42;
        short shortvar=byteVar;
        int intVar=shortvar;
        long longVar=intVar;
        float floatVar=longVar;
        double doubvar=floatVar;

        //Explicit casting
        double doubleVar = 42.0d;
        float floaVar = (float) doubleVar;
        long lonVar = (long) floaVar;
        int inVar = (int) lonVar;
        short shortVar = (short) inVar;
        byte bytVar = (byte) shortVar;

        char char1=1, char2=2;
//        char1=  (char1+char2);  error
        System.out.println(char1);

        char cha1   = (char)   65; // A
        byte byte1   = (byte)  'A'; // 65
        short short1 = (short) 'A'; // 65
        int int1     = (int)   'A'; // 65
        char cha2   = (char) 8253; // ‽
        byte byte2   = (byte)  '‽'; // 61 (truncated code-point into the ASCII range)
        short short2 = (short) '‽'; // 8253
        int int2     = (int)   '‽'; // 8253

        Object obj = new Object(); // Note the 'new' keyword
//        Where:
//        Object is a reference type.
//                obj is the variable in which to store the new reference.
//                Object() is the call to a constructor of Object.
//        What happens:
//        Space in memory is allocated for the object.
//        The constructor Object() is called to initialize that memory space.
//                The memory address is stored in obj, so that it references the newly created object.

        /*
        The Java programming language (and its runtime) has undergone numerous changes since its release since its
initial public release. These changes include:
Changes in the Java programming language syntax and semantics
Changes in the APIs provided by the Java standard class libraries.
Changes in the Java (bytecode) instruction set and classﬁle format.
With very few exceptions (for example the enum keyword, changes to some "internal" classes, etc), these changes
are backwards compatible.
A Java program that was compiled using an older version of the Java toolchain will run on a newer version
Java platform without recompilation.
A Java program that was written in an older version of Java will compile successfully with a new Java compiler
         */
        System.out.println(0f);

//        Widening Conversion:
        int aa = 1;
        double da = aa;    // valid conversion to double, no cast needed (widening)
//        Narrowing Conversion:
        double dq = 18.96;
//        int bq = dq;       // invalid conversion to int, will throw a compile-time error
        int b = (int) d; // valid conversion to int, but result is truncated (gets rounded down)
        // This is type-casting
        // Now, b = 18
        double notaExact = 1.32 - 0.42; // result should be 0.9
        System.out.println(notaExact); // 0.9000000000000001
/*
By default, long is a 64-bit signed integer (in Java 8, it can be either signed or unsigned). Signed, it can store a
minimum value of -263, and a maximum value of 263 - 1, and unsigned it can store a minimum value of 0 and a
maximum value of 264 - 1
 */
        ///Warning: Java caches Integer objects instances from the range -128 to 127
        Long val1 = 127L;
        Long val2 = 127L;
        System.out.println(val1 == val2); // true
        Long val3 = 128L;
        Long val4 = 128L;
        System.out.println(val3 == val4); // false
//        To properly compare 2 Object Long values, use the following code(From Java 1.7 onward):
        Long vala3 = 128L;
        Long vala4 = 128L;
        System.out.println(Objects.equals(vala3, vala4)); // true

    }
}
