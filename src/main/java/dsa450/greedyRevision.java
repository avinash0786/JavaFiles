package dsa450;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class greedyRevision {
    public static void main(String[] args) {
        barnChickReach(3,10,5,new int[]{0,2,3,5,7},new int[]{2,1,1,1,4});
    }
    //  [node][smaller][larger]  node-left-right preorder
    public static NodeBST constBSTpreorder(int[] pre){
        NodeBST root=new NodeBST(pre[0]);
        Stack<NodeBST> stk=new Stack<>();
        stk.push(root);
        for (int i = 1; i < pre.length; i++) {
            NodeBST temp=null;
            //find a element which is greater
            while (!stk.isEmpty() && stk.peek().data<pre[i])
                temp=stk.pop();

            if (temp!=null){
                temp.right=new NodeBST(pre[i]);
                stk.push(temp.right);
            }
            else {
                //if we could not find a smaller element than this pre[i] is left child of temp
                stk.peek().left=new NodeBST(pre[i]);
                stk.push(stk.peek().left);
            }
        }
        return root;
    }
    //wildcard matching  ? -match any single character
    //* - match any sequence of character
    public boolean wildCardMatch(String s, String p) {
        str=s;
        pat=p;
        DpWildCard=new Boolean[s.length()][p.length()];
        return isMatchUtil(0,0);
    }
    static String str;
    static String pat;
    static Boolean[][] DpWildCard;
    public static boolean isMatchUtil(int i,int j){
        if (i==str.length() && j==pat.length())
            return true;
        if (i==str.length()){
            for (int k = j; k < pat.length(); k++) {
                if (pat.charAt(k)!='*')
                    return false;
            }
            return true;
        }
        if (j==pat.length() && i!=str.length())
            return false;
        if (DpWildCard[i][j]!=null)
            return DpWildCard[i][j];
        boolean ans=false;
        if (pat.charAt(j)=='?'){
            if (isMatchUtil(i+1,j+1))
                ans=true;
        }
        else if (pat.charAt(j)=='*'){
            if (isMatchUtil(i,j+1))       //match 0 char
                ans=true;
            if (isMatchUtil(i+1,j+1))   //match 1 char
                ans=true;
            if (isMatchUtil(i+1,j)) //match more chars
                ans=true;
        }
        else {
            if (str.charAt(i-1)==pat.charAt(j-1) && isMatchUtil(i+1,j+1))
                ans=true;
        }
        return DpWildCard[i][j]=ans;
    }

    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
    //remove min no of interval to make all given interval non-overlapping
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int count=0;
        int n=intervals.length;
        int left=0;
        int right=1;
        while (right<n){
            if (intervals[left][1]<=intervals[right][0]){
                left=right;
                right++;
            }
            else if (intervals[left][1]<=intervals[right][1]){
                count++;
                right++;
            }
            else if (intervals[left][1]>intervals[right][1]){
                count++;
                left=right;
                right++;
            }
        }
        return count;
    }
    public static int eraseOverlapInt02(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int count=0;
        int[] prev=intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (prev[1]>intervals[i][0]){
                count++;
                if (prev[1]>intervals[i][1])
                    prev=intervals[i];
            }
            else
                prev=intervals[i];
        }
        return count;
    }

    public static void barnChickReach(int minChick,int barnDist,int maxtime,int[] initLoc,int[] speed){
        int n=initLoc.length;
        int count=0;
        int cantReach=0;
        int swap=0;
        for (int i = n-1; i >=0 ; i--) {
            int dist=barnDist-initLoc[i];
            int canCover=maxtime*speed[i];
            //if distToreach is < the dist chik can cove we inc the count of reach chick
            if (dist<=canCover){
                count++;
                //if there are chick ahead which cant reach the barn we need to
                // swap them with the cur reachable chik
                if (cantReach>0)
                    swap+=cantReach;
                //we dont reset cantReach chik bcoz this chill will need to be swapped again if some other
                //chick is not reachable and there is reachable chick behind that, then all unreachable chik
                //need to be swapped
            }
            else {  //if this chick cant reach inc count of cant reach chik by 1
                cantReach++;
            }
            if (count==minChick)
                break;
        }
        if (count<minChick)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println("min Swap req: "+swap);
    }

}
/*
Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

Arrays.sort(intervals, (o1, o2) -> o1[1]-o2[1]);

Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));


 */