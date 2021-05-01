package dsa450;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class recursionApr {
    public static void main(String[] args) {
        swapWOthirdVar(-3,-61);
    }
    /*
    Given a path in the form of a rectangular matrix having few landmines arbitrarily placed (marked as 0),
     calculate length of the shortest safe route possible from any cell in the first column to any cell in the last column
     of the matrix. We have to avoid landmines and their four adjacent cells (left, right, above and below) as they are also unsafe.
     We are allowed to move to only adjacent cells which are not landmines. i.e. the route cannot contains any diagonal moves.
     */

    static int R ;    // to store the no of rows
    static int C ;    // to store the no of columns
    static int minDistance;     //  min final answer
    //this is used to get the index of 4 adjacent cell of a landmine
    static int rowNum[] = { -1, 0, 0, 1 };
    static int colNum[] = { 0, -1, 1, 0 };
    //function to check of given cell(x,y) is safe
    public static boolean isSafe(int[][] mat,boolean[][] vis,int x,int y){
        // safe of that cell is not a landmine and that cell is not visited
        //simply   return mat[x][y] != 0 && !vis[x][y];
        if (mat[x][y]==0 || vis[x][y])
            return false;
        return true;
    }
    //fn to check of cell(x,y) is valid i.e we can visit that
    public static boolean isValid(int x,int y){
        //simply    return x < R && y < C && x >= 0 && y >= 0;
        if (x<R && y<C && x>=0 && y>=0)
            return true;
        return false;
    }
    // marking all 4 adj left right top bottom cell of a landmine as unsafe by making them a landmine
    public static void markAdjUnsafe(int[][] mat){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j]==0){  // if it is a landmine mark all 4 adj a landmine
                    //first the left, then top, right, bottom by getting left rigt top index using rowNum and colNum
                    for (int k = 0; k < 4; k++) {
                        if (isValid(i+rowNum[k],j+colNum[k]))
                            mat[i+rowNum[k]][j+colNum[k]]=-1;
                        //if we mark here 0 then in next iter it will again mark it a landmine so mark -1
                    }
                }
            }
        }
        //marking all landmins as 0 where ther is -1
        for(int i = 0; i < R; i++)
        {
            for(int j = 0; j < C; j++)
            {
                if (mat[i][j] == -1)
                    mat[i][j] = 0;
            }
        }
    }
    //main fn to find shortest path
    public static void findShortestPathRun(int[][] mat, boolean[][] vis,int x,int y,int dist){
        if (y==C-1){    // if distance is reached
            minDistance=Math.min(minDistance,dist);
            return;
        }
        //if cur path has more dist already we dont check that
        if (dist>minDistance)
            return;
        vis[x][y]=true;
        for (int i = 0; i < 4; i++) {
            //if this side is valid and safe wo go to that side with dist inc by one
            if (isValid(x+rowNum[i],y+colNum[i]) && isValid(x+rowNum[i],y+colNum[i]))
                findShortestPathRun(mat,vis,x+rowNum[i],y+colNum[i],dist+1);
        }
        vis[x][y]=false;// doing backtrack
    }

    public static int minDistThroughLandmin(int[][] matrix){
        R=matrix[0].length;
        C=matrix.length;
        minDistance=Integer.MAX_VALUE;
        boolean[][] visited=new boolean[R][C];
        markAdjUnsafe(matrix);
        for (int i = 0; i <R; i++) {
            //if cur cell is safe of this row
            if (matrix[i][0]==1){
                for (int j = 0; j < R; j++) {
                    Arrays.fill(visited[j],false);
                }
                //find from cur row, first col and 0 dist
                findShortestPathRun(matrix,visited,i,0,0);
                //min dist can be the no of columns
                if (minDistance==C-1)
                    break;
            }
        }
        return minDistance;
    }
