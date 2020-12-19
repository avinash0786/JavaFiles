import java.util.*;
import java.io.*;
public class kickStartQ12020 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int note=in.nextInt();
            int breaking=0;
            int []notesValue=new int[note];
            int []keys=new int[note];
            int tone=1;
            keys[0]=0;
            notesValue[0]=in.nextInt();
            for(int j=1;j<note;j++)
            {
                if(keys[j-1]==3) tone=0;
                int ele=in.nextInt();
                notesValue[j]=ele;
                if(ele==notesValue[j-1])
                    keys[j]=keys[j-1];
                else
                    keys[j] = tone++;
            }
            for(int j:notesValue) System.out.print(j+" "); System.out.println();
            for(int j:keys) System.out.print(j+" ");System.out.println();


            // 0 1 2 3
            // A B C D
            keys[0]=0;
            for(int j=1;j<note;j++)
            {
                if(notesValue[j]>notesValue[j-1] && !(keys[j]>keys[j-1])) {
                    breaking++;
                    System.out.println("Break: "+notesValue[j]);
                }

                else if (notesValue[j]<notesValue[j-1] && !(keys[j]<keys[j-1])) {
                    breaking++;
                    System.out.println("Break: "+notesValue[j]);
                }
            }
            System.out.println("Case #" + i + ": " + breaking);  //  Case #1: 2
        }
    }
}

/*  QUESTION 2 TEST SET

2
5
1 5 100 500 1
8
2 3 4 5 6 7 8 9

 */


/* QUESTION 1 TEST SET

4
8
1 2 0 7 2 0 2 0
6
4 8 15 16 23 42
9
3 1 4 1 5 9 2 6 5
6
9 9 9 9 9 9

 */
/*  QUESTION 1
Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int days = in.nextInt();
            int [] nosVisitor=new int[days];  // no of visitor on  days
            for(int j=0;j<days;j++)
                nosVisitor[j]=in.nextInt();   //storing the no f visitors
            int breaking=0;
            int curMax=Integer.MIN_VALUE;
            for(int j=0;j<days-1;j++)
            {
                if(nosVisitor[j]>curMax && nosVisitor[j+1]<nosVisitor[j])
                    breaking++;
                curMax=Math.max(curMax,nosVisitor[j]);
            }
            if(nosVisitor[days-1]>curMax)
                breaking++;
            System.out.println("Case #" + i + ": " + breaking);  //  Case #1: 2
        }
 */