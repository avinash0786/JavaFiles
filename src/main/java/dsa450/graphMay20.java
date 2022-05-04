package dsa450;

import java.util.*;

public class graphMay20 {
    public static void main(String[] args) {
        System.out.println(decodeString("2[abc3[a2[c]]]ef"));
//        System.out.println("decode string DFS: "+decodeStringDFS("2[abc3[a2[c]]]ef".toCharArray(),new Pair(0,0)));
//        System.out.println("Count subsets sum: "+countSubSetSum(new int[]{1,2,3,4,5},15));
        maxSumProduct2Gp(new int[]{1,2,3,4,5},0,0,0);
//        System.out.println("max sum product: "+maxProd);
//        System.out.println("Max fireball penguine: "+penguineAttach(new int[]{1,3,5,6,7},new int[]{2,2,3,2,3}));
    }
    //maximum are of islands represented by 1 and 0 in a grid
    public static int maxAreaOfIsland(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int maxArea=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]==1)
                    maxArea=Math.max(maxArea,islandDFS(i,j,grid));
            }
        }
        return maxArea;
    }
    public static int islandDFS(int i,int j,int[][] grid){
        if(i<0 || j>grid.length || j<0 || j>grid[0].length || grid[i][j]==0)
            return 0;
        grid[i][j]=0;   //marking as 0 so we dont visit again
        int count=1;
        count+=islandDFS(i+1,j,grid);
        count+=islandDFS(i-1,j,grid);
        count+=islandDFS(i,j+1,grid);
        count+=islandDFS(i,j-1,grid);
        return count;
    }
    //beautiful arrangement count    i (1 <= i <= n)
    //perm[i] is divisible by i.  or i is divisible by perm[i]
    public static int countArrangement(int n) {
        boolean[] vis=new boolean[n+1];
        countBeautDFS(1,vis);
        return beautCnt;
    }
    static int beautCnt=0;
    private static void countBeautDFS(int pos,boolean[] vis){
        if (pos>=vis.length){
            beautCnt++;
            return;
        }
        for (int i = 1; i <=vis.length; i++) {
            if (!vis[i] && (pos%i==0 || i%pos==0)){
                vis[i]=true;
                countBeautDFS(pos+1,vis);
                vis[i]=false;
            }
        }
    }
    //reconstruct route, only visit a edge once and return the lexically smallest route
    public static List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String,PriorityQueue<String>> gph=new HashMap<>();
        for (List<String> tkt : tickets) {
            gph.putIfAbsent(tkt.get(0),new PriorityQueue<>());
            gph.get(tkt.get(0)).add(tkt.get(1));
        }
        LinkedList<String> result=new LinkedList<>();
        findItrDFS("JFK",gph,result);
        return result;
    }
    public static void findItrDFS(String cur,HashMap<String,PriorityQueue<String>> gph,LinkedList<String> result){
        PriorityQueue<String> ariv=gph.get(cur);
        while (ariv!=null && !ariv.isEmpty())
            findItrDFS(ariv.poll(),gph,result);
        result.addFirst(cur);
    }
    public static int penguineAttach(int[] time,int [] damage){
        int prevTime=0;
        int curPower=0;
        int fireBall=0;
        for (int i = 0; i < time.length; i++) {
            curPower+=(time[i]-prevTime);   //cur power is gain bt cur time and prev time as
            //power inc by 1 each unit time
            if (curPower<=damage[i])
                fireBall++;         //if cur power is not sufficient to handle damage fire fireball
            else
                curPower-=damage[i];    //if sufficient take damage
            prevTime=time[i];
        }
        return fireBall;
    }
    static int maxProd=1;
    public static void maxSumProduct2Gp(int[] arr,int sum1,int sum2,int index){
        if (index==arr.length){
            maxProd=Math.max(maxProd,sum1*sum2);
            return;
        }   // we exlore both the possibility by adding this element to one gp and to other gp
        maxSumProduct2Gp(arr,sum1+arr[index],sum2,index+1);
        maxSumProduct2Gp(arr,sum1,sum2+arr[index],index+1);
    }
    //count no of subset with sum of element equal to a given target
    public static int countSubSetSum(int[] arr,int target){
        int n=arr.length;
        int[][] Dp_count=new int[n+1][target+1];//count of subset with n==i and sum==j
        //if sum=0 there is one subset with that sum [] empty subset is also valid
        for (int i = 0; i <=n; i++) {
            Dp_count[i][0]=1;
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=target; j++) {
                System.out.println("N= "+i+" sum: "+j+" arr[i-1]: "+arr[i-1]);
                System.out.println();
                if (j<arr[i-1])
                    Dp_count[i][j]=Dp_count[i-1][j];
                else if (arr[i-1]<=j)
                    Dp_count[i][j]=Dp_count[i-1][j]+Dp_count[i-1][j-arr[i-1]];
                for (int[] ints : Dp_count) {
                    System.out.println(Arrays.toString(ints));
                }
                System.out.println();
            }
            System.out.println("-----------------------------");
        }
        return Dp_count[n][target];
    }
    //decode string: Input: s = "2[abc]3[cd]ef"  Output: "abcabccdcdcdef"
    //Input: s = "3[a2[c]]"  Output: "accaccacc"
    public static String decodeString(String str){
        char[] arr=str.toCharArray();
        Stack<Integer> count=new Stack<>();
        Stack<String> word=new Stack<>();
        StringBuilder op= new StringBuilder();
        int idx=0;
        System.out.println(Arrays.toString(arr));
        while (idx<arr.length){
//            System.out.println(idx+": "+arr[idx]+" Out: "+op);
            if (Character.isDigit(arr[idx])){   //for cases like 32[a]  means 32 time a, so we need to
                //create 32 using the chars
                int num=0;
                while (Character.isDigit(arr[idx]))
                    num=10*num+(arr[idx++]-'0');
                count.push(num);
            }
            else if (arr[idx]=='['){
                word.push(op.toString());
                op = new StringBuilder();
                idx++;
            }
            else if (arr[idx]==']'){
                StringBuilder temp=new StringBuilder(word.pop());
                int cnt=count.pop();
                for (int i = 0; i < cnt; i++) {
                    temp.append(op);
                }
                op=temp;
                idx++;
            }
            else {
                op.append(arr[idx++]);
            }
//            System.out.println("count: "+count+  " word: "+word);
//            System.out.println();
        }
        return op.toString();
    }
    public static String decodeStringDFS(char[] arr,Pair index){
        StringBuilder res= new StringBuilder();
        System.out.println("index: "+index.x);
        while (index.x<arr.length && arr[index.x]!=']'){
            if (Character.isDigit(arr[index.x])){
                int num=0;
                while (index.x<arr.length && Character.isDigit(arr[index.x]))
                    num=num*10+arr[index.x++]-'0';
                index.x++;  //so skip the [ which comes after the number
                String nextRes=decodeStringDFS(arr,index);
                while (num-->0)
                    res.append(nextRes);
                index.x++;
            }
            else
                res.append(arr[index.x++]);
        }
        return res.toString();
    }
    //given equation and values, and queries evaluate all the queries
    // ["a","b"] represents a/b=2.0     ["b","c"] represents b/c=3.0
    //equations = [["a","b"],["b","c"]]     values = [2.0,3.0]
    //queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    //Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,List<eqNode>> gph=buidEqGraph(equations,values);
        double[] result=new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i]=equationDFS(queries.get(i).get(0),queries.get(i).get(1),new LinkedHashSet<>(),gph);
        }
        return result;
    }
    //doing dfs to find the answer
    private static double equationDFS(String src, String dest, Set<String> vis,Map<String,List<eqNode>> gph){
        if (!(gph.containsKey(src) && gph.containsKey(dest)))
            return -1.0;
        if (src.equals(dest))
            return 1.0;
        vis.add(src);
        for(eqNode ng:gph.get(src)){
            if (!vis.contains(ng.key)){
                double ans=equationDFS(ng.key,dest,vis,gph);
                if (ans!=-1.0)
                    return ans*ng.val;
            }
        }
        return -1.0;
    }
    //building the graph representing directed graph equation
    private static Map<String,List<eqNode>> buidEqGraph(List<List<String>> equations, double[] values){
        Map<String,List<eqNode>> graph=new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String src= equations.get(i).get(0);
            String dest= equations.get(i).get(1);

            graph.putIfAbsent(src,new ArrayList<>());
            graph.putIfAbsent(dest,new ArrayList<>());

            graph.get(src).add(new eqNode(dest,values[i]));     //adding link a->b with value val[i]
            graph.get(dest).add(new eqNode(src,1/values[i]));   //adding link b->a with value 1/val[i]
        }
        return graph;
    }
    //finding the right interval to all given interval array
    public static int[] findRightInterval(int[][] intervals) { //naive o(n^2)
        int[] res=new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int idx=-1;
            int minStart=Integer.MAX_VALUE;
            for (int j = 0; j < intervals.length; j++) {
                if (intervals[j][0]>intervals[i][1] && minStart>intervals[j][0]){
                    idx=j;
                    minStart=intervals[j][0];
                }
            }
            res[i]=idx;
        }
        return res;
    }
    //using binary search
    public static int[] findRightIntervOptimized(int[][] intervals) { //naive o(nlogn)
        int n=intervals.length;
        int[] res=new int[n];
        HashMap<Integer,Integer> startIndex=new HashMap<>();
        int[] first=new int[n];
        for (int i = 0; i <n; i++) {
            startIndex.put(intervals[i][0], i);
            first[i]=intervals[i][0];
        }
        Arrays.sort(first);

        for (int i = 0; i < intervals.length; i++) {
            int l=0,h=n-1,end=intervals[i][1];
            int min=-1;boolean found=false;
            while (l<=h){
                int mid=l+((h-l)/2);
                if (first[mid]>=end){
                    h=mid-1;
                    found=true;
                    min=first[mid];
                }
                else
                    l=mid+1;
            }
            res[i]=found?startIndex.get(min):-1;
        }
        return res;
    }
    private static int binSerInterval(int[] arr,int key){
        int s=0,e=arr.length-1,min=-1;
        while (s<e){
            int mid=s+(e-s)/2;
            if (arr[mid]>=key) {
                min=arr[mid];
                e = mid - 1;
            }
            else
                s=mid+1;
        }
        return min;
    }
}

