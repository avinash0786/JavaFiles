package dsa450;

import java.util.*;

public class dfsBfsRevMay06 {
    public static void main(String[] args) {
//        System.out.println("No of subarray sum=k: "+subArraySumk(new int[]{1, 2, -3, 1, 2, -3, 1, 2, -3},3));
//        System.out.println("Sub array opt: "+countSubArraySumK(new int[]{1, 2, -3, 1, 2, -3, 1, 2, -3},3));
        System.out.println("Course schedule : "+canFinish(3,new int[][]{{0,1},{1,2},{2,3}}));
    }
    public static List<Integer> findMinHeight(int n,int[][] edges){
        List<Integer> res=new ArrayList<>();
        if (n<=0)
            return res;
        if (n==1){
            res.add(0);
            return res;
        }
        int[] degree=new int[n];
        List<List<Integer>> adj=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            adj.get(edge[1]).add(edge[0]);
            adj.get(edge[0]).add(edge[1]);
        }
        Queue<Integer> que=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i]==1)
                que.add(i);
        }
        while (n>2){
            int size=que.size();
            n-=size;
            while (size-->0){
                int v=que.poll();
                for(int i:adj.get(v)){
                    degree[i]--;
                    if (degree[i]==1)
                        que.add(i);
                }
            }
        }
        res.addAll(que);
        return res;
    }
    public static boolean courseScheduleCycleBFS(int numCourse,int[][] prerequisite){
        ArrayList[] graph=new ArrayList[numCourse];
        int[] degree=new int[numCourse];
        Queue<Integer> que=new LinkedList<>();
        int count=0;
        for (int i = 0; i < numCourse; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i < prerequisite.length; i++) {
            degree[prerequisite[i][1]]++;
            graph[prerequisite[i][0]].add(prerequisite[i][1]);
        }
        for (int i = 0; i < degree.length; i++) {
            if (degree[i]==0){
                que.add(i);
                count++;
            }
        }
        while (!que.isEmpty()){
            int course=que.poll();
            for (int i = 0; i < graph[course].size(); i++) {
                int child=(int)graph[course].get(i);
                degree[child]--;
                if (degree[child]==0){
                    que.add(child);
                    count++;
                }
            }
        }
        return  (count==numCourse);
    }
    //detect cycle directed graph :: COURSE SCHEDULE
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> adjList=new HashMap<>();
        for (int[] pair : prerequisites) {
            if (adjList.containsKey(pair[0])){
                adjList.get(pair[0]).add(pair[1]);
            }
            else {
                adjList.put(pair[0],new ArrayList<>(Arrays.asList(pair[1])));
            }
        }

        boolean[] vis=new boolean[numCourses+1];
        boolean[] processing=new boolean[numCourses+1];
        for (int i = 0; i < numCourses; i++) {
            if (!vis[i] && adjList.containsKey(i)){
                if (checkCycleSchedule(vis,processing,i,adjList))
                    return true;
            }
        }
        return false;
    }
    private static boolean checkCycleSchedule(boolean[] vis,boolean[] process,int source,Map<Integer,List<Integer>> adjList){
        vis[source]=true;
        process[source]=true;
        if (!adjList.containsKey(source))
            return true;
        for(int chid:adjList.get(source)){
            if (!vis[chid]){
                if (checkCycleSchedule(vis,process,chid,adjList))
                    return true;
            }
            else if (vis[chid] && process[chid])
                    return false;
        }

        process[source]=false;
        return false;
    }
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathsSum=new ArrayList<>();
        rootToLeafPath(root,targetSum,new ArrayList<>());
        return pathsSum;
    }
    static List<List<Integer>> pathsSum;
    public static void rootToLeafPath(TreeNode root,int sum,List<Integer> temp){
        if (root==null)
            return;
        temp.add(root.val);
        if (root.val==sum && root.left==null && root.right==null){
            pathsSum.add(temp);
        }
        rootToLeafPath(root.left,sum-root.val,new ArrayList<>(temp));
        rootToLeafPath(root.right,sum-root.val,new ArrayList<>(temp));
    }

    //finding no of subarray with sum k
    public static int countSubArraySumK(int[] arr,int sum){
        int count=0;
        int curSum=0;
        HashMap<Integer,Integer> prefSum=new HashMap<>();
        prefSum.put(0,1);
        for (int i = 0; i < arr.length; i++) {
            curSum+=arr[i];
            if (prefSum.containsKey(curSum-sum)){
                count+=prefSum.get(curSum-sum);
            }
            prefSum.put(curSum,prefSum.getOrDefault(curSum,0)+1);
//            System.out.println(prefSum+" count: "+count);
        }
        return count;
    }

    //finding no of subarray of sum--given sum
    public static int subArraySumk(int[] arr,int sum){
        int n=arr.length;
        int[] prefSum=new int[n];
        prefSum[0]=arr[0];
        for (int i = 1; i < n; i++) {
            prefSum[i]=arr[i]+prefSum[i-1];
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(prefSum));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int curSum=prefSum[j]-((i==0)?0:prefSum[i-1]);
                if (sum==curSum)
                    System.out.println("i: "+i+" j: "+j+" curSum: "+curSum+" match Found");
                else
                System.out.println("i: "+i+" j: "+j+" curSum: "+curSum);
            }
        }
        return 0;
    }
    public static int maxSubarraySum(int[]arr){
        int result=0;
        int curSum=0;
        for (int i = 0; i < arr.length; i++) {
            curSum=Math.max(curSum,curSum+arr[i]);
            result=Math.max(curSum,result);
        }
        return result;
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
    public static int wordSearch(char[][] board,String word){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]==word.charAt(0)) {
                    wordSUtilMain(board, word,"", i, j, 0);
                }
            }
        }
        return res;
    }
    static int res;
    public static void wordSUtilMain(char[][] board,String word,String cur,int i,int j, int index){
        if (i<=-1 || i>=board.length || j<=-1 || j>=board[0].length || board[i][j]!=word.charAt(index))
            return;
        char temp=board[i][j];
        board[i][j]='*';
        String current=cur+word.charAt(index);
        if (current.equals(word)){
            res++;
            return;
        }
                wordSUtilMain(board,word,current,i+1,j,index+1);
                wordSUtilMain(board,word,current,i,j+1,index+1);
                wordSUtilMain(board,word,current,i-1,j,index+1) ;
                wordSUtilMain(board,word,current,i,j-1,index+1);

                wordSUtilMain(board,word,current,i-1,j-1,index+1) ;
                wordSUtilMain(board,word,current,i+1,j+1,index+1) ;
                wordSUtilMain(board,word,current,i-1,j+1,index+1) ;
                wordSUtilMain(board,word,current,i+1,j-1,index+1);

        board[i][j]=temp;
    }

}
/*
Scanner inp=new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t=inp.nextInt();
        for (int i=1;i<=t;i++){
            int m=inp.nextInt();
            int n=inp.nextInt();
            char[][] grid=new char[m][n];
            for (int j = 0; j < m; j++) {
                String tmep=inp.next();
                grid[j]=tmep.toCharArray();
            }
            res=0;
            System.out.println("Case "+i+": "+wordSearch(grid,inp.next()));
        }
1
3
4
catt
aata
tatc
cat
 */