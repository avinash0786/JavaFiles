package GEEKS_FOR_GEEKS;

import java.util.*;

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
    //better method
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
    /*
    we ensure that we get the correct head as linked list head
    we only return the node returend by the left rec call
    if left is null/ no left child we return null and set the head ==root  as prev is null
     */
    //Constructing tree from preorder and inorder traversal given
    static int preIndex=0;
    // is =inorder start index for that tree
    // ie = inorder end index for that tree
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
            push children of taken out node in s2 if exists
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

    public List<Integer> iterInorder(nodeTree root){
        List<Integer> op=new LinkedList<>();
        Stack<nodeTree> stk=new Stack<>();
         nodeTree  curr=root;
         while (curr!=null || stk.size()>0){
             while (curr!=null){
                 stk.push(curr);
                 curr=curr.left;
             }
             curr=stk.pop();
             op.add(curr.data);
             curr=curr.right;
         }
         return op;
    }
    public List<Integer> iterPreorder(nodeTree root){
        List<Integer> op=new LinkedList<>();
        Stack<nodeTree> stk=new Stack<>();
        stk.push(root);
        while (!stk.isEmpty()){
            nodeTree curr=stk.pop();
            op.add(curr.data);
            if (curr.right!=null)
                stk.push(curr.right);
            if (curr.left!=null)
                stk.push(curr.left);
        }
        return op;
    }
    public List<Integer> iterPreorderSpaceOpt(nodeTree root){   //Iterative preorder space optimized
        List<Integer> op=new LinkedList<>();
        Stack<nodeTree> stk=new Stack<>();
        nodeTree curr=root;
        while (curr!=null ||!stk.isEmpty()){
            while (curr!=null){
                op.add(curr.data);
                if (curr.right!=null)
                    stk.push(curr.right);
                curr=curr.left;
            }
            if (!stk.isEmpty())
                curr=stk.pop();
        }
        return op;
    }
    public List<Integer> iterPostorder(nodeTree root){   //Iterative preorder space optimized
        List<Integer> op=new LinkedList<>();
        Stack<nodeTree> stk1=new Stack<>();
        Stack<nodeTree> stk2=new Stack<>();
        stk1.push(root);
        while (!stk1.isEmpty()){
            nodeTree curr=stk1.pop();
            stk2.push(curr);

            if (curr.left!=null)
                stk1.push(curr.left);
            if (curr.right!=null)
                stk1.push(curr.right);
        }
        while (!stk2.isEmpty())
            op.add(stk2.pop().data);
        return op;
    }

    boolean findPath(nodeTree root, ArrayList<Integer> path,int n){
        if (root==null)
            return false;
        path.add(root.data);
        if (root.data==n)
            return true;
        if (findPath(root.left,path,n) || findPath(root.right,path,n))
            return true;
        path.remove(path.size()-1);
        return false;
    }
    int LCANaive(nodeTree root,int n1,int n2){
        ArrayList<Integer> path1=new ArrayList<>();
        ArrayList<Integer> path2=new ArrayList<>();
        if (!findPath(root, path1, n1) || !findPath(root,path2,n2))
            return -1;
        for (int i = 0; i <path1.size()-1 && i<path2.size()-1; i++) {
            if (!path1.get(i + 1).equals(path2.get(i + 1)))
                return path1.get(i);
        }
        return -1;
    }
    nodeTree LCA(nodeTree root, int n1, int n2){    //Assumes n1 and n2 to exist in tree
        if (root==null)
            return null;
        //if find any of the node n1, n2 we return that node
        if (root.data==n1 || root.data==n2)
            return root;
        nodeTree lca1=LCA(root.left,n1,n2);
        nodeTree lca2=LCA(root.right,n1,n2);
        // if both are not null this is our LCA and we return this node
        if (lca1!=null && lca2!=null)
            return root;
        //if this is not LCA we return any no not null value OR null if both are null
        if (lca1!=null)
            return lca1;
        else
            return lca2;
    }

    int result=0;
    int burnTime(nodeTree root,int leaf,distance dist){
        if (root==null)
            return 0;
        if (root.data==leaf){
            dist.val=0;
            return 1;
        }
        distance lDist=new distance(-1);
        distance rDist=new distance(-1);
        int lh=burnTime(root.left,leaf,lDist);
        int rh=burnTime(root.right,leaf,rDist);

        if (lDist.val!=-1){
            dist.val=lDist.val+1;
            result=Math.max(result,rh+dist.val);
        }
        else if (rDist.val!=-1){
            dist.val=rDist.val+1;
            result=Math.max(result, dist.val+lh);
        }
        return Math.max(lh,rh)+1;
    }
    int countNodes(nodeTree root){  //  O(n)
        if (root==null)
            return 0;
        else
            return 1+countNodes(root.left)+countNodes(root.right);
    }
    int cntNodeEfic(nodeTree root){
        int lh=0,rh=0;
        nodeTree cur=root;
        while (cur!=null){
            lh++;
            cur=cur.left;
        }
        cur=root;
        while (cur!=null){
            rh++;
            cur=cur.right;
        }
        if (lh==rh)
            return (int) (Math.pow(2,lh)-1);
        return
                1+cntNodeEfic(root.left)+cntNodeEfic(root.right);
    }

    static final int EMPTY=-1;
    void serialize(nodeTree root,ArrayList<Integer> arr){
        if (root==null){
            arr.add(EMPTY);
            return;
        }
        arr.add(root.data);
        serialize(root.left,arr);
        serialize(root.right,arr);
    }
    int index=0;
    nodeTree deSerialize(ArrayList<Integer> arr){
        if (index==arr.size())
            return null;
        int val=arr.get(index);
        index++;
        if (val==EMPTY)
            return null;
        nodeTree root=new nodeTree(val);
        root.left=deSerialize(arr);
        root.right=deSerialize(arr);
        return root;
    }
}
class distance{
    int val;
    distance(int d){this.val=d;}
}