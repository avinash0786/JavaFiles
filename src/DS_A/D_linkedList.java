package DS_A;

public class D_linkedList
{
    D_list head;
    D_list tail;

    public void insertAtStart(int i,String n)     //insertion from head
    {
        D_list node=new D_list();
        node.id=i;
        node.name=n;
        if(head==null)
        {
            head=node;
            node.prev=null;
            node.next=null;
            tail=node;
        }
        else
        {
            node.next=head;
            head=node;
            node.prev=null;
            D_list no=head;
            no=no.next;
            no.prev=node;
        }
    }

    //////////////////////////////////////
    public void insertAtEnd(int i,String n)     //insertion from head
{
    D_list node=new D_list();
    node.id=i;
    node.name=n;
    if(tail==null)
    {
        head=node;
        node.prev=null;
        node.next=null;
        tail=node;
    }
    else
    {
        node.next=null;
        node.prev=tail;
        tail=node;
        D_list no=tail;
        no=no.prev;
        no.next=node;
    }
}

    public void insertAtIndex(int index,int i,String n)     //insertion from head
    {
        D_list node=new D_list();
        node.id=i;
        node.name=n;

        if(index==0) {insertAtStart(i,n); }
        else
        {
            D_list nn=head;
            for(int j=1;j<index;j++)
            {
                nn=nn.next;
            }
            //node.prev=nn;
            node.next=nn.next;
            nn.next=node;
            node.prev=nn;
            nn=nn.next;
            nn=nn.next;
            nn.prev=node;
        }
    }









////////DISPLAY FUNCTION////////
    public void showFromhead()
    {
        D_list node=head;
        while(node.next!=null)
        {
            System.out.println("id: "+node.id+" Name:"+node.name);
            node=node.next;
        }
        System.out.println("id: "+node.id+" Name:"+node.name);
    }
    public void showFromTail()
    {
        D_list node=tail;
        while(node.prev!=null)
        {
            System.out.println("id: "+node.id+" Name:"+node.name);
            node=node.prev;
        }
        System.out.println("id: "+node.id+" Name:"+node.name);
    }

}
