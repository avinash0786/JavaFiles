package DS_A;

public class Bst_ll
{
    BstData root;
    public void insert(int d)
    {
        BstData node=new BstData();
        node.data=d;
        if(root==null)
            root=node;
        else {
            BstData r= null;
            BstData t= root;
            while (t!=null) {
                r=t;
                if(d==t.data)
                    return;
                else if(d<t.data)
                    t=t.left;
                else
                    t=t.right;
            }
            if(d<r.data)
                r.left=node;
            else
                r.right=node;
        }
    }

    public BstData search(int key)
    {
        if(root==null) { System.out.println("Tree is empty");}
        else
        {
            BstData n=root;
            System.out.print("Root: ");
            System.out.println(n.data);
            while (n!=null)
            {
                System.out.println(n.data);
                if(n.data==key)
                    {System.out.println("Key is found !");
                    //System.out.println(n.data);
                    return n;}
                else if(key<n.data)
                    n=n.left;
                else
                    n=n.right;
            }
        }
        return null;
    }

    public BstData nodeDelete(BstData root, int key)
    {
        BstData q;
        if(root==null) return null;
        if(root.left==null && root.right==null)
            {root=null;return null;}
        if(root.data<key) root.left=deletion(root.left,key);
        else if(root.data>key) root.right=deletion(root.right,key);
        else
        {
            if(height(root.left)>height(root.right)){
                q=inPre(root.right);
                root.data=q.data;
                root.left=deletion(root.left,key);
            }
            else {
                q=inSuc(root.left);
                root.data=q.data;
                root.right=deletion(root.right,key);
            }
        }
        return root;
    }


    ////deletion///////
    public BstData deletion(BstData root,int key)
    {
        //System.out.println("--------del----------");
        BstData q;
        if(root==null)
            return null;
        if(root.left==null && root.right==null)
        {
            root=null;
            return null;          //end condition
        }
        if(key<root.data)                      //go to that position and perform deletion
            root.left=deletion(root.left,key);
        else if(key>root.data)                      //go to that position and perform deletion
            root.right=deletion(root.right,key);
        else {                                      //key is reached
            if(height(root.left)>height(root.right))
            {
                q=inPre(root.left);        //pre means first left then right right ...
                root.data=q.data;          //replace key dta with pre data
                root.left=deletion(root.left,q.data);           //delete predecesor
            }
            else {
                q=inSuc(root.right);        //successor means first right then left left...
                root.data=q.data;               //replace key dta with succ data
                //System.out.println("ok");
                root.right=deletion(root.right,q.data);   //delete succecesor
            }
        }
        return root;
    }

    public int height(BstData root)       // traverse in both dir left and right  and find the maximum
    {
        int x,y;
        if(root==null) return 0;
        x=height(root.left);
        y=height(root.right);
        return x>y?x+1:y+1;
    }
    public BstData inPre(BstData p)         //goto left then right right...
    {
        while (p!=null && p.right!=null)
            p=p.right;
        //System.out.println("pred: "+p.data);
        return p;
    }
    public BstData inSuc(BstData root) //goto right then left left ..
    {
        while (root!=null && root.left!=null)
            root=root.left;
        //System.out.println("succ: "+root.data);
        return root;
    }
}


