package afterMay;

public class Dp_practice {
    public static void main(String[] args) {

    }
    //uncrossed lines, given 2 arr representing points
    //same point in both array can be linked but they should not cross some other linked line
    //find maximum no of uncrossed lines
    public static int uncrossedLinesCount(int[] first,int[] second){
        int m=first.length,n=second.length;
        int[][] dp=new int[m+1][n+1];
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                //if they match, add 1 and check for rest of elements
                if (first[i-1]==second[j-1])
                    dp[i][j]=1+dp[i-1][j-1];
                else //take the maximum of both case (i+1,j) and (i,j+1)
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
    //Return the minimum cost to reach the top of the floor.
    //bottom up approach tabulation
    public static int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        int[] dp=new int[n+1];//n+1 because to take n+1 as the destination to reach
        //we are allowed to start from 0,1 so cost to reach is cost[i]
        dp[0]=cost[0];
        dp[1]=cost[1];
        for (int i = 2; i < n; i++) {
            //to know the cost to reach this index , we check min cot to reach i-1
            //and add the cost as we will be taking hop from there
            int byOneHop=dp[i-1];
            int byTwoHop=dp[i-2];
            dp[i]=cost[i]+Math.min(byOneHop,byTwoHop);
        }
        return Math.min(dp[n-1], dp[n-2]);
    }
    //top down approach recursive
    public static int minCostRec(int i,int[] costs){
        if (i<=1) return 0;
        int takeOneHop=costs[i-1]+minCostRec(i-1,costs);
        int takeTwoHop=costs[i-2]+minCostRec(i-2,costs);
        return Math.min(takeOneHop,takeTwoHop);
    }
    //as we noticed that the tabulation only check the prev 2 values,
    //so we can store only 2 prev values and get answer
    public static int minCostSpcOpt(int[] cost){
        int n = cost.length;
        int first = cost[0];
        int second = cost[1];
        if (n<=2) return Math.min(first, second);
        for (int i=2; i<n; i++) {
            int curr = cost[i] + Math.min(first, second);
            first = second;
            second = curr;
        }
        return Math.min(first, second);
    }

    //max non consecutive sum
    //if we take current element we cannot take next element, take next to next element
    public static int maxConsSum(int[]arr){
        int n=arr.length;
        int[] dp=new int[n+1];
        dp[1]=arr[0];
        dp[2]=Math.max(arr[0],arr[1]);
        for (int i = 3; i <=n; i++) {
            int take=arr[i-1]+dp[i-2];
            int dontTake=dp[i-1];
            dp[i]=Math.max(take,dontTake);
        }
        return dp[n];
    }
    //find the number to ways to make sum using given type of coins
    //  1.we can simulate taking and not-taking and from all way count where sum==given sum
    //  2.we can take on valid coin and find way to get sum-coin[i]
    public static int countCoinWays(int index,int sum,int[] coins){
        if (sum==0)     //if we reached till sum==0 this is one way
            return 1;
        if(index==0)
            return 0;
        //if we dont consider this element sum remain same, we check for prev element
        int count=countCoinWays(index-1,sum,coins);
        //if it is possible to take cur element, we take and reduce the sum
        //we can also take this element again
        if (coins[index-1]<=sum)
            count+=countCoinWays(index,sum-coins[index-1],coins);
        return count;
    }
}




/*
june-14     0
july-14     25000
august-14   50000       50k for mandir
sept-14     75000       25000   //20k for mobile 5k for rest
oct-14      100000      50000   25k
nov-14      125000      75000   50k left
 */
