package GEEKS_FOR_GEEKS;

public class bstGFGlearn {
    public static void main(String[] args) {
//        BST bst2=new BST();
//        int[] arr=new int[]{30,20,35,15,26,37,36};
//        bst2.multipleInsert(arr);
//        bst2.preOrderIterSpcOpt(bst2.getRoot());
//        bst2.postOrderIter(bst2.getRoot());
////        bst2.inorderRec(bst2.getRoot());
//        System.out.println();
//        bst2.postOrderIter(bst2.getRoot());
//        System.out.println();
//        System.out.println("Height tree: "+bst2.heightTree(bst2.getRoot()));
//        System.out.println("K-Distance:(3)  ");bst2.kDistNode(bst2.getRoot(),3);
//        bst2.LOT(bst2.getRoot());
//        bst2.LOTlineByLine(bst2.getRoot());
//        bst2.LOTlineByLineM02(bst2.getRoot());
//        System.out.println("no of nodes: "+bst2.getSize(bst2.getRoot()));
//        System.out.println("Max node value: "+bst2.getMax(bst2.getRoot()));
//        bst2.printLeftView(bst2.getRoot(),1);
//        bst2.printRightView(bst2.getRoot(),0);
//        bst2.spiralTraversal(bst2.getRoot());
        System.out.println("------");
//        bst2.spiralTrav02Stack(bst2.getRoot());

        bstNode head=new bstNode(1);
        head.left=new bstNode(2);
        head.right=new bstNode(2);

        head.left.left=new bstNode(4);
        head.left.right=new bstNode(5);

        head.right.left=new bstNode(5);
        head.right.right=new bstNode(8);

        head.left.right.left=new bstNode(8);
        linkedList tra=new linkedList();
        tra.insertStart(8);
        tra.insertStart(5);
        tra.insertStart(2);
        tra.insertStart(1);
        tra.traverse();
        pathLLinBST(head,tra.getHEAD(),"");
    }
    public static void pathLLinBST(bstNode root, node head,String str){
        if (root==null) return;
        if (root.data==head.data) {
            if (head.next == null && root.right==null && root.left==null) {
                System.out.println(str.length());
                System.out.println(str.trim());
                return;
            }
            pathLLinBST(root.right, head.next, str + " R ");
            pathLLinBST(root.left, head.next, str + " L ");
        }
    }
}
