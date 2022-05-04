package dsa450;

import java.util.*;

//array practice may 22
public class practiceMayEnd23 {
    public static void main(String[] args) {
        nextPermuteNumber(new int[]{6,2,1,5,4,3,0});
        System.out.println("Decode String: "+decodeString("d3[a2[c]]"));
    }
    //next permutation of the given number array
    public static void nextPermuteNumber(int[] arr){
        int n=arr.length;
        int i=n-2;
        System.out.println(Arrays.toString(arr));
        while (i>=0 && arr[i]>=arr[i+1])
            i--;    //finding point of inflection
        if (i>=0){
            int j=n-1;
            while (arr[j]<=arr[i])
                j--;
            swap(arr,i,j);
        }
        reverse(arr,i+1,n-1);
        System.out.println(Arrays.toString(arr));
    }
    private static void reverse(int[]arr,int  i,int j){
        while (i<j)swap(arr,i++,j--);
    }
    private static void swap(int[] arr, int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    //subarray sum divisible by k
    public static int subArraySumDivK(int[] arr,int k){
        HashMap<Integer,Integer> hm=new HashMap<>();
        hm.put(0,1);
        int count=0;
        int prefSum=0;
        for (int i = 0; i < arr.length; i++) {
            prefSum+=arr[i];
            int rem=prefSum%k;
            if (rem<0)rem+=k;
            if (hm.containsKey(rem))
                count+=hm.get(rem);
            hm.put(rem,hm.getOrDefault(rem,0)+1);
        }
        return count;
    }
    //finding if given tree is subtree of the tree  O(m*n)
    //we go to each node and try to match this with the subtree
    public static boolean isSubtree(TreeNode first,TreeNode second){
        if (first==null)
            return false;
        else if (isSame(first,second))
            return true;
        else
            return isSubtree(first.left,second) || isSubtree(first.right, second);
    }
    private static boolean isSame(TreeNode first,TreeNode second){
        if (first==null || second==null)
            return first==null && second==null;
        else if (first.val==second.val)
            return isSubtree(first.left,second.left) || isSame(first.right,second.right);
        else
            return false;
    }

    //longest valid parantheses
    public static int longestValidParan(String str){
        int maxLen=0;
        int[] dp=new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i)==')'){
                if (str.charAt(i-1)=='(')
                    dp[i]=2+(i>=2?dp[i-2]:0);

                else if (i-dp[i-1]>0 && str.charAt(i-dp[i-1]-1)=='(')
                    dp[i]=2+dp[i-1]+(i-dp[i-1]>=2?dp[i-dp[i-1]-2]:0);
            }
            maxLen=Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }
    //trapping rain water
    public static int trappingRainWaterOpt(int[] arr){
        int n=arr.length;
        if (n<2) return 0;
        int trappedWater=0;
        int leftMax=arr[0];
        int rightMax=arr[n-1];
        int low=1,high=n-2;
        while (low<=high){
            if(leftMax<rightMax){
                if (arr[low]>leftMax)
                    leftMax=arr[low];
                else
                    trappedWater+=leftMax-arr[low];
                low++;
            }
            else {
                if (arr[high]>rightMax)
                    rightMax=arr[high];
                else
                    trappedWater+=rightMax-arr[high];
                high--;
            }
        }
        return trappedWater;
    }

    //dungeon game
    public static int dungeonGame(int i,int j,int[][] grid){
        if (i>=grid.length || j>=grid[0].length)
            return Integer.MAX_VALUE;
        int nextHealth=Math.min(dungeonGame(i+1,j,grid),dungeonGame(i,j+1,grid));
        if (nextHealth==Integer.MAX_VALUE)
            nextHealth=1;
        int res=0;
        //if health required in next step is greater than the cur power,
        //we need that much health-curHealth
        if (nextHealth-grid[i][j]>0)
            res=nextHealth-grid[i][j];
        else    //if cur health is sufficient for next step, we only need 1 power to reach cur pos
            res=1;
        return res;
    }

    //min no of jumps required to reach end index n-1
    public static int minNoJumps(int[] arr,int idx){
        if (idx==0)
            return 0;
        int res=Integer.MAX_VALUE;
        for (int i = 0; i <= idx-2; i++) {
            if (i+arr[i]>=idx-1){
                int subRes=minNoJumps(arr,i+1);
                if (subRes!=Integer.MAX_VALUE)
                    res=Math.min(res,subRes+1);
            }
        }
        return res;
    }

    //maximum profit stock buy and sell 3
    public static int maxProfitBuySell(int[]prices,int i,boolean buy,int k){
        if (i>=prices.length || k==0)
            return 0;
        int profit=0;
        if (buy){//if this time we have to buy we can either buy or buy next stock
            //if we buy now we next action is to sell i.e buy=false & transaction remains same
            int buyNow=maxProfitBuySell(prices,i+1,false,k)-prices[i];
            int dontBuyNow=maxProfitBuySell(prices,i+1,true,k);
            profit=Math.max(buyNow,dontBuyNow);
        }
        else {//if this time we have to SELL we can either SELL now or SELL next stock
            int sellNow=maxProfitBuySell(prices,i+1,true,k-1)+prices[i];
            int dontSellNow=maxProfitBuySell(prices,i+1,false,k);
            profit=Math.max(sellNow,dontSellNow);
        }
        return profit;
    }
    //we can either perform a transaction or dont perform a transaction
    public static int maxProfitStockBuySellKtrans(int[] prices,int k){
        int n=prices.length;
        int[][] dp=new int[k+1][n];
        for (int i = 1; i <=k; i++) {
            for (int j = 1; j < n; j++) {
                int dontSell=dp[i][j-1];//same trans but prev days
                int sell=0;
                for (int m = 0; m < j; m++) {
                    //max diff bt stock and their prev transaction value, same day ,prevoius transacton
                    sell=Math.max(sell,(prices[j]-prices[m])+dp[i-1][m]);
                }
                dp[i][j]=Math.max(sell,dontSell);
            }
        }
        return dp[k][n];
    }

    //frog jumps , frog can jump to given positions only,
    //and from there it can jump to  k-1, k,k+1 unit dist,
    //find if the frog can reach the destination
    public static boolean frogJumps(int[] stones){
        int n=stones.length;
        int lastStone=stones[n-1];
        Stack<Integer> pos=new Stack<>();
        Stack<Integer> jumps=new Stack<>();
        HashSet<Integer> stonePos=new HashSet<>();
        for (int stone : stones) {
            stonePos.add(stone);
        }
        pos.add(0);
        jumps.add(0);
        while (!pos.isEmpty()){
            int postion=pos.pop();
            int jumpDist=jumps.pop();
            //gen all postions k-1, k , k+1
            for (int i = jumpDist-1; i <=jumpDist+1; i++) {
                if (i<=0) continue;
                int nextPos=postion+i;
                if (nextPos==lastStone)
                    return true;
                //if position is  a stone we can reach that postion
                else if (stonePos.contains(nextPos)){
                    pos.add(nextPos);
                    jumps.add(i);
                }
            }
        }
        return false;
    }
    //decode strings ,"3[a2[c]]"  Output: "accaccacc"
    public static String  decodeString(String str){
        char[]arr=str.toCharArray();
        System.out.println(str);
        Stack<Integer> count=new Stack<>();
        Stack<String> words=new Stack<>();
        StringBuilder op=new StringBuilder();
        int i=0;        //iterating index
        while (i<arr.length){
            System.out.println("i "+i+" Count "+count+" words: "+words+" cur op: "+op);
            if (Character.isDigit(arr[i])){
                int num=0;
                while (Character.isDigit(arr[i])){
                    num=num*10+(arr[i]-'0');
                    i++;
                }
                count.push(num);
            }
            else if (arr[i]=='['){
                words.push(op.toString());
                op=new StringBuilder();
                i++;
            }
            else if (arr[i]==']'){
                StringBuilder tmp=new StringBuilder(words.pop());
                int cnt=count.pop();
                System.out.println("Count: "+cnt+" tmp: "+tmp+" op: "+op);
                for (int j = 0; j < cnt; j++) {
                    tmp.append(op);
                }
                op=tmp;
                i++;
            }
            else
                op.append(arr[i++]);
        }
        return op.toString();
    }
    //decode string DFS
    public static String decodeStringDFS(char[] arr,Pair i){
        StringBuilder sb=new StringBuilder();
        while (i.x<arr.length && arr[i.x]!=']'){
            if (Character.isDigit(arr[i.x])){
                int num=0;
                while (i.x<arr.length && Character.isDigit(arr[i.x]))
                    num=num*10+(arr[i.x++]-'0');
                i.x++;
                String dfsRes=decodeStringDFS(arr,i);
                while (num-->0)
                    sb.append(dfsRes);
                i.x++;
            }
            else
                sb.append(arr[i.x++]);
        }
        return sb.toString();
    }
    //find min cost to cut: given position of cuts to make,
    //cost of a cut the current lenght of the bar
    static int[] cutsPos;
    public static int minCostToCut(int i,int j,int left,int right){
        if (i<0 || j>=cutsPos.length || i>j)
            return 0;
        if (i==j)
            return right-left;
        int ans=Integer.MAX_VALUE;
        for (int k = i; k <=j; k++) {
            int curCost=right-left;
            int leftCost=minCostToCut(i,k-1,left,cutsPos[k]);
            int rightCost=minCostToCut(k+1,j,cutsPos[k],right);
            ans=Math.min(ans,curCost+leftCost+rightCost);
        }
        return ans;
    }
    //find min cost to burst all baloons
    //we just need to count how many are non overlapping intervals
    public static int minCostToBurstBaloon(int[][] points){
        //sort the array according the end point
        Arrays.sort(points, Comparator.comparingInt(a->a[1]));
        int arrow=1;
        int end=points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0]>end){
                arrow++;
                end=points[i][1];
            }
        }
        return arrow;
    }
}
