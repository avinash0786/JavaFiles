import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class googleCodingPractice
{
    public static int ropeCut(int n,int a,int b,int c)
    {
//        System.out.println("N: "+n);
        if(n==0) return 0;
        if(n<0) return -1;
        int A=ropeCut(n-a,a,b,c);
        int B=ropeCut(n-b,a,b,c);
        int C=ropeCut(n-c,a,b,c);
        int res=Math.max(A,Math.max(B,C));
//        System.out.println("Res: "+res);
        if(res==-1) return -1;
        return res+1;
    }

    public static void subString(String str,String cur,int index)
    {
        if(index==str.length()){
            System.out.print(cur+" - ");
            return;
        }
        subString(str,cur+str.charAt(index),index+1);
        subString(str,cur,index+1);
    }
    public static void main(String[] args) {
        Scanner inp=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        subString("ABC","",0);
//        System.out.println("Rope Cutting: "+ropeCut(23,11,9,12));
    }
}
