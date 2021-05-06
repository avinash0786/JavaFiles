package dsa450;

import java.util.*;

//BACKTRACK AND RECURSION REVISION PRACTICE
public class backtrackRecMay01 {
    public static void main(String[] args) {
        op=new ArrayList<>();
        num="25".toCharArray();
        System.out.println(permute(new int[]{1,2,3}));
        genNumberPermutation(0,0);
        System.out.println(op);
        spiralTraversalMatrix(new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}});
//        System.out.println("reorganize string: "+reorganiseString("aab"));
        System.out.println("Min path sum: "+minPathSumDp(new int[][]{{1,3,1},{4,5,1},{4,2,1}},0,0));
        System.out.println("Tabulation Path SUM: "+minPathSumTabulation(new int[][]{{1,2,3},{4,5,6}}));
        System.out.println("decimal to hexadecimal: "+toHexaDecimal(7562));
    }

    //detect loop and return the loop start pointer
    public static ListNode detechLoopLL(ListNode head){
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast)
                break;
        }
        if (slow!=fast)
            return head;
        slow=head;
        while (slow.next!=head.next){
            slow=slow.next;
            fast=fast.next;
        }
        fast.next=null;
        return head;
    }

    //K-th symbol in grammer
    // 0->01 , 1-> 10  we change the occurrence of 0 and 1
    public static int kthGrammer(int n,int k){
        if (n==1 && k==1)
            return 0;
        int mid=(int) Math.pow(2,n-1)/2;
        System.out.println("n: "+n+" k: "+k+" mid: "+mid);
        if (k<=mid)
            return kthGrammer(n-1,k);
        else
            return 1-kthGrammer(n-1,k-mid);
    }
    static ArrayList<Integer> op;
    static char[] num;
    public static void genNumberPermutation(int cur,int len){
        if (len>=num.length)
            return;
        for (int i = 0; i < num.length; i++) {
            int temp=cur*10+(num[i]-'0');
//            int temp1 =cur*10+Integer.parseInt(String.valueOf(num[i]));
            op.add(temp);
            genNumberPermutation(temp,len+1);
        }
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new LinkedHashSet<>(), nums); // iteration order = insertion order
        return list;
    }

    private static void backtrack(List<List<Integer>> list, Set<Integer> tmpList, int[] nums) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList(tmpList));
        } else {
            for (int num : nums) {
                if (tmpList.add(num)) { // elements are unique
                    backtrack(list, tmpList, nums); // recursive call
                    tmpList.remove(num); // backtrack
                }
            }
        }
    }
    //Generete subsets
    static int[] arr=new int[]{1,2,3};
    public static void genSubsets(int index, HashSet<Integer> set){ //  O(2^n*N) N for printing
        if (index>=arr.length) {
            System.out.println(set);
            return;
        }
        set.add(arr[index]);
        genSubsets(index+1,set);
        set.remove(arr[index]);
        genSubsets(index+1,set);
    }

    public static void spiralTraversalMatrix(int[][] mat){
        int top=0;
        int right=mat[0].length;
        int bottom=mat.length;
        int left=0;
        ArrayList<Integer> op=new ArrayList<>();
        while (top<bottom && left<right){
            for (int i = left; i <right; ++i)
                op.add(mat[top][i]);
            top++;

            for (int i = top; i <bottom; ++i)
                op.add(mat[i][right-1]);
            right--;

            if (top<bottom){
                for (int i = right-1; i >=left; --i)
                    op.add(mat[bottom-1][i]);
                bottom--;
            }

            if (left<right){
                for (int i = bottom-1; i >=top; --i)
                    op.add(mat[i][left]);
                left++;
            }
        }
        System.out.println(op);
    }

    public static String reorganiseString(String str){
        Map<Character,Integer> count=new HashMap<>();
        for (char c:str.toCharArray()) {
            count.put(c,count.getOrDefault(c,0)+1);
        }
        PriorityQueue<Character> maxHeap=new PriorityQueue<>((a,b)->count.get(b)-count.get(a));
        maxHeap.addAll(count.keySet());
        StringBuilder out=new StringBuilder();
        while (maxHeap.size()>1){
            char first=maxHeap.poll();
            char second=maxHeap.poll();
            out.append(first);
            out.append(second);
            count.put(first,count.get(first)-1);
            count.put(second,count.get(second)-1);
            if (count.get(first)>0)
                maxHeap.add(first);
            if (count.get(second)>0)
                maxHeap.add(second);
        }
        if (!maxHeap.isEmpty()){
            char last=maxHeap.poll();
            if (count.get(last)>1)
                return "";
            out.append(last);
        }
        return out.toString();
    }

    //check leaf at same level
    static int levelLeaf=0;
    public static boolean checkLeftLevel(TreeNode root,int level){
        if (root==null)
            return true;
        if (root.left==null && root.right==null){
            if (levelLeaf==0) {
                levelLeaf = level;
                return true;
            }
            return levelLeaf==level;
        }
        return checkLeftLevel(root.left,level+1) && checkLeftLevel(root.right,level+1);
    }
    //min dist bt 2 given nodes in a bt
    //use lowest common ancestor and find distance of both nodes in tree and return sum of bot dist

    //left left node sum
    static int leftLeafSum=0;
    public static int sumOfLeftLeafNode(TreeNode root){
        getLftSum(root);
        return leftLeafSum;
    }
    private static void getLftSum(TreeNode root){
        if (root==null)
            return;
        if (isLeftLeaf(root.left))
            leftLeafSum+=root.left.val;
        getLftSum(root.left);
        getLftSum(root.right);
    }
    private static boolean isLeftLeaf(TreeNode root){
        if (root==null)
            return false;
        if (root.left==null && root.right==null)
            return true;
        return false;
    }

    // temporary node to keep track of Node returned
