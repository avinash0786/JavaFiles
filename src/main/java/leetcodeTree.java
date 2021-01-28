import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class leetcodeTree {
    public static void main(String[] args) {

    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> op=new LinkedList<>();
        if (root==null)
            return op;
        Stack<TreeNode> stk=new Stack<>();
        TreeNode curr=root;
        while (curr!=null || stk.size()>0){
            while (curr!=null){
                stk.push(curr);
                curr=curr.left;
            }
            curr=stk.pop();
            op.add(curr.val);
            curr=curr.right;
        }
        return op;
    }

}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
         this.right = right;
      }
 }