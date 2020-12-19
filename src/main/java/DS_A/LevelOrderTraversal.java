package DS_A;

public class LevelOrderTraversal
{
    public static void LevelOTravers(BstData start)
    {
        BstData travPOinter;
        LOTqueue que=new LOTqueue();
        System.out.print(start.data+" ");
        que.LOTenque(start);
        while (que.head!=null)
        {
            travPOinter=que.LOTdeque();
            if(travPOinter.left!=null)
            {
                System.out.print(travPOinter.left.data+" ");
                que.LOTenque(travPOinter.left);
            }
            if(travPOinter.right!=null)
            {
                System.out.print(travPOinter.right.data+" ");
                que.LOTenque(travPOinter.right);
            }
        }
    }


    public static void main(String ...ss)
    {
        Bst_ll tree=new Bst_ll();
        int []aa={30,20,40,10,25,35,50,5,6,2};
        for(int i=0;i<aa.length;i++)
            tree.insert(aa[i]);
        System.out.println("INORDER TRAVERSAL: ");
        BstRun.inorder(tree.root);
        System.out.println();
        System.out.println("LEVEL ORDER TRAVERSAL: ");
        LevelOTravers(tree.root);
    }
}
