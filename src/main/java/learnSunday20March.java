import java.util.Arrays;

public class learnSunday20March {
    public static void main(String[] args) {
        int number=-117769990;
//        System.out.println(Integer.toBinaryString(number));
//        System.out.println(Integer.toBinaryString(number>>1));
//        System.out.println(Integer.toBinaryString(number));
//        System.out.println(Integer.toBinaryString(number>>>1));

        for (int i = 0; i <palSubSDP.length; i++) {
            Arrays.fill(palSubSDP[i], -1);
        }
        System.out.println("No of palindromic subsequence: "+palindSubSeq(0,2,"bccb"));

    }
    static int[][] palSubSDP=new int[1000][1000];
    public static int palindSubSeq(int i, int j, String str){
        if (i>j)
            return 0;
        if (i==j)
            return 1;
        if (palSubSDP[i][j]!=-1)
            return palSubSDP[i][j];
        if (str.charAt(i)==str.charAt(j))
            return palSubSDP[i][j]=palindSubSeq(i+1,j,str)+palindSubSeq(i,j-1,str)+1;
        else
            return palSubSDP[i][j]=palindSubSeq(i+1,j,str)+palindSubSeq(i,j-1,str)-palindSubSeq(i+1,j-1,str);
    }
}
/*
Abstraction is a process of hiding the implementation details and showing
only functionality to the user

Hiding the state of an object and providing
all interaction through an objects methods is known as Data Encapsulation.

Encapsulation refers to the practice of hiding the implementation of a class
from any users of that class
 */
/*
3 types of bitwise shift operator
--  >> SIGNED RIGHT SHIFT OPERATOR
--  <<  SIGNED LEFT SHIFT OPERATOR
--  >>> UN-SIGNED RIGHT SHIFT OPERATOR

SIGNED RIGHT SHIFT OPERATOR, PRESERVE THE SIGN BIT

UN-SIGNED RIGHT SHIFT OPERATOR FILL WITH 0
**using ">>>" a negative number can turned into positive number
 */