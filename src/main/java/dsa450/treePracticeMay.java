package dsa450;

import java.util.*;

public class treePracticeMay {
    public static void main(String[] args) {
        int[] arr={12,3,3};
        Integer a=12;
        inc(a);
        System.out.println(a);
    }
    public static void inc(Integer in){
        in++;
        System.out.println(in);
    }
    //binary tree max path sum
    public static int maxPathSum(TreeNode root){
        findMaxPathUtil(root);
        return maxSum;
    }
    static int maxSum=Integer.MIN_VALUE;
    private static int findMaxPathUtil(TreeNode root){
        if (root==null)
            return 0;
        int left=Math.max(0,findMaxPathUtil(root.left));
        int right=Math.max(0,findMaxPathUtil(root.right));
        maxSum=Math.max(maxSum,left+right+root.val);
        return Math.max(left,right)+root.val;
    }
    //left view of the BST
    static int maxLevLeft=0;
    public static void leftViewOfBST(TreeNode root,int level){
        if (root==null)
            return;
        if (maxLevLeft<level){
            System.out.println(root.val);
            maxLevLeft=level;
        }
        leftViewOfBST(root.left,level+1);
        leftViewOfBST(root.right,level+1);
    }
    //max path sum root to leaf
    public static int maxPathSumRootToleaf(TreeNode root){
        if (root==null)
            return 0;
        if (root.left==null && root.right==null)
            return root.val;
        return Math.max(
                maxPathSumRootToleaf(root.left),
                maxPathSumRootToleaf(root.right)
        )
                +root.val;
    }

    //binary tree to Doubly linked list
    static TreeNode prevBSTdll=null,headBSTdll=null;
    public static void BSTtoDLL(TreeNode root){
        if (root==null)
            return;
        BSTtoDLL(root.left);
        if (prevBSTdll==null)
            headBSTdll=root;
        else {
            root.left=prevBSTdll;
            prevBSTdll.right=root;
        }
        prevBSTdll=root;
        BSTtoDLL(root.right);
    }

    //diameter of a tree
    static int maxDiameter=0;
    public static int diameterTree(TreeNode root){
        if (root==null)
            return 0;
        int leftHeight=diameterTree(root.left);
        int rightHeight=diameterTree(root.right);
        maxDiameter=Math.max(maxDiameter,leftHeight+rightHeight+1);
        return leftHeight+rightHeight+1;
    }

    //path find for a given node value in BT
    public static boolean findPathExist(TreeNode root, int target, ArrayList<Integer> path){
        if (root==null)
            return false;
        path.add(root.val);
        if (root.val==target)
            return true;
        if (findPathExist(root.left,target,path) || findPathExist(root.right,target,path))
            return true;
        path.remove(path.size()-1);
        return false;
    }

