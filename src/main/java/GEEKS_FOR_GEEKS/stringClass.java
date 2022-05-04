package GEEKS_FOR_GEEKS;

import java.util.Arrays;

public class stringClass
{
    public static boolean anagramSort(String s1, String s2){
        if(s2.length()!=s1.length())
            return false;
        char [] a1=s1.toCharArray();
        char [] a2=s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        String f1=new String(a1);
        String f2=new String(a2);
        return f1.equals(f2);
    }
    public static boolean anagram(String s1,String s2){
        if(s2.length()!=s1.length())
        return false;
        int[] chars=new int[256];
        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i)]++;
            chars[s2.charAt(i)]--;
        }
        for (int i = 0; i < 256; i++) {
            if(chars[i]!=0)
                return false;
        }
        return true;
    }
    public static int leftmostRepLtoR(String str){  //left to right
        int [] findex=new int[256];
        Arrays.fill(findex,-1);
        int res=Integer.MAX_VALUE;
        for (int i = 0; i < str.length(); i++) {
            int fi=findex[str.charAt(i)];   //store the first occurance of a character
            if(fi==-1)
                findex[str.charAt(i)]=i;
            else
                res=Math.min(res,fi);   //only stores the first ocureance of lemt most element
        }
        return (res==Integer.MAX_VALUE)?-1:res;
    }
    public static int leftMostRepRtoL(String str){
        boolean[] visited=new boolean[256];
        int res=-1;
        for (int i = str.length()-1; i >=0; i--) {
            if(visited[str.charAt(i)])
                res=i;
            else
                visited[str.charAt(i)]=true;
        }
        return res;
    }
    public static int leftMostNonRep(String str){
        boolean[] visited=new boolean[256];
        int res=-1;
        for (int i = str.length()-1; i >=0; i--) {
            if(!visited[str.charAt(i)])
                res=i;
            else
                visited[str.charAt(i)]=true;
        }
        return res;
    }
    public static String reverseWords(String inp){
        char[] str=inp.toCharArray();
        int n=str.length;
        int start=0;
        for (int end = 0; end < n; end++) {
            if(str[end]==' '){
                reverse(str,start,end);
                start=end+1;
            }
        }
        reverse(str,start,n-1);     //  for last edge case
        reverse(str,0,n-1);     //for total string reversal
        String fin=new String(str);
//        System.out.println(fin);
        return  fin;
    }
    public static void reverseWordCond(String inp,int n){
        System.out.println("INPUT: "+inp);
        char[] str=inp.toCharArray();
        int len=str.length;
        int start=0;
        int wordsCount=0;
        int startIndex=0;
        for (int i = 0; i < len; i++) {
            if(str[i]==' '){
                wordsCount++;
                if (wordsCount==n){
                    startIndex=i;
                    break;
                }
            }
        }
        String fin="";
        fin=inp.substring(0,startIndex)+" "+reverseWords(inp.substring(startIndex));
        System.out.println("FIRST OUTPUT: "+fin);
        str=fin.toCharArray();
        start=0;
        for (int i = 1; i < len; i++) {
            if(str[i]==' '){
                reverse(str,start+n,i-1);
//                System.out.println("start: "+start+" end: "+i);
                start=i+1;
            }
        }
        System.out.println("SECOND OUTPUT: "+new String(str));
    }
    /*
    APPLE IS RED    --orignal string
    ELPPA SI DER    --individual word reverse
    RED IS APPLE    --total string reverse
     */
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
    public static void patternSearchNiave(String str, String ptrn){
        int n=str.length();
        int m=ptrn.length();
        for (int i = 0; i <= n-m;) {
            int j;
            for (j = 0; j < m; j++) {
                if(ptrn.charAt(j)!=str.charAt(i+j))
                    break;
            }
            if (j==m)
                System.out.print(i+" ");
            if (j==0)
                i++;
            else
                i=(i+j);
        }
    }
    public static void RBKSearch(String str,String pat){
        int n=str.length();     //Length of string
        int m=pat.length();     //Length of pattern
        int d=256;
        int q=101;
        System.out.println("str length: "+n+" pattern lenght: "+m);
        int h=1;
        for (int i = 1; i <= m-1; i++)       //Computing (D^(M-1))%Q
            h=(h*d)%q;

        int p=0,t=0;                        //Computing pattern hash and initinal text hash
        for (int i = 0; i < m; i++) {
            p=(p*d + pat.charAt(i))%q;
            t=(t*d + str.charAt(i))%q;
        }
//        System.out.println("p: "+p+" d: "+d+" h: "+h+" t: "+t);
        for (int i = 0; i <=(n-m); i++) {
//            System.out.println("Running i: "+i+"  t: "+t);
            //  Check for HIT
            if (p==t){
                boolean flag=true;
                for (int j = 0; j < m; j++) {
                    if(str.charAt(i+j)!=pat.charAt(j)){
                        flag=false;
                        break;
                    }
                }
                if (flag){
                    System.out.println(pat+" found at index i: "+i);
                }
            }
            //  Compute ti+1 using ti
            if (i<n-m){
                //  ti+1= d(ti -str[i]*d^m-1) + str[i+m]
                t=((d*(t-str.charAt(i)*h)) + str.charAt(i+m))%q;
                if (t<0)
                    t+=q;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("start");
        reverseWordCond("Here is the source code of the java program ",2);
//        System.out.println(anagram("apple","ebppa"));
//            RBKSearch("GEEKS FOR GEEKS","GEEK");
//        patternSearchNiave("abcedbefabcd","abcd");
//        System.out.println(anagramSort("aaple","apple"));
//        System.out.println(anagramSort("mmngo","mango"));
//        System.out.println(leftmostRepLtoR("abcdd"));
//        System.out.println(leftMostRepRtoL("abccbd"));
//        System.out.println(leftMostNonRep("abbccdd"));
//        reverseWords("avinash is name my ");
    }
}
