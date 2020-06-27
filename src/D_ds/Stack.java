package D_ds;

public class Stack
{
    node head;
    public void push(int id,String food)
    {
        node a=new node();
        a.id=id;
        a.food=food;
        if(head==null)
            head=a;
        else
        {
            a.dj=head;
            head=a;
        }
    }
    public void pop()
    {
        node n=head;
        head=n.dj;

    }

    public void show()
    {
        if(head==null)
            System.out.println("stack is empty");
        else
        {
            node n=head;
            while(n.dj!=null)
            {
                System.out.println("id:"+n.id+"food:"+n.food);
                n=n.dj;
            }
            System.out.println("id:"+n.id+"food:"+n.food);
        }
    }


}
