package dsa450;

import java.util.*;

public class practiceDP_rev {
    public static void main(String[] args) {
//        System.out.println("Shops and candies: "+shopAndCandies(new int[]{1,2,3,4,5,6,7,8,9},2));
//        System.out.println(wordBreak01("leetcode",new ArrayList<>(Arrays.asList("leet","code"))));
//        System.out.println("Shops and candies new: "+shopsAndCandiesOptimized(new int[]{1,2,3,4,5},2));
        System.out.println("Regex match: "+isMatch("aab","c*a*b"));
    }
    public static void  util(String[] arr){
        String[] pop=arr[0].substring(1,arr[0].length()-1).split(",");
        System.out.println(Arrays.toString(pop));  // [(1, 2)]
    }
    public static int shopAndCandies(int[] shops,int x){
        int n=shops.length;
        int[] DP=new int[n+1];
        DP[1]=shops[0];
        DP[2]=Math.min(shops[1],shops[0]+x);
        DP[3]=Math.min(Math.min(shops[2],shops[1]+x),shops[0]+2*x);
        DP[4]=Math.min(Math.min(shops[1],shops[2]+x),Math.min(shops[1]+2*x,shops[2]+x));
//        System.out.println(Arrays.toString(DP));
        //we go to a shop and find the point from where min of cost can be reached
        for (int i = 5; i <= n; i++) {
            int takeThis=shops[i-1]+DP[i-1];
            int skipOne=shops[i-1]+x+DP[i-2];
            int skipTwo=shops[i-1]+(2*x)+DP[i-3];
            int skipThree=shops[i-1]+(3*x)+DP[i-4];
            DP[i]=Math.min(Math.min(takeThis,skipOne),Math.min(skipTwo,skipThree));
        }
        System.out.println(Arrays.toString(DP));
        return DP[n]+shops[n-1];
    }

    //Maximum non-consecutive sum
    public static int maxConstSum(int[] arr){
        int n=arr.length;
        int[] DP=new int[n+1];
        DP[1]=arr[0];
        DP[2]=Math.max(arr[0],arr[1]);
        for (int i = 3; i<=n ; i++) {
            int take=DP[i-2]+arr[i];
            int dontTake=DP[i-1];
            DP[i]=Math.max(take,dontTake);
        }
        return DP[n];
    }

    //coin change: no of ways to get the sum by using the given coin type
    public static int getCountWays(int[] coins, int sum, int n){
        if (sum==0)
            return 1;
        if (n==0)
            return 0;
        int res=getCountWays(coins,sum,n-1);//when we dont consider this element
        if (coins[n-1]<=sum)
            res+=getCountWays(coins,sum-coins[n-1],n);
        //we are not reducing the no of ways because we have infinit supply of coins
        return res;
    }

    public static int changeCoinWays(int[] coins,int sum){
        HashMap<String,Integer> map=new HashMap<>();
        return solveSumCoinChangeWays(0,sum,coins,map);
    }
    //we will take a coin and find if there is way to find way of sum== sum-coin
    //only if the coin value is less than the required sum

    private static int solveSumCoinChangeWays(int index,int sum,int[]coins, HashMap<String,Integer> map){
        if (sum==0)
            return 1;
        if (sum<0 || index>=coins.length)
            return 0;
        String key=sum+"#"+index;
        if (map.containsKey(key))
            return map.get(key);
        int res=0;
        for (int i = index; i < coins.length; i++) {
            if (coins[i]<=sum)
                res+=solveSumCoinChangeWays(index,sum-coins[i],coins,map);
            // we are not changing the indx of next call because we can take one coin multiple times
        }//no of ways we can find sum-coin[i] in the other right parts
        map.put(key,res);
        return res;
    }

