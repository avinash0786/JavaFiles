import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeMap;

public class kickstartRoundF
{
    public static void main(String ...dd)
    {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int limit = in.nextInt();
            System.out.println("n: "+n+"  lim: "+limit);
            int[] people=new int[n];
            for (int j = 0; j < n; j++) {
                people[j]= (int) Math.ceil(in.nextDouble()/limit);
            }
            for (int person : people) {
                System.out.print(person+" ");
            }
            int out[]=new int[n];
            int s=0;
            int counter=1;
            while (s!=n){
                for (int j = 0; j < n; j++) {
                    if(people[j]==counter){
                        out[s++]=(j+1);
                    }
                }
                counter++;
            }
            TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
            for( int a : people ) {
                map.put( people[a-1], a );
            }
            for (Integer value : map.values()) {
                System.out.println("Index: "+value);
            }
//            for (int person : people) {
//                System.out.print(person+" ");
//            }
            System.out.print("Case #" + i+": " );
            for (int integer : out) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
}
