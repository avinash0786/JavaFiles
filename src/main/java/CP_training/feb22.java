package CP_training;

import GEEKS_FOR_GEEKS.BST;
import GEEKS_FOR_GEEKS.bstNode;

import java.util.*;

//  BINARY SEARCH TREE
public class feb22 {
    public static void main(String[] args) {
        BST bs=new BST();
        bs.multipleInsert(new int[]{6,4,8,3,5,7,9});
//        bs.preorderIter(bs.getRoot());
        preOrderSpcOpt(bs.getRoot());
        System.out.println("Leaves: "+countLeaf(bs.getRoot()));

        System.out.println("Get floor: "+getFloor(bs.getRoot(), 10).data);
        System.out.println("Get ceil: "+getCeil(bs.getRoot(), 8).data);
//        topViewLOT(bs.getRoot());
        System.out.println("Top view of BST: ");topView(bs.getRoot());
        System.out.println();
        System.out.print("Bottom view of BST: ");bottomView(bs.getRoot());
        inOrderIter02(bs.getRoot());
        invertTree(bs.getRoot());
        inOrderIter02(bs.getRoot());
    }

    public static void preorderIter(bstNode root){
        Stack<bstNode> st=new Stack<>();
        List<Integer> preord=new ArrayList<>();

        while (root!=null || !st.isEmpty()){
            if (root==null){
                root=st.pop();
            }
            else {
                preord.add(root.data);      // node
                if (root.right!=null)
                    st.push(root.right);        //last-- right
                root=root.left;             //left
            }
        }
        System.out.println(preord);
    }
    public static void preOrderSpcOpt(bstNode root){
        List<Integer> op=new LinkedList<>();
        Stack<bstNode> stack=new Stack<>();
        bstNode cur=root;
        while (cur!=null || !stack.isEmpty()){
            while (cur!=null){
                op.add(cur.data);
                if (cur.right!=null)
                    stack.add(cur.right);
                cur=cur.left;
            }
            if (!stack.isEmpty())
                cur=stack.pop();
        }
        System.out.println("Preorder spc opt: "+op);
    }
    public static void inOrderIter02(bstNode root){
        Stack<bstNode> st=new Stack<>();
        List<Integer> preord=new ArrayList<>();

        while (root!=null || !st.isEmpty()){
            if (root==null){
                root=st.pop();
                preord.add(root.data);
                root=root.right;
            }
            else {
                st.push(root);
                root=root.left;
            }
        }
        System.out.println("Inorder: "+preord);
    }
    public static void postorderIter(bstNode root){
        Stack<pair> st=new Stack<>();
        List<Integer> postord=new ArrayList<>();

        while (root!=null || !st.isEmpty()){
            if (root==null){
                pair temp=st.pop();
                if (temp.sec==3)
                    postord.add(temp.cur.data);
                else {
                    temp.sec++;
                    st.push(temp);
                    root=temp.cur.right;
                }
            }
            else {
                st.push(new pair(root,2));
                root=root.left;
            }
        }
        System.out.println(postord);
    }
    public static void levelOrdTrav(bstNode root){
        Queue<bstNode> qu=new LinkedList<>();
        qu.add(root);
        List<Integer> lot=new ArrayList<>();

        while (!qu.isEmpty()){
            bstNode temp=qu.poll();
            lot.add(temp.data);
            if (temp.left!=null)
                qu.add(temp.left);
            if (temp.right!=null)
                qu.add(temp.right);
        }
        System.out.println(lot);
    }
    public static void LOT_lineByLine(bstNode root){
        Queue<bstNode> qu=new LinkedList<>();
        qu.add(root);
        while (!qu.isEmpty()){
            int childs=qu.size();
            for (int i = 0; i < childs; i++) {
                bstNode cur= qu.poll();
                System.out.print(cur.data+" ");
                if (cur.left!=null)
                    qu.add(cur.left);
                if (cur.right!=null)
                    qu.add(cur.right);
            }
            System.out.println();
        }
    }

    public static void  leftView(bstNode root){
        Queue<bstNode> qu=new LinkedList<>();
        qu.add(root);
        List<Integer> lot=new ArrayList<>();
        while (!qu.isEmpty()){
            lot.add(qu.peek().data);
            int size=qu.size();

            while (size-->0){
                bstNode tmp=qu.poll();
                if (tmp.left!=null)
                    qu.add(tmp.left);
                if (tmp.right!=null)
                    qu.add(tmp.right);
            }
        }
        System.out.println(lot);
    }

    public static int countLeaf(bstNode root){
        if (root.left==null && root.right==null)
            return 1;
        else
            return countLeaf(root.left)+countLeaf(root.right);
    }
    public static void  rightView(bstNode root){
        Queue<bstNode> qu=new LinkedList<>();
        qu.add(root);
        List<Integer> lot=new ArrayList<>();
        while (!qu.isEmpty()){
//            lot.add(qu.peek().data);
            int size=qu.size();
            bstNode last=null;
            while (size-->0){
                bstNode tmp=qu.poll();
                if (tmp.left!=null)
                    qu.add(tmp.left);
                if (tmp.right!=null)
                    qu.add(tmp.right);
                last=tmp;
            }
            lot.add(last.data);
        }
        System.out.println(lot);
    }
    static HashMap<Integer,ArrayList<Integer>> mp=new HashMap<>();
    public static void verticalTrav(bstNode root,int barNo){
        mp.get(barNo).add(root.data);
        if (root.left!=null)
            verticalTrav(root.left,barNo-1);
        if (root.right!=null){
            verticalTrav(root.right,barNo+1);
        }
    }

