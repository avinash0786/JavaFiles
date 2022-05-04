package dsa450;

import java.util.*;

public class mayCodePractice {
    public static void main(String[] args) {
        System.out.println("Longest palindromic substring: "+longPalSubsequence("agbdba"));
//        generateAllSubsequence(0,new int[]{1,2,3},new ArrayList<>());
//        System.out.println(op);
//        System.out.println("Max closest sum: "+maxClosestSum(new int[]{1,2,3,4,5},0,0,7));
    }
    public static int maxClosestSum(int[]arr,int index,int cuSum,int target){
        if (cuSum>target)
            return 0;
        if (index==arr.length-1)
            return cuSum;
        int pick=maxClosestSum(arr,index+1,cuSum+arr[index],target);
        int dontPick=maxClosestSum(arr,index+1,arr[index],target);
        return Math.max(pick,dontPick);
    }
    //we use Dp here,as we know a single char/ 1-length is a palindrome
    //we also know the lenght 2 substring is palindrome of both equal
    public static int longestPalindromicSubString(String str){
        int n=str.length();
        int[][] dp=new int[n][n];
        //single length char is a palindrome, i.e 0-0, 1-1, 2-2, 3-3
        for (int i = 0; i < n; i++)
            dp[i][i]=1;
        int start=0;
        int maxLength=1;
        //length 2 substring is palindrome if both are equal
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = 1;
                start = i;
                maxLength = 2;
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        //similarly we  check if length 3 is a palindrome of corner elements are same
        // if both edge element are equal we find dp of inner elements
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
    //longest palindromic subSequence
    public static int longPalSubsequence(String str){
        int n=str.length();
        int[][] dp=new int[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i]=1;
        int start=0;
        int maxLength=1;
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = 2;
                start = i;
                maxLength = 2;
            }
            else
                dp[i][i + 1] = 1;
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        for (int k = 3; k <=n; k++) {
            for (int i = 0; i < n-k+1; i++) {
                int j=i+k-1;
                if (str.charAt(i)==str.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;    //  +2 bcoz the char at end match, and this adds 2 len to inner elements
                    if (k>maxLength){
                        start=i;
                        maxLength=k;
                    }
                }
                else    //take max if previous combinations 2 eg abd  a & d dont match
                    // so take max pal len of (ab, bd), similar for bigger elements
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        System.out.println("max palind subSequence: "+str.substring(start,start+maxLength));
        return maxLength;
    }
    public static int maxContainerArea(int[] height){
        int area=0;
        int l=0;
        int r=height.length-1;
        while (l<r){
            int lh=height[l];
            int rh=height[r];
            int minLen=Math.min(lh,rh);
            int dist=r-l;
            int curArea=minLen*dist;
            area=Math.max(area,curArea);
            if (lh<rh)
                l++;
            else
                r--;
        }
        return area;
    }
    static List<List<Integer>> op=new ArrayList<>();
    public static void generateAllSubsequence(int index, int[] arr, List<Integer> temp){
        if (index==arr.length){
            op.add(new ArrayList<>(temp));
            return;
        }
        generateAllSubsequence(index+1,arr,temp);
        temp.add(arr[index]);
        generateAllSubsequence(index+1,arr,temp);
        temp.remove(temp.size()-1);
    }
    public static int minCost(int n, int[] cuts) {
        cutsArr=cuts;
        Arrays.sort(cuts);
        return findMinCost(0,cuts.length-1,0,n);
    }
    // i and j are index in the array
    // left and right are the lengths in both sides
    static int[] cutsArr;
    static Integer[][] DP_Cuts=new Integer[109][109];
    public static int findMinCost(int i,int j,int left,int right){
        if (i<0 || j>=cutsArr.length || i>j)
            return 0;
        if (i==j) return DP_Cuts[i][j]=right-left;
        //if only one cut is there
        // eg: line  4 5 6 7  if we need to cut at 5 the cost is 7-4=[3]
        if (DP_Cuts[i][j]!=null)
            return DP_Cuts[i][j];
        int ans=Integer.MAX_VALUE;
        for (int k = i; k <=j; k++) {
            int curCost=right-left;
            int leftPart=findMinCost(i,k-1,left,cutsArr[k]);
            int rightPart=findMinCost(k+1,j,cutsArr[k],right);
            ans=Math.min(ans,leftPart+rightPart+curCost);
        }
        return DP_Cuts[i][j]=ans;
    }

    public static int asFarFromLand(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        Queue<Pair> que=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]==1)
                    que.add(new Pair(i,j));
            }
        }
        if (que.size()==m*n || que.size()==0)
            return -1;  //if all is water OR all is land, there is no connection
        int dist=0;
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};

        while (!que.isEmpty()){
            int size=que.size();
            while (size-->0){
                Pair cur=que.poll();
                int i=cur.x;
                int j=cur.y;
                for (int k = 0; k < 4; k++) {
                    int nX=i+dx[k];
                    int nY=j+dy[k];
                    if (nX>=0 && nX<n && nY>=0 && nY<m && grid[nX][nY]==0){
                        que.add(new Pair(nX,nY));
                        grid[nX][nY]=1;
                    }
                }
            }
            dist++;
        }
        return dist-1;
    }
    
}
