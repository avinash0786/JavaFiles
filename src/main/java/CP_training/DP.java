package CP_training;

import java.util.HashMap;

public class DP {
    public static void main(String[] args) {
        System.out.println("-----------------DYNAMIC PROGRAMMING-----------------------");
        System.out.println("Count ways: "+countWays(0,2));
    }

    static HashMap<Integer, Integer> dpCntWays=new HashMap<>();
    public static int  countWays(int index, int n){     //  TC: O(n)  SC:   O(
        if (index==n) return 1;
        if (index>n) return 0;
        if (dpCntWays.containsKey(index))   //  using memoization
            return dpCntWays.get(index);

        dpCntWays.put(index,countWays(index+1, n)+countWays(index+1, n));
        return dpCntWays.get(index);
    }

    static HashMap<Integer, Integer> dpNonAdjSum=new HashMap<>();
    public static int nonadjSum(int[] arr, int index){
        if (index>=arr.length)
            return 0;
        if (dpNonAdjSum.containsKey(index))
            return dpNonAdjSum.get(index);

        int take=nonadjSum(arr,index+2)+arr[index];
        int dontTake=nonadjSum(arr,index+1);

        dpNonAdjSum.put(index,Math.max(take,dontTake)); //  storing the answer for index
        return dpNonAdjSum.get(index);
    }

    public static int waysDieSum(int N, int x, int face){
        if (N==0 && x==0) return 1;
        if (N<=0 || x<=0) return 0;
        int ans=0;
        for (int i = 1; i < face ; i++) {
            ans+=waysDieSum(N-1,x-i,face);
        }
        return ans;
    }
    public static int dieWays02(int n, int x, int f){
        int[][] dpWays=new int[n+1][x+1];
        for (int i = 1; i <n ; i++) {
            for (int j = 1; j < x; j++) {
                for (int k = 1; k <f ; k++) {
                    if (j-k>=0)
                    dpWays[i][j]+=dpWays[i-1][j-k];
                }
            }
        }
        return dpWays[n][x];
    }

    static int n;
    static int m;
    static int[][] waysDP=new int[n][m];
    public static int pathWays(int i, int j){
        if (i==n-1 && j==m-1)   return 1;
        if (i==n || j==m)   return 0;
        if (waysDP[i][j]!=-1)
            return waysDP[i][j];
        waysDP[i][j]=pathWays(i+1,j)+pathWays(i,j+1);
        return waysDP[i][j];
    }

    public static int pathWaysIter(int n, int m){
        int[][] waysDP=new int[n][m];
        waysDP[n-1][m-1]=1;
        for (int i = n-1; i >=0; i--) {
            for (int j = m-1; j >=0; j--) {
                if (i==n-1 && j==m-1) continue;
                waysDP[i][j]=waysDP[i][j+1]+waysDP[i+1][j];
            }
        }
        return waysDP[0][0];
    }
    static int[] subSumArr=new int[]{1,3,6,2,10};
    static int sumSub=10;
    static int[][] DPsubSum=new int[subSumArr.length+1][sumSub+1];
    public static int subSetSum(int index,int sum){ //  tc: O(n*k)      sc: O(n*k)
        if (sum==0)
            return 1;
        if (index==subSumArr.length || sum<0)
            return 0;
        if (DPsubSum[index][sum]!=-1)
            return DPsubSum[index][sum];

        DPsubSum[index][sum]=subSetSum(index+1,sum-subSumArr[index]) | subSetSum(index+1,sum);
        return DPsubSum[index][sum];
    }
    //  ITERATIVE TABULATION
    public static boolean subSetSumIter(int[] arr,int sum){ //  tc: O(n*k)      sc: O(n*k)
        boolean[][] DP_subSum=new boolean[arr.length+1][sum+1];
        int n=arr.length;
        for (int i = 0; i < n; i++) {   //setting fn(index,0)==true
            DP_subSum[i][0]=true;
        }
        for (int i = 1; i < sum; i++) {   //setting fn(index,sum)==false
            DP_subSum[n][i]=false;
        }
        for (int i = n-1; i >=0; i--) {
            for (int j = 1; j <=sum; j++) {
                DP_subSum[i][j]=DP_subSum[i+1][j];
                if (j-arr[i]>=0){
                    DP_subSum[i][j]=DP_subSum[i+1][j] || DP_subSum[i+1][j-arr[i]];
                }
            }
        }
        return DP_subSum[0][sum];
    }

    static String s1="acdeab";
    static String s2="gcekb";
    static int[][] DP_LCS=new int[s1.length()][s2.length()];        //  DP table

    public static int LCS(int i, int j){        //  LCS: longest common subsequence
        if (i==s1.length() || j==s2.length())
            return 0;
        if (DP_LCS[i][j]!=-1)       //  Using memoization
            return DP_LCS[i][j];

        if (s1.charAt(i)==s2.charAt(j))
            DP_LCS[i][j]=LCS(i+1,j+1)+1;
        else
            DP_LCS[i][j]=Math.max(LCS(i+1,j),LCS(i,j+1));
        return DP_LCS[i][j];
    }

}