    //min coin required to make sum
    public static int minCoinSum(int sum,int[] coins){
        int n=coins.length;
        if (sum==0)
            return 0;
        int res=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (coins[i]<=sum){
                int sumRes=minCoinSum(sum-coins[i],coins);
                if (sumRes!=Integer.MAX_VALUE)
                    res=Math.min(res,sumRes+1);
            }
        }
        return res;
    }
    //DP implementation of min coin sum
    //starting from finding no of ways to find the sum 1,
    // and going to find the no of ways to find the no of ways to find sum==given sum
    //tabulation method:: only if coin[i] is <=sum
    public static int minCoinSumDP(int[] coins,int sum){
        int[] DP=new int[sum+1];
        Arrays.fill(DP,Integer.MAX_VALUE);
        DP[0]=0;
        //min coins req to make sum:: 1-> sum
        for (int s = 1; s <=sum; s++) {
            for (int coin : coins) {
                if (coin<=s){
                    int subRes=DP[s-coin];
                    if (subRes!=Integer.MAX_VALUE)
                        DP[s]=Math.min(subRes+1,DP[s]);
                }
            }
        }
        return DP[sum];
    }
    //we go for a given sum to all the coins
    //what are the no of ways to get sum ==1
    // and then next find no of ways to get sum==2 which is dependent on 1
    public static int getCoinSumWays(int[] coins,int sum){
        int n=coins.length;
        int[][] DP=new int[sum+1][n+1];
        for (int i = 0; i < sum; i++) {
            DP[i][0]=1;
        }   //if no element is there, then there is one way to make any sum, by not taking any element
        for (int i = 1; i < n; i++) {
            DP[0][i]=0;
        }
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                DP[i][j]=DP[i][j-1];    //when we dont take the coin
                if (coins[j-1]<=i)      // we can take the coin
                    DP[i][j]+=DP[i-coins[j-1]][j];
            }
        }
        return DP[sum][n];
    }

    //KNAPSACK PROBLEM
    //given the weights and its value in array and a capacity
    ///find max value to accomodate in that capacity
    public static int knapSackRec(int[] weights,int[] value,int capacity, int index){
        if (index==0 || capacity==0)
            return 0;
        if(weights[index-1]>capacity)
            return knapSackRec(weights,value,capacity,index-1);
        else {
            int take=knapSackRec(weights,value,capacity-weights[index-1],index-1);
            int donTake=knapSackRec(weights,value,capacity,index-1);
            return Math.max(take,donTake);
        }
    }

    //min no of jumps required to reach the end
    //index is size not the arr index

    public static int minJumpsRequired(int[] jumps,int index){
        if (index==1)
            return 0;
        int res=Integer.MAX_VALUE;
        for (int i = 0; i <=index-1; i++) {
            if (i+jumps[i]>=index-1){
                int subRes=minJumpsRequired(jumps,index+1);
                if (subRes!=Integer.MAX_VALUE)
                    res=Math.min(res,subRes+1);
            }
        }
        return res;
    }
    //DP tabulation
    // find the min jumps required to reach place 1 then min jump to reach place 2
    //then calculating the min jumps to reach the end
    public static int minJumpsEndDP(int[] jumps){
        int n=jumps.length;
        int[] DP=new int[n];
        Arrays.fill(DP,-1);
        DP[0]=0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j+jumps[j]>=i){
                    if (DP[j]!=Integer.MAX_VALUE)
                        DP[i]=Math.min(DP[i],DP[j]+1);
                }
            }
        }
        return DP[n-1];
    }
    public static int minCostTicket(int[] DP,int[] days,int[] cost,int index){
        if (index>=days.length)
            return 0;
        if (DP[index]>0)
            return DP[index];

        int oneDayPass=cost[0]+minCostTicket(DP,days,cost,index+1);
        int k=index;
        for (; k <days.length; k++) {
            if (days[k]>=days[index]+7)
                break;
        }
        int oneWeekPass=cost[1]+minCostTicket(DP,days,cost,k);
        for (; k < days.length; k++) {
            if (days[k]>=days[index]+30)
                break;
        }
        int oneMonthPass=cost[2]+minCostTicket(DP,days,cost,k);
        DP[index]=Math.min(oneDayPass,Math.min(oneWeekPass,oneMonthPass));
        return DP[index];
    }

    //minimum element in sorted and rotated element
    public static int minSortedRotated(int[] arr){
        int n=arr.length;
        int start=0;
        int end=n-1;
        while (start<end){
            int mid=(start+end)/2;
            if (arr[mid]>arr[end])
                start=mid+1;
            else
                end=mid;
        }
        return arr[start];
    }
    //for duplicates
    public static int minSortedRotatedDuplicates(int[] arr){
        int n=arr.length;
        int start=0;
        int end=n-1;
        while (start<end){
            int mid=(start+end)/2;
            if (arr[mid]>arr[end])
                start=mid+1;
            else if (arr[mid]<arr[end])
                end=mid;
            else
                end--;
        }
        return arr[start];
    }
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
//        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a2 -> a2[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2)-> a1[0] - a2[0]);
        pq.offer(new int[]{0,0,0});//step, current index, direction(0 is back, 1 is forward)
        Set<Integer> forbit = new HashSet<>();
        Set<String> visited = new HashSet<>();
        int maxLimit = 2000 + 2 * b;
        for(int num : forbidden){
            forbit.add(num);
            maxLimit = Math.max(maxLimit, num + 2 * b);
        }
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int step = node[0];
            int idx = node[1];
            int dir = node[2];
            if(idx == x) return step;
            //try jump forward
            if(idx+a < maxLimit && !forbit.contains(idx+a) && !visited.contains(idx+a+","+0)){
                visited.add(idx+a+","+0);
                pq.offer(new int[]{step+1, idx+a, 0});
            }
            //try jump back
            if(idx-b >= 0 && !forbit.contains(idx-b) && !visited.contains(idx-b+","+1) && dir != 1){
                visited.add(idx-b+","+1);
                pq.offer(new int[]{step+1, idx-b, 1});
            }
        }
        return -1;
    }

    //word break 2:: given string s and a list of valid words,
    //generate all the  sentences of valid words
    static HashMap<Integer,List<String>> memoList=new HashMap<>();
    static HashSet<String> words;
    static String inpStr;
    public static List<String> wordBreak02(String s,List<String> wordDict){
        inpStr=s;
        words=new HashSet<>(wordDict);
        return wordBrkSolution(0);
    }
    public static List<String> wordBrkSolution(int index){
        if (memoList.containsKey(index))
            return memoList.get(index);
        List<String> validSentences=new ArrayList<>();
        if (index==inpStr.length())
            validSentences.add("");

        for (int end = index+1; end <=inpStr.length(); end++) {
            String prefix=inpStr.substring(index,end);
            if (words.contains(prefix)){
                List<String> nextAns=wordBrkSolution(end);
                for (String nextAn : nextAns) {
                    validSentences.add(prefix+(nextAn.equals("")?"":" ")+nextAn);
                }
            }
        }
        memoList.put(index,validSentences);
        return validSentences;
    }
    //word break simple
    public static boolean wordBreak01(String s, List<String> wordDict) {
        HashSet<String> hs=new HashSet<>(wordDict);
        if (s==null || s.length()==0)
            return false;
        int n=s.length();   //LEETCODE
        StringBuilder sb=new StringBuilder(s);
        boolean[] DP=new boolean[n];
        int start=0;
        for (int i = 0; i < n; i++) {
            for (int j = start; j <=i; j++) {
                String sub=sb.substring(j,i+1);
                System.out.println("j: "+j+" i: "+i+" str: "+sub);
                if (hs.contains(sub) && (j==0 || DP[j-1])) {
                    DP[i] = true;
                    System.out.println(Arrays.toString(DP));
                    start=i+1;
                    break;
                }
            }
        }
        return DP[n-1];
    }

    //uncrossed lines same as LCS tabulation method
    public static int uncrossedLines(int[] first, int[] second){
        int m=first.length;
        int n=second.length;
        int[][] DP=new int[m+1][n+1];
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                if (first[i-1]==second[j-1])
                    DP[i][j]=1+DP[i-1][j-1];
                else
                    DP[i][j]=Math.max(DP[i-1][j],DP[i][j-1]);
            }
        }
        return DP[m][n];
    }
    public static boolean isMatch(String s, String p) {
        str=s;
        pat=p;
        DPregex=new Boolean[s.length()+1][p.length()+1];
        System.out.println(s+"  "+p);
        return regexMatchUtil(0,0);
    }
    //      '.' Matches any single characer
    //      '*' Matches zero or more of the preceding element.
    static String str;
    static String pat;
    static Boolean[][] DPregex;
    //      i-> string index
    //      j-> pattern index
    private static boolean regexMatchUtil(int i,int j){
        System.out.println("Call for i: "+i+" j: "+j);
        if (i>=str.length() && j>=pat.length())
            return true;
        if (j>=pat.length())
            return false;
        if (DPregex[i][j]!=null)
            return DPregex[i][j];
        boolean match=i<str.length() && (str.charAt(i)==pat.charAt(j) || pat.charAt(j)=='.');

        if (j+1<pat.length() && pat.charAt(j+1)=='*'){
            System.out.println("* match");
            if (regexMatchUtil(i,j+2))  //if we dont match means 0 occurance, we go to next char
                return DPregex[i][j]=true;
            //we can only match * with any char when the char are same of its a . only we can consder one or more of its occurance
            if (match && regexMatchUtil(i+1,j)) //if we match one then we move index to next string keeping the pattern to same
                return DPregex[i][j]=true;
        }
        if (match)
            return DPregex[i][j]=regexMatchUtil(i+1,j+1);
        return DPregex[i][j]=false;
    }

    //find min coins
    static Integer[][]DPshops;
    static int[] coins;
    static int skipCost;
    public static int shopsAndCandiesOptimized(int[] shops,int x){
        DPshops=new Integer[shops.length][4];
        coins=shops;
        skipCost=x;
        return findMinCoins(0,3);
    }
    private static int findMinCoins(int i,int skipLeft){
        System.out.println("Call for i: "+i+" skipLeft: "+skipLeft);
        if (i>=coins.length)
            return 0;
        if (DPshops[i][skipLeft]!=null)
            return DPshops[i][skipLeft];

        if (i==0 || i==coins.length-1)
            return coins[i]+findMinCoins(i+1,3);

        int take=coins[i]+findMinCoins(i+1,3);
        int skip=(skipLeft>0)?skipCost+findMinCoins(i+1,skipLeft-1):Integer.MAX_VALUE;

        DPshops[i][skipLeft]=Math.min(take,skip);
        return DPshops[i][skipLeft];
    }
}
/*
public boolean isMatch(String s, String p) {
        return isMatch(0,s,0,p);
    }
    private boolean isMatch(int i, String s, int j, String p) {
        int sn = s.length(), pn = p.length();
        if(j==pn) { // since * in p can match 0 of previous char, so empty string(i==sn) may match p
            return i==sn;
        }
        char pj = p.charAt(j);
        boolean mat=i<sn && (s.charAt(i) == pj || pj=='.');

        if(j+1<pn && p.charAt(j+1)=='*') { //match *, needs to look at the next char to repeate current char
            if(isMatch(i,s,j+2,p)) {
                return true;
            }
            if(mat && isMatch(i+1,s,j,p)) {
                    return true;
            }
        }
        if(mat) {              //match dot
            return isMatch(i+1, s, j+1, p);
        }
        return false;
    }
 */
