package CP_training;

import java.util.*;

public class practiceRecBC7apr {
    public static void main(String[] args) {
        System.out.println("Count ways to reach destination: "+countWays(0,4));
        genBalParenthesis("",3,3);
//        System.out.println("Sub sets Numbers: ");genSubsetNum(0,new HashSet<>());
//        genSubsetDuplicate(0,new LinkedList<>());
//        genSubSetForLoop(0,new HashSet<>());
        System.out.println("Numer permutation generation: ");
//        genPermutationNum(new LinkedHashSet<>());
        System.out.println("Unique path to reach destination: "+uniquePaths(0,0));
        stepMemo=new int[11];
        System.out.println("min step to make 1: "+minStepsToReduce(10));
    }
    public static int countWays(int cur,int dest){
        if (cur==dest)
            return 1;
        if (cur>=dest)
            return 0;
        int skip01=countWays(cur+1,dest);
        int skip02=countWays(cur+2,dest);
        return skip01+skip02;
    }

    public static void genBalParenthesis(String str,int open,int close){
        if (open==0 && close==0){
            System.out.println(str);
            return;
        }
        if (open>0)
            genBalParenthesis(str+"(",open-1,close);
        if (close>open)
            genBalParenthesis(str+")",open,close-1);
    }
    static int[] subSetArr=new int[]{1,2,3};
    public static void genSubsetNum(int index, HashSet<Integer> set){
        if (index==subSetArr.length){
            System.out.print(set+", ");
            return;
        }
        set.add(subSetArr[index]);
        genSubsetNum(index+1,set);
        set.remove(subSetArr[index]);
        genSubsetNum(index+1,set);
    }
    static int[] genSubFor=new int[]{1,2,3};
    public static void genSubSetForLoop(int index, HashSet<Integer> se){
        for (int i = index; i < genSubFor.length ; i++) {
            se.add(genSubFor[i]);
            System.out.print(se+", ");
            genSubSetForLoop(i+1,se);
            se.remove(genSubFor[i]);
        }
    }
    //generate subset with input having duplicate numbers
    //we need to skip duplicate elements
    // for duplicate elements we will only run once , then we will skip duplicate elements
    static int[] subSetDuplicate=new int[]{1,2,2};
    public static void  genSubsetDuplicate(int index, List<Integer> intern){
        for (int i = index; i < subSetDuplicate.length; i++) {
            if (i>index && subSetDuplicate[i]==subSetDuplicate[i-1]) {    //not check for i==index i.e first run
//                System.out.println("Continue");
            }
            else {
                intern.add(subSetDuplicate[i]);
                System.out.print(intern+", ");
                genSubsetDuplicate(i+1,intern);
                intern.remove(intern.size()-1);
            }
        }
    }
    //GENERATING PERMUTATION OF [1,2,3]
    static int[] permNums=new int[]{1,2,3};
    public static void genPermutationNum(Set<Integer> lis){
        if (lis.size()== permNums.length){
            System.out.print(lis+", ");;
        }
        else {
            for (int num : permNums) {
                if (lis.add(num)) { // elements are unique
                    genPermutationNum(lis); // recursive call
                    lis.remove(num); // backtrack
                }
            }
        }
    }
    static int[][] grid=new int[3][3];
    static HashMap<String,Integer> pathMemo=new HashMap<>();
    public static int uniquePaths(int i,int j){
        if (i== grid.length-1 && j== grid[0].length-1)
            return 1;
        if (i>= grid.length || j>= grid[0].length)
            return 0;
        String key=i+"#"+j;
        if (pathMemo.containsKey(key))
            return pathMemo.get(key);
        int down=uniquePaths(i+1,j);
        int right=uniquePaths(i,j+1);
        pathMemo.put(key,down+right);
        return pathMemo.get(key);
    }
    static int[] stepMemo;
    public static int minStepsToReduce(int n){
         if (n==1)
             return 1;
         if (stepMemo[n]>0)
             return stepMemo[n];
         int minStep=Integer.MAX_VALUE;
         minStep= minStepsToReduce(n-1);
         if (n%2==0){
             int div2=minStepsToReduce(n/2);
             minStep=Math.min(minStep,div2);
         }
         if (n%3==0){
             int div3=minStepsToReduce(n/3);
             minStep=Math.min(minStep,div3);
         }
         stepMemo[n]=1+minStep;
         return stepMemo[n];  // as we performed one step here
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        words=new HashSet<>(wordDict);
        inpStr=s;
        return wordBreakSoln(0);
    }
    HashMap<Integer,List<String>> memoList=new HashMap<>();
    HashSet<String> words;
    String inpStr;

    private List<String> wordBreakSoln(int start){
        if (memoList.containsKey(start))
            return memoList.get(start);

        List<String> validSubs=new ArrayList<>();
        if (start==inpStr.length())
            validSubs.add("");

        for (int end = start+1; end <= inpStr.length(); end++) {
            String prefix=inpStr.substring(start,end);

            if (words.contains(prefix)){
                List<String> nextAns=wordBreakSoln(end);
                for (String nextAn : nextAns) {
                    validSubs.add(prefix+(nextAn.equals("")?"":" ")+nextAn);
                }
            }
        }
        memoList.put(start,validSubs);
        return validSubs;
    }
}
