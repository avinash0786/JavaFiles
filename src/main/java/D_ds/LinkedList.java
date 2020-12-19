package D_ds;

public class LinkedList
{
    node head;
    public void insert(int id,String food)
    {
        node a=new node();
        a.id=id;
        a.food=food;
        if(head==null)
        {
            head=a;
        }
        else
        {
            node n=head;
            while(n.dj!=null)
                n=n.dj;
            n.dj=a;
        }

    }
    public void insertAtStart(int id,String food)
    {   node b=new node();
        b.id=id;
        b.food=food;
        if(head==null)
            head=b;
        else
        {
                b.dj=head;
                head=b;
        }
    }
    public void insertAtIndex(int index,int id,String food)
    {
        node c=new node();
        c.id=id;
        c.food=food;
        if(index==0)
            insertAtStart(id,food);
        else
        {
            node n=head;
            for(int i=1;i<index;i++)
            {
                n=n.dj;
            }
            c.dj=n.dj;
            n.dj=c;
        }
    }

    public void show()
    {
        if(head==null)
            System.out.println("list is empty");
        else
        {
            node n=head;
            while(n.dj!=null)
            { System.out.println("id="+n.id+" food="+n.food);
              n=n.dj;
            }
            System.out.println("id="+n.id+" food="+n.food);
        }
    }

}