    public static int isBSTbalanced(bstNode root){
        if (root==null)
            return 0;
        int lh=isBSTbalanced(root.left);
        if (lh==-1)
            return -1;
        int rh=isBSTbalanced(root.right);
        if (rh==-1)
            return -1;
        if (Math.abs(lh-rh)>1)
            return -1;
        else
            return Math.max(lh,rh)+1;
    }
    public static bstNode getFloor(bstNode root,int key){
        bstNode res=null;
        while (root!=null){
            if (root.data==key)
                return  root;
            else if (root.data>key)
                root=root.left;
            else {
                res=root;
                root=root.right;
            }
        }
        return res;
    }
    public static bstNode getCeil(bstNode root,int key){
        bstNode res=null;
        while (root!=null){
            if (root.data==key)
                return  root;
            else if (root.data<key)
                root=root.right;
            else {      // greater is a potential ceil
                res=root;
                root=root.left;
            }
        }
        return res;
    }
    public static void topViewLOT(bstNode root){
        Queue<bstNode> qu=new LinkedList<>();
        qu.add(root);
        while (!qu.isEmpty()){
            int childs=qu.size();
            for (int i = 0; i < childs; i++) {
                bstNode cur= qu.poll();
                if (i==0 || i==childs-1)
                    System.out.print(cur.data+" ");
                if (cur.left!=null)
                    qu.add(cur.left);
                if (cur.right!=null)
                    qu.add(cur.right);
            }
            System.out.println();
        }
    }


    // find 2 nodes in bst that are not at same place
    //they are voilating the property and we need to find and swap them
    static bstNode prevNode,firstNode,secondNode;
    public static void fixBSTnodes(bstNode root){
        if (root==null)
            return;
        fixBSTnodes(root.left);
        if (prevNode!=null && root.data< prevNode.data){
            if (firstNode!=null)
                firstNode=prevNode;
            secondNode=root;
        }
        prevNode=root;
        fixBSTnodes(root.right);
    }
//    TOP VIEW OF BST
    public static void topView(bstNode root){
        Queue<Pair02> q=new LinkedList<>();
        Map<Integer,Integer> mp=new TreeMap<>();
        q.add(new Pair02(root,0));
        while(!q.isEmpty()){
            Pair02 p=q.poll();
            bstNode curr=p.node;
            int hd=p.hd;
            if(!mp.containsKey(hd))
                mp.put(hd,curr.data);
            if(curr.left!=null)
                q.add(new Pair02(curr.left,hd-1));
            if(curr.right!=null)
                q.add(new Pair02(curr.right,hd+1));
        }
        for(Map.Entry<Integer,Integer> x:mp.entrySet()){
            System.out.print(x.getValue()+" ");
        }
    }
//  BOTTOM VIEW OF TREE
    public static void bottomView(bstNode root){
        Queue<Pair02> q=new LinkedList<>();
        Map<Integer,Integer> mp=new TreeMap<>();
        q.add(new Pair02(root,0));
        while(!q.isEmpty()){
            Pair02 p=q.poll();
            bstNode curr=p.node;
            int hd=p.hd;
            if(mp.containsKey(hd))
                mp.replace(hd,curr.data);
            else
                mp.put(hd,curr.data);
            if(curr.left!=null)
                q.add(new Pair02(curr.left,hd-1));
            if(curr.right!=null)
                q.add(new Pair02(curr.right,hd+1));
        }
        for(Map.Entry<Integer,Integer> x:mp.entrySet()){
            System.out.print(x.getValue()+" ");
        }
    }
    public static void invertTree(bstNode root){
        if (root==null)
            return;
        invertTree(root.left);
        invertTree(root.right);
        bstNode temp=root.left;
        root.left=root.right;
        root.right=temp;
    }
    public static void invertIterative(bstNode root){
        Queue<bstNode> qu=new LinkedList<>();
        qu.add(root);
        while (!qu.isEmpty()){
            bstNode node=qu.poll();
            if (node!=null){
                qu.add(node.left);
                qu.add(node.right);
                bstNode temp=node.left;
                node.left=node.right;
                node.right=temp;
            }
        }
        System.out.println("Done invert tree iterative");
    }
    static int preIndex=0;
    public static bstNode constTree(int[] inorder, int[] preorder, int inStart, int inEnd){
        if (inStart>inEnd)
            return null;
        //preIndex is preorder index for the node and it is linear so preIndex++
        bstNode root=new bstNode(preorder[preIndex++]);
        int inIndx=0;
        for (int i = inStart; i <=inEnd ; i++) {
            if (inorder[i]==root.data){ // OR  inorder[i]==preorder[preIndex-1]
                inIndx=i;
                break;
            }
        }
        root.left=constTree(inorder,preorder,inStart,inIndx-1);
        root.right=constTree(inorder,preorder,inIndx+1,inEnd);
        return root;
    }
    public static bstNode LCABinaryTree(bstNode root,int n1, int n2){
        if (root==null)
            return null;
        //if find any of the node n1, n2 we return that node
        if (root.data==n1 || root.data==n2)
            return root;
        bstNode lcaLeft=LCABinaryTree(root.left,n1,n2);
        bstNode lcaRight=LCABinaryTree(root.right,n1,n2);

        // if both are not null this is our LCA and we return this node
        if (lcaLeft!=null && lcaRight!=null)
            return root;
        //if this is not LCA we return any no not null value OR null if both are null
        if (lcaLeft!=null)
            return lcaLeft;
        else
            return lcaRight;
    }

}
class Pair02{
    int hd;
    bstNode node;
    Pair02(bstNode n,int dis){
        this.hd=dis;
        this.node=n;
    }
}
class pair{
    int sec;
    bstNode cur;
    pair(bstNode c,int n){
        sec=n;
        cur=c;
    }
}