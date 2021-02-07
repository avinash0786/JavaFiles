package GEEKS_FOR_GEEKS;

import java.util.*;

public class bstGFGlearn {
    public static void main(String[] args) {
        BST bst2=new BST();
        int[] arr=new int[]{95,80,100,60,97,99,50,65,70};
        bst2.multipleInsert(arr);
        bst2.preOrderIterSpcOpt(bst2.getRoot());
//        bst2.inorderRec(bst2.getRoot());
        System.out.println();
        bst2.postOrderIter(bst2.getRoot());
        System.out.println();
        System.out.println("Height tree: "+bst2.heightTree(bst2.getRoot()));
//        System.out.println("K-Distance:(3)  ");bst2.kDistNode(bst2.getRoot(),3);
//        bst2.LOT(bst2.getRoot());
//        bst2.LOTlineByLine(bst2.getRoot());
//        bst2.LOTlineByLineM02(bst2.getRoot());
        System.out.println("no of nodes: "+bst2.getSize(bst2.getRoot()));
        System.out.println("Max node value: "+bst2.getMax(bst2.getRoot()));
//        bst2.printLeftView(bst2.getRoot(),1);
//        bst2.printRightView(bst2.getRoot(),0);
//        bst2.spiralTraversal(bst2.getRoot());
        System.out.println("------");
//        bst2.spiralTrav02Stack(bst2.getRoot());

    }
}
class bstNode{
    int data;
    bstNode left;
    bstNode right;
    bstNode(int d){
        this.data=d;
    }
}
class BST{
    bstNode root;

    //---------     ALGORITHM IMPLEMENTATION    -------------

    public bstNode getRoot(){return root;}

    public bstNode insert(int key){ // O(h) , height-h
        bstNode temp=new bstNode(key);
        bstNode cur=root;
        bstNode par=null;
        while (cur!=null){
            par=cur;
            if (key<cur.data)
                cur=cur.left;
            else if (key>cur.data)
                cur=cur.right;
            else
                return root;
        }
        if (par==null) {  //when root null;
            root=temp;
            return temp;
        }
        if (key>par.data)
            par.right=temp;
        else
            par.left=temp;
        return root;
    }

    public bstNode insertRec(bstNode root,int key){
        if (root==null) {
            root=new bstNode(key);
            return root;
        }
        if (key<root.data)
            root.left=insertRec(root.left,key);
        else if (key>root.data)
            root.right=insertRec(root.right,key);
        return root;
    }

    public void inorderRec(bstNode root){  //  L-N-R
        if (root!=null){
            inorderRec(root.left);
            System.out.print(root.data+" - ");
            inorderRec(root.right);
        }
    }

    public void preorderRec(bstNode root){     // N-L-R
        if (root!=null){
            System.out.print(root.data+" - ");
            inorderRec(root.left);
            inorderRec(root.right);
        }
    }

