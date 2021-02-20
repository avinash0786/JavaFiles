import java.util.ArrayList;
import java.util.Collections;

public class maqSoftPrac {
    public static void main(String[] args) {
        stringPerm("ABC",0,2);
        Collections.sort(op);
        System.out.println(op);
        for (String s:op)
            System.out.print(s+" ");
        System.out.println("is fbL : "+checkFibonacci(4));
    }
    static long nthFibonacci(long n){
        long[] ar=new long[(int) n+1];
        ar[0]=0;
        ar[1]=1;
        for (int i = 2; i <= n; i++) {
            ar[i]=(ar[i-1]+ar[i-2])%1000000007;
        }
        return ar[(int) n];
    }

    static String checkFibonacci(int N){
        return (isPerfSquare(5*N*N+4) || isPerfSquare(5*N*N-4))?"Yes":"No";
    }
    static boolean isPerfSquare(int n){
        int sqr= (int) Math.sqrt(n);
        System.out.println("n: "+n+" sqr: "+sqr);
        System.out.println("mul: "+(sqr*sqr));
        return sqr*sqr==(double) n;
    }
    static ArrayList<String> op=new ArrayList<>();
    public static void stringPerm(String s,int l, int h){
        if (l==h) {
            op.add(s);
            return;
        }
        for (int i=l; i<=h; i++) {
            s=interchange(s,l,i);
            stringPerm(s,l+1,h);
            s=interchange(s,l,i);
        }
    }
    public static String interchange(String s, int a, int b){
        char[] ar=s.toCharArray();
        char temp=ar[a];
        ar[a]=ar[b];
        ar[b]=temp;
        return new String(ar);
    }

    String reverseWords(String inp)
    {
        // your code here'
        char[] str=inp.toCharArray();
        int n=str.length;
        int start=0;
        for (int end = 0; end < n; end++) {
            if(str[end]=='.'){
                reverse(str,start,end-1);
                start=end+1;
            }
        }
        reverse(str,start,n-1);     //  for last edge case
        // reverse(str,0,n-1);     //for total string reversal
        String fin=new String(str);
        return fin;
    }
    public static void reverse(char[] arr,int low,int high){
        while (low<=high){
            swap(arr,low,high);
            low++;
            high--;
        }
    }
    public static void swap(char[]arr, int low,int high){
        char t=arr[high];
        arr[high]=arr[low];
        arr[low]=t;
    }
}
