package dsa450;

import java.util.*;

//tree , bst practice revision
public class treePracticeApr {
    public static void main(String[] args) {

    }
    //all nodes k dist from given node
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> op=new ArrayList<>();
        if (root==null)
            return op;
        HashMap<TreeNode,TreeNode> parentMap=new HashMap<>();
        findParent(parentMap,root); //populating parent may with parent childs
        Queue<TreeNode> que=new LinkedList<>();
        HashSet<TreeNode> visited=new HashSet<>();

        que.add(target);
        while (!que.isEmpty()){ // doing BFS
            int size=que.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur=que.poll();
                visited.add(cur);
                if (K==0)
                    op.add(cur.val);
                if (parentMap.containsKey(cur) && !visited.contains(parentMap.get(cur)))
                    que.add(parentMap.get(cur));
                if (cur.left!=null && !visited.contains(cur.left))
                    que.add(cur.left);
                if (cur.right!=null && !visited.contains(cur.right))
                    que.add(cur.right);
            }
            K--;
            if (K<0)
                break;
        }

        return op;
    }
    public static void findParent(HashMap<TreeNode,TreeNode> hm,TreeNode root){
        if (root==null)
            return;
        if (root.left!=null)
            hm.put(root.left,root);
        if (root.right!=null)
            hm.put(root.right,root);
        findParent(hm,root.left);
        findParent(hm,root.right);
    }

    //tree construct from arr divided by the max element in arr
    // all element left to max element is left sub tree and all element right to max element is right sub tree
    public static TreeNode constructMaxBT(int[] arr){
        return constructMaxMain(arr,0,arr.length-1);
    }
    public static TreeNode constructMaxMain(int[] arr,int start, int end){
        if (start>end)
            return null;
        if (start==end)
            return new TreeNode(arr[start]);

        int maxIndex=findMaxIndex(arr,start,end);
        TreeNode root=new TreeNode(arr[maxIndex]);
        root.left=constructMaxMain(arr,start,maxIndex-1);
        root.right=constructMaxMain(arr,maxIndex+1,end);
        return root;
    }
    public static int findMaxIndex(int[] arr,int s,int e){
        int max=Integer.MIN_VALUE;
        int indx=-1;
        for (int i = s; i <=e ; i++) {
            if (arr[i]>max){
                max=arr[i];
                indx=i;
            }
        }
        return indx;
    }
}
