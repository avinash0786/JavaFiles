package GEEKS_FOR_GEEKS;

import java.util.Arrays;
import java.util.HashMap;

public class DP_gfgLearn {
    public static void main(String[] args) {
        DP_FIB[0]=0;
        DP_FIB[1]=1;
        System.out.println("6 th fib: "+nthFib(7));

        DP_LCS=new int[s1.length()+1][s2.length()+1];
        for (int[] dpLc : DP_LCS) {
            Arrays.fill(dpLc,-1);
        }
        System.out.println("Longest Common Subsequence: "+LCS_DP_REC(s1.length(),s2.length()));
        System.out.println("Longest Common Subsequence(Tabulation): "+LCS_tabulation("AXJHDSDKSAHFBOIAH","DJBASDSABAHBD"));
        System.out.println("Longest palindromic subsequence: "+longestPalindromicSubSeq("ABCPQRKQBA"));
        System.out.println("Min edit : "+minEditDistDp("SATURDAY","SUNDAY"));
        System.out.println("Longest increasing sub-sequence: "+LIS_Naive(new int[]{3,4,2,8,10,5,1}));
        System.out.println("LOngest decreasing subsequence: "+LDS(new int[]{1,11,2,10,4,5,2,1}));
        System.out.println("Min jump to reach dist: "+minJumpsRec(new int[]{3,4,2,1,2,4},6));
        System.out.println("KnapSack res: "+knapsackRec(10,4));
        System.out.println("Max non cons sum: "+maxSumNonCons(new int[]{10,5,15,20},4));
        System.out.println("Max non cons sum DP inp: "+maxSumNConsDp(new int[]{10,5,15,20},4));

    }
    public boolean isMatch(String s, String p) {
        str=s;
        pat=p;
        DPregex=new Boolean[s.length()+1][p.length()+1];
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
        //if both i & j reached end without terminating, means they matched till now
        if (i>=str.length() && j>=pat.length())
            return true;
        if (j>=pat.length())
            return false;
        if (DPregex[i][j]!=null)
            return DPregex[i][j];
        boolean match=i<str.length() && (str.charAt(i)==pat.charAt(j) || pat.charAt(j)=='.');

        if (j+1<pat.length() && pat.charAt(j+1)=='*'){
            if (regexMatchUtil(i,j+2))      //if we dont match any character
                return DPregex[i][j]=true;
            //if char before * match we need to match next chars with the char before *
            //so to match next char in str we do i+1
            //and as we need to match it to the char before * we keep our j to j itself
            if (match && regexMatchUtil(i+1,j))
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
        //we can skip minimum 3 shops
        return findMinCoins(0,3);
    }
    private static int findMinCoins(int i,int skipLeft){
        System.out.println("Call for i: "+i+" skipLeft: "+skipLeft);
        if (i>=coins.length)
            return 0;
        if (DPshops[i][skipLeft]!=null)
            return DPshops[i][skipLeft];
        //we cannot skip the first and the last shop
        if (i==0 || i==coins.length-1)
            return coins[i]+findMinCoins(i+1,3);
        //if we take the coin, out skipsLeft dont decrease and we add the shop cost
        int take=coins[i]+findMinCoins(i+1,3);
        //if we dont take the coin our skips decrease by one and we go to next element
        int skip=(skipLeft>0)?skipCost+findMinCoins(i+1,skipLeft-1):Integer.MAX_VALUE;

        //we find the min cost we can spend by skipping or not skipping the shops
        DPshops[i][skipLeft]=Math.min(take,skip);
        return DPshops[i][skipLeft];
    }
    static int[] DP_FIB=new int[100];
    public static int nthFib(int n){
        if (n==0 || n==1)
            return n;
        if (DP_FIB[n]!=0)
            return DP_FIB[n];
        DP_FIB[n]=nthFib(n-1)+nthFib(n-2);
        return DP_FIB[n];
    }
    //  TABULATION
    //  table is filled in bottom up approach by filling 0,1,2 ...n
    static int fib(int n) {
        int f[] = new int[n+1];
        f[0]=0;
        f[1]=1;
        for(int i=2;i<=n;i++)
            f[i] = f[i-1] + f[i-2];
        return f[n];
    }

    //longest common subsequence
    public static int[][] DP_LCS;
    public static String s1="AXJHDSDKSAHFBOIAH";
    public static String s2="DJBASDSABAHBD";
    //  i index in s1, j index in s2  initialized as the lenght of both the strings
    //top-> bottom approach
    public static int LCS_DP_REC(int i, int j){     // O(mn)
        if (DP_LCS[i][j]!=-1)
            return DP_LCS[i][j];
        if (i==0 || j==0)
            DP_LCS[i][j]=0;
        else
            if (s1.charAt(i-1)==s2.charAt(j-1))
                DP_LCS[i][j]= 1+LCS_DP_REC(i-1,j-1);
            else
                DP_LCS[i][j]=Math.max(LCS_DP_REC(i,j-1),LCS_DP_REC(i-1,j));

        return DP_LCS[i][j];
    }
    //bottom-> top approach
    public static int LCS_tabulation(String s1,String s2){
        int m=s1.length(),n=s2.length();
        int[][] DP_Tab=new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=n; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1))
                    DP_Tab[i][j]=1+DP_Tab[i-1][j-1];
                else
                    DP_Tab[i][j]=Math.max(DP_Tab[i-1][j],DP_Tab[i][j- 1]);
            }
        }
        return DP_Tab[m][n];
    }
    // longest palindromic subsequence in a string
    public static int longestPalindromicSubSeq(String s1){
        String s2="";// creating reverse of given string
        for (int i = s1.length()-1; i>=0; i--) {
            s2+=s1.charAt(i);
        }
        System.out.println(s1);
        System.out.println(s2);
        int m=s1.length(),n=s2.length();
        int[][] DP_Tab=new int[m+1][n+1];
        //perform lCS on given string and its reverse string
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=n; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1))
                    DP_Tab[i][j]=1+DP_Tab[i-1][j-1];
                else
                    DP_Tab[i][j]=Math.max(DP_Tab[i-1][j],DP_Tab[i][j-1]);
            }
        }
        //  Printing LCS string
        int index=DP_Tab[m][n];
        char[] lcs=new char[index+1];
        int i=s1.length();
        int j=s2.length();
        while (i>0 && j>0){
            if (s1.charAt(i-1)==s2.charAt(j-1)){
                lcs[index-1]=s1.charAt(i-1);
                i--;j--;index--;
            }
            else if (DP_Tab[i-1][j]> DP_Tab[i][j-1])
                i--;
            else
                j--;
        }
        System.out.println("Palindromic String: "+new String(lcs));
        return DP_Tab[m][n];
    }

    //longest palindromic substring
    public static int longestPalindromicSubString(String str){
        int n=str.length();
        int[][] dp=new int[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i]=1;
        int start=0;
        int maxLength=1;
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = 1;
                start = i;
                maxLength = 2;
            }
        }
        for (int k = 3; k <=n; k++) {
            for (int i = 0; i < n-k+1; i++) {
                int j=i+k-1;
                if (str.charAt(i)==str.charAt(j) && dp[i+1][j-1]==1){
                    dp[i][j]=1;
                    if (k>maxLength){
                        start=i;
                        maxLength=k;
                    }
                }
            }
        }
        System.out.println("max palind subStre: "+str.substring(start,start+maxLength));
        return maxLength;
    }
    /*
    In reursive algo 2 param change i.e n and sum, so create a 2d matrix denoting both var a t axis and
     */

    //EDIT DISTANCE PROBLEM
    /*
    In this problem we can insert, delete and replace a character to make the string s1 equal to s2
    and find the min no of steps required to make s1==s2
     */

    static String str1="ecfbefdcfca";
    static String str2="badfcbebbf";
    // i index in str1,  j index in str2
    // min no of op required to convert s1 into s2
    public static int editDistRec(int i, int j){
        if (i==0)
            return j;
        if (j==0)
            return i;
        if (str1.charAt(i)==str2.charAt(j))
            return editDistRec(i-1,j-1);
        else {
            //if the char are not equal we can perform any one of the delete, insert and replace
            int insert=1+editDistRec(i,j-1);
            int delete=1+editDistRec(i-1,j);
            int replace=1+editDistRec(i-1,j-1);
            //Add 1 because we are performing any one operation
            return Math.min(Math.min(insert,delete),replace);
        }
    }
    /*
    When we insert out i remains same j dec by 1 as
    string dont match at
    SATUR
    SUN

    if we add N at first string it become SATURN and second is SUN so they are now equal and next iter is
    for SATUR and SU so first remains same second index dec by one

    WE CAN SEE OVERLAPPING SUB PROBLEMS SO WE WILL USE DP HERE
     */
    public static int minEditDistDp(String s1, String s2){
        int m=s1.length();
        int n=s2.length();
        int[][] DP_EDIT=new int[m+1][n+1];
        for (int i = 0; i <=m; i++) {
            DP_EDIT[i][0]=i;
        }
        for (int i = 1; i <=n; i++) {
            DP_EDIT[0][i]=i;
        }
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1))
                    DP_EDIT[i][j]=DP_EDIT[i-1][j-1];
                else {
                    DP_EDIT[i][j]=1+Math.min(Math.min(DP_EDIT[i][j-1],DP_EDIT[i-1][j]),DP_EDIT[i-1][j-1]);
                }
            }
        }
        return DP_EDIT[m][n];
    }

    //LONGEST INCREASING SUB-SEQUENCE
    public static int LIS_Naive(int[]arr){
        int n=arr.length;
        int[] lis=new int[n];
        lis[0]=1;
        for (int i = 1; i <n ; i++) {
            lis[i]=1;
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i])
                    lis[i]=Math.max(lis[i],lis[j]+1);
            }
        }
        int res=lis[0];
        for (int i = 0; i < n; i++) {
            res=Math.max(res,lis[i]);
        }
        return res;
    }
    public static int LIS02(int[] arr){ //O(nlogn)
        int n=arr.length;
        int[] tail=new int[n];
        int len=1;
        tail[0]=arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i]>tail[i-1]) {
                tail[len] = arr[i];
                len++;
            }
            else {
                int indx=ceil(arr,0,len-1,arr[i]);
                tail[indx]=arr[i];
            }
        }
        return len;
    }
    private static int ceil(int[]arr,int l, int r, int key){
        while (r>l){
            int mid=l+(r-l)/2;
            if (arr[mid]>=key)
                r=mid;
            else
                l=mid+1;
        }
        return r;
    }
    //Maximum sum increasing sum
    public static int LSIS(int[] arr){
        int[] MSIS=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            MSIS[i]=arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j]<arr[i])
                    MSIS[i]=Math.max(MSIS[i],arr[i]+MSIS[j]);
            }
        }
        int res=MSIS[0];
        for (int msi : MSIS) {
            res = Math.max(res, msi);
        }
        return res;
    }
    public static int LDS(int[] arr){
        int n=arr.length;
        int[] dpLDS=new int[n];
        dpLDS[n-1]=1;
        for (int i= n-1; i >=0; i--) {
            dpLDS[i]=1;
            for (int j = i+1; j <n ; j++) {
                if (arr[j]<arr[i])
                    dpLDS[i]=Math.max(dpLDS[i],dpLDS[j]+1);
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(dpLDS));
        int res=dpLDS[0];
        for (int msi : dpLDS) {
            res = Math.max(res, msi);
        }
        return res;
    }
    //  Maximum cuts problem recursion -> Dp
    public static int maxCutsDP(int n, int a, int b, int c){
        int[] dpCuts=new int[n+1];
        for (int i = 1; i <=n; i++) {
            dpCuts[i]=-1;
            if (i-a>=0)
                dpCuts[i]=Math.max(dpCuts[i],dpCuts[i-a]);
            if (i-b>=0)
                dpCuts[i]=Math.max(dpCuts[i],dpCuts[i-b]);
            if (i-c>=0)
                dpCuts[i]=Math.max(dpCuts[i],dpCuts[i-c]);

            if (dpCuts[i]!=-1)
                dpCuts[i]++;    //inc the previous cuts as we now made a cut
        }
        return dpCuts[n];
    }
    //  Minimum coin change problem
    // given a type of coins and a sum we need to find min no of coins required to make to sum
    public static int getMinCoins(int[] coins,int n,int val){
        if (val==0)
            return 0;
        int res=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (coins[i]<=val){// skips for calling value
                int subRes=getMinCoins(coins,n,val-coins[i]);
                if (subRes!=Integer.MAX_VALUE)
                    res=Math.min(res,subRes+1); // adding 1 bcoz we used one coin
            }
        }
        return res;
    }
    //DP[i]-> min no of coins required to get value i
    public static int minCoinDP(int[] arr, int sum){    //O(n*sum)
        int[] DP_minCoin=new int[sum+1];
        DP_minCoin[0]=0;
        for (int i = 1; i < DP_minCoin.length; i++) {
            DP_minCoin[i]=Integer.MAX_VALUE;
        }
        //INTUTION: d the swe first find the min no of ways to finum ==1 then sum==2
        //and at last we find the min no of ways to find sum=val using the eqrlier results
        for (int i = 1; i <= sum; i++)
            for (int k : arr)
                if (k <= i) {
                    int subRes = DP_minCoin[i - k];
                    if (subRes != Integer.MAX_VALUE)
                        DP_minCoin[i] = Math.min(subRes + 1, DP_minCoin[i]);
                }
        return DP_minCoin[sum];
    }
    //  Given a array of types of coins and a sum find the no fo ways by which we can make
    //  the money equal to sum using the coins
    // we can take any number of coins of a type
    public static int coinChange(int[] coins, int n, int sum){
        if (sum==0)
            return 1;
        if (n==0)
            return 0;
        int res=coinChange(coins,n-1,sum);  // Don't take, sum remains same
        if (coins[n-1]<=sum)
            res+=coinChange(coins,n,sum-coins[n-1]);    // Take the coin now sum is reduced
        return res;
    }
    //if sum is 0 then there is one way to not take any element
    //if n=0 nocoin then there is no way we can make any sum
    public static int getCount(int[] coins, int n, int sum){
        int[][] DP_COINS=new int[sum+1][n+1];
        for (int i = 0; i <=n; i++) {
            DP_COINS[0][i]=1;
        }
        for (int i = 1; i <=sum; i++) {
            DP_COINS[i][0]=0;   //if there are no coins then there are no way to make sum==anything
        }
        for (int i = 1; i <=sum; i++) {
            for (int j = 1; j <=n; j++) {
                DP_COINS[i][j]=DP_COINS[i][j-1];
                if (coins[j-1]<=i)
                    DP_COINS[i][j]+=DP_COINS[i-coins[j-1]][j];
            }
        }
        return DP_COINS[sum][n];
    }

    //  Minimum jumps to reach destination
    // we call for no of ways to reach destination first and then rec call
    // for the elements which are reachable and
    //find no of ways to reach those elements
    // n is passed length of the array
    public static int minJumpsRec(int[] arr,int n){
//        System.out.println("call for n: "+n);
        if (n==0)   //if we are at first position
            return 0;
        int res=Integer.MAX_VALUE;  //if we cant reach desired destination
        //as we need to reach the (n-1) the element i.e last element, we don't need to
        //make any jump from the last element as we are on that place/destination
        //so we only loop till n-2 elements
        ///but we will check for (n-1) bcoz it is our destination
        for (int i = 0; i <=n-2; i++) { //we can make jumps one step back from our destination only
            if (i+arr[i]>=n-1){
                //we recur for (i+1) bcoz in call we only check or dest to be n-1 i.e i+1-1 ==i
                //means if we find a element from where we can reach our destination, we will find the ways to
                //reach the destination, from where we can reach our original destination
                int subRes=minJumpsRec(arr,i+1);
                if (subRes!=Integer.MAX_VALUE){
                    res=Math.min(res,subRes+1); //added 1 to sub res bcoz we made a jump
                }
            }
//            System.out.println("----------");
        }
        return res;
    }
    public static int minJumpsDP(int[] arr,int n){  //theta(n^2)  space: theta(n)
        int[] DP_jumps=new int[n];
        DP_jumps[0]=0;  //if we are first place we dont need ant step so 0
        //all other place initially we cant reach there now
        for (int i = 1; i <n; i++) {
            DP_jumps[i]=Integer.MAX_VALUE;
        }
        for (int i = 1; i <n; i++) {
            for (int j = 0; j < i; j++) {
                if (j+arr[j]>=i){   // we are adding j in arr[j]+j bcoz to add the
                    // previous steps as we will react a point only after covering previous steps
                    if (DP_jumps[j]!=Integer.MAX_VALUE)
                        DP_jumps[i]=Math.min(DP_jumps[i], DP_jumps[j]+1);
                }
            }
        }
        return DP_jumps[n-1];
    }
    //  0-1 KNAPSACK PROBLEM
    /*
    GIVEN A ARRAY OF WEIGHTS AND A ARRAY OF VALUES AND A WEIGHT VAR
    WE NEED TO MAXIMIZE THE VALUE AND NOT EXCEED THE WEIGHT
     */
    private static final int[] weights=new int[]{5,4,6,3};
    private static final int[] values=new int[]{10,40,30,50};

    public static int knapsackRec(int capacity,int n){
//        System.out.println("Call for capacity: "+capacity+" nos item: "+n);
        if (n==0 || capacity==0)
            return 0;

        if (weights[n-1]>capacity)  //if weight is greater than capacity we cant take that we ignore
            return knapsackRec(capacity,n-1);
        else {
            int take=values[n-1]+knapsackRec(capacity-weights[n-1],n-1);
            int dontTake=knapsackRec(capacity,n-1);
            return Math.max(take,dontTake);
        }
    }
    //DP implementation of Knapsack problem
    public static int knapSackDP(int capacity,int[] weight, int [] value,int n){    //theta(n*cap)
        int[][] DP_KNAP=new int[n+1][capacity+1];
        for (int i = 0; i <=capacity; i++) {
            DP_KNAP[0][i]=0;
        }
        for (int i = 0; i <=n; i++) {
            DP_KNAP[i][0]=0;
        }
        for (int i = 1; i <=n; i++) {   //i denote the number of elements
            for (int j = 1; j <=capacity; j++) {        //denotes the capacity
                if (weight[i-1]>j)
                    DP_KNAP[i][j]=DP_KNAP[i-1][j];
                else
                    DP_KNAP[i][j]=Math.max(value[i-1]+DP_KNAP[i-1][j-weight[i-1]], DP_KNAP[i-1][j]);
            }
        }
        return DP_KNAP[n][capacity];
    }

    //Egg dropping problem
    // we need to find out the min no of trails required to find a threshold floor
    //we need to find how many trials are required when egg breaks and don't break and find the min of those
    //if egg broke we need to find the threshold in loser floors
    //if egg dosen't broke we need to find the threshold
    public static int eggDropThreshold(int eggs,int floors){
        if (eggs==1)
            return floors;
        if (floors==1)
            return 1;
        if (floors==0 || eggs==0)
            return 0;
        int ans=Integer.MAX_VALUE;
        for (int i = 1; i <= floors; i++) {
            int eggBroke=eggDropThreshold(eggs-1,i-1);
            int eggSurvive=eggDropThreshold(eggs,floors-i);
            ans=Math.min(ans,Math.max(eggBroke,eggSurvive));
        }
        return ans+1;
    }
    //  res(x,e)=Min(
    public static int eggDropping(int floors, int eggs){
        int[][] DP_ANS=new int[floors+1][eggs+1];
        //at first floor with any no of eggs we need 1 trial
        //with 0 floor with any no of eggs we need 0 trials
        for (int i = 1; i <=eggs; i++) {
            DP_ANS[1][i]=1;
            DP_ANS[0][i]=0;
        }
        //with one 1 egg we need trials ==floors
        for (int i =1; i <=floors; i++) {
            DP_ANS[i][1]=i;
        }
        //finding min of the upperbound(break, dontBreak)
        //Drop egg from all the floors 2->f and find the floor with min no of trials
        for (int i = 2;i <=floors; i++) {
            for (int j = 2; j <=eggs; j++) {
                int ans=Integer.MAX_VALUE;

                for (int x = 1; x <=i ; x++) {
                    int eggBroke=DP_ANS[x-1][j-1];
                    int eggSurvive=DP_ANS[i-x][j];
                    //Taking best of worst
                    ans=Math.min(ans,Math.max(eggBroke,eggSurvive));
                }
                DP_ANS[i][j]=1+ans;
            }
        }
        // we are taking max of break/dont break and taking min of them
        //bcoz we need to find the worst case that what is threshold
        return DP_ANS[floors][eggs];
    }
    //Count BST with n keysgr
    //Catalan no DP
    public static int nKeyBST(int n){
        int[] DP_Cat=new int[n+1];
        DP_Cat[0]=1;
        for (int i = 1; i <=n; i++) {
//            DP_Cat[i]=0;
            for (int j = 0; j < i; j++) {
                DP_Cat[i]+=DP_Cat[j]*DP_Cat[i-j-1];
            }
        }
        return DP_Cat[n];
    }
    //Maximum sum with non-consecutive numbers
    public static int maxSumNonCons(int[] arr,int n){
        if (n==1)
            return arr[0];
        else if (n==2){
            return Math.max(arr[0],arr[1]);
        }
        else {
            //if we take a element we must no take its previous element, we have to take element leaving its adjacent
            int take=maxSumNonCons(arr,n-2)+arr[n-1];      //take this elemnem
            int dontTake=maxSumNonCons(arr,n-1);    //ignore last element
            return Math.max(take,dontTake);
        }
    }
    //Dp[i] stores the max sum with length of array i
    //maximum sum of non consecutive elements
    public static int maxSumNConsDp(int[] arr,int n){
        int[] DPCONS=new int[n+1];
        //array length min is 1 to dp[1] =arr[0]
        DPCONS[1]=arr[0];
        DPCONS[2]=Math.max(arr[0],arr[1]);
        for (int i = 3; i <=n; i++) {
            int take=DPCONS[i-2]+arr[i-1];//if we take this element we must have taken the prev adjacent element
            int dontTake=DPCONS[i-1];//if we dont take this element we must have taken the prev element
            DPCONS[i]=Math.max(take,dontTake);
        }
        return DPCONS[n];
    }

    //give a array and a sum and find the number of subsets in array with count sum
    static int[]arr=new int[]{10,20,15};
    public static int subSetSum(int n, int sum){
        if (n==0)
            return sum==0?1:0;
        else {
            int include=subSetSum(n-1,sum-arr[n-1]);
            int dontInclude=subSetSum(n-1,sum);
            return include+dontInclude;
        }
    }
    //Dp solution for subset sum problem
    // 2d as there are 2 parameters which change sum and n
    public static int subSetSumDP(int[]arr, int sum){       //O(n*sum)  tc:O(n*sum)
        int[][] DP_SUBSUM=new int[arr.length+1][sum+1];
        // base case
        //sum=0 1 subset
        for (int i = 0; i < arr.length; i++) {
            DP_SUBSUM[i][0]=1;
        }
        //if no of element ==0 ans is 0
        //in iteration if cur sum is less than the element we cannot take that element we need to skip it
        // in other case we need to consider both case take and dont take
        for (int i = 1; i <= arr.length; i++) {
            for (int s = 1; s <= sum; s++) {
                if (sum<arr[i-1])   //to ensure that we dont access -ve index in DP array
                    DP_SUBSUM[i][s]=DP_SUBSUM[i-1][s];
                else
                    DP_SUBSUM[i][s]=DP_SUBSUM[i-1][s]+DP_SUBSUM[i][s-arr[i-1]];
            }
        }
        return DP_SUBSUM[arr.length][sum];
    }

    // min no of cuts to make string palindrome
    static HashMap<String,Integer> hashPart;
    public static int palindPart(String str,int i,int j){
        String key=i+"#"+j;
        if (hashPart.containsKey(key))
            return hashPart.get(key);
        if (isPalindrome(str,i,j))
            return 0;
        int res=Integer.MAX_VALUE;
        for (int k =i; k < j; k++) {
            int leftPart=palindPart(str,i,k);
            int rightPart=palindPart(str,k+1,j);
            res=Math.min(res,1+leftPart+rightPart);
        }
        hashPart.put(key,res);
        return res;
    }
    private static boolean isPalindrome(String str,int i, int j){
        while (i<=j){
            if (!(str.charAt(i)==str.charAt(j)))
                return false;
            i++;
            j--;
        }
        return true;
    }
    private static final int[] bookArr=new int[]{10,26,13,49,28,37};
    public static int minBoolAlloc(int n,int k){
        if (k==1)
            return getSum(0,n-1);
        if (n==1)
            return bookArr[0];
        int res=Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            res=Math.min(res,Math.max(minBoolAlloc(i,k-1),getSum(i,n-1)));
        }
        return res;
    }
    private static int getSum(int i, int j){
        int sum=0;
        for (int k = i; k <= j; k++) {
            sum+=bookArr[i];
        }
        return sum;
    }
    //DP inplementation min book allocation
    public static int sum(int arr[],int b, int e){
        int s=0;
        for(int i=b;i<=e;i++)
            s+=arr[i];
        return s;
    }

    public static int minPages(int arr[],int n, int k){     //O(nlog*sum)
        int[][] dp=new int[k+1][n+1];
        for(int i=1;i<=n;i++)
            dp[1][i]=sum(arr,0,i-1);
        for(int i=1;i<=k;i++)
            dp[i][1]=arr[0];

        for(int i=2;i<=k;i++){
            for(int j=2;j<=n;j++){
                int res=Integer.MAX_VALUE;
                for(int p=1;p<j;p++){
                    res=Math.min(res,Math.max(dp[i-1][p],sum(arr,p,j-1)));
                }
                dp[i][j]=res;
            }
        }
        return dp[k][n];
    }
}


/*
DP is an optimization over plain recursion.
--- Number of dimensions in DP_Table/ memo_table depends on the number of parameter
that change in recursive call
 */

