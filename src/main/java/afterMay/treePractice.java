package afterMay;

import dsa450.ListNode;
import dsa450.TreeNode;

import java.util.*;

//Tree practice may
public class treePractice {
    public static void main(String[] args) {

    }
    //check if two given binary tree are same/identical
    public static boolean checkIfSame(TreeNode one,TreeNode two){
        if (one==null && two==null)
            return true;
        if (one!=null && two!=null)
            return one.val==two.val && checkIfSame(one.left,two.left) && checkIfSame(one.right,two.right);
        return false;
    }
    //check if two binary tree are symmetric
    //2 ways 1.Recursive value equate 2. using queue and equate values, push to queuq order
    //passing root of same tree with same value initially
    public static boolean checkSymmetric(TreeNode one,TreeNode two){
        if (one==null && two==null)
            return true;
        if (one==null || two==null)
            return false;
        return one.val==two.val && checkSymmetric(one.left,two.right) && checkSymmetric(one.right,two.left);
    }
    public boolean isSymmetricOpt(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);    //passing same value initially
        while (!q.isEmpty()) {
            //taking 2 adjacent and equating values
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            //putting in queue in order, [leftmost, rightmost, centre left, center rigth]
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
    //recover a binary search tree, 2 nodes are swapped,
    static TreeNode first,second,prev;
    //we use the inorder traversal, as in inorder the node are in increasing order
    //which can be used to detech faulty node
    //IF we find only one pair, we need to find pair of cons element such that the first ele is greater than the second element
    //if we find 2 pairs with fault, we need to find first ele of first pair and second ele of second pair
    public static void recoverTree(TreeNode cur) {
        if (cur==null)
            return;
        recoverTree(cur.left);
        //fault/order inversion will occur two times
        if (prev!=null && prev.val>cur.val){
            if (first==null)
                first=prev;  //first is updated only once, on first fault
            second=cur; //
        }
        prev=cur;
        recoverTree(cur.right);
    }
    //used to traverse inorder without any stack and recursion
    //for inorder trav, we need to go to the left, then the root,and then to right node
    //we can go to left by recursion but we need to have a link to go to the root, and from there we go to the right node
    public void morrisTraversal(TreeNode root){
        TreeNode temp = null;
        while(root!=null){
            //if left exists, we need to find the inorder predecessor, so by using it we can come back to this node,
            //and move to its right as in inorder left node right
            if(root.left!=null){
                // finding the cur node predecessor, move one left and than right
                temp = root.left;
                //we stop when we encounter a null or the node right points to cur node
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists, when temp.right!=null & temp.right==root
                if(temp.right!=null){
                    temp.right = null;  //break the link of predecessor
                    System.out.println(root.val);       //print the cur(node) and move to cur right
                    root = root.right;
                }
                else{
                    //link this to its prev top root, and move to left
                    temp.right = root;
                    root = root.left;
                }
            }
            //if root.left is null means there is nothing in left, we print curr(node) and move to right
            else{
                System.out.println(root.val);
                //now move to right if right exisit, or using the link we made we go to prev top root node
                root = root.right;
            }
        }
    }
    /*      USING MORRIS TRAVERSAL
        public void recoverTree(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null;
        // Morris Traversal
        TreeNode temp = null;
		while(root!=null){
			if(root.left!=null){
				// connect threading for root
				temp = root.left;
				while(temp.right!=null && temp.right != root)
					temp = temp.right;
				// the threading already exists
				if(temp.right!=null){
				    if(pre!=null && pre.val > root.val){
				        if(first==null)
                            first = pre;
                        second = root;
				    }
				    pre = root;
				    System.out.println(root.val);
					temp.right = null;
					root = root.right;
				}else{
					// construct the threading
					temp.right = root;
					root = root.left;
				}
			}else{
				if(pre!=null && pre.val > root.val){
				    if(first==null)
                        first = pre;
                    second = root;
				}
				pre = root;
                System.out.println(root.val);

				root = root.right;
			}
		}
		// swap two node values;
		if(first!= null && second != null){
		    int t = first.val;
		    first.val = second.val;
		    second.val = t;
		}
    }
     */

    //PATH SUM, return all paths from root to leaf
    static List<List<Integer>> allPaths=new ArrayList<>();
    public static List<List<Integer>> findAllPathSum(TreeNode root, int target){
        pathGenUtil(root,target,new ArrayList<>());
        return allPaths;
    }
    public static void pathGenUtil(TreeNode root,int sum,List<Integer> temp){
        if (root==null)
            return;
        temp.add(root.val);
        if (root.left==null && root.right==null && sum==root.val){
            allPaths.add(new ArrayList<>(temp));
            return;
        }
        pathGenUtil(root.left,sum-root.val,temp);
        pathGenUtil(root.right,sum-root.val,temp);
    }
    //populating next right point, tree node have a next pointer linking the next node horizontally right
    public static TreeNode populateNext(TreeNode root){
        if (root==null) return null;
        TreeNode cur=root;
        while (cur!=null){
            TreeNode dummy=new TreeNode(0);
            TreeNode temp=dummy;
            while (cur!=null){
                if (cur.left!=null){
                    temp.next=cur.left;
                    temp=temp.next;
                }
                if (cur.right!=null){
                    temp.next=cur.right;
                    temp=temp.next;
                }
                cur=cur.next;
            }
            cur=dummy.next;
        }
        return root;
    }
    //count nodes efficiently, given a complete binary tree
    public static int countNodes(TreeNode root) {
        if (root==null)
            return 0;
        int leftHeight=0,rightHeight=0;
        TreeNode cur=root;
        while (cur!=null){
            leftHeight++;
            cur=cur.left;
        }
        cur=root;
        while (cur!=null){
            rightHeight++;
            cur=cur.right;
        }
        if (leftHeight==rightHeight)
            return (int) (Math.pow(2,leftHeight)-1);
        return 1+countNodes(root.left)+countNodes(root.right);
    }
    public static TreeNode invertTree(TreeNode root) {
        if (root==null)
            return null;
        TreeNode temp=root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(temp);
        return root;
    }
    public static void mirrorBTutil(TreeNode root){
        if (root==null)
            return;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        mirrorBTutil(root.left);
        mirrorBTutil(root.right);
    }
    //max amount we can take by not taking 2 adjacent node
    HashMap<TreeNode,Integer> hsRob=new HashMap<>();
    public int houseRobber(TreeNode root) {
         if (root==null)
             return 0;
         if (hsRob.containsKey(root))
             return hsRob.get(root);
         //if we rob current house, we need to move to next to next house
        int gain=root.val;
        if (root.left!=null)
            gain+=houseRobber(root.left.left)+houseRobber(root.left.right);
        if (root.right!=null)
            gain+=houseRobber(root.right.left)+houseRobber(root.right.right);

        //if we dont rob this house, we can rob next house
        int robNextLeft=houseRobber(root.left);
        int robNextRigt=houseRobber(root.right);

        hsRob.put(root,Math.max(gain,robNextLeft+robNextRigt));
        return Math.max(root.val+gain,robNextLeft+robNextRigt);
    }

    //smallest subtree with deepest nodes
    //return the root of the subtree with all deepest nodes
    //actually it is similar to find the complete tree root
    public static TreeNode deepestNodeSubtree(TreeNode root){
        if (root==null) return null;
        int leftDepth=getDepth(root.left);
        int rightDepth=getDepth(root.right);
        if (leftDepth==rightDepth)
            return root;
        if (leftDepth>rightDepth)
            return deepestNodeSubtree(root.left);
        else
            return deepestNodeSubtree(root.right);
    }
    public static int getDepth(TreeNode root){
        if (root==null)
            return 0;
        return Math.max(getDepth(root.left),getDepth(root.right))+1;
    }

    //smallest string starting from left
    //Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
    public static String smallestStringLeftLeaftoRoot(TreeNode root){
        smallStringUtil(root,new StringBuilder());
        return smallestString;
    }
    static String smallestString="~";
    public static void smallStringUtil(TreeNode root,StringBuilder str){
        if (root==null)
            return;
        str.append('a' +root.val);  //appending curr node val to string
        //if we reached root node, we got our string,
        if (root.left==null && root.right==null){
            //as we are comping from top to root, we need to revrese the string
            //to make string from leaf to root
            str.reverse();
            String tmp=str.toString();
            System.out.println(tmp);
            str.reverse();
            if (tmp.compareTo(smallestString)<0)
                smallestString=tmp;
        }
        smallStringUtil(root.left,str);
        smallStringUtil(root.right,str);
        str.deleteCharAt(str.length()-1);

    }
}