//Given an array arr[] of size N, check if it can be partitioned into two parts such that the sum of elements
// in both parts is the same.
    static int equalPartition(int N, int arr[])
    {
        int sum=0;
        for (int i : arr) {
            sum+=i;
        }
        //if sum is odd we cannot find a subset with equal sum
        if (sum%2==1)
            return 0;
        // we need to find subset with half sum only
        //starting from last element
        return isSubsetSum(arr,sum/2,N)?1:0;
    }
    public static boolean isSubsetSum(int[] arr,int sum, int n){
        if (sum==0)
            return true;
        if (n == 0)
            return false;
        //if cur ement is greater than the sum we dont need to consider the element
        if (arr[n-1]>sum)
            return isSubsetSum(arr,n-1,sum);
        //considering the element then dec the sum , if dont consider then keep sum same
        return isSubsetSum(arr,n-1,sum) || isSubsetSum(arr,n-1,sum-arr[n-1]);
    }

    //DP IMPLEMENTATION OF SAME
    static int equalPartitionDP(int N, int arr[])
    {
        int sum=0;
        for (int i : arr) {
            sum+=i;
        }
        //if sum is odd we cannot find a subset with equal sum
        if (sum%2==1)
            return 0;
        boolean[][] part=new boolean[sum/2+1][N+1];
        //if sum ==0 we can make this by not including any element
        //whatever the no of elemet we dont take any and make sum 0
        for (int i = 0; i <=N; i++) {
            part[0][i]=true;
        }
        //if there are 0 element we cannot find any sum so false
        for (int i = 1; i <=sum/2; i++) {
            part[i][0]=false;
        }
        for (int i = 1; i <= sum / 2; i++) {
            for (int j = 1; j <= N; j++) {
                part[i][j] = part[i][j - 1];// dont take cur element
                if (i >= arr[j - 1])
                    part[i][j] = part[i][j] || part[i - arr[j - 1]][j - 1];
                //take curr element find both take dont take which gives answer
            }
        }
        return part[sum/2][N]?1:0;
    }
    //longest repeating subsequence
    public static int LCS_tabulation(String s1){
        String s2=s1+"";
        int m=s1.length(),n=s2.length();
        int[][] DP_Tab=new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=n; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1) && i!=j)
                    DP_Tab[i][j]=1+DP_Tab[i-1][j-1];
                else
                    DP_Tab[i][j]=Math.max(DP_Tab[i-1][j],DP_Tab[i][j- 1]);
            }
        }
        return DP_Tab[m][n];
    }
    //longest pair chain
    int maxChainLength(lisPair arr[], int n)
    {
        int[] lis=new int[n];
        Arrays.sort(arr, Comparator.comparingInt(a -> a.end));
        lis[0]=1;
        for (int i = 1; i < n; i++) {
            lis[i]=1;
            for (int j = 0; j < i; j++) {
                if (arr[j].end<arr[i].start)
                    lis[i]=Math.max(lis[i],lis[j]+1);
            }
        }
        int res=lis[0];
        for (int li : lis) {
            res=Math.max(res,li);
        }
        return res;
    }
    //longest comon substring
    int longestCommonSubstr(String S1, String S2, int n, int m){
        int[][] DP=new int[n+1][m+1];
        char[] str01=S1.toCharArray();
        char[] str02=S2.toCharArray();
        int maxLen=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str01[i-1]==str02[j-1]) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                    maxLen=Math.max(DP[i][j],maxLen);
                }
                else
                    DP[i][j]=0;
            }
        }
        return maxLen;
    }
    //longest subsequence such that adj element diff is one 01
    //similar to longest increasing subsequence
    static int longestSubsequence(int N, int arr[]) {
        int[] lis=new int[N];
        lis[0]=1;
        for (int i = 1; i < N; i++) {
            lis[i]=1;
            for (int j = 0; j < i; j++) {
                if (Math.abs(arr[j]-arr[i])==1)
                    lis[i]=Math.max(lis[i],lis[j]+1);
            }
        }
        int res=lis[0];
        for (int li : lis) {
            res=Math.max(res,li);
        }
        return res;
    }
    static int longestSubsequenceOptimized(int N, int arr[]) {
        int res=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 1; i < N; i++) {
            int len=0;
            //if map already contains arr[i]-1 prev adj we store in len
            if (map.containsKey(arr[i]-1))
                len=map.get(arr[i]-1);
            //if map already contains arr[i]+1 next adj and its len is > arr[i]-1 len
            //we update the len

            if (map.containsKey(arr[i]+1) && map.get(arr[i]+1)>len)
                len=map.get(arr[i]+1);
            // we store cur arr[i]+1 as lenght increment by 1
            map.put(arr[i],len+1);
            res=Math.max(res,len+1);
        }
        return res;
    }
    public static void swapWOthirdVar(int a,int b){
        System.out.println("a: "+a+" b: "+b);
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println("a: "+a+" b: "+b);
    }
    public static int coinChageWays(int[] coins,int n,int sum){
        if (sum==0)
            return 1;
        if (n==0)
            return 0;
        int res=coinChageWays(coins,n-1,sum);
        //when we are taking the coin we can take if again also
        //so the index remains same but sum decreases, and in next call we cann
        //either take and dont take the coin at index
        if (coins[n-1]<=sum)
            res+=coinChageWays(coins,n,sum-coins[n-1]);
        return res;
    }
    //longest common subsquence of 3 strings
    /*
    Result for state (i, j, k) can be evaluated as:
    If (A[i] == B[j] == C[k]): 1 + (i + 1, j + 1, k + 1)
    else: max ((i + 1, j, k), (i, j + 1, k),
               (i, j, k + 1), (i + 1, j + 1, k),
               (i + 1, j, k + 1), (i, j + 1, k + 1))
     */
}
class lisPair implements Comparable<lisPair>{
    int start;
    int end;
    public lisPair(int x,int y){
        this.start=x;
        this.end=y;
    }
    public int compareTo( lisPair o) {
        return this.end-o.end;
    }
}
