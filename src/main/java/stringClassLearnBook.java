public class stringClassLearnBook {
    public static void main(String[] args) {

        String a1 = "apple";
        String a2 = "apple";
        //n order to compare Strings for equality, you should use the String object's equals or equalsIgnoreCase methods.
        System.out.println("a1 equals a2 " + (a1.equals(a2)));

        String a = "alpha";
        String b = "alpha";
        String c = new String("alpha");
        //All three strings are equivalent
        System.out.println(a.equals(b) && b.equals(c));
        //Although only a and b reference the same heap object
        System.out.println(a == b);
        System.out.println(a != c);
        System.out.println(b != c);

        StringBuilder sb = new StringBuilder("a");
        String s = sb.append("b").append("c").toString();
//        System.out.println(System.getProperty("line.separator"));;


        
    }
}
/*          ----NOTES----
        Strings (java.lang.String) are pieces of text stored in your program. Strings are not a primitive data type in Java,
however, they are very common in Java programs.
In Java, Strings are immutable, meaning that they cannot be changed. (Click here for a more thorough explanation
of immutability.)

Do not use the == operator to compare Strings
Unless you can guarantee that all strings have been interned (see below), you should not use the == or !=
operators to compare Strings. These operators actually test references, and since multiple String objects can
represent the same String, this is liable to give the wrong answer.
Instead, use the String.equals(Object) method, which will compare the String objects based on their values. For a
detailed explanation, please refer to Pitfall: using == to compare strings.

When we use double quotes to create a String, it ﬁrst looks for String with same value in the String pool, if found it
just returns the reference else it creates a new String in the pool and then returns the reference.
However using new operator, we force String class to create a new String object in heap space. We can use intern()
method to put it into the pool or refer to other String object from string pool having same value.
The String pool itself is also created on the heap.

The StringBuilder class was added in Java 5 to address performance issues with the original StringBuffer
class. The APIs for the two clases are essentially the same. The main diﬀerence between StringBuffer and
StringBuilder is that the former is thread-safe i.e. multiple threads cannot access it simultaneously. and synchronized and the latter is not.
         */