    public void postorderRec(bstNode root){     // L-R-N
        if (root!=null){
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data+" ");
        }
    }
    public void inorderIter(bstNode root){  //  L-N-R
        List<Integer> op=new LinkedList<>();
        Stack<bstNode> stk=new Stack<>();
        bstNode cur=root;
        while (cur!=null || stk.size()>0){
            while (cur!=null){  //go to deep left
                stk.push(cur);
                cur=cur.left;
            }

            cur=stk.pop();  //pop deepest left and
            op.add(cur.data);   // add the data
            cur=cur.right;  // go to the right
        }
        System.out.println(op);
    }
    public void preorderIter(bstNode root){     // N-L-R
        List<Integer> op=new LinkedList<>();
        Stack<bstNode> stk=new Stack<>();
        stk.push(root);
        while (!stk.isEmpty()){
            bstNode cur=stk.pop();
            op.add(cur.data);
            if (cur.right!=null)
                stk.push(cur.right);
            if (cur.left!=null)
                stk.push(cur.left);
        }
        System.out.println(op);
    }
    /*
    in previous preorder iter trav we are just adding left node and popping out in
    next while loop , so we can optimize it by not inserting it in stack rather moving the curent to left
     */
    public List<Integer> preOrderIterSpcOpt(bstNode root){
        List<Integer> op=new LinkedList<>();
        Stack<bstNode> stk=new Stack<>();
        bstNode cur=root;
        while (cur!=null || !stk.isEmpty()){
            while (cur!=null){
                op.add(cur.data);
                if (cur.right!=null)
                    stk.push(cur.right);
                cur=cur.left;
            }
            if (!stk.isEmpty())
                cur=stk.pop();
        }
        System.out.print("PreOrder: ");
        System.out.print(op);
        return op;
    }

    public List<Integer> postOrderIter(bstNode root){
        List<Integer> op=new LinkedList<>();
        Stack<bstNode> stk1=new Stack<>();
        Stack<bstNode> stk2=new Stack<>();
        stk1.push(root);
        while (!stk1.isEmpty()){
            bstNode cur=stk1.pop();
            stk2.push(cur);

            if (cur.left!=null)
                stk1.push(cur.left);
            if (cur.right!=null)
                stk1.push(cur.right);
        }
        while (!stk2.isEmpty())
            op.add(stk2.pop().data);
        System.out.print("PostOrder: ");
        System.out.print(op);
        return op;
    }
    public void multipleInsert(int[] arr){
        for (int i : arr) {
            this.insert(i);
        }
        System.out.print("Inorder: ");
        this.inorderIter(this.getRoot());
    }
    public int heightTree(bstNode root){
        if (root==null)
            return 0;
        else
            return Math.max(
                heightTree(root.left),
                heightTree(root.right)
            )+1;
    }
    public void kDistNode(bstNode root, int k){
        if (root==null)
            return;
        if (k==0)
            System.out.println(root.data);
        else {
            kDistNode(root.left,k-1);
            kDistNode(root.right,k-1);
        }
    }

    public void LOTNaive(bstNode root){
        int h=heightTree(root);
        for (int i = 0; i < h; i++) {
            kDistNode(root,i);
        }
    }

    public void LOT(bstNode root){
        if (root==null)
            return;
        Queue<bstNode> que=new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()){
            bstNode cur=que.poll();
            System.out.println(cur.data);
            if (cur.left!=null)
                que.add(cur.left);
            if (cur.right!=null)
                que.add(cur.right);
        }
    }
    /*
    in LOT line by line
    be add a track i,e null to know a new line
    we first add the root and null
    and when curr is not null we add its children
    and when we reach the previous null we add another null because
    before we insert null all the children of prev element of a level is inserted
    and now a new line is required so we add null
     */
    public void LOTlineByLine(bstNode root){
        if (root==null)
            return;
        Queue<bstNode> que=new LinkedList<>();
        que.add(root);
        que.add(null);
        while (que.size()>1){
            bstNode cur=que.poll();
            if (cur==null){
                System.out.println();
                que.add(null);
                continue;
            }
            System.out.print(cur.data+" ");
            if (cur.left!=null)
                que.add(cur.left);
            if (cur.right!=null)
                que.add(cur.right);
        }
    }
    /*
    in method 02
    we add the number of children of a level i.e size
    and print the element and add its children
    and when at next level we System.out.println();

     */
    public void LOTlineByLineM02(bstNode root){
        Queue<bstNode> que=new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()){
            int size=que.size();
            for (int i = 0; i < size; i++) {
                bstNode cur=que.poll();
                assert cur != null;
                System.out.print(cur.data+" ");
                if (cur.left!=null)
                    que.add(cur.left);
                if (cur.right!=null)
                    que.add(cur.right);
            }
            System.out.println();
        }
    }
    public int getSize(bstNode root){
        if (root==null)
            return 0;
        else
            return 1+getSize(root.left)+getSize(root.right);
    }
    public int getMax(bstNode root){
        if (root==null)
            return Integer.MIN_VALUE;
        else
            return Math.max(root.data, Math.max(getMax(root.left), getMax(root.right)));
    }
    int maxLev=0;
    public void printLeftView(bstNode root, int level){
        if (root==null)
            return;
        if (maxLev<level){
            System.out.println(root.data);
            maxLev=level;
        }
        printLeftView(root.left,level+1);
        printLeftView(root.right,level+1);
    }

    int maxLRR=0;
    public void printRightView(bstNode root,int level){
        if (root==null)
            return;
        if (maxLRR>=level){
            System.out.println(root.data);
            maxLRR=level;
        }

        printLeftView(root.right,level+1);
        printRightView(root.left,level+1);
    }
    /*
    ***we can use the level order traversal approach and print only the first child
    in case of left view nd last child in case of right view
    *--
    * we can use this lot to even find the max width of a level
    * by maintaining a variable to track the mak no of children present at a particular level
    * int count=que.size();
            res=Math.max(res,count);
     */
    boolean isChildSum(bstNode root){
        if (root==null)
            return true;
        if (root.right==null && root.left==null)
            return true;
        int sum=0;
        if (root.left!=null)
            sum+=root.left.data;
        if (root.right!=null)
            sum+=root.right.data;
        return (root.data==sum && isChildSum(root.left) && isChildSum(root.right));
    }
    /*
    if the root is unbalanced at any node we just return -1
    otherwise we return the length of the tree
     */
    public int isBalanced(bstNode root){
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
        else
            return Math.max(lh,rh)+1;
    }

    bstNode prev=null;
    public bstNode BST_DLL(bstNode root){
        if (root==null)
            return root;
        bstNode head=BST_DLL(root.left);
        if (prev==null)
            head=root;
        else {
            root.left=prev;
            prev.right=root;
        }
        prev=root;
        BST_DLL(root.right);
        return head;
    }

    public void spiralTraversal(bstNode root){
        if (root==null)
            return;
        Queue<bstNode> Q=new LinkedList<>();
        Stack<Integer> S=new Stack<>();
        boolean reverse=false;
        Q.add(root);
        while (!Q.isEmpty()){
            int count=Q.size();
            for (int i = 0; i < count; i++) {
                bstNode cur=Q.poll();
                if (reverse)
                    S.push(cur.data);
                else
                    System.out.print(cur.data+" ");
                if (cur.left!=null)
                    Q.add(cur.left);
                if (cur.right!=null)
                    Q.add(cur.right);
            }
            if (reverse){
                while (!S.isEmpty())
                    System.out.print(S.pop()+" ");
            }
            reverse=!reverse;
            System.out.println();
        }
    }
    public void spiralTrav02Stack(bstNode root){
        Stack<bstNode> s1=new Stack<>();
        Stack<bstNode> s2=new Stack<>();
        s1.push(root);
        while (!s2.isEmpty() || !s1.isEmpty()){
            while (!s1.isEmpty()){
                bstNode cur=s1.pop();
                System.out.print(cur.data+" ");
                if (cur.left!=null)
                    s2.push(cur.left);
                if (cur.right!=null)
                    s2.push(cur.right);
            }
            System.out.println();
            while (!s2.isEmpty()){
                bstNode cur=s2.pop();
                System.out.print(cur.data+" ");
                if (cur.right!=null)
                    s1.push(cur.right);
                if (cur.left!=null)
                    s1.push(cur.left);
            }
            System.out.println();
        }
    }
    public int diameterNaive(bstNode root){
        if (root==null)
            return 0;
        int d1=1+heightTree(root.left)+heightTree(root.right);
        int d2=diameterNaive(root.left);
        int d3=diameterNaive(root.right);
        return Math.max(d1,Math.max(d2,d3));
    }
    int res=0;
    public int diameterEfic(bstNode root){
        if (root==null)
            return 0;
        int lh=diameterEfic(root.left);
        int rh=diameterEfic(root.right);
        res=Math.max(res,1+lh+rh);
        return 1+Math.max(lh,rh);
    }

    public int countNodesEffic(bstNode root){
        int lh=0;
        int rh=0;
        bstNode cur=root;
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
        return 1+countNodesEffic(root.left)+countNodesEffic(root.right);
    }
     final int EMPTY=-1;
    public void serialize(bstNode root, ArrayList<Integer> op){
        if (root==null){
            op.add(EMPTY);
            return;
        }
        op.add(root.data);
        serialize(root.left,op);
        serialize(root.right,op);
    }

    int index=0;
    public bstNode deSerialize(ArrayList<Integer> arr){
        if (index==arr.size())
            return null;
        int val=arr.get(index);
        index++;
        if (val==EMPTY)
            return null;
        bstNode node=new bstNode(val);
        node.left=deSerialize(arr);
        node.right=deSerialize(arr);
        return node;
    }

    public bstNode getSuccessor(bstNode root){
        bstNode cur=root.right;
        while (cur!=null && cur.left!=null)
            cur=cur.left;
        return cur;
    }

    public bstNode deleteNode(bstNode root, int key){
        if (root==null)
            return null;
        if (key<root.data)
            root.left=deleteNode(root.left,key);
        else if (key>root.data)
            root.right=deleteNode(root.right,key);
        else {
            if (root.left==null)
                return root.right;
            else if (root.right==null)
                return root.left;
            else {
                bstNode succ=getSuccessor(root);
                root.data=succ.data;
                root.right=deleteNode(root.right,succ.data);
            }
        }
        return root;
    }
    public bstNode floorBST(bstNode root, int n){
        bstNode res=null;
        while (root!=null){
            if (root.data==n)
                return root;
            else if (n<root.data)
                root=root.left;
            else {
                res=root;
                root=root.right;
            }
        }
        return res;
    }

    public bstNode ceilBST(bstNode root, int n){
        bstNode res=null;
        while (root!=null){
            if (root.data==n)
                return root;
            else if (n>root.data)
                root=root.right;
            else {
                res=root;
                root=root.left;
            }
        }
        return res;
    }
    int count=0;
    /*
    this is same as inorder traversal only till n element
    because the n the element is nth smallest
    as inorder gives the ascending order of the bst
     */
    public void kSmallest(bstNode root, int k){
        if (root!=null){
            kSmallest(root.left,k);
            count++;
            if (count==k) {
                System.out.println(root.data);
                return;
            }
            kSmallest(root.right,k);
        }
    }
    /*
    second approach is to change the node structure of the bst,
    we store a extra element in the node that ccount the number of element on the left
    so we get the number of element smaller before going to actual element;
     */

    public boolean isBst(bstNode root,int min, int max){
        if (root==null)
            return true;
        return (
                root.data>min && root.data<max &&
                        isBst(root.left,min,root.data) &&
                        isBst(root.right,root.data,max)

                );
    }
    int prevValue;
    public boolean isbstM02(bstNode root){
        if (root==null)
            return true;
        if (!isbstM02(root.left))
            return false;
        if (root.data<=prevValue)
            return false;
        prevValue=root.data;
        return isbstM02(root.right);
    }
    /*
    only update the first and second var nodes
    and contains the nodes to be swap
    and we swap the data of both the refrences;
     */
    bstNode prev02,first,second;
    public void fixBst(bstNode root){
        if (root==null)
            return;
        fixBst(root.left);
        if (prev02!=null && root.data<prev02.data){
            if (first==null)
                first=root;
            second=root;
        }
        prev=root;
        fixBst(root.right);
    }

    public boolean isPairSum(bstNode root,int sum, HashSet<Integer> set){
        if (root==null)
            return false;
        if (isPairSum(root.left,sum,set))
            return true;
        if (set.contains(sum-root.data))
            return true;
        else
            set.add(root.data);
        return isPairSum(root.right,sum,set);
    }
    /*
    vertical sum in a bst;
    we create a map and store the elements by their distance form the root vertically
    and do simple traversal to store the value;
     */
    public void vSumR(bstNode root,int hd, TreeMap<Integer,Integer> map){
        if (root==null)
            return;
        vSumR(root.left,hd-1,map);
        int pSum=(map.get(hd)==null)?0:map.get(hd);
        map.put(hd,pSum+root.data);
        vSumR(root.right,hd+1,map);
    }
    public void vSum(bstNode root){
        TreeMap<Integer,Integer> map= new TreeMap<>();
        vSumR(root,0,map);
        for (Map.Entry<Integer,Integer> sum:map.entrySet())
            System.out.print(sum.getValue()+" ");
    }