class eqNode{
    String key;
    double val;
    eqNode(String s,double v){
        this.key=s;
        this.val=v;
    }
}
/*
public List<Integer> nums;
    public int[] sums;
    public int possibleSquareSide;

    public Solution() {
        this.sums = new int[4];
    }

    // Depth First Search function.
    public boolean dfs(int index) {

        // If we have exhausted all our matchsticks, check if all sides of the square are of equal length
                System.out.println("index: "+index+" sum: "+Arrays.toString(this.sums));

        if (index == this.nums.size()) {
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
        }
        // Get current matchstick.
        int element = this.nums.get(index);

        // Try adding it to each of the 4 sides (if possible)
        for(int i = 0; i < 4; i++) {
            if (this.sums[i] + element <= this.possibleSquareSide) {
                this.sums[i] += element;
                if (this.dfs(index + 1)) {
                    return true;
                }
                this.sums[i] -= element;
            }
        }

        return false;
    }

    public boolean makesquare(int[] nums) {
        // Empty matchsticks.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Find the perimeter of the square (if at all possible)
        int L = nums.length;
        int perimeter = 0;
        for(int i = 0; i < L; i++) {
            perimeter += nums[i];
        }

        this.possibleSquareSide =  perimeter / 4;
        if (this.possibleSquareSide * 4 != perimeter) {
            return false;
        }

        // Convert the array of primitive int to ArrayList (for sorting).
        this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(this.nums, Collections.reverseOrder());
        System.out.println(this.nums);
        return this.dfs(0);
    }
 */