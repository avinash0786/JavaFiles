package CP_training;

import java.util.*;

//  BINARY SEARCH TREE
public class feb22 {
    public static void main(String[] args) {

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
}

class bstNode{
    int data;
    bstNode left;
    bstNode right;
    bstNode(int d){
        this.data=d;
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