package dsa450;

import java.util.*;

//sting practice
public class stringsLearnMay14 {
    public static void main(String[] args) {
//        System.out.println(longestValidParentheses("(())(()())"));
        shortestDistDAC(new int[][]{
                {0,2,0,0,1,0},
                {0,0,3,0,0,0},
                {0,0,0,6,0,0},
                {0,0,0,0,0,0},
                {0,0,2,0,0,4},
                {0,0,0,1,0,0}
        },0);
    }
    //longest length substring with distince chars
    public static int distinctSubStrLen(String str){
        int maxLen=0;
        char[] arr=str.toCharArray();
        int n=arr.length;
        for (int i = 0; i < n; i++) {
            boolean[] visited=new boolean[256];
            for (int j = 0; j < n; j++) {
                if (visited[arr[j]])
                    break;
                else {
                    maxLen=Math.max(maxLen,j-i+1);
                    visited[arr[i]]=true;
                }
            }
        }
        return maxLen;
    }
    public static int maxLenDistinctSubStr(String str){
        char[] arr=str.toCharArray();
        int n=arr.length;
        int[] prevIndex=new int[256];
        int maxLen=0;
        int start=0;
        Arrays.fill(prevIndex,-1);
        //maintaining the max of start index is crucial so that we dont encounter a greater
        //prev index which may have repeating other index
        for (int i = 0; i < n; i++) {
            start=Math.max(start,prevIndex[arr[i]]+1);
            int maxEnd=i-start+1;
            maxLen=Math.max(maxLen,maxEnd);
            prevIndex[arr[i]]=i;
        }
        return maxLen;
    }
    public static String convert(String str, int numRows) {
        if (numRows==1)
            return str;
        StringBuilder op=new StringBuilder();
        char[] arr=str.toCharArray();
        int n=arr.length;
        int cycleLen=2*numRows-2;   //subtracting 2 for the upper and lower most nodes in bt
        //we will be going to each row and print all chars coming in that row using index
        for (int i = 0; i < numRows; i++) {
            //in each row iter will add the cur col char and next up going char
            for (int j = 0; i+j < n; j+=cycleLen) {
                op.append(arr[i+j]);    //for the char in cur column
                if (i!=0 && i!=numRows-1 && j+cycleLen-i<n)
                    op.append(arr[j+cycleLen-i]);
            }
        }
        return op.toString();
    }
    public static int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        System.out.println(Arrays.toString(s.toCharArray()));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(')
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;

                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(')
                    dp[i] = dp[i - 1] + 2 +  ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0);

                System.out.println("index: "+i);
                System.out.println(Arrays.toString(dp));
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
    //checking if given num as string is a valid number in given format
    public boolean isValidNumber(String str) {
        boolean firstPart=false;
        boolean secondPart=false;
        boolean dot=false;
        boolean e=false;
        int index=0;

        if (str.charAt(index)=='-' || str.charAt(index)=='+')
            index++;
        while (index<str.length()){
            char c=str.charAt(index);
            //if there is a +/- not at index 0 then there should be e/E before them
            if (c=='+' || c=='-'){
                if (str.charAt(index-1)!='e' && str.charAt(index-1)!='E')
                    return false;
            }
            //if there is dot . then there cant be dot earlier and a e/E earlier
            else if (c=='.'){
                if (dot || e)
                    return false;
                dot=true;
            }
            // if there is a e/E ther cant be e/E earlier and must be first part
            else if (c=='e' || c=='E'){
                if (e || !firstPart)
                    return false;
                e=true;
            }
            //if this is a digit then this is first part if ther is no e/E earlier else this must be second part
            else if (Character.isDigit(c)){
                if (e)
                    secondPart=true;
                else
                    firstPart=true;
            }
            else
                return false;
            index++;
        }
        //if there is e/E there has to be second part else NO
        return firstPart && ((!e && !secondPart) || (e && secondPart));
    }

    //simplify the given path directory structure to a simplified canonical path
    public static String simplifyDirPath(String str){
        char[] arr=str.toCharArray();
        int n=arr.length;
        Stack<String> stk=new Stack<>();
        int idx=0;
        while (idx<n){
            while (idx<n && arr[idx]=='/')
                idx++;
            StringBuilder cur=new StringBuilder();
            while (idx<n && arr[idx]!='/'){
                cur.append(arr[idx]);
                idx++;
            }
            String rec=cur.toString();
            switch (rec) {
                case "":
                    idx++;
                    continue;
                case "..":
                    if (!stk.isEmpty())
                        stk.pop();
                    break;
                case ".":
                    continue;
                default:
                    stk.push(rec);
                    break;
            }
            idx++;
        }
        Stack<String> reversed=new Stack<>();
        while (!stk.isEmpty())
            reversed.push(stk.pop());
        StringBuilder op=new StringBuilder();
        while (!reversed.isEmpty())
            op.append("/").append(reversed.pop());
        return op.toString().equals("") ?"/":op.toString();
    }

    //find no of ways to decode the given string containing numbers
    //mapping A->1 B->2 and Z->26  we can consider a single char and a double char <=26
    //if char is 0 we cant consider it as single
    //ip: 226
    public static int decodeWaysNum(String str, int index){
        if (index==str.length())
            return 1;
        if (str.charAt(index)=='0')
            return 0;
        //the last char can always be
        if (index==str.length()-1)
            return 1;
        int singleDigit=decodeWaysNum(str,index+1);
        int twoDigit=0;
        if (Integer.parseInt(str.substring(index,index+2))<=26)
            twoDigit=decodeWaysNum(str,index+2);
        return singleDigit+twoDigit;
    }
    public static boolean isInterleave(String s1, String s2, String s3) {
        str1=s1;str2=s2;str3=s3;
        l1=s1.length();l2=s2.length();l3=s3.length();
        if (l3!=l1+l2)
            return false;
        interLeaveMap=new HashMap<>();
        return checkInterleaveUtil(0,0,0);
    }
    static String str1,str2,str3;
    static int l1,l2,l3;
    static HashMap<String,Boolean> interLeaveMap;
    public static boolean checkInterleaveUtil(int p1,int p2,int p3){
        if (p3==l3)//base case if p3 reached its end, check if both p1 & p2 are also at the end
            return p1 == l1 && p2 == l2;
        String key=p1+"#"+p2+"#"+p3;
        if (interLeaveMap.containsKey(key))
            return interLeaveMap.get(key);
        //if string s1 is parsed, we need to check the s2 mathces s3
        if (p1==l1){
            boolean ans= str2.charAt(p2) == str3.charAt(p3) && checkInterleaveUtil(p1, p2 + 1, p3 + 1);
            interLeaveMap.put(key,ans);
            return interLeaveMap.get(key);
        }
        //if string s2 is parsed, we need to check s1 if it matches with  rest of s3
        if (p2==l2){
            boolean ans= str1.charAt(p1) == str3.charAt(p3) && checkInterleaveUtil(p1+1, p2, p3 + 1);
            interLeaveMap.put(key,ans);
            return interLeaveMap.get(key);
        }
        //if both pointer are ne min, we will check if the char at both the pointer are same p1==p3 or p2==p3
        boolean one=false,two=false;
        if (str1.charAt(p1)==str3.charAt(p3))       //if p1==p3
            one=checkInterleaveUtil(p1+1,p2,p3+1);
        if (str2.charAt(p2)==str3.charAt(p3))       //p2==p3
            two=checkInterleaveUtil(p1,p2+1,p3+1);
        interLeaveMap.put(key,one || two);      //if any of p1 p2 gives the answer we store it in our map
        return interLeaveMap.get(key);
    }

    //egg dropping problem
    public static int eggDropThreshold(int eggs,int floor){
        if (floor==1 || floor==0)
            return floor;
        if (eggs==1)
            return floor;
        return 0;
    }
    //shortest path DAG
    public static int[] shortestDistDAC(int[][] graph,int src){
        //do a topological sort and then find the shortest path for DAG
        int n=graph.length;
        int[] indegree=new int[n];
        int[] topoSort=new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j]!=0)
                    indegree[j]++;
            }
        }
        Queue<Integer> que=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i]==0)
                que.add(i);
        }
        System.out.println(Arrays.toString(indegree));
        int idx=0;
        while (!que.isEmpty()){
            int par=que.poll();
            topoSort[idx++]=par;
            for (int i = 0; i < n; i++) {
                //for each child we decrement its indegree and if
                // indegree becomes 0  we add it to queue
                if (graph[par][i]!=0 && --indegree[i]==0)
                    que.add(i);
            }
        }
        System.out.println(Arrays.toString(topoSort));
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        //we  go to each element in out topo sort
        //and go to their childrens and calculate their min dist
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j]!=0 && dist[i]!=Integer.MAX_VALUE){
                    dist[j]=Math.min(dist[j],dist[i]+graph[i][j]);
                }
            }
        }
        System.out.println(Arrays.toString(dist));
        return dist;
    }

    //symmetric tree
    public static boolean isSymmetricTree(TreeNode root){
        Queue<TreeNode> que=new LinkedList<>();
        que.add(root);
        que.add(root);
        while (!que.isEmpty()){
            TreeNode t1=que.poll();
            TreeNode t2=que.poll();
            if (t1==null && t2==null)
                continue;
            if (t1==null || t2==null)
                return false;
            if (t1.val!=t2.val)
                return false;
            //we need to equate the outer most and then coming to inner ones as pair
            que.add(t1.left);
            que.add(t2.right);
            que.add(t1.right);
            que.add(t2.left);
        }
        return true;
    }
}