// from previous recursive call during backtrack
    static TreeNode temp = null;
    static int k;

    // recursive function to calculate Kth ancestor
    static TreeNode kthAncestorDFS(TreeNode root, int node )
    {
        // Base case
        if (root == null)
            return null;
        //if we are getting valid node i.e notNull we will decrement the k value
        if (root.val == node||
                (temp = kthAncestorDFS(root.left,node)) != null ||
                (temp = kthAncestorDFS(root.right,node)) != null)
        {
            if (k > 0)
                k--;

            else if (k == 0)
            {
                // print the kth ancestor
                System.out.print("Kth ancestor is: "+root.val);

                // return null to stop further backtracking
                return null;
            }

            // return current node to previous call
            return root;
        }
        return null;
    }


    //deepest smallest subTree
    public static TreeNode subTreeAllDepest(TreeNode root){
        if (root==null)
            return null;
        int leftDepth=getdepth(root.left);
        int rightDepth=getdepth(root.right);
        if (leftDepth==rightDepth)
            return root;
        else {
            if (leftDepth>rightDepth)
                return subTreeAllDepest(root.left);
            else
                return subTreeAllDepest(root.right);
        }
    }
    private static int getdepth(TreeNode root){
        if (root==null)
            return 0;
        return 1+Math.max(getdepth(root.left),getdepth(root.right));
    }

    //subtree duplicates
    //we assign the smallest nodes a identifier as
    // rootnode+leftnode+rightnode
    //root node has 0 identifier
    //and as we go up the subtree we make the subtree identifier
    // using the curnode and the uid of left node and the right node
    // when we find a new identifier we assing the id t and increment t,
    //and also we store this id in count map
    //when we encounter any id to be already in may we  increment its count in count map
    //if count ==2 we add the node to answer
    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap();
        count = new HashMap();
        ans = new ArrayList();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        //assing a new uid by incrementing t if serial in not present
        int uid = trees.computeIfAbsent(serial, x-> t++);
        //increment the uid by 1 if present and put 1 if not present
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        //whenever the count ==2 we found a duplicate we add this node to ans
        if (count.get(uid) == 2)
            ans.add(node);
        return uid;
    }

    public static int searchInRotatedSorted(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]){
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
    public static int findPivot(int[] nums){
        int start=0;
        int end=nums.length-1;
        if (end==0)
            return 0;
        if (nums[start]<nums[end])
            return 0;
        while (start<=end){
            int mid=start+(end-start)/2;
            System.out.println("mid: "+mid+" start: "+start+" end: "+end);
            if (nums[mid]>nums[mid+1])
                return mid+1;
            if (nums[mid-1]>nums[mid])
                return mid;
            if (nums[mid]>nums[0])
                start=mid+1;
            else
                end=mid-1;
        }
        return -1;
    }
    public static int minInSortedRotated(int[] arr){
        int s=0;
        int e=arr.length-1;
        while (s<e){
            int mid=s+(e-s)/2;
            //if end element if less that the mid, our inversion is in right
            //as in sorted aray the end is the greatest element
            if (arr[e]<arr[mid])
                s=mid+1;
            else
                e=mid;
        }
        return s;
    }
    public static int findMinSortRotDuplicated(int[] arr) {
        int s=0;
        int e=arr.length-1;
        while (s<e){
            int mid=s+(e-s)/2;
            //if end element if less that the mid, our inversion is in right
            //as in sorted aray the end is the greatest element
            if (arr[e]<arr[mid])
                s=mid+1;
            else if (arr[e]==arr[mid]){
                e--;
            }
            else
                e=mid;
        }
        return arr[s];
    }
    public static int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] <= target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    public int maxSubArray(int[] nums) {
        int sum=nums[0];
        int ans=nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum=Math.max(sum+nums[i],nums[i]);
            ans=Math.max(ans,sum);
        }
        return ans;
    }

    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
    public static boolean canReachDestJump(int[]arr){
        int reach=0;
        int n=arr.length-1;
        for (int i = 0; i <=reach; i++) {
            reach=Math.max(reach,i+arr[i]);
            if (reach>=n)
                return true;
        }
        return false;
    }
    static Integer[] DP_stairs;
    public static int recr(int n){
        if (n==0)
            return 1;
        if (n<0)
            return 0;
        if (DP_stairs[n]!=null)
            return DP_stairs[n];
        int oneStep=recr(n-1);
        int twoStep=recr(n-2);
        return DP_stairs[n]=oneStep+twoStep;
    }
    public static int climbStairs(int n) {
        DP_stairs=new Integer[n];
        return recr(n);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m - 1, tail2 = n - 1, finished = m + n - 1;
        while (tail1 >= 0 && tail2 >= 0) {
            nums1[finished--] = (nums1[tail1] > nums2[tail2]) ?
                    nums1[tail1--] : nums2[tail2--];
        }

        while (tail2 >= 0) { //only need to combine with remaining nums2
            nums1[finished--] = nums2[tail2--];
        }
    }
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

    public static int longestValidParan(String str){
        char[] arr=str.toCharArray();
        //indx stack store the index of previous wrong paranthesis
        Stack<Integer> index=new Stack<>();
        index.add(-1);
        int maxLen=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]=='(')
                index.push(i);  
            else {
                index.pop();
                if (!index.isEmpty())
                    maxLen=Math.max(maxLen,i-index.peek());
                else
                    index.push(i);
            }
        }
        return maxLen;
    }
    //reach the bottomRight point from top left with the min path values
    //we can only move right and down
    static Integer[][] DP_MINGRID=new Integer[4][4];
    public static int minPathSumDp(int[][] grid,int i,int j){
        if(DP_MINGRID[i][j]!=null)
            return DP_MINGRID[i][j];
        if (i==grid.length-1 && j==grid[0].length-1)
            return grid[i][j];
        if (i>=grid.length || j>=grid[0].length)
            return Integer.MAX_VALUE;
        int right=minPathSumDp(grid,i+1,j);
        int down=minPathSumDp(grid,i,j+1);
        return DP_MINGRID[i][j]=grid[i][j]+Math.min(right,down);
    }
    public static int minPathSumTabulation(int[][] grid){
        int m=grid.length;
        int n=grid[0].length;
        int[][] table=new int[m][n];
        table[0][0]=grid[0][0];
        for (int i = 1; i <n; i++) {
            table[0][i]=grid[0][i]+table[0][i-1];
        }
        for (int i = 1; i <m; i++) {
            table[i][0]=grid[i][0]+table[i-1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                table[i][j]=grid[i][j]+Math.min(table[i-1][j],table[i][j-1]);
            }
        }
        for (int[] ints : table) {
            System.out.println(Arrays.toString(ints));
        }
        return table[m-1][n-1];
    }

    public static String toHexaDecimal(int dec){
        int rem=0;
        StringBuilder hex= new StringBuilder();
        char[] hexChar={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while (dec>0){
            rem=dec%16;
            hex.insert(0, hexChar[rem]);
            dec=dec/16;
        }
        return hex.toString();
    }
    //finding the previous smaller element to the element at index i
    //using stack in O(n)
    public static void previousSmaller(int[] arr){
        Stack<Integer> stack=new Stack<>();
        int[] op=new int[arr.length];
        op[0]=-1;
        stack.push(0);
        for (int i =1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()]>=arr[i])
                stack.pop();
            int prevSmallIndex=stack.isEmpty()?-1:stack.peek();
            op[i]=prevSmallIndex;
            stack.push(i);
        }
        System.out.println(Arrays.toString(op));
    }
    public static void nextSmallerElement(int[] arr){
        Stack<Integer> stack=new Stack<>();
        int n=arr.length;
        int[] op=new int[n];
        stack.push(n-1);
        op[n-1]=n;
        for (int i = n-2; i >=0 ; i--) {
            while (!stack.isEmpty() && arr[stack.peek()]>=arr[i])
                stack.pop();
            int nextSmallerIndex=stack.isEmpty()?n:stack.peek();
            op[i]=nextSmallerIndex;
            stack.push(i);
        }
        System.out.println(Arrays.toString(op));
    }
    public static int trappingRainWater(int[] heights){
        int n=heights.length;
        int leftMax=0;
        int rightMax=0;
        int start=0;
        int end=n-1;
        int res=0;
        while (start<=end){
            if (heights[start]<=heights[end]){
                if (heights[start]>=leftMax)
                    leftMax=heights[start];
                else
                    res+=leftMax-heights[start];
                start++;
            }
            else {
                if (heights[end]>=rightMax)
                    rightMax=heights[end];
                else
                    res+=rightMax-heights[end];
            }
            end--;
        }
        return res;
    }
    //find the min initial health required to reach th bottom right point from top left
    static Integer[][] Dp_health;
    public static int calcMinInitValue(int i,int j,int[][] grid){
        if (Dp_health[i][j]!=null)
            return Dp_health[i][j];
        if (i>=grid.length || j>=grid[0].length)
            return Integer.MAX_VALUE;
        int health=Math.min(calcMinInitValue(i+1,j,grid),calcMinInitValue(i,j+1,grid));
        if (health==Integer.MAX_VALUE)
            health=1;
        int res=0;
        //if cur position req is less that the requirement downwards, we need the required energy
        //from the upper positions
        if (health-grid[i][j]>0)
            res=health-grid[i][j];
        else
            //if cur position have sufficient energy to fullfill the requirements of the lower level
        //we then only need to reach that position with one (1) point
            res=1;
        return Dp_health[i][j]=res;
    }

    static Integer[] DpDecode;
    public static int decodeWays(String str,int index){
        if (DpDecode[index]!=null)
            return DpDecode[index];
        if (index==str.length())
            return 1;
        //0 cannot be decoded to anyting
        if (str.charAt(index)=='0')
            return 0;
        //if we are at the last element we can only take that element
        if (index==str.length()-1)
            return 1;

        int singleChar=decodeWays(str,index+1);
        int twochar=0;
        //we can only consider the  2 elements if the number made by this is less than equal to 26
        if (Integer.parseInt(str.substring(index,index+2))<=26)
            twochar=decodeWays(str,index+2);
        return DpDecode[index]=singleChar+twochar;
    }
    public static int decodeWaysTabulation(String str){
        if (str==null || str.length()==0)
            return 0;
        int n=str.length();
        int[]DP=new int[n+1];
        DP[0]=1;
        DP[1]=str.charAt(0)=='0'?0:1;
        for (int i = 2; i <=n; i++) {
            //if taking one element is valid we increment by one the previous answer
            int first=Integer.parseInt(str.substring(i-1,i));
            int second=Integer.parseInt(str.substring(i-2,i));
            if (first>=1 && first<=9)
                DP[i]+=DP[i-1];
            //if taking 2 element it is valid we can increment the previous by one
            if (second>=10 && second<=26)
                DP[i]+=DP[i-2];
            //if both, single as well as the double element are valid we would increment the previous answer by 2
            //as we got 2 ways to decode the cur index
        }
        return DP[n];
    }
    //given a array of stock prices find the maximum profit we can make by doing 2 trans
    //one day buy and other day sell
    //we only need to find the min and max value such that min comes before the maximum
    public static int stockBuyMaxProfit2Trans(int[] prices){
        int maxProfit=0;
        int minPrice=Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            minPrice=Math.min(minPrice,prices[i]);
            if (prices[i]-minPrice>maxProfit)
                maxProfit=prices[i]-minPrice;
        }
        return maxProfit;
    }
    //123. Best Time to Buy and Sell Stock III
    //making max 2 profit
    static HashMap<String,Integer> mapBuysSell;
    public static int maxProfitbuySell2trans(int i,int buyOrSell,int[] prices,int k){
        if (i>=prices.length || k==0)
            return 0;
        StringBuilder indx=new StringBuilder();
        indx.append(i).append("#").append(buyOrSell).append("#").append(k);
        String key=indx.toString();
        if (mapBuysSell.containsKey(key))
            return mapBuysSell.get(key);
        int profit=0;
        //BUY==0   SELL==1;
        if (buyOrSell==0){
            int buy=maxProfitbuySell2trans(i+1,1,prices,k)-prices[i];
            int dontBuy=maxProfitbuySell2trans(i+1,0,prices,k);
            profit=Math.max(buy,dontBuy);
        }
        else {
            int sell=maxProfitbuySell2trans(i+1,0,prices,k-1)+prices[i];
            int dontSell=maxProfitbuySell2trans(i+1,1,prices,k);
            profit=Math.max(sell,dontSell);
        }
        mapBuysSell.put(key,profit);
        return profit;
    }
    //finding the maximum length using the elements of the given array
    //consecutive elements
    public static int longestConsecutiveSubSequence(int[] nums){
        HashMap<Integer,Boolean> hm=new HashMap<>();
        for (int i : nums)
            hm.put(i,true);
        for (int i : nums) {
            if (hm.containsKey(i-1))
                hm.put(i,false);
        }
        int maxLen=0;
        for (Integer key : hm.keySet()) {
            if (hm.get(key)){
                maxLen=Math.max(maxLen,findLegth(hm,key));
            }
        }
        return maxLen;
    }
    private static int findLegth(HashMap<Integer,Boolean> hm,int k){
        int ans=0;
        while (hm.containsKey(k)){
            ans++;
            k++;
        }
        return ans;
    }
    //finding the largest component size/group by common factors
    public static int largestComponentSize(int[] arr){
        int n=arr.length;
        Integer[] parent=new Integer[1000001];
        for (int i = 0; i < n; i++) {
            //calculating factors
            for (int j = 2; j < Math.sqrt(arr[i]); j++) {
                if (arr[i]%j==0){
                    unionFind(arr[i],j,parent);
                    unionFind(arr[i],arr[i]/j,parent);
                }
            }
        }
        int count=0;
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p=findParent(arr[i],parent);
            count=Math.max(count,1+hm.getOrDefault(p,0));
            hm.put(p,1+hm.getOrDefault(p,0));
        }
        return count;
    }
    private static int findParent(int x,Integer[] par){
        if (par[x]==-1)
            return x;
        par[x]=findParent(par[x],par);
        return par[x];
    }
    private static void unionFind(int x1,int y1,Integer[] par){
        int xPar=findParent(x1,par);
        int yPar=findParent(y1,par);
        if (xPar!=yPar)
            par[yPar]=xPar;
    }

    //maximum size square in a grid of m*n
    public static int maximalSquare(int[][] grid){
        int ans=0;
        Dp_square=new Integer[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1){
                    ans=Math.max(ans,findmaxSquareUtil(i,j,grid));
                }
            }
        }
        return ans*ans;
    }
    static Integer[][] Dp_square;
    //we will we moving in all 3 direction right, diagonal, down, and find the max square we can make
    //taking min of all three because by taking min, we are assured that we will get that size
    private static int findmaxSquareUtil(int i,int j,int[][] grid){
        if (i>=grid.length || j>=grid[0].length || grid[i][j]==0)
            return 0;
        if (Dp_square[i][j]!=null)
            return Dp_square[i][j];
        int right=findmaxSquareUtil(i+1,j,grid);
        int down=findmaxSquareUtil(i,j+1,grid);
        int diagonal=findmaxSquareUtil(i+1,j+1,grid);
        //taking minimum of all 3 and adding 1 to denote that cur 1 element is also included in the max square
        return Dp_square[i][j]=1+Math.min(right,Math.min(diagonal,down));
    }

}

//        System.out.println("kth symbol in grammer: "+kthGrammer(4,5));

//        char a='5';
//        System.out.println((int)'5');   //53
//        System.out.println((int)'0');   //48
//        //  53+48=101
//        //  53-48=5
//        System.out.println(a+'0');      //101
//        System.out.println((a-'0'));    //5
//
//        System.out.println(a+0);      //53
//        System.out.println((a-0));    //53
//
//        Object s=(a-'2');
//        Object qd=(a+'2');
//        System.out.println(s.getClass().getName());     //java.lang.Integer
//        System.out.println(qd.getClass().getName());    //java.lang.Integer
//
//        System.out.println(""+'a'+'b');
//        System.out.println('a'+'b'+"");