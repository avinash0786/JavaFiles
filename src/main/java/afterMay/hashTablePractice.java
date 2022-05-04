package afterMay;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

//hashing practice
public class hashTablePractice {
    public static void main(String[] args) {
        System.out.println("Logest distinct character: "+longestDistinctSubString("pwwkew"));
        System.out.println("Fraction to rec dec: "+fractionToDecimal(1,6));
    }
    public static int longestDistinctSubString(String str){
        int maxLen=0,n=str.length();
        char[] arr=str.toCharArray();
        HashSet<Character> hs=new HashSet<>();
        int low=0,high=0;
        while (low<n){
            //expanding the window, while checking all distint chars
            while (high<n && !hs.contains(arr[high]))
                hs.add(arr[high++]);
            maxLen=Math.max(maxLen,hs.size());
            //shrinking the window, removing tail char
            hs.remove(arr[low]);
            low++;
        }
        return maxLen;
    }
    //recurring decimal,If the fractional part is repeating, enclose the repeating part in parentheses.
    //Input: numerator = 2, denominator = 3
    //Output: "0.(6)"
    public static String fractionToDecimal(int num, int den) {
        StringBuilder ans=new StringBuilder();
        ans.append(((num > 0) ^ (den > 0)) ? "-" : "");
        num=Math.abs(num);
        den=Math.abs(den);
        int quo=num/den;
        int rem=num%den;
        ans.append(quo);    //appending quotent before the decimal
        if (rem==0)
            return ans.toString();
        else
            ans.append(".");    //now adding the required . to represent decimal values
        HashMap<Integer,Integer> hs=new HashMap<>();//to check of remainder repetes
        while (rem!=0){
            System.out.println(hs);
            System.out.println("rem: "+rem+" quo: "+quo);
            if (hs.containsKey(rem)){
                System.out.println(hs);
                System.out.println(ans);
                int repIdx=hs.get(rem);
                ans.insert(repIdx,"(");
                ans.append(")");
                break;
            }
            hs.put(rem,ans.length());
            rem=rem*10;
            quo=rem/den;
            rem=rem%den;
            ans.append(quo);
        }
        return ans.toString();
    }
    //simplify path
    public static String simplifyPath(String path) {
        char[] arr=path.toCharArray();
        Stack<String> stk=new Stack<>();
        int n=arr.length;
        int i=0;
        while (i<n){
            while (i<n && arr[i]=='/')
                i++;
            String curDir="";
            while (i<n && arr[i]!='/')
                curDir+=arr[i++];
            if (curDir.equals(""))
                continue;
            if (curDir.equals("..")){
                if (!stk.isEmpty())
                    stk.pop();
            }
            else if (!curDir.equals("."))
                stk.push(curDir);
        }
        Stack<String> rev=new Stack<>();
        while (!stk.isEmpty())
            rev.push(stk.pop());
        String op="";
        while (!rev.isEmpty())
            op+=("/"+rev.pop());
        return op==""?"/":op;
    }
}
