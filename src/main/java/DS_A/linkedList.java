package DS_A;
public class linkedList
{
    Node head;                //main head node for ll
    static int count=1;

    public void insert(int dta,String na,String b)
    {
        Node node=new Node(); ////// creating a container
        node.id=dta;
        node.name=na;
        node.course=b;           ///storing data
        node.next=null;       //not req as last link is null automatically
        if(head==null)        //checking as first node
        {
            head=node;        //  saving head refrence to first container
        }
        else
        {
            Node n=head;          //traversing node from head  till it reaches null  i.e last link
            while (n.next!=null)
            {
                n=n.next;             //sending to next link if available
            }
            n.next=node;              ///assigning to last link
        }
    }

    public void insertAtStart(int dta,String na,String b)
    {
        Node node=new Node(); ////// creating a container
        node.id=dta;
        node.name=na;
        node.course=b;           ///storing data
        node.next=head;      // pointing the ref head is having to new container
        head=node;            //and head point  to new node
    }

    public void insertAt(int index,int dta,String na,String b)
    {
        Node node=new Node(); ////// creating a container
        node.id=dta;
        node.name=na;
        node.course=b;

        Node n=head;
        if(index==0){insertAtStart(dta,na,b);}
        else
        {for(int i=0;i<index-1;i++)
        {
            n=n.next;             //traverser till that index
        }
        node.next=n.next;           ///set our container link to that index next
        n.next=node; }              ///and previous link to our container
    }
    public void delete(int index)
    {
        Node m=head;
        Node n=head;
        m=m.next;
        if(index==0)
        {
            head=n.next;
        }
        else {
            for(int i=1;i<index;i++)
            {
                n=n.next;
                m=m.next;
            }
            n.next=m.next;
        }
    }


    public void show()
    {
        Node node=head;
        while (node.next!=null)
        {
            System.out.println(node.id+" "+node.name+" "+node.course);
            node=node.next;
            count++;
        }
        System.out.println(node.id+" "+node.name+" "+node.course);
        System.out.println("Total Linked List size: "+count);
    }
}
