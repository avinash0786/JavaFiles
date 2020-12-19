package GEEKS_FOR_GEEKS;
import java.util.ArrayList;
public class maxCuts
{
    public static String words []={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static int index=2;

    static void possibleWords(int a[], String next, int index)   //N - no of elements
    {
        if(next.length()==a.length)
        {
            System.out.print(next+" ");
            return;
        }
        int num=a[index];
        String wrds=words[num];
        for(int i=0;i<wrds.length();i++)
        {
            possibleWords(a,next+wrds.charAt(i),index+1);
        }
    }
    /*
    *   System.out.println();
        while (next.length()<a.length)
        {   System.out.println("index: "+index);
            int number=a[index];
            String chars=words[number];
            System.out.println();
            //System.out.println("chars: "+chars+"  size: "+chars.length());
            for(int i=index;i<chars.length();i++)
            {
                System.out.println("for loop indx:"+index);
                possibleWords(a,next+chars.charAt(i),index+1);
            }
            //////////----increasing k go to next element-----//////////////
            //System.out.println("k: "+k);
             //k+=1;
             //possibleWords(a,N);
        }*/

    // Complete the function
    // n: Input n
    // counter: variable has already been declared in driver code
    //          you just have to use this variable.
    static int counter = 2;

    // Return True if the given number is a lucky number else return False
    public static boolean isLucky(int n)
    {
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=1;i<=n;i++)
            arr.add(i);
        System.out.println(arr);
        while (counter<=arr.size())
        {
            System.out.println("size: "+arr.size());
            if(!arr.contains(n))
                return false;
            for(int i=counter-1;i<arr.size();i+=counter)
            {   System.out.println("i: "+i);
                arr.remove(i);
                //i+=counter;
            }
            System.out.println("counter: "+counter+" arr: "+arr);
            counter++;
            System.out.println("-------------------------------------------------------");
        }

        //System.out.println(arr.contains(19));   checking availability in list

        return true;
    }

    static int RecursivePower(int n,int p)
    {
        if(p==0)
            return 1;
        return RecursivePower(n,p-1)*n;
    }

    public static int josephus(int n, int k)
    {
        if(n==1)
            return 0;
        else
            return (josephus(n-1,k)+k)%n;
    }

    public static ArrayList<String> op=new ArrayList<>();

    public static void printSubStr(String str, String cur, int index)
    {
        if(index==str.length())
        {
            //System.out.println(cur);return;
            if(cur.length()!=0)
                op.add(cur);
            return;
        }
        //System.out.println("cur: "+cur+" concat: "+cur.concat(String.valueOf(str.charAt(index))));
        printSubStr(str,cur.concat(String.valueOf(str.charAt(index))),index+1);
        printSubStr(str,cur,index+1);
    }

    public static int maxCuts(int n, int a, int b, int c)
    {
        if(n==0) return 0;
        if(n<0) return -1;
        int res;
        int A=maxCuts(n-a,a,b,c);
        int B=maxCuts(n-b,a,b,c);
        int C=maxCuts(n-c,a,b,c);
        res=Math.max(A,B);
        res=Math.max(res,C);
        if(res==-1)
            return -1;
        return res+1;
    }

    static ArrayList<String> powerSet(String s)
    {
        // add your code here
        printSubStr(s, "", 0);
        return op;
    }
    public static void main(String ...ss)
    {
       // System.out.println(maxCuts(23,11,9,12));
        ///printSubStr("ABC","",0);
        //System.out.println(josephus(5,3));
       // System.out.println(RecursivePower(2,3));
        //System.out.println(isLucky(19));
        //possibleWords(new int[] {2,3,4},"",0);
        //System.out.println(powerSet("abc"));
        //String aa="";
        //System.out.println(aa.length());
        ArrayList<Integer> aa=new ArrayList<>();
        aa.add(23);
        aa.add(56);
        //System.out.println(aa);
        aa.add(89);
        ArrayList<Integer> ee=new ArrayList<>();
        ee.add(11);
        ee.add(22);
        aa.addAll(ee);
        //System.out.println(aa);
        System.out.println((23%1)==0);
    }
}
/*
* adg adh adi aeg aeh aei afg afh afi bdg bdh bdi beg beh bei bfg bfh bfi cdg cdh cdi ceg ceh cei cfg cfh cfi
* */