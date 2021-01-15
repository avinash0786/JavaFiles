package GEEKS_FOR_GEEKS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class treeLearn {
    public static void main(String[] args) {

    }
}
class nodeTree{
    nodeTree left;
    nodeTree right;
    int data;
    nodeTree(int dta){
        this.data=dta;
    }
    void inorderTrav(nodeTree root){    // L N R
        if (root!=null){
            inorderTrav(root.left);
            System.out.println(root.data);
            inorderTrav(root.right);
        }
    }
    void preorderTrav(nodeTree root){    // N L R
        if (root!=null){
            System.out.println(root.data);
            preorderTrav(root.left);
            preorderTrav(root.right);
        }
    }
    void postorderTrav(nodeTree root){      // L R N
        if (root!=null){
            postorderTrav(root.left);
            postorderTrav(root.right);
            System.out.println(root.data);
        }
    }
    int heightTree(nodeTree root){
        if (root==null)
            return 0;
        else
            return Math.max(
                    heightTree(root.left),
                    heightTree(root.right)
                            )+1;
    }

    void kDistance(nodeTree root,int k){
        if (root==null)
            return;
        if (k==0)
            System.out.println(root.data);
        else {
            kDistance(root.left,k-1);
            kDistance(root.right,k-1);
        }
    }
    void levelOrderTravNaive(nodeTree root){
        int h=heightTree(root);
        for (int i = 0; i < h; i++) {
            kDistance(root,i);
        }
    }
    void levelOrdertrav(nodeTree root){
        if (root==null)
            return;
        Queue<nodeTree> que=new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()){
            nodeTree cur=que.poll();
            System.out.println(cur.data);
            if (cur.left!=null)
                que.add(cur.left);
            if (cur.right!=null)
                que.add(cur.right);
        }
    }
    void LOTlineByLIne(nodeTree root){
        if (root==null)
            return;
        Queue<nodeTree> que=new LinkedList<>();
        que.add(root);
        que.add(null);
        while (que.size()>1){
            nodeTree cur=que.poll();
            if (cur==null){
                System.out.println();
                que.add(null);
                continue;
            }
            System.out.println(cur.data);
            if (cur.left!=null)
                que.add(cur.left);
            if (cur.right!=null)
                que.add(cur.right);
        }
    }
    void LOTLineByLineM2(nodeTree root){
        if (root==null)
            return;
        Queue<nodeTree> que=new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()){
            int size=que.size();
            for (int i = 0; i < size; i++) {
                nodeTree cur=que.poll();
                System.out.println(cur.data+" ");
                if (cur.left!=null)
                    que.add(cur.left);
                if (cur.right!=null)
                    que.add(cur.right);
            }
            System.out.println();
        }
    }
    int getSize(nodeTree root){
        if (root==null)
            return 0;
        else return 1+getSize(root.left)+getSize(root.right);
    }
    int getMax(nodeTree root){
        if (root==null)
            return Integer.MIN_VALUE;
        else
            return Math.max(root.data,Math.max(getMax(root.left),getMax(root.right)));
    }
    int maxLevel=0;
    void printLeftView(nodeTree root,int level){
        if (root==null)
            return;
        if (maxLevel<level){
            System.out.println(root.data);
            maxLevel=level;
        }
        printLeftView(root.left,level+1);
        printLeftView(root.right,level+1);
    }
    boolean isChildSum(nodeTree root){
        if (root==null)
            return true;
        if(root.left==null && root.right==null)
            return true;
        int sum=0;
        if (root.left!=null)
            sum+=root.left.data;
        if (root.right!=null)
            sum+=root.right.data;
        return (root.data==sum && isChildSum(root.left) && isChildSum(root.right));
    }
    int isBalanced(nodeTree root){
        if (root==null)
            return 0;
        int lh=isBalanced(root.left);
        if (lh==-1)
            return -1;
        int rh=isBalanced(root.right);
        if (rh==-1)
            return -1;
        if (Math.abs(lh-rh)>1)
            return -1;
        else return Math.max(lh,rh)+1;
    }
    int maxWidth(nodeTree root){
        if (root==null)
            return 0;
        Queue<nodeTree> que=new LinkedList<>();
        que.add(root);
        int res=0;
        while (!que.isEmpty()){
            int count=que.size();
            res=Math.max(res,count);
            for (int i = 0; i < count; i++) {
                nodeTree cur=que.poll();
                if (cur.left!=null)
                    que.add(cur.left);
                if (cur.right!=null)
                    que.add(cur.right);
            }
        }
        return res;
    }
    static nodeTree prev=null;
    static nodeTree BTtoDLL(nodeTree root){
        if (root==null)
            return root;
        nodeTree head=BTtoDLL(root.left);
        if (prev==null)
            head=root;
        else {
            root.left=prev;
            prev.right=root;
        }
        prev=root;
        BTtoDLL(root.right);
        return head;
    }
    //Constructing tree from preorder and inorder traversal given
    static int preIndex=0;
    nodeTree inPreTree(int[] in,int[] pre,int is, int ie){
        if (is>ie)
            return null;
        nodeTree root=new nodeTree(pre[preIndex++]);
        int inIndex=0;
        for (int i =is; i <=ie; i++) {
            if (in[i]==root.data){
                inIndex=i;
                break;
            }
        }
        root.left=inPreTree(in,pre,is,inIndex-1);
        root.right=inPreTree(in,pre,inIndex+1,ie);
        return root;
    }
    //Spiral naive tstravesral
    void sprialTravNaive(nodeTree root){
        if (root==null)
            return;
        Queue<nodeTree> que=new LinkedList<>();
        Stack<Integer> stk=new Stack<>();
        boolean reverse=false;
        que.add(root);
        while (!que.isEmpty()){
            int count=que.size();
            for (int i = 0; i < count; i++) {
                nodeTree cur=que.poll();
                if (reverse)
                    stk.push(cur.data);
                else
                    System.out.println(cur.data+" ");
                if (cur.left!=null)
                    que.add(cur.left);
                if (cur.right!=null)
                    que.add(cur.right);
            }
            if (reverse) {

                while (!stk.isEmpty())
                    System.out.println(stk.pop()+" ");
            }
            reverse=!reverse;
            System.out.println();
        }
    }
    /*
    push root to stack
    while both the stack are not empty
        while s1 is not empty
            take out one by one , print it
            push chidren of taken out node in s2 if exists
        while s2 is not empty
            take out one by one , print it
            push its children in s1 in reverse order first right then left chile if exist
     */
    public int diameterNaive(nodeTree root){
        if (root==null)
            return 0;
        int d1=1+heightTree(root.left)+heightTree(root.right);
        int d2=diameterNaive(root.right);
        int d3=diameterNaive(root.right);
        return Math.max(d1,Math.max(d2,d3));
    }
    static int res=0;
    public static int diameterTree(nodeTree root){
        if (root==null)
            return 0;
        int lh=diameterTree(root.left);
        int rh=diameterTree(root.right);
        res=Math.max(res,1+lh+rh);
        return 1+Math.max(lh,rh);
    }
}