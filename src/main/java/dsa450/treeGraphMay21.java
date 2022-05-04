package dsa450;

import java.util.LinkedList;
import java.util.Queue;

//tree graph practice questions
public class treeGraphMay21 {
    public static void main(String[] args) {

    }
    //finding the bottom left value in a tree
    //similar to finding the left most value of nodes in last level
    static int btmDptVal=0,maxDepth=0;
    public static void findBottomLeftVal(TreeNode root,int depth){
        if (maxDepth<depth){
            btmDptVal=root.val;
            maxDepth=depth;
        }
        if (root.left!=null)
            findBottomLeftVal(root.left,depth+1);
        if (root.right!=null)
            findBottomLeftVal(root.right,depth+1);
    }
    //finding the smallest string starting from leaf to root
    static String smallStr="~";
    public static void smallestStrDFS(TreeNode root,StringBuilder sb){
        if (root==null)
            return;
        sb.append((char) (root.val+'a')); //adding cur node to path
        if (root.right==null && root.left==null){
            sb.reverse();
            String s=sb.toString();
            sb.reverse();
            if (s.compareTo(smallStr)<0)
                smallStr=s;
        }
        smallestStrDFS(root.left,sb);
        smallestStrDFS(root.right,sb);
        sb.deleteCharAt(sb.length()-1);     //to do backtrack
    }
    //return the path sum of the maximum path in a binary tree: only path sum
    static int maxSum=Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root){
        if (root==null)
            return 0;
        int leftPath=maxPathSum(root.left);
        int rightPath=maxPathSum(root.right);
        int maxStraight=Math.max(Math.max(leftPath,rightPath)+root.val,root.val);
        int maxIncMid=Math.max(maxStraight,leftPath+rightPath+ root.val);
        maxSum=Math.max(maxStraight,maxIncMid);
        return maxStraight;
    }
    //maximum width of binary tree
    //we try to index all the elements at a level, after that
    //we can use the index to find the width of the level and calc max width
    public static int maxWidthBT(TreeNode root){
        if (root==null)
            return 0;
        int result=1;
        Queue<infoTree> que=new LinkedList<>();
        que.add(new infoTree(root,0));
        while (!que.isEmpty()){
            int size=que.size();
            int start=que.peek().index;

            int end=0;
            for (int i = 0; i < size; i++) {
                infoTree cur=que.poll();
                int idx= cur.index;
                end=cur.index;
                if (cur.node.left!=null)
                    que.add(new infoTree(cur.node.left,idx*2+1));
                if (cur.node.right!=null)
                    que.add(new infoTree(cur.node.right,idx*2+2));
            }
            result=Math.max(result,end-start+1);
        }
        return result;
    }
}
class infoTree{
    TreeNode node;
    int index;
    infoTree(TreeNode n,int idx){
        this.node=n;
        this.index=idx;
    }
}