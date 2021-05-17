package dsa450;

import java.util.*;

public class apr11Prac {
    public static void main(String[] args) {
        System.out.println("Cnt palind subsequence: "+countPS("bccb"));
        System.out.println("Longest common prefix: "+longestCommonPrefix(new String[]{"flower","fliow","flight","fll"}));
        System.out.println(removeConsecutiveCharacter("bcdebeaec"));
        System.out.println("Roman to dec: "+romanToDecimal("LXVII"));
        System.out.println(secFrequent(new String[]{"geeks", "for", "geeks", "for", "geeks", "aaa"},2));
        System.out.println("min platform: "+findPlatform(new int[]{900, 940, 950, 1100, 1500, 1800},new int[]{910, 1200, 1120, 1130, 1900, 2000},8));
    }
    //count min no of reversal required to make a bracket comb string balanced
    static int countRev (String str)
    {
        //if string is odd lenght we cannot balance it
        if (str.length()%2==1)
            return -1;
        char[] arr=str.toCharArray();
        Stack<Character> stk=new Stack<>();
        int open=0;
        int close=0;
        for (int i = 0; i < arr.length; i++) {
              if (arr[i]=='{'){
                  stk.push('{');
                  open++;
              }
              else if (arr[i]=='}' && !stk.isEmpty()  && stk.peek()=='{'){
                  stk.pop();
                  open--;
              }
              else
                  close++;
        }
        if (close%2==1)
            close=close/2+1;
        else
            close=close/2;
        if (open%2==1)
            open=open/2+1;
        else
            open=open/2;
        return open+close;
    }

    //count the no of palindromic subsequence in given string
    static long[][] palSubSDP=new long[1001][1001];
    public int countPalindromicSubsequences(String str) {
        for (int i = 0; i <palSubSDP.length; i++) {
            Arrays.fill(palSubSDP[i], 0,str.length(),-1);
        }
        return (int) cntPalSubS(str,0,str.length()-1);
    }
    static long countPS(String str)
    {
        for (int i = 0; i <palSubSDP.length; i++) {
            Arrays.fill(palSubSDP[i], 0,str.length(),-1);
        }
        return cntPalSubS(str,0,str.length()-1);
    }
    public static long cntPalSubS(String str,int i,int j){
        if (i>j)
            return 0;
        if (i==j)
            return 1;
        if (palSubSDP[i][j]!=-1)
            return palSubSDP[i][j];
        if (str.charAt(i)==str.charAt(j))
            return palSubSDP[i][j]=(cntPalSubS(str, i+1,j)+cntPalSubS(str,i,j-1)+1)%1000000007;
        else
            return palSubSDP[i][j]=(cntPalSubS(str,i+1,j)+cntPalSubS(str,i,j-1)-cntPalSubS(str,i+1,j-1))%1000000007;
    }
    //Longest common prefix in array of string
    public static String longestCommonPrefix(String[] strings) {
        if (strings.length==0)
            return "";
        String prefix=strings[0];
        System.out.println(Arrays.toString(strings));
        for (int i = 1; i < strings.length; i++) {
//            System.out.println("Out: "+prefix+" check for: "+strings[i]);
            while (strings[i].indexOf(prefix)!=0){
//                System.out.println("indx: "+strings[i].indexOf(prefix));
                prefix=prefix.substring(0,prefix.length()-1);
//                System.out.println(prefix);
                if (prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }
    public static String removeConsecutiveCharacter(String S){
        char[] arr=S.toCharArray();
        int index=1;
        for (int i = 1; i <arr.length ; i++) {
            if (arr[i]!=arr[index-1])
                arr[index++]=arr[i];
        }
        String op=new String(arr);
        return op.substring(0,index);
    }

    // converting given roman number to integer

    public static int romanToDecimal(String str) {
        HashMap<Character,Integer> map=new HashMap<>();
        map.put('I',1);map.put('V',5);map.put('X',10);map.put('L',50);map.put('C',100);map.put('D',500);map.put('M',1000);

        int number=0;
        for (int i = 0; i < str.length(); i++) {
            int curNum=map.get(str.charAt(i));

            if (i+1<str.length()){
                int nextNum=map.get(str.charAt(i+1));
                //if cur num is greater than the next num we need to simply add that num
                if (curNum>=nextNum)
                    number+=curNum;
                else {
                    //if cur num is samller than next num val we need to subtract curnum from next number to get the value
                    // and we add nextnum-curnum and we skip next no as we counted that by doing i++
                    number=number+nextNum-curNum;
                    i++;
                }
            }
            else
                number+=curNum;
        }
        return number;
    }
    static int search(int arr[], int n,
                      int x, int k)
    {

        // Traverse the given array starting
        // from leftmost element
        int i = 0;

        while (i < n) {

            // If x is found at index i
            if (arr[i] == x)
                return i;

            // Jump the difference between
            // current array element and x
            // divided by k We use max here
            // to make sure that i moves
            // at-least one step ahead.
            i = i + Math.max(1, Math.abs(arr[i] - x) / k);
        }

        System.out.println("number is " + "not present!");
        return -1;
    }
    static String secFrequent(String arr[], int N)
    {
        TreeMap<String,Integer> tre=new TreeMap<>();
        int count=0;
        for (String s : arr) {
            if (tre.containsKey(s)){
                tre.replace(s,tre.get(s)+1);
            }
            else
                tre.put(s,1);
        }
        System.out.println(tre);
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(tre.entrySet());
        Collections.sort(entries, Map.Entry.comparingByValue());
        System.out.println(entries);
        return entries.get(entries.size()-2).getKey();
    }

    static int findPlatform(int arr[], int dep[], int n)
    {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int maxPlatform=1;
        int neededPlatform=1;
        int i=1,j=0;
        while (i<arr.length && j<dep.length){
            //if a train arrives before the other train has not departed, we need one more platform
            if (arr[i]<=dep[j]){
                maxPlatform++;
                neededPlatform=Math.max(neededPlatform,maxPlatform);
                i++;
            }
            else {
                maxPlatform--;
                j++;
            }
        }
        return neededPlatform;
    }
    /*  VERTICAL SCANNING  :: longest common prefix
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
    }
    return strs[0];
}
     */
}
