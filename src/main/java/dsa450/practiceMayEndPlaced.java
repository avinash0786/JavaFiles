package dsa450;

import java.util.Stack;

public class practiceMayEndPlaced {
    public static void main(String[] args) {

    }
    //longest Palindromic subsequence
    public static int longestPalinSubSeq(char[] arr){
        int n=arr.length;
        int[][] dp=new int[n][n];
        int maxLen=0,palStart=0;
        //for length one(1) it is a palindrome 1-1, 2-2, 3-3, 4-4
        for (int i = 0; i < n; i++)
            dp[i][i]=1;
        //for length two(2) it is a palindrome of len 2 if adj are equal else 1
        for (int i = 0; i < n-1; i++) {
            if (arr[i]==arr[i+1]) {
                dp[i][i + 1] = 2;
                maxLen=2;
                palStart=i;
            }
            else
                dp[i][i+1]=1;
        }
        //for length 3 or more we will use the previous saved answers
        for (int k = 3; k < n; k++) {
            for (int i = 0; i < n-k+1; i++) {
                int j=i+k-1;
                if (arr[i]==arr[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                    if (k>maxLen){
                        maxLen=k;
                        palStart=i;
                    }
                }
                else
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return maxLen;
    }

    //maximum profit by performing 2 transactions
    //as we can perform 2 trans non-overlapping, in an array of 0-n
    //one transac will be from 0-i and i+1-n , so we calculate first max profit by trans from right side
    //and then from left and cal max profit
    public static int maxProfitAtmost2Tran(int[] prices){
        int n=prices.length;
        int[] dp=new int[n];
        int spRight=prices[n-1];
        int cpLeft=prices[0];
        //for max profit from right side we store the MAX value of selling price
        for (int i = n-2; i >=0; i--) {
            if (prices[i]>spRight)
                spRight=prices[i];//profit=sp-cp, each prices[i] is cp
            dp[i]=Math.max(dp[i+1],spRight-prices[i]);
        }
        //for max profit from left side we store the MIN value of cost price
        //here we calc max profit by considering each prices[i] as sp and min cp
        //and also the max profit in right side which is already calculated
        for (int i = 1; i < n; i++) {
            if (prices[i]<cpLeft)
                cpLeft=prices[i];
            int curProfit=prices[i]-cpLeft;//profit=cp-sp, each price[i] is a sp
            int rightProfit=dp[i];
            dp[i]=Math.max(dp[i-1],curProfit+rightProfit);
        }
        return dp[n-1];
    }

    public static boolean checkRedundantBrackets(char[] arr){
        Stack<Character> stk=new Stack<>();
        for (char c : arr) {
            if (c==')'){
                char top=stk.peek();
                stk.pop();
                boolean flag=true;
                while (top!='('){
                    if (top=='+' || top=='-' || top=='*' || top=='/')
                        flag=false;
                    top=stk.peek();stk.pop();
                }
                if (flag)
                    return true;
            }
            else
                stk.push(c);
        }
        return false;
    }

}
