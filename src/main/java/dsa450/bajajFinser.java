package dsa450;

import java.util.*;

public class bajajFinser {
    public static void main(String[] args) {
        nextPermutationNumber(new int[]{3,2,1,4,6,9,8,7,6,5,4,3,2,1});
    }
    //next permutation of number by using same number
    // 1 2 3 6 5 4 -> next number by using same nos
    // 1 2 4 3 5 6
    public static void nextPermutationNumber(int[] digits){
        int n=digits.length;
        System.out.println(Arrays.toString(digits));
        if (n<=1)
            return;
        int i=n-2;
        while (i>=0 && digits[i]>=digits[i+1])
            i--;
        //find a inversion
        System.out.println("i: "+i);
        if (i>=0){
            int j=n-1;
            //find a element greater than the inversion point and swap
            while (digits[j]<=digits[i])
                j--;
            System.out.println("j: "+j);
            swap(digits,i,j);
        }
        System.out.println(Arrays.toString(digits));
        System.out.println("revese:: "+(i+1)+" -> "+(n-1));
        reverse(digits,i+1,n-1);
        System.out.println(Arrays.toString(digits));

    }
    private static void reverse(int[]arr,int  i,int j){
        while (i<j)swap(arr,i++,j--);
    }
    private static void swap(int[] arr, int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    //min no of operation required to convert string s1 to s2
    //with operation we can do is insert, remove and replace
    public static int minEditDistance(String s1,String s2){
        char[] A=s1.toCharArray();
        char[] B=s2.toCharArray();
        int m=s1.length();
        int n=s2.length();
        int[][] DP_EDIT=new int[m+1][n+1];
        for (int i = 0; i <=m; i++) {
            DP_EDIT[i][0]=i;
        }
        for (int i = 1; i <=n; i++) {
            DP_EDIT[0][i]=i;
        }
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                if (A[i]==B[j])
                    DP_EDIT[i][j]= DP_EDIT[i-1][j-1];
                else {
                    int insert=DP_EDIT[i][j-1];
                    int remove=DP_EDIT[i-1][j];
                    int replace=DP_EDIT[i-1][j-1];
                    DP_EDIT[i][j]=1+Math.min(insert,Math.min(remove,replace));
                }
            }
        }
        return DP_EDIT[m][n];
    }

    //check if paranthesis is balanced
    public static boolean isBalancedParenthesis(String str){
        char[] parn=str.toCharArray();
        Stack<Character> stk=new Stack<>();

        for (char cur : parn) {
            if (cur == '{' || cur == '[' || cur == '(')
                stk.push(cur);
            else {
                if (stk.isEmpty())
                    return false;
                if (!matching(cur, stk.peek()))
                    return false;
                else
                    stk.pop();
            }
        }
        return stk.isEmpty();
    }
    static private boolean matching(char a, char b) {
        return (a=='(' && b==')') || (a=='{' && b=='}') || (a=='[' && b==']');
    }

    //generate all balanced paranthesis
    public static void genBalacedParan(String str, int open, int close){
        if (open==0 && close==0){
            System.out.println(str);
            return;
        }
        if (open>0)
            genBalacedParan(str+"(",open-1,close);
        if (close>open)
            genBalacedParan(str+")",open,close-1);
    }
    public static Node mergeLinkedList(Node first,Node second){
        Node cur=new Node(1);
        Node res=cur;
        while (first!=null && second!=null){
            if (first.data<second.data){
                cur.bottom=first;
                first=first.next;
            }
            else {
                cur.bottom=second;
                second=second.next;
            }
            cur=cur.bottom;
        }
        if (first!=null)
            cur.bottom=first;
        if (second!=null)
            cur.bottom=second;
        return res.bottom;
    }
    Node flattenLinkedList(Node root){
        Node cur=root;
        Node merged=root;
        while (cur!=null){
            merged=mergeLinkedList(merged,cur);
            cur=cur.next;
        }
        return root;
    }

    Node reverseLinkedList(Node head){
        Node prev=null;
        while (head!=null){
            Node next=head.next;
            head.next=prev;
            prev=head;
            head=next;
        }
        return prev;
    }
    Node middleLinkedlist(Node head){
        Node slow=head;
        Node fast=head;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    //  floyd's cycle detection
    public static boolean cycledetectLinkedList(Node head){
        Node fast=head;
        Node slow=head;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast)
                return true;
        }
        return false;
    }
    Node reverseLLrec(Node cur,Node prev){
        if (cur==null)
            return prev;
        Node next=cur.next;
        cur.next=prev;
        return reverseLLrec(next,cur);
    }

    //reverse linked list in group of k
    public static Node reversekgroup(Node head,int k){
        Node cur=head;
        Node prevFirst=null;
        boolean firstPass=true;
        while (cur!=null){
            Node first=cur;
            Node prev=null;
            int count=0;
            while (cur!=null && count<k){
                Node next=cur.next;
                cur.next=prev;
                prev=cur;
                cur=next;
            }
            //prev is at the end of the k th node
            if (firstPass){
                head=prev;
                firstPass=false;
            }
            else {
                prevFirst.next=prev;
            }
            prevFirst=first;
        }

        return head;
    }

    public static int heightTreeBT(TreeNode root){
        if (root==null)
            return 0;
        return 1+Math.max(heightTreeBT(root.left),heightTreeBT(root.right));
    }


    public static void LevelOrderTravBT(TreeNode root){
        if (root==null)return;
        Queue<TreeNode> que=new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()){
            int size=que.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur=que.poll();
                System.out.println(cur.val);
                if (cur.left!=null)
                    que.add(cur.left);
                if (cur.right!=null)
                    que.add(cur.right);
            }
            System.out.println();
        }
    }

    //check if BT is balanced
    public static int isBalancedBT(TreeNode root){
        if (root==null)
            return 0;
        int lh=isBalancedBT(root.left);
        if (lh==-1)
            return -1;
        int rh=isBalancedBT(root.right);
        if (rh==-1)
            return -1;
        if (Math.abs(lh-rh)>1)
            return -1;
        return Math.max(lh,rh)+1;   //else returning the height to calc balanced upp
    }

    //Binary tree to Doubly Linked list convert in preorder fashion
    static TreeNode headOfLL=null;
    static TreeNode prev=null;
    public static void BTtoDLL(TreeNode root){      //O(n)
        if (root==null)
            return;
        BTtoDLL(root.left);         //going to left-most node
        if (prev==null){        //only run one time for head assing
            headOfLL=root;
            prev=root;
        }
        else {      //change the links,
            root.left=prev;
            prev.right=root;
        }
        prev=root;          //moving prev to cur root
        BTtoDLL(root.right);        //going to right-most node
    }

    //construct tree from preorder and inorder given
    static int preIndex=0;
    public static TreeNode constructInPreBT(int[] in,int[] pre,int inStart,int inEnd){
        if (inStart>inEnd)
            return null;
        TreeNode cur=new TreeNode(pre[preIndex++]);
        int inIndex=0;
        for (int i = inStart; i <=inEnd; i++) {
            if (in[i]==cur.val){
                inIndex=i;
                break;
            }
        }
        cur.left=constructInPreBT(in,pre,inStart,inIndex-1);
        cur.right=constructInPreBT(in,pre,inIndex+1,inEnd);
        return cur;
    }
    //printing all path from root to leaf
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> op=new ArrayList<>();
        allPathFind(root,"",op);
        return op;
    }
    public static void allPathFind(TreeNode root,String curPath,List<String> op){
        if (root.left==null && root.right==null){
            op.add(curPath+root.val);
            return;
        }
        if (root.left!=null)
            allPathFind(root.left,curPath+root.val+"->",op);
        if (root.right!=null)
            allPathFind(root.right,curPath+root.val+"->",op);
    }
    public static boolean findPathBT(TreeNode root, ArrayList<Integer> path,int key){
        if (root==null)
            return false;
        path.add(root.val);
        if (root.val==key)
            return true;
        if (findPathBT(root.left,path,key) || findPathBT(root.right,path,key))
            return true;
        path.remove(path.size()-1);
        return false;
    }
    int LCANaive(TreeNode root, int n1, int n2){
        ArrayList<Integer> path1=new ArrayList<>();
        ArrayList<Integer> path2=new ArrayList<>();
        if (!findPathBT(root, path1, n1) || !findPathBT(root,path2,n2))
            return -1;
        for (int i = 0; i <path1.size()-1 && i<path2.size()-1; i++) {
            //when ever the path dont match means not path is divided and previous node is least common ancestor
            if (!path1.get(i + 1).equals(path2.get(i + 1)))
                return path1.get(i);
        }
        return -1;
    }

    //lowest common ancestor , efficient method
    public static TreeNode lowestCommonAncestor(TreeNode root,int n1,int n2){
        if (root==null)
            return null;
        if (root.val==n1 || root.val==n2)
            return root;
        TreeNode lcaLeft=lowestCommonAncestor(root.left,n1,n2);
        TreeNode lcaRight=lowestCommonAncestor(root.right,n1,n2);
        if (lcaLeft!=null && lcaRight!=null)
            return root;

        if (lcaLeft!=null)
            return lcaLeft;
        else
            return lcaRight;
    }

    //k th smallest element
    static int count=0;
    static int kthSmallest=0;
    public static int kthSmallestBST(TreeNode root,int k){
        return kthSmallest;
    }

    // we will do the level order traversal and for the kth element we visit we will print that element
    public static void recurKthSmallest(TreeNode root,int k){
        if(root.left!=null)
            recurKthSmallest(root.left,k);
        count++;
        if (count==k){
            kthSmallest=root.val;
            return;
        }
        if (root.right!=null)
            recurKthSmallest(root.right,k);
    }

    static int kthLargest=0;
    public static int kthLargest(TreeNode root,int k){
        recurKthLargest(root,k);
        return kthLargest;
    }

    public static void recurKthLargest(TreeNode root,int k){
        if(root.right!=null)
            recurKthLargest(root.right,k);
        count++;
        if (count==k){
            kthLargest=root.val;
            return;
        }
        if (root.left!=null)
            recurKthLargest(root.left,k);
    }

    //equal partition subset of array is possible or not
    public static boolean equalPartitionPossible(int[] arr,int n){
        int sum=0;
        for (int i : arr) {
            sum+=i;
        }
        if (sum%2==1)
            return false;
        boolean[][] DP_Part=new boolean[sum/2+1][n+1];
        //if sum==0  we can make by not including any element
        for (int i = 0; i <=n; i++) {
            DP_Part[0][i]=true;
        }
        //as empty set is also a subset of any given set, we my not take any element and get a sum of 0
        //if element ==0 we cannot form a sum
        for (int i = 0; i <= sum/2; i++) {
            DP_Part[i][0]=false;
        }
        for (int i = 1; i <= sum/2; i++) {
            for (int j = 1; j <=n; j++) {
                DP_Part[i][j]=DP_Part[i][j-1];
                if (arr[j-1]<=i)    //we can take the element only when its value is less than or equal to sum we need
                    DP_Part[i][j]=DP_Part[i][j] || DP_Part[i-arr[j-1]][j-1];
            }
        }
        return DP_Part[sum/2][n];
    }

    //maximum path sum of going from first row to last row
    // movement allowed :  down,  diagonal right, diagonal left
    public static int maxPathSumRow(int[][] matrix,int n){
        int[][] DPPATH=new int[n][n];
        //in first row we have the path sum as its initial value
        for (int i = 0; i < n; i++) {
            DPPATH[0][i]=matrix[0][i];
        }
        for (int i = 1; i < n; i++) {       //starting from 2nd row
            for (int j = 0; j < n; j++) {       //traversing each column
                if (j==0 && j+1<n)      //if at left end
                    DPPATH[i][j]=matrix[i][j]+Math.max(DPPATH[i-1][j],DPPATH[i-1][j+1]);
                else if (j-1>=0 && j==n-1)      //if at right end
                    DPPATH[i][j]=matrix[i][j]+Math.max(DPPATH[i-1][j],DPPATH[i-1][j-1]);
                else if (j>0 && j+1<n)      //if at any mid end, not at corner
                    DPPATH[i][j]=matrix[i][j]+Math.max(DPPATH[i-1][j],Math.max(DPPATH[i-1][j-1],DPPATH[i-1][j+1]));
                else    //if there is only one column , EDGE CASE Handle,
                    DPPATH[i][j]=matrix[i][j]+DPPATH[i-1][j];
            }
        }
        int maxSum=0;
        for(int i = 0;i < n;i++)
            maxSum = Math.max(maxSum, DPPATH[n-1][i]);
        return maxSum;
    }

    //minimum cost of ticket
    //given days to travel and cost of ticket for
    //one day pass, 7 day pass, 30 day pass
    //find min cost of ticket to buy to travel on all given days
    public static int minCostTicket(int[] days,int[] cost){
        int[] DPCOST=new int[days.length];
        int ans=recMinTicketCost(DPCOST,days,cost,0);
        return ans;
    }
    public static int recMinTicketCost(int[] dp,int[] days,int[] cost,int i){
        if (i>=days.length)
            return 0;
        if (dp[i]>0){
            return dp[i];
        }
        int oneDayPass=cost[0]+recMinTicketCost(dp,days,cost,i+1);
        int k=i;
        for (; k < days.length; k++) {
            if (days[k]>=days[i]+7)
                break;
        }
        int oneWeekPass=cost[1]+recMinTicketCost(dp,days,cost,k);
        for (; k < days.length; k++) {
            if (days[k]>=days[i]+30)
                break;
        }
        int oneMonthPass=cost[2]+recMinTicketCost(dp,days,cost,k);
        return dp[i]=Math.min(oneDayPass,Math.min(oneWeekPass,oneMonthPass));
    }

    //longest lenght palindrome we can make
    //by taking character from a given string// characters use in any order
    public static int longestPalindromeMake(String str){
        char[] arr=str.toCharArray();
        int n=arr.length;
        HashMap<Character,Integer> map=new HashMap<>();
        for (char c : arr) {
            if (map.containsKey(c))
                map.put(c,map.get(c)+1);
            else
                map.put(c,1);
        }
        int ans=0;
        boolean isFirstOdd=true;
        for (Character chr : map.keySet()) {
            if (map.get(chr)%2==0)  // we can take all the even elements
                ans+=map.get(chr);
            else {
                if (isFirstOdd){        // we only take one odd element
                    ans+=map.get(chr);
                    isFirstOdd=false;
                }
                else    //rest odd we make it even by decrementing by 1
                    ans+=map.get(chr)-1;
            }
        }
        return ans;
    }

    public static int subArraySumDivK(int[] arr, int k){
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int prefSum=0;
        int res=0;
        for (int i = 0; i < arr.length; i++) {
            prefSum+=arr[i];
            int rmdr=prefSum%k;
            if (rmdr<0) //if reminder is negative, we inc remdr by k to make it positive
                rmdr+=k;
             if (map.containsKey(rmdr))
                res+=map.get(rmdr);
            map.put(rmdr,map.get(rmdr)+1);
        }
        return res;
    }

    //largest rectangle area
    //given the 4 coordinates of 2 recangle we need to find the area of rectangle
    //both the area may overlap so we need to consider this case
    public static int rectangleArea(int a,int b,int c,int d,int e,int f,int g,int h){
        int areaA=(c-a)*(d-b);
        int areaB=(g-e)*(h-f);

        int overLenght=Math.min(c,g)-Math.min(a,e);
        int overbreadth=Math.min(d,h)-Math.max(b,f);
        int overLap=Math.max(overLenght,0)*Math.max(overbreadth,0);

        return areaA+areaB-overLap;
    }
    static int perimeter=0;
    //finding the perimeter of a island represented by binary grid
    public static int islandPerimeter(int[][] grid){
        int n=grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]==1){
                    dfsGrid(grid,i,j);
                    break;
                }
            }
        }
        return perimeter;
    }
    public static void dfsGrid(int[][] grid,int i,int j){
        //if we reach a water side i.e grid[i][j]==0 or we move to a point outside the grid, so we inc the perimeter
        if (i<0 || i>=grid.length || j<0 || j>=grid.length || grid[i][j]==0){
            perimeter++;
            return;
        }
        if (grid[i][j]==-1)
            return;
        grid[i][j]=-1;
        dfsGrid(grid,i+1,j);
        dfsGrid(grid,i-1,j);
        dfsGrid(grid,i,j+1);
        dfsGrid(grid,i,j-1);
    }

    //finding the friend circle in a binary matrix
    //finding the no of connected components

}