    //lowest common ancestor
    public static TreeNode LCA(TreeNode root,int n1,int n2){
        if (root==null)
            return null;
        if (root.val==n1 || root.val==n2)
            return root;
        TreeNode lca01=LCA(root.left,n1,n2);
        TreeNode lca02=LCA(root.right,n1,n2);
        if (lca01!=null && lca02!=null)
            return root;
        if (lca01!=null)
            return lca01;
        else
            return lca02;
    }
    //find if a given pattern of 0,1 exist in the given BT
    public static boolean pathExistBT(TreeNode root,int index,int[] arr){
        if (root==null || index>=arr.length)
            return arr.length==0;
        if ((root.left==null && root.right==null) && (root.val==arr[index] && root.val==arr[arr.length-1]))
            return true;
        boolean ans=false;
        if (root.val==arr[index]){
            ans=pathExistBT(root.left,index+1,arr) || pathExistBT(root.right,index+1,arr);
        }
        return ans;
    }
    //invert the binary tree
    public static TreeNode invertBT(TreeNode root){
        if (root==null)
            return null;
        TreeNode temp=root.left;
        root.left=invertBT(root.right);
        root.right=invertBT(temp);
        return root;
    }
    public static TreeNode invertBTiter(TreeNode root){
        Queue<TreeNode> que=new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()){
            TreeNode cur=que.poll();
            if (cur!=null){
                que.add(cur.left);
                que.add(cur.right);
                TreeNode temp=cur.left;
                cur.left=cur.right;
                cur.right=temp;
            }
        }
        return root;
    }
    //construct tree from given arr
    public static TreeNode constructTreeBST(int[] arr,int s,int e){
        if (s>e)
            return null;
        if (s==e)
            return new TreeNode(arr[s]);
        int idx=findMaxIndex(arr,s,e);

        TreeNode root=new TreeNode(arr[idx]);
        root.left=constructTreeBST(arr,s,idx-1);
        root.right=constructTreeBST(arr,idx+1,e);
        return root;
    }
    private static int findMaxIndex(int[] arr,int s,int e){
        int max=Integer.MIN_VALUE;
        int index=-1;
        for (int i = s; i <=e; i++)
            if (arr[i]>max) {
                max = arr[i];
                index = i;
            }

        return index;
    }
    //finding the largest BST subtree
    public static int largestBST(TreeNode root){
        if (root==null)
            return 0;
        if (isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE))
            return size(root);
        return Math.max(largestBST(root.left),largestBST(root.right));
    }
    public static boolean isBST(TreeNode root,int min,int max){
        if (root==null)
            return true;
        if (root.val<=min || root.val>=max)
            return false;
        return isBST(root.left,min,root.val) && isBST(root.right,root.val,max);
    }
    public static int size(TreeNode root){
        if (root==null)
            return 0;
        return 1+size(root.left)+size(root.right);
    }


    //deletion in BST
    public static TreeNode deleteNodeBST(TreeNode root,int key){
        if (root==null)
            return null;
        if (key>root.val)
            root.right=deleteNodeBST(root.right,key);
        else if (key<root.val)
            root.left=deleteNodeBST(root.left,key);
        else {
            //to delete a node with both child not null, we need to find a node having mid value
            //among the left and right child , and it is present in the max of left child,
            //so we find max in left child and go right right, as the largest node dont have right child
            if (root.left!=null && root.right!=null){
                //find max from left tree
                int v=findMaxDelBST(root.left,Integer.MIN_VALUE);
                root.val=v; //swap cur del node with max val
                root.left=deleteNodeBST(root.left,v);   //delete the max element in left tree
                return root;
            }
            else if (root.left!=null)
                return root.left;
            else if (root.right!=null)
                return root.right;
            else
                return null;
        }
        return root;
    }
    private static int findMaxDelBST(TreeNode root,int max){
        while (root!=null){
            max=root.val;
            root=root.right;
        }
        return max;
    }

    //time take to inform all the employee
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        adjInfEmp=new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            if (manager[i]!=-1){
                if (adjInfEmp.containsKey(manager[i])){
                    adjInfEmp.get(manager[i]).add(i);
                }
                else
                    adjInfEmp.put(manager[i],new ArrayList<>(Arrays.asList(i)));
            }
        }
        return dfsInformEmp(headID,informTime);
    }
    static HashMap<Integer,List<Integer>> adjInfEmp;
    public static int dfsInformEmp(int headID,int[] informTime){
        if (!adjInfEmp.containsKey(headID))
            return 0;
        int maxTime=0;
        for(int i:adjInfEmp.get(headID)){
            maxTime=Math.max(maxTime,dfsInformEmp(i,informTime));
        }
        return informTime[headID]+maxTime;
    }
    //vertical order traversal of the tree
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> op=new ArrayList<>();
        if (root==null)
            return op;
        Map<Integer,PriorityQueue<Point>> map=new HashMap<>();
        Queue<Point> que=new LinkedList<>();
        que.offer(new Point(root,0,0));
        int minIdx=0;
        int maxIdx=0;
        Comparator<Point> cmp=(a,b)->{
            if (a.hLex==b.hLex)
                return a.node.val-b.node.val;
            else
                return a.hLex-b.hLex;
        };
        while (!que.isEmpty()){
            Point pnt=que.poll();
            root=pnt.node;
            map.putIfAbsent(pnt.vLev,new PriorityQueue<>(cmp));
            map.get(pnt.vLev).add(pnt); //adding this node to the map

            minIdx=Math.min(minIdx,pnt.vLev);
            maxIdx=Math.max(maxIdx,pnt.vLev);

            //adding its child to queue for further processing
            if (root.left!=null)
                que.offer(new Point(root.left,pnt.vLev-1,pnt.hLex+1));
            if (root.right!=null)
                que.offer(new Point(root.right,pnt.vLev+1,pnt.hLex+1));
        }
        //using the minIdx and maxIdx to traverse from left ro right and add all nodes to list in that order
        for (int i = minIdx; i <=maxIdx; i++) {
             PriorityQueue<Point> pq=map.get(i);
             List<Integer> lst=new ArrayList<>();
             while (!pq.isEmpty()){
                 lst.add(pq.poll().node.val);
             }
             op.add(lst);
        }
        return op;
    }

    //sum of dist bt all nodes in a tree,

    //max width of a binary tree
    public static int maxWidthBT(TreeNode root){
        if (root==null)
            return 0;
        Deque<TreeNode> dq=new ArrayDeque<>();
        dq.add(root);
        int maxWidth=1;
        while (!dq.isEmpty()){
            while (!dq.isEmpty() && dq.getFirst()==null)
                dq.removeFirst();
            while (!dq.isEmpty() && dq.getLast()==null)
                dq.removeLast();
            maxWidth=Math.max(maxWidth,dq.size());
            int size=dq.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur=dq.poll();
                if (cur==null){
                    dq.add(null);
                    dq.add(null);
                }
                else {
                    if (cur.left!=null)
                        dq.add(cur.left);
                    if (cur.right!=null)
                        dq.add(cur.right);
                }
            }
        }
        return maxWidth;
    }
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    // Linked List in Binary Tree
    public static boolean btLLpath(TreeNode root, ListNode head){
        if (root==null)
            return false;
        if (foundBtLL(root,head))
            return true;
        return btLLpath(root.left,head) || btLLpath(root.right,head);
    }
    public static boolean foundBtLL(TreeNode root, ListNode head){
        if (head==null)
            return true;
        if (root==null || root.val!=head.val)
            return false;
        return foundBtLL(root.left,head.next) || foundBtLL(root.right,head.next);
    }
    public static TreeNode connectNextPointerTree(TreeNode root){
        if (root==null)
            return null;
        TreeNode head=root;
        while (head!=null){
            TreeNode dummy=new TreeNode(0);
            TreeNode temp=dummy;
            while (head!=null){
                if (head.left!=null){
                    temp.next=head.left;
                    temp=temp.next;
                }
                if (head.right!=null){
                    temp.next=head.right;
                    temp=temp.next;
                }
                head=head.next;
            }
            head=dummy.next;
        }
        return root;
    }
}
class Point{
    TreeNode node;
    int vLev;
    int hLex;
    Point(TreeNode n,int v,int h){
        this.node=n;
        this.vLev=v;
        this.hLex=h;
    }
}