/*  ---VERTICAL TRAVERSAL---
    public static void vTraversal(Node root){
        Queue<Pair> q=new LinkedList<>();
        Map<Integer,ArrayList<Integer>> mp=new TreeMap<>();
        q.add(new Pair(root,0));
        while(q.isEmpty()==false){
            Pair p=q.poll();
            Node curr=p.node;
            int hd=p.hd;
            if(mp.containsKey(hd))
                mp.get(hd).add(curr.key);
            else{
                ArrayList<Integer> al=new ArrayList<>();
                al.add(curr.key);
                mp.put(hd,al);
            }
            if(curr.left!=null)
                q.add(new Pair(curr.left,hd-1));
            if(curr.right!=null)
                q.add(new Pair(curr.right,hd+1));
        }
        for(Map.Entry<Integer,ArrayList<Integer>> p:mp.entrySet()){
            ArrayList<Integer> al=p.getValue();
            for(int x: al)
                System.out.print(x+" ");
            System.out.println();
        }
    }

    class Pair{
    Node node;
    int hd;
    Pair(Node n,int h){node=n;hd=h;}

    --TOP VIEW---
    public static void topView(Node root){
        Queue<Pair> q=new LinkedList<>();
        Map<Integer,Integer> mp=new TreeMap<>();
        q.add(new Pair(root,0));
        while(q.isEmpty()==false){
            Pair p=q.poll();
            Node curr=p.node;
            int hd=p.hd;
            if(mp.containsKey(hd)==false)
                mp.put(hd,curr.key);
            if(curr.left!=null)
                q.add(new Pair(curr.left,hd-1));
            if(curr.right!=null)
                q.add(new Pair(curr.right,hd+1));
        }
        for(Map.Entry<Integer,Integer> x:mp.entrySet()){
                System.out.print(x.getValue()+" ");
        }
    }
}

 */
}
