package DS_A;

public class BstRun
{
    static public int count(BstData start){   ///INORDER TRAVERSAL
        int x,y;
        if(start!=null)
        {
            x=count(start.left);
            y=count(start.right);
            return x+y+1;
        }
        return 0;
    }
    /* TO COUNT NODE WIHT DEGREE 2
    if(start.left!=null && start.right!=null)
        return x+y+1;
    else
        return x+y;

     TO COUNT LEAF NODES
     if(start.left==null && start.right==null)
          return x+y+1;
      else
          return x+y;     //for counting node with degree 2
       TO ADD VALUE OF EACH NODE
       return x+y+start.data;
       */


    static public void inorder(BstData start){   ///INORDER TRAVERSAL

        if(start!=null)
        {inorder(start.left);
        System.out.print(start.data+" ");
        inorder(start.right);}
    }

    static public void preorder(BstData start){   ///PREORDER TRAVERSAL

        if(start!=null)
        {System.out.print(start.data+" ");
        preorder(start.left);
        preorder(start.right);}
    }
    static public void postorder(BstData start){   ///POSTORDER TRAVERSAL

        if(start!=null)
        {
            postorder(start.left);
            postorder(start.right);
            System.out.print(start.data+" ");
        }
    }

    static public  void path(BstData start,int key)
    {
        System.out.print(start.data+"\\");
        if(start==null || start.data== key)
            return;
        if(key<start.data)
            path(start.left, key);
        else
            path(start.right, key);
    }

    public static void main(String ...ff)
    {
        Bst_ll l1=new Bst_ll();
     /*   l1.insert(23);
        l1.insert(12);
        l1.insert(67);
        l1.insert(15);
        l1.insert(9);
        l1.insert(45);*/

        //n= S.nextInt();
        int []aa={60,25,75,66,15,50,33,44};
        for(int i=0;i<aa.length;i++)
           l1.insert(aa[i]);

        //////////////////////////////////////////////////////////////////
        System.out.println("Search the element: 50");
        path(l1.root,25);//infix print in ascending order
        System.out.println();
        System.out.println("Print the bst tree");
        //l1.search(23);
        System.out.println("-----------inorder L-N-R  -------------");
        inorder(l1.root);
        System.out.println();
        System.out.println("-----------preorder N-L-R -------------");
        preorder(l1.root);
        System.out.println();
        System.out.println("-----------postorder L-R-N  -------------");
        postorder(l1.root);
        System.out.println();
        System.out.println(l1.search(50));
        System.out.println("No of node: "+count(l1.root));
        l1.deletion(l1.root,25);
        System.out.println("25: deleted");
        System.out.println("-----------inorder L-N-R  -------------");
        inorder(l1.root);
    }

}
