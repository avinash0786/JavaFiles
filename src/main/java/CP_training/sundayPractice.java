package CP_training;

import java.util.Arrays;
import java.util.HashSet;

public class sundayPractice {
    public static void main(String[] args) {
        System.out.println("Largest distinct: "+longestDistM01("abcabcde"));
        System.out.println("Long dist: "+lengthOfLongestSubstring("abccbad"));
        minWindow("ADOBECODEBANC","ABC");
    }
    //-------LARGEST SUBSTRING WITHOUT REPETITION---------
    //      O(n^3)
    public static boolean isDistinct(char[] str, int start, int end){
        boolean[] visited=new boolean[256];
        for (int i =start; i <=end; i++) {
            if (visited[str[i]])
                return false;
            visited[str[i]]=true;
        }
        return true;
    }
    public static int  longestDistM01(String str){
        char[] arr=str.toCharArray();
        int res=0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isDistinct(arr,i,j))
                    res=Math.max(res,j-i+1);
            }
        }
        return res;
    }
    public static int longDistinceM02(String str){
        char[] arr=str.toCharArray();
        int res=0;
        for (int i = 0; i < arr.length; i++) {
            boolean[] vis=new boolean[256];
            for (int j = i; j <arr.length ; j++) {
                if (vis[arr[j]])
                    break;
                else {
                    res=Math.max(res,j-i+1);
                    vis[arr[j]]=true;
                }
            }
        }
        return res;
    }
    public static int longDistinctEfficM03(String str){
        char[] arr=str.toCharArray();
        int[] prev=new int[256];
        Arrays.fill(prev,-1);
        int res=0;
        int start=0;    //previous starting index for prev window
        for (int i = 0; i < arr.length; i++) {
            start=Math.max(start,prev[arr[i]]+1);
            int maxEnd=i-start+1;
            res=Math.max(res,maxEnd);
            prev[arr[i]]=i;
        }
        return res;
    }
    public static int lengthOfLongestSubstring(String str) {
        int low=0;
        int high=0;
        int n=str.length();
        int ans=0;
        char[] s=str.toCharArray();
        HashSet<Character> chr=new HashSet<>();
        while (high<n){
            // while low< n and we have not found the next element
            while (low<n && !chr.contains(s[low])){
                chr.add(s[low]);
                low++;
            }
            ans=Math.max(ans,chr.size());
            chr.remove(s[high]);
            high++;
        }
        return ans;
    }
    public static String longestPalindrome(String s) {
        int low=0;
        int high=0;
        int start=0;
        int end=1;
        char[] arr=s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            low=i-1;
            high=i;
            while (low>=0 && high<s.length() && arr[low]==arr[high]){
                if (high-low+1>end){
                    start=low;
                    end=high-low+1;
                }
                low--;
                high++;
            }
            low=i-1;
            high=i+1;
            while (low>=0 && high<s.length() && arr[low]==arr[high]){
                if (high-low+1>end){
                    start=low;
                    end=high-low+1;
                }
                low--;
                high++;
            }
        }
        return s.substring(start,start+end);
    }
    //      s = "ADOBECODEBANC", t = "ABC"
    public static String minWindow(String s, String t) {
        int low=0;
        int high=0;
        char[] main=s.toCharArray();
        char[] match=t.toCharArray();
        HashSet<Character> win=new HashSet<>();
        HashSet<Character> curSet=new HashSet<>();
        for (char c : match) {
            win.add(c);
        }
        int matLen=match.length;
        int need=matLen;
        int cur=0;
        System.out.println(win);
        int reqAns=main.length;
        int ansLow=0;
        int ansHigh=0;
        System.out.println(Arrays.toString(main));
        while (high<main.length && low+matLen<main.length){
            System.out.println("---------------Window: low: "+low+" high: "+high+" --------------");

            while (cur!=need){  //  INVALID    expand
                System.out.println("Cur: "+cur+" need: "+need+" high: "+high+" low: "+low);
                if (win.contains(main[high])){
                    curSet.add(main[high]);
                    cur++;

                    System.out.println("Search win: "+win);
                    System.out.println("Cur win: "+curSet);
                    System.out.println("---------------");
                }
                high++;
            }
            --high;
            System.out.println("***********Second part**********");
            while (cur>=need){// valid contract
                System.out.println("low: "+low+" high: "+high+" cur: "+cur);
                if (high-low<reqAns){
                    System.out.println("***update index low: "+low+" high: "+high);
                    ansLow=low-1;
                    ansHigh=high;
                }
                if (curSet.contains(main[low])){
                    curSet.remove(main[low]);

                    cur--;
                }

                low++;
                System.out.println("Cur:: "+cur);
                System.out.println(curSet);
            }


            System.out.println("low: "+low+" high: "+high);
            high++;
        }

        System.out.println("High: "+ansHigh+" low: "+ansLow);
        return s.substring(ansLow,ansHigh);
    }
}
